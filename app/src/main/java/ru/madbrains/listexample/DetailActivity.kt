package ru.madbrains.listexample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val CAT_FACT_TEXT = "cat_fact_text"

        fun openDetailActivity(context: Context, catFactText: String) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(CAT_FACT_TEXT, catFactText)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            title = "Detail"
        }

        intent?.extras?.getString(CAT_FACT_TEXT)?.let {
            textViewId.text = it
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
