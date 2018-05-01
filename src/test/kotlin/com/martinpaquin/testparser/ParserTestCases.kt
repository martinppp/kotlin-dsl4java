package com.martinpaquin.testparser

import com.github.javaparser.JavaParser
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.expr.Expression
import javassist.expr.Expr
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.io.FileInputStream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JavaParserTestCase() {
    val file_path = "/Volumes/STOCKAGE/Users/martin/Documents/workspace-xomg3/hospital2/src/main/java/com/freschelegacy/file/hospital/Hospital.java"

    @Test
    fun parseClass() {
        val compilationUnit: CompilationUnit = JavaParser.parse(FileInputStream(file_path))
        println(compilationUnit.toString())
    }

    @Test
    fun parseFragment() {
        val expr: Expression = JavaParser.parseExpression("hospital == null")
        println(expr.toString())
    }
}