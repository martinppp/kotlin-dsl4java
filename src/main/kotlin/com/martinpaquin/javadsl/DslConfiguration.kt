package com.martinpaquin.javadsl

import com.github.javaparser.ast.Node
import com.github.javaparser.printer.PrettyPrinter
import com.github.javaparser.printer.PrettyPrinterConfiguration

object DslConfiguration {
    private val configuration :PrettyPrinterConfiguration =  PrettyPrinterConfiguration()
    private val prettyPrinter: PrettyPrinter = PrettyPrinter(configuration)
    var indent = "    "
        set(value) {
            field = value
            configuration.setIndent(indent)
        }
    var isOrderImports: Boolean = true
        set(value) {
            field = value
            configuration.setOrderImports(isOrderImports)
        }
    var endOfLineCharacter = "\n"
        set(value) {
            field = value
            configuration.setEndOfLineCharacter(endOfLineCharacter)
        }

    init {
        configuration.setIndent(indent)
        configuration.setOrderImports(isOrderImports)
        configuration.setEndOfLineCharacter(endOfLineCharacter)
    }

    fun print(node: Node) :String {
        return prettyPrinter.print(node)
    }
}
