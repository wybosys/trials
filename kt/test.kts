#!/usr/bin/env kotlin

open class A {

}

open class B : A() {

}

open class E: B() {

}

open class C {

    fun process(a: A): String {
        println("A")
        return "A"
    }

    fun process(b: B): String {
        println("B")
        return "B"
    }

}

class D : C() {

    fun process(e: E): String {
        println("E")
        return "E"
    }
}

fun C.process2(a: A) = process(a)

/*
inline fun <reified T: A, TC: C> TC.process3(a: T) = process(a)
inline fun <reified T: B, TC: C> TC.process3(a: T) = process(a)
inline fun <reified T: E, TC: D> TC.process3(a: T) = process(a)
*/

// 等价
inline fun <reified T: A> C.process3(a: T) = process(a)
inline fun <reified T: B> C.process3(a: T) = process(a)
inline fun <reified T: E> D.process3(a: T) = process(a)

fun main() {
    println("Hello Kotlin!")

    val a = A()
    val b = B()
    val c = C()
    val d = D()
    val e = E()
    require(c.process(a) == "A")
    require(c.process(b) == "B")
    require(c.process2(a) == "A")
    require(c.process2(b) == "A")
    require(c.process3(a) == "A")
    require(c.process3(b) == "B")
    require(d.process3(e) == "E")
}

main()
