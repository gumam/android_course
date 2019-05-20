package ru.madbrains.listexample

interface Speech {

    /*  определяет, что любой класс,
    который реализует интерфейс Speech
    должен реализовать функцию sayHello() */
    fun sayHello()
}

class Human : Speech {
    override fun sayHello() {
        print("Hello!")
    }
}

class Dog : Speech {
    override fun sayHello() {
        print("Woof")
    }
}

fun main() {

    val human = Human()
    human.sayHello()

    val dog = Dog()
    dog.sayHello()
}



