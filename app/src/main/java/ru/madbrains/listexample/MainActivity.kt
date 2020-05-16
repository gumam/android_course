package ru.madbrains.listexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private val url = "https://raw.githubusercontent.com/gumam/android_course/master/db.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRealm()

        val queue = Volley.newRequestQueue(this)
        getCatsFromServer(queue)

        showListFromDB()
    }

    private fun getCatsFromServer(queue: RequestQueue) {
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener { response ->
                val catList = parseResponse(response)

                saveIntoDB(catList)
                showListFromDB()
            },
            Response.ErrorListener {
                Toast.makeText(this, "Ошибка запроса", Toast.LENGTH_SHORT).show()
            }
        )

        queue.add(stringRequest)
    }

    private fun saveIntoDB (cats: List<Cat>) {
        //получаем ссылку на базу данных
        val realm = Realm.getDefaultInstance()
        //сохраняем список котов в базе данных в транзакции
        realm.beginTransaction()
        realm.copyToRealm(cats)
        realm.commitTransaction()
    }

    private fun loadFromDB (): List<Cat> {
        //получаем ссылку на базу данных
        val realm = Realm.getDefaultInstance()
        return realm.where(Cat::class.java).findAll()
    }

    private fun showListFromDB () {
        val cats = loadFromDB()
        setList(cats)
    }

    private fun initRealm() {
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            //при изменении конфигурации база данных будет пересоздаваться
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
    }

    private fun parseResponse(responseText: String): List<Cat> {
        //создаем пустой список объектов класса Cat
        val catList: MutableList<Cat> = mutableListOf()
        //преобразуем текст ответа сервера в JSON массива
        val jsonArray = JSONArray(responseText)
        //в цикле по количеству элементов массива JSON объектов
        for (index in 0 until jsonArray.length()) {
            // получаем каждый элемент в виде JSON объекта
            val jsonObject = jsonArray.getJSONObject(index)
            //получаем значение параметра text
            val catText = jsonObject.getString("text")
            //получаем значение параметра image
            val catImage = jsonObject.getString("image")
            //создаем объект класса Cat с вышеполученными параметрами
            val cat = Cat()
            cat.text = catText
            cat.image = catImage

            catList.add(cat) //добавляем в список
        }
        return catList //возвращаемое значение функции
    }

    private fun setList(cats: List<Cat>) {
        val adapter = CatAdapter(cats)
        recyclerViewId.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        recyclerViewId.layoutManager = layoutManager
    }
}