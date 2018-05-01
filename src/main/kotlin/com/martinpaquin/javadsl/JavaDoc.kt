package com.martinpaquin.javadsl

import com.github.javaparser.javadoc.Javadoc
import com.github.javaparser.javadoc.JavadocBlockTag
import com.github.javaparser.javadoc.description.JavadocDescription

class JavaDoc: JavaNode() {
    val javadocDescription: JavadocDescription = JavadocDescription()
    val javadoc: Javadoc = Javadoc(javadocDescription)


    fun author(text: String) {
        val author: JavadocBlockTag = JavadocBlockTag(JavadocBlockTag.Type.AUTHOR, text)
        javadoc.addBlockTag(author)
    }

    fun depracated(text: String) {
        val depracated: JavadocBlockTag = JavadocBlockTag(JavadocBlockTag.Type.DEPRECATED, text)
        javadoc.addBlockTag(depracated)
    }

    fun exception(text: String) {
        val exception: JavadocBlockTag = JavadocBlockTag(JavadocBlockTag.Type.EXCEPTION, text)
        javadoc.addBlockTag(exception)
    }

    fun param(text: String) {
        val param: JavadocBlockTag = JavadocBlockTag(JavadocBlockTag.Type.PARAM, text)
        javadoc.addBlockTag(param)
    }

    fun returnTag(text: String) {
        val rtag: JavadocBlockTag = JavadocBlockTag(JavadocBlockTag.Type.RETURN, text)
        javadoc.addBlockTag(rtag)
    }

    fun see(text: String) {
        val see: JavadocBlockTag = JavadocBlockTag(JavadocBlockTag.Type.SEE, text)
        javadoc.addBlockTag(see)
    }

    fun serial(text: String) {
        val serial: JavadocBlockTag = JavadocBlockTag(JavadocBlockTag.Type.SERIAL, text)
        javadoc.addBlockTag(serial)
    }

    fun serialData(text: String) {
        val serialData: JavadocBlockTag = JavadocBlockTag(JavadocBlockTag.Type.SERIAL_DATA, text)
        javadoc.addBlockTag(serialData)
    }

    fun serialField(text: String) {
        val serialField: JavadocBlockTag = JavadocBlockTag(JavadocBlockTag.Type.SERIAL_FIELD, text)
        javadoc.addBlockTag(serialField)
    }

    fun since(text: String) {
        val since: JavadocBlockTag = JavadocBlockTag(JavadocBlockTag.Type.SINCE, text)
        javadoc.addBlockTag(since)
    }

    fun throws(text: String) {
        val throws: JavadocBlockTag = JavadocBlockTag(JavadocBlockTag.Type.THROWS, text)
        javadoc.addBlockTag(throws)
    }

    fun unknown(text: String) {
        val unknown: JavadocBlockTag = JavadocBlockTag(JavadocBlockTag.Type.UNKNOWN, text)
        javadoc.addBlockTag(unknown)
    }

    fun version(text: String) {
        val version: JavadocBlockTag = JavadocBlockTag(JavadocBlockTag.Type.UNKNOWN, text)
        javadoc.addBlockTag(version)
    }
}