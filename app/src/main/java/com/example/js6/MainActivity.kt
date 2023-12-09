package com.example.js6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.js6.ResponsMorty
import com.example.js6.ResponsMorty
import com.example.js6.api.ApiConfig
import org.chromium.base.Callback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val morty = findViewById<RecyclerView>(R.id.rv_morty)
        ApiConfig.getService().getMorty().enqueue(object : Callback<ResponsMorty>{
            override fun onResponse(call: Call<ResponsMorty>, response: Response<ResponsMorty>) {
                if (response.isSuccessful){
                    val responseMorty = response.body()
                    val dataMorty = responseMorty?.results
                    val mortyAdapter = MortyAdapter(dataMorty)
                    morty.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        setHasFixedSize(true)
                        mortyAdapter.notifyDataSetChanged()
                        adapter = mortyAdapter
                    }
                }
            }

            override fun onFailure(call: Call<ResponsMorty>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }


        })

    }
}