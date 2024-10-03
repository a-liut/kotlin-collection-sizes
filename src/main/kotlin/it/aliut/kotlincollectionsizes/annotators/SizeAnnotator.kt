package it.aliut.kotlincollectionsizes.annotators

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import it.aliut.kotlincollectionsizes.MainBundle

abstract class SizeAnnotator : Annotator {
    abstract fun accept(element: PsiElement): Boolean

    abstract fun extractData(element: PsiElement): AnnotationData

    abstract fun annotationRange(element: PsiElement): PsiElement

    override fun annotate(
        element: PsiElement,
        holder: AnnotationHolder,
    ) {
        element.takeIf { accept(it) }
            ?.let {
                val data = extractData(it)

                val message = createMessage(data)

                val range = annotationRange(it)

                holder
                    .newAnnotation(HighlightSeverity.INFORMATION, message)
                    .range(range)
                    .highlightType(ProblemHighlightType.INFORMATION)
                    .create()
            }
    }

    private fun createMessage(data: AnnotationData): String {
        val sizeInfo = data.itemsCount ?: MainBundle.message("sizeDetectError")

        return MainBundle.message("sizeMessage", sizeInfo)
    }

    data class AnnotationData(
        val itemsCount: Int?,
    )
}
