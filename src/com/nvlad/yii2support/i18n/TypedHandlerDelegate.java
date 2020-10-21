package com.nvlad.yii2support.i18n;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.php.lang.psi.elements.ClassReference;
import com.jetbrains.php.lang.psi.elements.MethodReference;
import com.jetbrains.php.lang.psi.elements.ParameterList;
import org.jetbrains.annotations.NotNull;

public class TypedHandlerDelegate  extends com.intellij.codeInsight.editorActions.TypedHandlerDelegate {
    @NotNull
    @Override
    public Result checkAutoPopup(char charTyped, @NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file) {
        MethodReference reference = PsiTreeUtil.getParentOfType(file, MethodReference.class);
        if (reference != null && reference.getName() != null && reference.getName().equals("t") && reference.getClassReference() instanceof ClassReference) {
            ClassReference classReference = (ClassReference) reference.getClassReference();
            if (classReference == null || classReference.getName() == null || !classReference.getName().equals("Yii")) {
                return Result.STOP;
            }
            if (charTyped == '\'' || charTyped == '"') {
                if (file instanceof LeafPsiElement && (file.getText().equals("$category") || file.getText().equals("$message"))) {
                    return Result.CONTINUE;
                }
                if (file.getNextSibling() instanceof ParameterList) {
                    return Result.CONTINUE;
                }
            }
        }
        return Result.STOP;
    }
}
