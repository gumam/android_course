package ru.madbrains.listexample

interface Walking {

    fun doStep()
}

fun main() {

    //объект анонимного класса
    val human = object : Walking {
        override fun doStep() {
            print("step")
            //do something
        }
    }

    human.doStep()
}



