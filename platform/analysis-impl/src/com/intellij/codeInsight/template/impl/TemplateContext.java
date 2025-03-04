// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.codeInsight.template.impl;

import com.intellij.codeInsight.template.LiveTemplateContextBean;
import com.intellij.codeInsight.template.TemplateContextType;
import com.intellij.openapi.extensions.ExtensionPointAdapter;
import com.intellij.openapi.extensions.ExtensionsArea;
import com.intellij.openapi.util.ClearableLazyValue;
import com.intellij.util.JdomKt;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.containers.JBIterable;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;
import org.jetbrains.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class TemplateContext {
  private final Map<String, Boolean> myContextStates = new HashMap<>();

  private static final ClearableLazyValue<Map<String, String>> INTERN_MAP = new ClearableLazyValue<>() {
    private final AtomicBoolean isListenerAdded = new AtomicBoolean();

    @NotNull
    @Override
    protected Map<String, String> compute() {
      if (isListenerAdded.compareAndSet(false, true)) {
        LiveTemplateContextBean.EP_NAME.getPoint().addExtensionPointListener(new ExtensionPointAdapter<>() {
          @Override
          public void areaReplaced(@NotNull ExtensionsArea oldArea) {
            drop();
          }

          @Override
          public void extensionListChanged() {
            drop();
          }
        }, false, null);
      }

      return LiveTemplateContextBean.EP_NAME.getExtensionList().stream()
        .map(LiveTemplateContextBean::getContextId)
        .distinct()
        .collect(Collectors.toMap(Function.identity(), Function.identity()));
    }
  };

  public TemplateContext createCopy() {
    TemplateContext cloneResult = new TemplateContext();
    cloneResult.myContextStates.putAll(myContextStates);
    return cloneResult;
  }

  @Nullable LiveTemplateContextBean getDifference(@NotNull TemplateContext defaultContext) {
    return ContainerUtil.find(LiveTemplateContextBean.EP_NAME.getExtensionList(),
                              type -> isEnabled(type) != defaultContext.isEnabled(type));
  }

  @Nullable TemplateContextType getDifferenceType(@NotNull TemplateContext defaultContext) {
    LiveTemplateContextBean differenceExtension = getDifference(defaultContext);
    if (differenceExtension != null) {
      return differenceExtension.getInstance();
    }
    return null;
  }

  public boolean isEnabled(@NotNull TemplateContextType contextType) {
    synchronized (myContextStates) {
      Boolean storedValue = getOwnValue(contextType.getContextId());
      if (storedValue == null) {
        TemplateContextType baseContextType = contextType.getBaseContextType();
        return baseContextType != null && isEnabled(baseContextType);
      }
      return storedValue.booleanValue();
    }
  }

  private boolean isEnabled(@NotNull LiveTemplateContextBean contextType) {
    synchronized (myContextStates) {
      Boolean storedValue = getOwnValue(contextType.getContextId());
      if (storedValue == null) {
        LiveTemplateContextBean baseContextType = contextType.getBaseContextType();
        return baseContextType != null && isEnabled(baseContextType);
      }
      return storedValue.booleanValue();
    }
  }

  public @Nullable Boolean getOwnValue(@NotNull TemplateContextType contextType) {
    return getOwnValue(contextType.getContextId());
  }

  private @Nullable Boolean getOwnValue(String contextTypeId) {
    synchronized (myContextStates) {
      return myContextStates.get(contextTypeId);
    }
  }

  public void setEnabled(TemplateContextType contextType, boolean value) {
    synchronized (myContextStates) {
      myContextStates.put(contextType.getContextId(), value);
    }
  }

  // used during initialization => no sync
  @VisibleForTesting
  public void setDefaultContext(@NotNull TemplateContext defContext) {
    Map<String, Boolean> copy = new HashMap<>(myContextStates);
    myContextStates.clear();
    myContextStates.putAll(defContext.myContextStates);
    myContextStates.putAll(copy);
  }

  // used during initialization => no sync
  @VisibleForTesting
  public void readTemplateContext(@NotNull Element element) {
    Map<String, String> internMap = INTERN_MAP.getValue();
    for (Element option : element.getChildren("option")) {
      String name = option.getAttributeValue("name");
      String value = option.getAttributeValue("value");
      if (name != null && value != null) {
        myContextStates.put(ContainerUtil.getOrElse(internMap, name, name), Boolean.parseBoolean(value));
      }
    }

    myContextStates.putAll(makeInheritanceExplicit());
  }

  /**
   * Mark contexts explicitly as excluded which are excluded because some of their bases is explicitly marked as excluded.
   * Otherwise, that `excluded` status will be forgotten if the base context is enabled.
   */
  @NotNull
  private Map<String, Boolean> makeInheritanceExplicit() {
    Map<String, Boolean> explicitStates = new HashMap<>();
    for (LiveTemplateContextBean type : LiveTemplateContextBean.EP_NAME.getExtensionList()) {
      if (isDisabledByInheritance(type)) {
        explicitStates.put(type.getContextId(), false);
      }
    }
    return explicitStates;
  }

  private boolean isDisabledByInheritance(LiveTemplateContextBean type) {
    return !hasOwnValue(type) &&
           !isEnabled(type) &&
           JBIterable.generate(type, LiveTemplateContextBean::getBaseContextType).filter(this::hasOwnValue).first() != null;
  }

  private boolean hasOwnValue(LiveTemplateContextBean t) {
    return getOwnValue(t.getContextId()) != null;
  }

  @TestOnly
  public Element writeTemplateContext(@Nullable TemplateContext defaultContext) {
    return writeTemplateContext(defaultContext, getIdToType());
  }

  @VisibleForTesting
  @Nullable
  public Element writeTemplateContext(@Nullable TemplateContext defaultContext, @NotNull Lazy<? extends Map<String, TemplateContextType>> idToType) {
    if (myContextStates.isEmpty()) {
      return null;
    }

    Element element = new Element(TemplateConstants.CONTEXT);
    List<Map.Entry<String, Boolean>> entries = new ArrayList<>(myContextStates.entrySet());
    entries.sort(Map.Entry.comparingByKey());
    for (Map.Entry<String, Boolean> entry : entries) {
      Boolean ownValue = entry.getValue();
      if (ownValue == null) {
        continue;
      }

      TemplateContextType type = idToType.getValue().get(entry.getKey());
      if (type == null) {
        // https://youtrack.jetbrains.com/issue/IDEA-155623#comment=27-1721029
        JdomKt.addOptionTag(element, entry.getKey(), ownValue.toString());
      }
      else if (isValueChanged(ownValue, type, defaultContext)) {
        JdomKt.addOptionTag(element, type.getContextId(), ownValue.toString());
      }
    }
    return element;
  }

  @NotNull
  public static Lazy<Map<String, TemplateContextType>> getIdToType() {
    return LazyKt.lazy(new Function0<>() {
      @Override
      public Map<String, TemplateContextType> invoke() {
        Map<String, TemplateContextType> idToType = new HashMap<>();
        for (LiveTemplateContextBean type : LiveTemplateContextBean.EP_NAME.getExtensionList()) {
          idToType.put(type.getContextId(), type.getInstance());
        }
        return idToType;
      }
    });
  }

  /**
   * Default value for GROOVY_STATEMENT is `true` (defined in the `plugins/groovy/groovy-psi/resources/liveTemplates/Groovy.xml`).
   * Base value is `false`.
   * <p>
   * If default value is defined (as in our example)  we must not take base value in account.
   * Because on init `setDefaultContext` will be called, and we will have own value.
   * Otherwise, it will be not possible to set value for `GROOVY_STATEMENT` neither to `true` (equals to default), nor to `false` (equals to base).
   * See TemplateSchemeTest.
   */
  private boolean isValueChanged(@NotNull Boolean ownValue, @NotNull TemplateContextType type, @Nullable TemplateContext defaultContext) {
    Boolean defaultValue = defaultContext == null ? null : defaultContext.getOwnValue(type.getContextId());
    if (defaultValue == null) {
      TemplateContextType base = type.getBaseContextType();
      boolean baseEnabled = base != null && isEnabled(base);
      return ownValue != baseEnabled;
    }
    return !ownValue.equals(defaultValue);
  }

  @Override
  public String toString() {
    return myContextStates.toString();
  }
}
