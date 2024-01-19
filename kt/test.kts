#!/usr/bin/env kotlin

open class A {

}

open class B : A() {

}

class C {

    fun process(a: A): String {
        println("A")
        return "A"
    }

    fun process(b: B): String {
        println("B")
        return "B"
    }

}

fun C.process2(a: A) = process(a)

inline fun <reified T: A> C.process3(a: T) = process(a)
inline fun <reified T: B> C.process3(a: T) = process(a)

fun main() {
    println("Hello Kotlin!")

    val a = A()
    val b = B()
    val c = C()
    require(c.process(a) == "A")
    require(c.process(b) == "B")
    require(c.process2(a) == "A")
    require(c.process2(b) == "A")
    require(c.process3(a) == "A")
    require(c.process3(b) == "B")
}

main()
