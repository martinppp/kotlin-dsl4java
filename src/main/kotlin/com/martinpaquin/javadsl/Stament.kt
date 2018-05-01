package com.martinpaquin.javadsl

import com.github.javaparser.ast.expr.AssignExpr
import com.github.javaparser.ast.expr.Expression
import com.github.javaparser.ast.stmt.BlockStmt
import com.github.javaparser.ast.stmt.ExpressionStmt
import com.github.javaparser.ast.stmt.IfStmt
import com.github.javaparser.ast.stmt.Statement


abstract class KStmt() :JavaNode() {
    internal lateinit var stmnt: Statement
}

class KIfStamt :KStmt() {
    fun condition(expression: String) {
        (stmnt as IfStmt).condition = parseExpression(expression)
    }

    fun then(init: Block.() -> Unit) {
        val b = Block()
        b.stmnt = BlockStmt()
        (stmnt as IfStmt).thenStmt = b.stmnt as BlockStmt
        initNode(b, init)
    }

    fun elseStmt(init: Block.() -> Unit) {
        val b = Block()
        b.stmnt = BlockStmt()
        (stmnt as IfStmt).setElseStmt(b.stmnt as BlockStmt)
        initNode(b, init)
    }
}

class Block: KStmt() {
    fun ifStmt(init: KIfStamt.() -> Unit) {
        val statement = KIfStamt()
        statement.stmnt = IfStmt()
        (this.stmnt as BlockStmt).addStatement(statement.stmnt)
        initNode(statement, init)
    }

    fun exprStmt(init: ExprStmt.() -> Unit) {
        val statement = ExprStmt()
        statement.stmnt = ExpressionStmt()
        (this.stmnt as BlockStmt).addStatement(statement.stmnt)
        initNode(statement, init)
    }
}

class Stamnt: KStmt()

class ExprStmt: KStmt() {
    fun expression(init: KExpressionContainer.() -> Unit) {
        val e = KExpressionContainer()
        initNode(e, init)
        (stmnt  as ExpressionStmt).expression = e.inner()
    }
}