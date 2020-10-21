package com.nvlad.yii2support.database;

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.jetbrains.php.lang.psi.elements.ArrayCreationExpression;
import org.jetbrains.annotations.NotNull;

public class QueryTypedHandlerDelegate extends TypedHandlerDelegate {
    @NotNull
    @Override
    public Result checkAutoPopup(char charTyped, @NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file) {
        if ((charTyped == '\'' || charTyped == '"') && file.getParent() instanceof ArrayCreationExpression) {
            return Result.CONTINUE;
        }
        return Result.STOP;
    }
}
