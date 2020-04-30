package ru.madbrains.listexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setList()
    }

    private fun setList() {
        val colors = listOf(
            "blue",
            "black",
            "green",
            "red",
            "white",
            "gray",
            "pink",
            "yellow",
            "orange",
            "light gray",
            "violet",
            "brown",
            "purple",
            "magenta"

        )
        val adapter = ColorAdapter(colors)
        recyclerId.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        recyclerId.layoutManager = layoutManager
    }
}