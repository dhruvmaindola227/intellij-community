// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.util.indexing.roots;

import com.intellij.openapi.project.Project;
import com.intellij.util.indexing.roots.builders.IndexableIteratorBuilders;
import com.intellij.workspaceModel.storage.bridgeEntities.api.LibraryEntity;
import com.intellij.workspaceModel.storage.bridgeEntities.api.LibraryRoot;
import com.intellij.workspaceModel.storage.url.VirtualFileUrl;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@ApiStatus.Internal
@ApiStatus.Experimental
class LibraryIndexableEntityProvider implements IndexableEntityProvider<LibraryEntity> {

  @Override
  public @NotNull Class<LibraryEntity> getEntityClass() {
    return LibraryEntity.class;
  }

  @Override
  public @NotNull Collection<? extends IndexableIteratorBuilder> getAddedEntityIteratorBuilders(@NotNull LibraryEntity entity,
                                                                                                @NotNull Project project) {
    //  sure we are interested only in libraries used in project, but in case registered library is downloaded
    // no change in dependencies happen, only Added event on LibraryEntity.
    // For debug see com.intellij.roots.libraries.LibraryTest
    return IndexableIteratorBuilders.INSTANCE.forLibraryEntity(entity.getPersistentId(), false);
  }

  @Override
  public @NotNull Collection<? extends IndexableIteratorBuilder> getReplacedEntityIteratorBuilders(@NotNull LibraryEntity oldEntity,
                                                                                                   @NotNull LibraryEntity newEntity) {
    if (hasSomethingToIndex(oldEntity, newEntity)) {
      return IndexableIteratorBuilders.INSTANCE.forLibraryEntity(newEntity.getPersistentId(), false);
    }
    else {
      return Collections.emptyList();
    }
  }

  private static boolean hasSomethingToIndex(LibraryEntity oldEntity, LibraryEntity newEntity) {
    if (newEntity.getRoots().size() > oldEntity.getRoots().size()) return true;
    if (oldEntity.getExcludedRoots().size() > newEntity.getExcludedRoots().size()) return true;
    List<LibraryRoot> oldEntityRoots = oldEntity.getRoots();
    for (LibraryRoot root : newEntity.getRoots()) {
      if (!oldEntityRoots.contains(root)) return true;
    }
    List<VirtualFileUrl> newEntityExcludedRoots = newEntity.getExcludedRoots();
    for (VirtualFileUrl excludedRoot : oldEntity.getExcludedRoots()) {
      if (!newEntityExcludedRoots.contains(excludedRoot)) return true;
    }
    return false;
  }
}
