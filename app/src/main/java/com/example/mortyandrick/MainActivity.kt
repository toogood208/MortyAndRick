package com.example.mortyandrick

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mortyandrick.databinding.ActivityMainBinding
import com.example.mortyandrick.helpers.MortyAdapter
import com.example.mortyandrick.models.MortyRick
import com.example.mortyandrick.services.MortyRickService
import com.example.mortyandrick.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
    }

   private fun loadData(){
       //initiate the service
       val destinationService  = ServiceBuilder.buildService(MortyRickService::class.java)
       val requestCall =destinationService.getMortyAndRickData()

       //make network call asynchronously
       requestCall.enqueue(object : Callback<MortyRick>{
           override fun onFailure(call: Call<MortyRick>, t: Throwable) {
               Toast.makeText(this@MainActivity, "Something went wrong $t", Toast.LENGTH_SHORT).show()
           }

           override fun onResponse(call: Call<MortyRick>, response: Response<MortyRick>) {
               Log.d("Response", "onResponse: ${response.body()}")
               if (response.isSuccessful){
                   val mortyList  = response.body()!!
                   Log.d("Response", "mortylist size : ${mortyList.results.size}")
                   binding.mortyRecycler.apply {
                       setHasFixedSize(true)
                       layoutManager = LinearLayoutManager(this@MainActivity)
                       adapter = MortyAdapter(response.body()!!.results)
                   }
               }else{
                   Toast.makeText(this@MainActivity, "Something went wrong ${response.message()}", Toast.LENGTH_SHORT).show()
               }
           }

       })

   }
}