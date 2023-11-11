package com.example.tugasretrofit

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasretrofit.databinding.ActivityMainBinding
import com.example.tugasretrofit.model.SuperHeroData
import com.example.tugasretrofit.model.SuperHeroModel
import com.example.tugasretrofit.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var superHeroList: ArrayList<SuperHeroData>
    private lateinit var recycle: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val client = ApiClient.getInstance()
        superHeroList = arrayListOf()

        with(binding) {
            ApiClient.getInstance().getSuperHero().enqueue(object : Callback<SuperHeroModel> {
                override fun onResponse(
                    call: Call<SuperHeroModel>,
                    response: Response<SuperHeroModel>
                ) {
                    val thisResult = response.body()
                    println(thisResult)
                    val datas = thisResult?.results
                    if (datas != null) {
                        for (data in datas) {
                            superHeroList.add(data)
                        }

                    }
                    // Inisialisasi adapter setelah mendapatkan data dari API
                    val superHeroAdapter = SuperHeroAdapter(this@MainActivity, superHeroList)
//                        recycle.adapter = superHeroAdapter
                    binding.lvKarakter.layoutManager=LinearLayoutManager(this@MainActivity)
                    binding.lvKarakter.adapter=superHeroAdapter
                }

                override fun onFailure(call: Call<SuperHeroModel>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity, "Error Connection",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        }
    }
}
