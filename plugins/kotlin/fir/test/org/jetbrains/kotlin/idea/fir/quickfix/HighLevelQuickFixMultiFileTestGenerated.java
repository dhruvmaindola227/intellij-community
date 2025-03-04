// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package org.jetbrains.kotlin.idea.fir.quickfix;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.idea.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.idea.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TestMetadata;
import org.jetbrains.kotlin.idea.test.TestRoot;
import org.junit.runner.RunWith;

/**
 * This class is generated by {@link org.jetbrains.kotlin.testGenerator.generator.TestGenerator}.
 * DO NOT MODIFY MANUALLY.
 */
@SuppressWarnings("all")
@TestRoot("fir")
@TestDataPath("$CONTENT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
@TestMetadata("../idea/tests/testData/quickfix/autoImports")
public abstract class HighLevelQuickFixMultiFileTestGenerated extends AbstractHighLevelQuickFixMultiFileTest {
    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("../idea/tests/testData/quickfix/autoImports/invisible")
    public static class Invisible extends AbstractHighLevelQuickFixMultiFileTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTestWithExtraFile, this, testDataFilePath);
        }

        @TestMetadata("annotation.before.Main.kt")
        public void testAnnotation() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/invisible/annotation.before.Main.kt");
        }

        @TestMetadata("class.before.Main.kt")
        public void testClass() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/invisible/class.before.Main.kt");
        }

        @TestMetadata("fun.before.Main.kt")
        public void testFun() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/invisible/fun.before.Main.kt");
        }

        @TestMetadata("property.before.Main.kt")
        public void testProperty() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/invisible/property.before.Main.kt");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("../idea/tests/testData/quickfix/autoImports/kt21515")
    public static class Kt21515 extends AbstractHighLevelQuickFixMultiFileTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTestWithExtraFile, this, testDataFilePath);
        }

        @TestMetadata("staticFromJava.test")
        public void testStaticFromJava() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/kt21515/staticFromJava.test");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("../idea/tests/testData/quickfix/autoImports/mismatchingArgs")
    public static class MismatchingArgs extends AbstractHighLevelQuickFixMultiFileTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTestWithExtraFile, this, testDataFilePath);
        }

        @TestMetadata("constantExpectedTypeMismatch.test")
        public void testConstantExpectedTypeMismatch() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/mismatchingArgs/constantExpectedTypeMismatch.test");
        }

        @TestMetadata("expectedTypeRequired.test")
        public void testExpectedTypeRequired() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/mismatchingArgs/expectedTypeRequired.test");
        }

        @TestMetadata("extensionExplicitReceiver.test")
        public void testExtensionExplicitReceiver() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/mismatchingArgs/extensionExplicitReceiver.test");
        }

        @TestMetadata("extensionImplicitReceiver.test")
        public void testExtensionImplicitReceiver() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/mismatchingArgs/extensionImplicitReceiver.test");
        }

        @TestMetadata("extensionOperator.before.Main.kt")
        public void testExtensionOperator() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/mismatchingArgs/extensionOperator.before.Main.kt");
        }

        @TestMetadata("extensionWrongReceiver.test")
        public void testExtensionWrongReceiver() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/mismatchingArgs/extensionWrongReceiver.test");
        }

        @TestMetadata("extensionWrongTypeParam.test")
        public void testExtensionWrongTypeParam() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/mismatchingArgs/extensionWrongTypeParam.test");
        }

        @TestMetadata("extensionWrongTypeParam2.test")
        public void testExtensionWrongTypeParam2() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/mismatchingArgs/extensionWrongTypeParam2.test");
        }

        @TestMetadata("extensionWrongTypeParam3.test")
        public void testExtensionWrongTypeParam3() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/mismatchingArgs/extensionWrongTypeParam3.test");
        }

        @TestMetadata("ignoreErrorsOutsideCall.test")
        public void testIgnoreErrorsOutsideCall() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/mismatchingArgs/ignoreErrorsOutsideCall.test");
        }

        @TestMetadata("namedArgument.test")
        public void testNamedArgument() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/mismatchingArgs/namedArgument.test");
        }

        @TestMetadata("smartCast.test")
        public void testSmartCast() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/mismatchingArgs/smartCast.test");
        }

        @TestMetadata("topLevelFun.test")
        public void testTopLevelFun() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/mismatchingArgs/topLevelFun.test");
        }

        @TestMetadata("topLevelFun_notWithReceiver.test")
        public void testTopLevelFun_notWithReceiver() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/mismatchingArgs/topLevelFun_notWithReceiver.test");
        }

        @TestMetadata("typeMismatch.test")
        public void testTypeMismatch() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/mismatchingArgs/typeMismatch.test");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("../idea/tests/testData/quickfix/autoImports")
    public static class Uncategorized extends AbstractHighLevelQuickFixMultiFileTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTestWithExtraFile, this, testDataFilePath);
        }

        @TestMetadata("ambiguousNamePreferFromJdk.before.Main.kt")
        public void testAmbiguousNamePreferFromJdk() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/ambiguousNamePreferFromJdk.before.Main.kt");
        }

        @TestMetadata("ambiguousNamePreferWithImportsFromPackage.before.Main.kt")
        public void testAmbiguousNamePreferWithImportsFromPackage() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/ambiguousNamePreferWithImportsFromPackage.before.Main.kt");
        }

        @TestMetadata("callWithTrailingComma.before.Main.kt")
        public void testCallWithTrailingComma() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/callWithTrailingComma.before.Main.kt");
        }

        @TestMetadata("callableReferenceExtension.before.Main.kt")
        public void testCallableReferenceExtension() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/callableReferenceExtension.before.Main.kt");
        }

        @TestMetadata("callableReferenceExtension2.before.Main.kt")
        public void testCallableReferenceExtension2() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/callableReferenceExtension2.before.Main.kt");
        }

        @TestMetadata("callableReferenceTopLevel.before.Main.kt")
        public void testCallableReferenceTopLevel() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/callableReferenceTopLevel.before.Main.kt");
        }

        @TestMetadata("classImport.before.Main.kt")
        public void testClassImport() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/classImport.before.Main.kt");
        }

        @TestMetadata("constructorParameterAnnotation.test")
        public void testConstructorParameterAnnotation() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/constructorParameterAnnotation.test");
        }

        @TestMetadata("constructorReference.before.Main.kt")
        public void testConstructorReference() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/constructorReference.before.Main.kt");
        }

        @TestMetadata("delegateExtensionBoth.test")
        public void testDelegateExtensionBoth() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/delegateExtensionBoth.test");
        }

        @TestMetadata("delegateExtensionGet.test")
        public void testDelegateExtensionGet() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/delegateExtensionGet.test");
        }

        @TestMetadata("delegateExtensionProvideDelegate.test")
        public void testDelegateExtensionProvideDelegate() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/delegateExtensionProvideDelegate.test");
        }

        @TestMetadata("delegateExtensionSet.test")
        public void testDelegateExtensionSet() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/delegateExtensionSet.test");
        }

        @TestMetadata("delegateNoOperator.test")
        public void testDelegateNoOperator() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/delegateNoOperator.test");
        }

        @TestMetadata("divOperator.before.Main.kt")
        public void testDivOperator() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/divOperator.before.Main.kt");
        }

        @TestMetadata("dslMarkers.before.Main.kt")
        public void testDslMarkers() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/dslMarkers.before.Main.kt");
        }

        @TestMetadata("dslMarkersOnReceiver.before.Main.kt")
        public void testDslMarkersOnReceiver() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/dslMarkersOnReceiver.before.Main.kt");
        }

        @TestMetadata("extensionFunctionImport.before.Main.kt")
        public void testExtensionFunctionImport() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/extensionFunctionImport.before.Main.kt");
        }

        @TestMetadata("extensionFunctionImportImplicitReceiver.before.Main.kt")
        public void testExtensionFunctionImportImplicitReceiver() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/extensionFunctionImportImplicitReceiver.before.Main.kt");
        }

        @TestMetadata("extensionPreferDeprecatedSinceApplicable.test")
        public void testExtensionPreferDeprecatedSinceApplicable() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/extensionPreferDeprecatedSinceApplicable.test");
        }

        @TestMetadata("extensionPreferDeprecatedSinceNotApplicable.test")
        public void testExtensionPreferDeprecatedSinceNotApplicable() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/extensionPreferDeprecatedSinceNotApplicable.test");
        }

        @TestMetadata("extensionPropertyImport.before.Main.kt")
        public void testExtensionPropertyImport() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/extensionPropertyImport.before.Main.kt");
        }

        @TestMetadata("extensionPropertyOnTypeAliasFromExpansion.before.Main.kt")
        public void testExtensionPropertyOnTypeAliasFromExpansion() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/extensionPropertyOnTypeAliasFromExpansion.before.Main.kt");
        }

        @TestMetadata("extensionPropertyOnTypeAliasFromOtherTypeAlias.before.Main.kt")
        public void testExtensionPropertyOnTypeAliasFromOtherTypeAlias() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/extensionPropertyOnTypeAliasFromOtherTypeAlias.before.Main.kt");
        }

        @TestMetadata("factoryFunctionFromLambda.before.Main.kt")
        public void testFactoryFunctionFromLambda() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/factoryFunctionFromLambda.before.Main.kt");
        }

        @TestMetadata("falsePostfixOperator.before.Main.kt")
        public void testFalsePostfixOperator() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/falsePostfixOperator.before.Main.kt");
        }

        @TestMetadata("functionImport.before.Main.kt")
        public void testFunctionImport() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/functionImport.before.Main.kt");
        }

        @TestMetadata("importAliasClassAlreadyExists.before.Main.kt")
        public void testImportAliasClassAlreadyExists() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/importAliasClassAlreadyExists.before.Main.kt");
        }

        @TestMetadata("importAliasClassAlreadyExistsCollision.before.Main.kt")
        public void testImportAliasClassAlreadyExistsCollision() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/importAliasClassAlreadyExistsCollision.before.Main.kt");
        }

        @TestMetadata("importAliasFunctionAlreadyExists.before.Main.kt")
        public void testImportAliasFunctionAlreadyExists() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/importAliasFunctionAlreadyExists.before.Main.kt");
        }

        @TestMetadata("importAliasFunctionAlreadyExistsCollision.before.Main.kt")
        public void testImportAliasFunctionAlreadyExistsCollision() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/importAliasFunctionAlreadyExistsCollision.before.Main.kt");
        }

        @TestMetadata("importAliasPropertyAlreadyExists.before.Main.kt")
        public void testImportAliasPropertyAlreadyExists() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/importAliasPropertyAlreadyExists.before.Main.kt");
        }

        @TestMetadata("importFromRoot.before.Main.kt")
        public void testImportFromRoot() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/importFromRoot.before.Main.kt");
        }

        @TestMetadata("importFunctionWithDefinitelyNotNullType.before.Main.kt")
        public void testImportFunctionWithDefinitelyNotNullType() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/importFunctionWithDefinitelyNotNullType.before.Main.kt");
        }

        @TestMetadata("importGetValueExtensionForDelegateInsideRun.before.Main.kt")
        public void testImportGetValueExtensionForDelegateInsideRun() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/importGetValueExtensionForDelegateInsideRun.before.Main.kt");
        }

        @TestMetadata("importGetValueExtensionForDelegateWithLambda.before.Main.kt")
        public void testImportGetValueExtensionForDelegateWithLambda() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/importGetValueExtensionForDelegateWithLambda.before.Main.kt");
        }

        @TestMetadata("importInFirstPartInQualifiedExpression.before.Main.kt")
        public void testImportInFirstPartInQualifiedExpression() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/importInFirstPartInQualifiedExpression.before.Main.kt");
        }

        @TestMetadata("importInFirstPartInUserType.test")
        public void testImportInFirstPartInUserType() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/importInFirstPartInUserType.test");
        }

        @TestMetadata("importKotlinCompanionPropertyAsFieldFromJava.test")
        public void testImportKotlinCompanionPropertyAsFieldFromJava() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/importKotlinCompanionPropertyAsFieldFromJava.test");
        }

        @TestMetadata("importKotlinCompanionStaticFunctionFromJava.test")
        public void testImportKotlinCompanionStaticFunctionFromJava() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/importKotlinCompanionStaticFunctionFromJava.test");
        }

        @TestMetadata("importKotlinCompanionStaticPropertyDefaultGetterFromJava.test")
        public void testImportKotlinCompanionStaticPropertyDefaultGetterFromJava() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/importKotlinCompanionStaticPropertyDefaultGetterFromJava.test");
        }

        @TestMetadata("importKotlinCompanionStaticPropertyDefaultSetterFromJava.test")
        public void testImportKotlinCompanionStaticPropertyDefaultSetterFromJava() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/importKotlinCompanionStaticPropertyDefaultSetterFromJava.test");
        }

        @TestMetadata("importKotlinCompanionStaticPropertyOverloadedGetterFromJava.test")
        public void testImportKotlinCompanionStaticPropertyOverloadedGetterFromJava() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/importKotlinCompanionStaticPropertyOverloadedGetterFromJava.test");
        }

        @TestMetadata("importKotlinCompanionStaticPropertyOverloadedSetterFromJava.test")
        public void testImportKotlinCompanionStaticPropertyOverloadedSetterFromJava() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/importKotlinCompanionStaticPropertyOverloadedSetterFromJava.test");
        }

        @TestMetadata("importKotlinPropertyAsFieldFromJava.test")
        public void testImportKotlinPropertyAsFieldFromJava() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/importKotlinPropertyAsFieldFromJava.test");
        }

        @TestMetadata("importKotlinStaticFunctionFromJava.test")
        public void testImportKotlinStaticFunctionFromJava() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/importKotlinStaticFunctionFromJava.test");
        }

        @TestMetadata("importKotlinStaticPropertyDefaultGetterFromJava.test")
        public void testImportKotlinStaticPropertyDefaultGetterFromJava() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/importKotlinStaticPropertyDefaultGetterFromJava.test");
        }

        @TestMetadata("importKotlinStaticPropertyDefaultSetterFromJava.test")
        public void testImportKotlinStaticPropertyDefaultSetterFromJava() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/importKotlinStaticPropertyDefaultSetterFromJava.test");
        }

        @TestMetadata("importKotlinStaticPropertyOverloadedGetterFromJava.test")
        public void testImportKotlinStaticPropertyOverloadedGetterFromJava() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/importKotlinStaticPropertyOverloadedGetterFromJava.test");
        }

        @TestMetadata("importKotlinStaticPropertyOverloadedSetterFromJava.test")
        public void testImportKotlinStaticPropertyOverloadedSetterFromJava() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/importKotlinStaticPropertyOverloadedSetterFromJava.test");
        }

        @TestMetadata("importNullableTraitWithGenerics.before.Main.kt")
        public void testImportNullableTraitWithGenerics() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/importNullableTraitWithGenerics.before.Main.kt");
        }

        @TestMetadata("ImportOperatorInvokeWithConvention.before.Main.kt")
        public void testImportOperatorInvokeWithConvention() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/ImportOperatorInvokeWithConvention.before.Main.kt");
        }

        @TestMetadata("importTrait.before.Main.kt")
        public void testImportTrait() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/importTrait.before.Main.kt");
        }

        @TestMetadata("indexCallExtensionGet.test")
        public void testIndexCallExtensionGet() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/indexCallExtensionGet.test");
        }

        @TestMetadata("indexCallExtensionGetNoOperator.test")
        public void testIndexCallExtensionGetNoOperator() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/indexCallExtensionGetNoOperator.test");
        }

        @TestMetadata("indexCallExtensionImportGetOnNoOperatorWarning.test")
        public void testIndexCallExtensionImportGetOnNoOperatorWarning() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/indexCallExtensionImportGetOnNoOperatorWarning.test");
        }

        @TestMetadata("indexCallExtensionImportSetOnNoOperatorWarning.test")
        public void testIndexCallExtensionImportSetOnNoOperatorWarning() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/indexCallExtensionImportSetOnNoOperatorWarning.test");
        }

        @TestMetadata("indexCallExtensionSet.test")
        public void testIndexCallExtensionSet() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/indexCallExtensionSet.test");
        }

        @TestMetadata("indexCallNoImportWhenGetNeededButSetAvailable.test")
        public void testIndexCallNoImportWhenGetNeededButSetAvailable() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/indexCallNoImportWhenGetNeededButSetAvailable.test");
        }

        @TestMetadata("indexCallNoImportWhenSetNeededButGetAvailable.test")
        public void testIndexCallNoImportWhenSetNeededButGetAvailable() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/indexCallNoImportWhenSetNeededButGetAvailable.test");
        }

        @TestMetadata("infixCall.before.Main.kt")
        public void testInfixCall() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/infixCall.before.Main.kt");
        }

        @TestMetadata("infixCall2.before.Main.kt")
        public void testInfixCall2() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/infixCall2.before.Main.kt");
        }

        @TestMetadata("invokeExtension.test")
        public void testInvokeExtension() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/invokeExtension.test");
        }

        @TestMetadata("invokeExtension2.test")
        public void testInvokeExtension2() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/invokeExtension2.test");
        }

        @TestMetadata("invokeExtensionNoOperator.test")
        public void testInvokeExtensionNoOperator() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/invokeExtensionNoOperator.test");
        }

        @TestMetadata("iteratorOperator.before.Main.kt")
        public void testIteratorOperator() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/iteratorOperator.before.Main.kt");
        }

        @TestMetadata("memberImportFunction.test")
        public void testMemberImportFunction() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/memberImportFunction.test");
        }

        @TestMetadata("memberImportJavaField.test")
        public void testMemberImportJavaField() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/memberImportJavaField.test");
        }

        @TestMetadata("memberImportJavaMethod.test")
        public void testMemberImportJavaMethod() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/memberImportJavaMethod.test");
        }

        @TestMetadata("memberImportNotForClassFunction.test")
        public void testMemberImportNotForClassFunction() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/memberImportNotForClassFunction.test");
        }

        @TestMetadata("memberImportNotForClassProperty.test")
        public void testMemberImportNotForClassProperty() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/memberImportNotForClassProperty.test");
        }

        @TestMetadata("memberImportNotForJavaNonStaticField.test")
        public void testMemberImportNotForJavaNonStaticField() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/memberImportNotForJavaNonStaticField.test");
        }

        @TestMetadata("memberImportNotForJavaNonStaticMethod.test")
        public void testMemberImportNotForJavaNonStaticMethod() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/memberImportNotForJavaNonStaticMethod.test");
        }

        @TestMetadata("memberImportNotForTopLevelFunction.test")
        public void testMemberImportNotForTopLevelFunction() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/memberImportNotForTopLevelFunction.test");
        }

        @TestMetadata("memberImportProperty.test")
        public void testMemberImportProperty() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/memberImportProperty.test");
        }

        @TestMetadata("memberWithTopLevelConflict.before.Main.kt")
        public void testMemberWithTopLevelConflict() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/memberWithTopLevelConflict.before.Main.kt");
        }

        @TestMetadata("minusOperator.before.Main.kt")
        public void testMinusOperator() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/minusOperator.before.Main.kt");
        }

        @TestMetadata("multiDeclarationExtensionAllComponents.test")
        public void testMultiDeclarationExtensionAllComponents() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/multiDeclarationExtensionAllComponents.test");
        }

        @TestMetadata("multiDeclarationExtensionAllComponentsMany.test")
        public void testMultiDeclarationExtensionAllComponentsMany() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/multiDeclarationExtensionAllComponentsMany.test");
        }

        @TestMetadata("multiDeclarationExtensionAllComponentsPrefereFull.test")
        public void testMultiDeclarationExtensionAllComponentsPrefereFull() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/multiDeclarationExtensionAllComponentsPrefereFull.test");
        }

        @TestMetadata("multiDeclarationExtensionAllComponentsPrefereNotDeprecated.test")
        public void testMultiDeclarationExtensionAllComponentsPrefereNotDeprecated() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/multiDeclarationExtensionAllComponentsPrefereNotDeprecated.test");
        }

        @TestMetadata("multiDeclarationExtensionComponent1.test")
        public void testMultiDeclarationExtensionComponent1() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/multiDeclarationExtensionComponent1.test");
        }

        @TestMetadata("multiDeclarationExtensionComponent2.test")
        public void testMultiDeclarationExtensionComponent2() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/multiDeclarationExtensionComponent2.test");
        }

        @TestMetadata("multiDeclarationExtensionComponentNoOperator.test")
        public void testMultiDeclarationExtensionComponentNoOperator() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/multiDeclarationExtensionComponentNoOperator.test");
        }

        @TestMetadata("nestedClass.before.Main.kt")
        public void testNestedClass() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/nestedClass.before.Main.kt");
        }

        @TestMetadata("noFunctionImportOnSimpleName.test")
        public void testNoFunctionImportOnSimpleName() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/noFunctionImportOnSimpleName.test");
        }

        @TestMetadata("noImportForFunInQualifiedNotFirst.before.Main.kt")
        public void testNoImportForFunInQualifiedNotFirst() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/noImportForFunInQualifiedNotFirst.before.Main.kt");
        }

        @TestMetadata("noImportForNestedInPrivate.before.Main.kt")
        public void testNoImportForNestedInPrivate() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/noImportForNestedInPrivate.before.Main.kt");
        }

        @TestMetadata("noImportForPrivateClass.before.Main.kt")
        public void testNoImportForPrivateClass() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/noImportForPrivateClass.before.Main.kt");
        }

        @TestMetadata("noImportForPrivateFunction.before.Main.kt")
        public void testNoImportForPrivateFunction() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/noImportForPrivateFunction.before.Main.kt");
        }

        @TestMetadata("noImportInImports.before.Main.kt")
        public void testNoImportInImports() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/noImportInImports.before.Main.kt");
        }

        @TestMetadata("noImportInQualifiedExpressionNotFirst.before.Main.kt")
        public void testNoImportInQualifiedExpressionNotFirst() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/noImportInQualifiedExpressionNotFirst.before.Main.kt");
        }

        @TestMetadata("noImportInQualifiedUserTypeNotFirst.before.Main.kt")
        public void testNoImportInQualifiedUserTypeNotFirst() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/noImportInQualifiedUserTypeNotFirst.before.Main.kt");
        }

        @TestMetadata("noImportInSafeQualifiedExpressionNotFirst.before.Main.kt")
        public void testNoImportInSafeQualifiedExpressionNotFirst() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/noImportInSafeQualifiedExpressionNotFirst.before.Main.kt");
        }

        @TestMetadata("noImportInterfaceRefAsConstructor.before.Main.kt")
        public void testNoImportInterfaceRefAsConstructor() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/noImportInterfaceRefAsConstructor.before.Main.kt");
        }

        @TestMetadata("noImportsForClassInExcludedPackage.before.Main.kt")
        public void testNoImportsForClassInExcludedPackage() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/noImportsForClassInExcludedPackage.before.Main.kt");
        }

        @TestMetadata("noImportsForExcludedClass.before.Main.kt")
        public void testNoImportsForExcludedClass() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/noImportsForExcludedClass.before.Main.kt");
        }

        @TestMetadata("noImportsForFunctionInExcludedPackage.before.Main.kt")
        public void testNoImportsForFunctionInExcludedPackage() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/noImportsForFunctionInExcludedPackage.before.Main.kt");
        }

        @TestMetadata("noMemberFunctionImportOnSimpleName.test")
        public void testNoMemberFunctionImportOnSimpleName() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/noMemberFunctionImportOnSimpleName.test");
        }

        @TestMetadata("noneApplicableFromInstanceButExtension.before.Main.kt")
        public void testNoneApplicableFromInstanceButExtension() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/noneApplicableFromInstanceButExtension.before.Main.kt");
        }

        @TestMetadata("notExcludedClass.before.Main.kt")
        public void testNotExcludedClass() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/notExcludedClass.before.Main.kt");
        }

        @TestMetadata("objectImport.before.Main.kt")
        public void testObjectImport() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/objectImport.before.Main.kt");
        }

        @TestMetadata("objectMemberFunctionImportWhenReceiverPresent.before.Main.kt")
        public void testObjectMemberFunctionImportWhenReceiverPresent() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/objectMemberFunctionImportWhenReceiverPresent.before.Main.kt");
        }

        @TestMetadata("operatorAssignPlus.test")
        public void testOperatorAssignPlus() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/operatorAssignPlus.test");
        }

        @TestMetadata("operatorAssignPlusAssign.test")
        public void testOperatorAssignPlusAssign() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/operatorAssignPlusAssign.test");
        }

        @TestMetadata("operatorAssignPlusTwoVariantsDifferentPackages.test")
        public void testOperatorAssignPlusTwoVariantsDifferentPackages() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/operatorAssignPlusTwoVariantsDifferentPackages.test");
        }

        @TestMetadata("packageClass.before.Main.kt")
        public void testPackageClass() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/packageClass.before.Main.kt");
        }

        @TestMetadata("plusOperator.before.Main.kt")
        public void testPlusOperator() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/plusOperator.before.Main.kt");
        }

        @TestMetadata("plusOperatorWithTypeMismatch.before.Main.kt")
        public void testPlusOperatorWithTypeMismatch() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/plusOperatorWithTypeMismatch.before.Main.kt");
        }

        @TestMetadata("postfixOperator.before.Main.kt")
        public void testPostfixOperator() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/postfixOperator.before.Main.kt");
        }

        @TestMetadata("propertyImport.before.Main.kt")
        public void testPropertyImport() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/propertyImport.before.Main.kt");
        }

        @TestMetadata("sameModuleImportPriority.before.Main.kt")
        public void testSameModuleImportPriority() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/sameModuleImportPriority.before.Main.kt");
        }

        @TestMetadata("timesAssign.before.Main.kt")
        public void testTimesAssign() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/timesAssign.before.Main.kt");
        }

        @TestMetadata("typeAliasExtensionFunction.before.Main.kt")
        public void testTypeAliasExtensionFunction() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/typeAliasExtensionFunction.before.Main.kt");
        }

        @TestMetadata("typeAliasExtensionFunctionInTypeAliasChain.before.Main.kt")
        public void testTypeAliasExtensionFunctionInTypeAliasChain() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/typeAliasExtensionFunctionInTypeAliasChain.before.Main.kt");
        }

        @TestMetadata("typeAliasExtensionProperty.before.Main.kt")
        public void testTypeAliasExtensionProperty() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/typeAliasExtensionProperty.before.Main.kt");
        }

        @TestMetadata("typeAliasImport.before.Main.kt")
        public void testTypeAliasImport() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/typeAliasImport.before.Main.kt");
        }

        @TestMetadata("unaryMinusOperator.before.Main.kt")
        public void testUnaryMinusOperator() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/unaryMinusOperator.before.Main.kt");
        }

        @TestMetadata("unaryPlusOperator.before.Main.kt")
        public void testUnaryPlusOperator() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/unaryPlusOperator.before.Main.kt");
        }

        @TestMetadata("withSmartCastQualifier.before.Main.kt")
        public void testWithSmartCastQualifier() throws Exception {
            runTest("../idea/tests/testData/quickfix/autoImports/withSmartCastQualifier.before.Main.kt");
        }
    }
}
