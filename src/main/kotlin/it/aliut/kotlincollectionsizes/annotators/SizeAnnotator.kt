package it.aliut.kotlincollectionsizes.annotators

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import it.aliut.kotlincollectionsizes.MainBundle

abstract class SizeAnnotator : Annotator {

    abstract fun accept(element: PsiElement): Boolean

    abstract fun extractCount(element: PsiElement): Int?

    abstract fun annotationRange(element: PsiElement): PsiElement

    override fun annotate(
        element: PsiElement,
        holder: AnnotationHolder,
    ) {
        element.takeIf { accept(it) }
            ?.let {
                val data = extractData(it)

                val message =
                    itemsCount
                        ?.let { MainBundle.message("sizeMessage", itemsCount) }
                        ?: MainBundle.message("sizeErrorMessage")

                val range = annotationRange(it)

                holder
                    .newAnnotation(HighlightSeverity.INFORMATION, message)
                    .range(range)
                    .highlightType(ProblemHighlightType.INFORMATION)
                    .create()
            }
    }
}
