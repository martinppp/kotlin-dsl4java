package com.martinpaquin.javadsl

import com.github.javaparser.ast.expr.AssignExpr
import com.github.javaparser.ast.expr.BinaryExpr
import com.github.javaparser.ast.expr.Expression
import com.github.javaparser.ast.stmt.*
import com.github.javaparser.ast.stmt.CatchClause


abstract class KStmt() :JavaNode() {
    internal lateinit var stmnt: Statement
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

    fun doStmt(init: KDoStmt.() -> Unit) {
        val statement = KDoStmt()
        statement.stmnt = DoStmt()
        (this.stmnt as BlockStmt).addStatement(statement.stmnt)
        initNode(statement, init)
    }

    fun breakStmt(label :String = "") {
        if (label.isNotBlank()){
            (this.stmnt as BlockStmt).addStatement(BreakStmt(label))
        } else {
            (this.stmnt as BlockStmt).addStatement(BreakStmt())
        }
    }

    fun conueStmt(label :String = "") {
        if (label.isNotBlank()){
            (this.stmnt as BlockStmt).addStatement(ContinueStmt(label))
        } else {
            (this.stmnt as BlockStmt).addStatement(ContinueStmt())
        }
    }

    fun emptyStmt() {
        (this.stmnt as BlockStmt).addStatement(EmptyStmt())
    }
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

class Stamnt: KStmt()

class ExprStmt: KStmt() {
    fun expression(init: KExpressionContainer.() -> Unit) {
        val e = KExpressionContainer()
        initNode(e, init)
        (stmnt  as ExpressionStmt).expression = e.inner()
    }
}

class KDoStmt: KStmt() {
    fun condition(init: KExpressionContainer.() -> Unit) {
        val b = KExpressionContainer()
        initNode(b, init)
        (stmnt  as DoStmt).condition = b.inner()
    }

    fun body(init: Block.() -> Unit) {
        val b = Block()
        b.stmnt = BlockStmt()
        (stmnt as DoStmt).body = b.stmnt as BlockStmt
        initNode(b, init)
    }

}