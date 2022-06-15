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

val t = Test()
println(if (t.uniqueobject() === t.uniqueobject()) "eq" else "uneq")