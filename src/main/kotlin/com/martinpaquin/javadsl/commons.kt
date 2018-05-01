package com.martinpaquin.javadsl

import com.github.javaparser.ast.expr.*

fun annotationExpr (name: String, value: String) :AnnotationExpr{
    lateinit var annotationExpr: AnnotationExpr
    if (value.isBlank())
        annotationExpr = MarkerAnnotationExpr(name)
    else {
        annotationExpr = NormalAnnotationExpr().addPair(name, value)
    }
    return annotationExpr
}