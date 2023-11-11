package com.example.tugasretrofit

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

    // Binding untuk layout ActivityMain
    private lateinit var binding: ActivityMainBinding

    // Daftar hero
    private lateinit var superHeroList: ArrayList<SuperHeroData>

    // RecyclerView untuk menampilkan daftar hero
    private lateinit var recycle: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menginisialisasi binding untuk layout ActivityMain
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Mendapatkan instance dari ApiClient
        val client = ApiClient.getInstance()

        // Inisialisasi daftar hero
        superHeroList = arrayListOf()

        // Inisialisasi adapter untuk RecyclerView
        val superHeroAdapter = SuperHeroAdapter(this@MainActivity, superHeroList)

        // Mengatur layout manager dan adapter untuk RecyclerView
        binding.rvKarakter.layoutManager=LinearLayoutManager(this@MainActivity)
        binding.rvKarakter.adapter=superHeroAdapter


        // Melakukan permintaan ke API untuk mendapatkan data hero
        with(binding) {
            ApiClient.getInstance().getSuperHero().enqueue(object : Callback<SuperHeroModel> {
                override fun onResponse(
                    call: Call<SuperHeroModel>,
                    response: Response<SuperHeroModel>
                ) {
                    val thisResult = response.body()
                    println(thisResult)
                    val datas = thisResult?.results

                    // Memastikan data tidak null sebelum memproses
                    if (datas != null) {

                        // Menambahkan setiap pahlawan ke dalam daftar
                        for (data in datas) {
                            superHeroList.add(data)
                        }

                    }
                    // Membuat adapter baru setelah mendapatkan data hero
                    val superHeroAdapter = SuperHeroAdapter(this@MainActivity, superHeroList)

                   // Mengatur layout manager dan adapter untuk RecyclerView setelah melakukan response untuk mendapatkan data hero
                    binding.rvKarakter.layoutManager=LinearLayoutManager(this@MainActivity)
                    binding.rvKarakter.adapter=superHeroAdapter
                }

                // Menampilkan pesan kesalahan jika koneksi eror
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
