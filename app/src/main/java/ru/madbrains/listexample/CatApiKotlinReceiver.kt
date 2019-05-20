package ru.madbrains.listexample

import java.net.URL

fun main() {
    val catFactsContent = URL("https://api.myjson.com/bins/w4bj6").readText()
    print(catFactsContent)
}