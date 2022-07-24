package it.aliut.kotlincollectionsizes.annotators

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtCallExpression

class ListSizeAnnotator: SizeAnnotator() {
    override fun accept(element: PsiElement): Boolean =
        (element as? KtCallExpression)
            ?.let { isListOfExpression(it) }
            ?: false

    override fun extractCount(element: PsiElement): Int? =
        (element as? KtCallExpression)
            ?.valueArgumentList
            ?.arguments
            ?.size

    override fun annotationRange(element: PsiElement): PsiElement =
        element.firstChild

    private fun isListOfExpression(expression: KtCallExpression): Boolean =
        expression.firstChild.text == "listOf"
}