package com.martinpaquin.javadsl

import com.github.javaparser.ast.Node

@DslMarker
annotation class JavaMarker

@JavaMarker
abstract class JavaNode () {
    protected fun <T : JavaNode> initNode(javaNode: T, init: T.() -> Unit): T {
        javaNode.init()
        return javaNode;
    }
}

