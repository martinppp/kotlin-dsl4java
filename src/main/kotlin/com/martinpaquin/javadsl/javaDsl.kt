package com.martinpaquin.javadsl

import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.Modifier
import com.github.javaparser.ast.Node
import com.github.javaparser.ast.body.*
import com.github.javaparser.ast.comments.Comment
import com.github.javaparser.ast.comments.JavadocComment
import com.github.javaparser.ast.expr.*
import com.github.javaparser.ast.stmt.BlockStmt
import com.github.javaparser.ast.stmt.ExpressionStmt
import com.github.javaparser.ast.stmt.IfStmt
import com.github.javaparser.ast.type.PrimitiveType
import com.github.javaparser.ast.type.TypeParameter
import com.github.javaparser.javadoc.Javadoc
import com.github.javaparser.javadoc.JavadocBlockTag
import com.github.javaparser.javadoc.description.JavadocDescription
import com.github.javaparser.javadoc.description.JavadocInlineTag
import com.github.javaparser.javadoc.description.JavadocSnippet
import com.sun.xml.internal.ws.wsdl.writer.document.ParamType
import java.util.*
import kotlin.collections.HashSet

fun compilationUnit(packageName: String = "", init: KCompilationUnit.() -> Unit) :KCompilationUnit{
    val kcompilationUnit = KCompilationUnit()
    kcompilationUnit.initNode(kcompilationUnit, init)
    kcompilationUnit.compilationUnit.setPackageDeclaration(packageName)
    return kcompilationUnit
}

class KCompilationUnit : JavaNode() {
    val compilationUnit = CompilationUnit()

    internal fun import(name: String, isStatic: Boolean = false, isAsterisk: Boolean = false) {
        compilationUnit.addImport(name, isStatic, isAsterisk)
    }

    internal fun classe(name: String, vararg modifiers: Modifier, init: Classe.() -> Unit) {
        val c = Classe()
        c.classe = compilationUnit.addClass(name, *modifiers)
        initNode(c, init)
    }

    internal fun interfaceDecl(name: String, init: InterfaceDecl.() -> Unit) {
        val i = InterfaceDecl()
        i.interfaceDeclaration = compilationUnit.addInterface(name)
        initNode(i, init)
    }

    internal fun enum(name: String, init: EnumDecl.() -> Unit) {
        val e = EnumDecl()
        e.enum = compilationUnit.addEnum(name)
        initNode(e, init)
    }

    override fun toString(): String {
        return DslConfiguration.print(compilationUnit)//compilationUnit.toString()
    }

    internal fun initNode(javaNode: KCompilationUnit, init: KCompilationUnit.() -> Unit) {
        super.initNode(this, init)
    }
}

class Classe : JavaNode() {
    lateinit var  classe :ClassOrInterfaceDeclaration

    fun field(type: String, name: String, initialValue: Any? = null, vararg modifiers: Modifier, init: KField.() -> Unit) {
        val field = KField()
        if (initialValue == null) {
            field.field = classe.addField(type, name, *modifiers)
        } else {
            val initValue =
                    when(initialValue) {
                        is Long -> LongLiteralExpr(initialValue)
                        is Int -> IntegerLiteralExpr(initialValue)
                        else -> {
                            StringLiteralExpr(initialValue.toString())
                        }
                    }
            field.field = classe.addFieldWithInitializer(TypeParameter(type), name, initValue, *modifiers)
        }
        initNode(field, init)
    }

    fun javadoc(text: String, init: JavaDoc.() -> Unit) {
        val javadoc = JavaDoc()
        initNode(javadoc, init)
        javadoc.javadocDescription.addElement(JavadocSnippet(text))
        classe.setJavadocComment(javadoc.javadoc)
    }

    fun method(name: String, type: String, vararg modifiers: Modifier, init: Method.() -> Unit) {
        val m = Method()
        m.method = classe.addMethod(name, *modifiers)
        m.method.setType(type)
        initNode(m, init)
    }

    fun implements(name: String) {
        classe.addImplementedType(name)
    }

    fun markerAnnotation(name: String) {
        classe.addMarkerAnnotation(name)
    }

    fun annotation(name: String, init: KAnnotation.() -> Unit) {
        val a = KAnnotation()
        val annotation = NormalAnnotationExpr()
        annotation.setName(name)
        a.annotation = annotation
        classe.addAnnotation(annotation)
        initNode(a, init)
    }

    override fun toString(): String {
        return classe.toString()
    }
}

class InterfaceDecl: JavaNode() {
    lateinit var  interfaceDeclaration :ClassOrInterfaceDeclaration

}

class Method: JavaNode() {
    lateinit var method : MethodDeclaration

    fun javadoc(text: String = "", init: JavaDoc.() -> Unit) {
        val javadoc = JavaDoc()
        initNode(javadoc, init)
        javadoc.javadocDescription.addElement(JavadocSnippet(text))
        method.setJavadocComment(javadoc.javadoc)
    }

    fun param(name: String, type: String) {
        method.addParameter(type, name)
    }

    fun body(init: Block.() -> Unit) {
        val b = Block()
        b.stmnt = BlockStmt()
        method.setBody(b.stmnt as BlockStmt)
        initNode(b, init)
    }

    fun field (type: String, name: String, initialValue: Any? = null, vararg modifiers: Modifier, init: KField.() -> Unit){
        val f = KField()
    }
}

class EnumDecl: JavaNode() {
    lateinit var enum: EnumDeclaration

    fun entry (name: String) {
        enum.addEntry(EnumConstantDeclaration(name))
    }

    fun javadoc(text: String, init: JavaDoc.() -> Unit) {
        val javadoc = JavaDoc()
        initNode(javadoc, init)
        javadoc.javadocDescription.addElement(JavadocSnippet(text))
        enum.setJavadocComment(javadoc.javadoc)
    }

}

class Param: JavaNode() {
    val param = Parameter()
}

class KAnnotation(): JavaNode() {
    lateinit var annotation: NormalAnnotationExpr

    fun param(name: String, value: String) {
        val nameValue = MemberValuePair(name, StringLiteralExpr(value))
        annotation.addPair(name, NameExpr(value))
    }
}

class KField(): JavaNode() {
    lateinit var field: FieldDeclaration

    fun markerAnnotation(name: String) {
        field.addMarkerAnnotation(name)
    }

    fun annotation(name: String, init: KAnnotation.() -> Unit) {
        val a = KAnnotation()
        val annotation = NormalAnnotationExpr()
        annotation.setName(name)
        a.annotation = annotation
        field.addAnnotation(annotation)
        initNode(a, init)
    }

    fun generateGetter() {
        field.createGetter()
    }

    fun generateSetter() {
        field.createSetter()
    }

    fun generateGetterAndSetter() {
        generateGetter()
        generateSetter()
    }
}