package com.example.retrofit_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rick = findViewById<RecyclerView>(R.id.rv_character)

        ApiConfig.getService().getRick().enqueue(object : retrofit2.Callback<ResponseRick> {
            override fun onResponse(call: Call<ResponseRick>, response: retrofit2.Response<ResponseRick>){
                if (response.isSuccessful){
                    val responseRick = response.body()
                    val dataRick = responseRick?.results
                    val rickAdapter = RickAdapter(dataRick)
                    rick.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        setHasFixedSize(true)
                        rickAdapter.notifyDataSetChanged()
                        adapter = rickAdapter
                    }
                }
            }

            override fun onFailure (call: Call<ResponseRick>, t:Throwable){
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
        )
    }
}