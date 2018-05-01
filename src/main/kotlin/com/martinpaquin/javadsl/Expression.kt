package com.martinpaquin.javadsl

import com.github.javaparser.JavaParser
import com.github.javaparser.ast.expr.*

abstract class KExpression(pexpr: Expression) :JavaNode() {
    //fun ifStmt(init: KIfStamt.() -> Unit) = initNode(KIfStamt(), init)
    val expr: Expression = pexpr
}

//    fun javadoc(text: String = "", init: JavaDoc.() -> Unit) {
//        val javadoc = JavaDoc()
//        initNode(javadoc, init)
//        javadoc.javadocDescription.addElement(JavadocSnippet(text))
//        method.setJavadocComment(javadoc.javadoc)
//    }
class KExpressionContainer: KExpression(EnclosedExpr()) {

    fun nameExpression(name: String, init: KNameExpr.() -> Unit) {
        val n = initNode(KNameExpr(name), init)
        (expr as EnclosedExpr).setInner(n.expr)
    }
    fun binaryEpression(init: KBinaryExpr.() -> Unit) {
        val n = initNode(KBinaryExpr(), init)
        (expr as EnclosedExpr).setInner(n.expr)
    }
    fun assign(init: KAssignExpr.() -> Unit) {
        val n = initNode(KAssignExpr(), init)
        (expr as EnclosedExpr).setInner(n.expr)
    }

    fun inner() :Expression = (expr as EnclosedExpr).inner
}

class KBinaryExpr(): KExpression(BinaryExpr()) {
    fun left(init: KExpressionContainer.() -> Unit) {
        val b = KExpressionContainer()
        initNode(b, init)
        (expr as BinaryExpr).left = b.inner()
    }

    fun operator(operator: BinaryExprOperator) {
        (expr as BinaryExpr).operator = operator.op
    }


    fun right (init: KExpressionContainer.() -> Unit) {
        val b = KExpressionContainer()
        initNode(b, init)
        (expr as BinaryExpr).right = b.inner()
    }
}

class KNameExpr(value: String): KExpression(NameExpr(value))
class KAssignExpr(): KExpression(AssignExpr()) {
    fun target(init: KExpressionContainer.() -> Unit) {
        val b = KExpressionContainer()
        initNode(b, init)
        (expr as AssignExpr).target = b.inner()
    }

    fun operator(operator: AssignExprOperator) {
        (expr as AssignExpr).operator = operator.op
    }
    fun value(init: KExpressionContainer.() -> Unit) {
        val b = KExpressionContainer()
        initNode(b, init)
        (expr as AssignExpr).value = b.inner()
    }
}

fun parseExpression(expression: String) :Expression = JavaParser.parseExpression<Expression>(expression)