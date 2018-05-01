package com.martinpaquin.javadsl

import com.github.javaparser.ast.Modifier
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JavaDslTestJUnit5 {

    @Test
    fun createJavaClassTest() {
        DslConfiguration.indent = "    "
        println(compilationUnit("com.martinpaquin.test1") {
            import("com.github.javaparser.ast.CompilationUnit")
            classe("FirstClass") {
                javadoc("This is my first class create with JavaDsl") {
                    author("Martin Paquin")
                }
                field("String", "first", Modifier.PRIVATE) {}
                method("setField", "void", Modifier.PUBLIC) {
                    javadoc() {
                        param("firstName")
                        returnTag("first value")
                    }
                    param("field", "String")
                    body {
                        //assign(/*("this.name" = "name") */)
                    }
                }
            }
        }.toString())
    }

    @Test
    fun createCompilationUnitWithEnum() {
        compilationUnit("com.martinpaquin.test1") {
            enum("RainbowColor") {
                javadoc("Rainbow Colors enumration") {
                    author("Martin Paquin")
                }
                entry("RED")
                entry("ORANGE")
                entry("YELLOW")
                entry("GREEN")
                entry("BLUE")
            }
        }.toString()
    }

    @Test
    fun testHospitalJpaEntity() {
        println(compilationUnit("com.freschelegacy.file.hospital") {
            import("java.io.Serializable")
            import("com.freschelegacy.file.doctor.Doctor")
            import("com.freschelegacy.file.ward.Ward")
            import("org.apache.commons.lang3.builder.ReflectionToStringBuilder")
            import("javax.persistence.Version")
            import("com.freschelegacy.model.CountryEnum")
            import("com.freschelegacy.file.workfile.WorkFile")
            import("javax.persistence.Transient")
            import("avax.persistence.Table")
            import("javax.persistence.OneToMany")
            import("javax.validation.constraints.NotNull")
            import("javax.persistence.Id")
            import("javax.persistence.FetchTyp")
            import("org.joda.time.DateTime")
            import("com.freschelegacy.model.JodaLocalTimeConverter")
            import("javax.persistence.Entity")
            import("javax.persistence.Convert")
            import("javax.persistence.Column")
            import("java.util.List")
            import("org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE", isStatic = true)
            import("org.joda.time.LocalTime")
            import("com.freschelegacy.model.JodaDateTimeConverter")
            import("java.math.BigDecimal")
            import("java.util.ArrayList")
            import("com.freschelegacy.model.CountryConverter")
            classe("Hospital", Modifier.PUBLIC) {
                implements("Serializable")
                javadoc("""Entity file for Hospital (TSACREP).
                    File Attribute: REF.
                """.trimMargin()) {
                    author("X2EGenerator")
                }
                markerAnnotation("Entity")
                annotation("table") {
                    param("name", "\"Hospital\"")
                    param("schema", "\"HospitalMgmt\"")
                }
                field("long","serialVersionUID", 1L, Modifier.STATIC, Modifier.FINAL, Modifier.PRIVATE) {}
                field("int", "name", 0, Modifier.PRIVATE) {
                    markerAnnotation("version")
                    annotation("Column") {
                        param("name", "\"version\"")
                    }
                    markerAnnotation("NotNull")
                    generateGetterAndSetter()
                }
                field("List<Doctor>", "doctors", Modifier.PRIVATE) {
                    annotation("OneToMany") {
                        param("mappedBy", "hospitalObj")
                        param("fetch", "FetchType.LAZY")
                    }
                    generateGetterAndSetter()
                }
                method("addWard", "void", Modifier.PUBLIC) {
                    param("ward", "Ward")
                    body {
                        ifStmt {
                            condition("hospital == null")
                            then {
                                exprStmt {
                                    expression {
                                        nameExpression("Martin"){}
                                    }
                                }
                            }
                        }
                        exprStmt {
                            expression {
                                binaryEpression {
                                    left {
                                        nameExpression("Tom"){}
                                    }
                                    operator(BinaryExprOperator.EQUALS)
                                    right {
                                        nameExpression("\"NewName\""){}
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }.toString())
    }
}