package com.nvlad.yii2support.forms;

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.jetbrains.php.lang.psi.elements.MethodReference;
import org.jetbrains.annotations.NotNull;

public class FieldAttributesTypedHandlerDelegate extends TypedHandlerDelegate {
    @NotNull
    @Override
    public Result checkAutoPopup(char charTyped, @NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file) {
        if ((charTyped == '\'' || charTyped == '"') && file.getParent() instanceof MethodReference) {
            return Result.CONTINUE;
        }
        return Result.STOP;
    }
}
