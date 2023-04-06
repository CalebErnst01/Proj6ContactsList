package com.example.proj6contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity()
{
    private lateinit var contactList : MutableList<String>
    private lateinit var fullNames : MutableList<String>
    private lateinit var phoneNumbers : MutableList<String>
    private lateinit var rvCons : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactList = mutableListOf()
        rvCons = findViewById(R.id.listview)

        getContacts()
    }

    private fun getContacts()
    {
        val client = AsyncHttpClient()

        client["https://randomuser.me/api/", object : JsonHttpResponseHandler() {
            override fun onSuccess(StatusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON)
            {
                val pictureArray = json.jsonObject.getJSONArray("picture/thumbnail")
                val nameArray = json.jsonObject.getJSONArray("name")
                val numArray = json.jsonObject.getJSONArray("phone")

                for(i in 0 until pictureArray.length()) //all arrays of same length
                {
                    contactList.add(pictureArray.getString(i))
                    fullNames.add(nameArray.getString(i))
                    phoneNumbers.add(numArray.getString(i))
                }

                var Adapt = Adapter(contactList, fullNames, phoneNumbers)
                rvCons.adapter = Adapt
                rvCons.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(statusCode: Int, headers: Headers?, errorResponse: String, throwable: Throwable?)
            {
                Log.d("Issue connecting to API", errorResponse)
            }
        }]
    }
}