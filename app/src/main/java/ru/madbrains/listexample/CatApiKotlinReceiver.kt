package ru.madbrains.listexample

import java.net.URL

fun main() {
    val catFactsContent = URL("https://raw.githubusercontent.com/gumam/android_course/master/db.json").readText()
    print(catFactsContent)
}