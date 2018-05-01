package com.martinpaquin.javadsl

import com.github.javaparser.ast.expr.AssignExpr
import com.github.javaparser.ast.expr.BinaryExpr

enum class BinaryExprOperator (val op: BinaryExpr.Operator){
    OR(BinaryExpr.Operator.OR),
    AND(BinaryExpr.Operator.AND),
    BINARY_AND(BinaryExpr.Operator.BINARY_AND),
    BINARY_OR(BinaryExpr.Operator.BINARY_OR),
    XOR(BinaryExpr.Operator.XOR),
    DIVIDE(BinaryExpr.Operator.DIVIDE),
    REMAINDER(BinaryExpr.Operator.REMAINDER),
    EQUALS(BinaryExpr.Operator.EQUALS),
    NOT_EQUALS(BinaryExpr.Operator.NOT_EQUALS),
    GREATER(BinaryExpr.Operator.GREATER),
    GREATER_EQUALS(BinaryExpr.Operator.GREATER_EQUALS),
    SIGNED_RIGHT_SHIFT(BinaryExpr.Operator.SIGNED_RIGHT_SHIFT),
    UNSIGNED_RIGHT_SHIFT(BinaryExpr.Operator.UNSIGNED_RIGHT_SHIFT),
    LEFT_SHIFT(BinaryExpr.Operator.LEFT_SHIFT),
    LESS(BinaryExpr.Operator.LESS),
    LESS_EQUALS(BinaryExpr.Operator.LESS_EQUALS),
    PLUS(BinaryExpr.Operator.PLUS),
    MINUS(BinaryExpr.Operator.MINUS),
    MULTIPLY(BinaryExpr.Operator.MULTIPLY)
}

enum class AssignExprOperator (val op: AssignExpr.Operator){
    ASSIGN(AssignExpr.Operator.ASSIGN),
    PLUS(AssignExpr.Operator.PLUS),
    MINUS(AssignExpr.Operator.MINUS),
    MULTIPLY(AssignExpr.Operator.MULTIPLY),
    DIVIDE(AssignExpr.Operator.DIVIDE),
    BINARY_AND(AssignExpr.Operator.BINARY_AND),
    BINARY_OR(AssignExpr.Operator.BINARY_OR),
    XOR(AssignExpr.Operator.XOR),
    REMAINDER(AssignExpr.Operator.REMAINDER),
    LEFT_SHIFT(AssignExpr.Operator.LEFT_SHIFT),
    SIGNED_RIGHT_SHIFT(AssignExpr.Operator.SIGNED_RIGHT_SHIFT),
    UNSIGNED_RIGHT_SHIFT(AssignExpr.Operator.UNSIGNED_RIGHT_SHIFT)
}