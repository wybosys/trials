interface IA {

    fun proc()

}

class Test {

    fun uniqueobject():IA {
        return object:IA {
            override fun proc() {
                
            }
        }
    }

    companion object {

    }


}

class T {
    
    companion object TEST {

        fun Proc() {
            println("TEST:Proc")
        }

    }

}

val t = Test()
println(if (t.uniqueobject() === t.uniqueobject()) "eq" else "uneq")
T.TEST.Proc()

println(null + "abc")
