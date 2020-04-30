package ru.madbrains.listexample

open class Chord //объявление корневого класса

open class Mammal : Chord() { //объявление наследника
    open fun legs(): Int { return 4 } //объявление метода
}

open class Primate : Mammal() {
    override fun legs(): Int { return 2 } //переопределение метода (изменение свойств)
    fun hands(): Int { return 2 } //объявление метода
}

open class Human (val name: String): Primate()

fun main () {
    val alexey = Human("Alexey") //создание объекта определенного класса
    print(alexey.name) //использование объекта
}