package ru.madbrains.listexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRealm()

        getCatsFromServer()

        showListFromDB()
    }

    private fun initRealm() {
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            //при изменении конфигурации база данных будет пересоздаваться
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
    }

    private fun getCatsFromServer() {
        getApi().getCats().enqueue(callback)
    }

    private val callback = object : Callback<ResponseBody> {
        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            t.printStackTrace()
        }

        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            val responseText = response.body()?.string()
            responseText?.let {
                val catList = parseResponse(it)

                saveIntoDB(catList)
                showListFromDB()
            }
        }
    }

    fun saveIntoDB(cats: List<Cat>) {
        //получаем ссылку на базу данных
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        //сохраняем список котов в базе данных в транзакции
        realm.copyToRealm(cats)
        realm.commitTransaction()
    }

    fun loadFromDB(): List<Cat> {
        //получаем ссылку на базу данных
        val realm = Realm.getDefaultInstance()
        return realm.where(Cat::class.java).findAll()
    }

    fun showListFromDB() {
        val cats = loadFromDB()
        setList(cats)
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

    private fun getApi(): CatApi {
        val retrofit = Retrofit.Builder()
            //Базовая часть адреса
            .baseUrl("https://api.myjson.com")
            .build()

        val api = retrofit.create(CatApi::class.java)
        return api
    }
}