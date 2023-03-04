package com.example.lunarp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.lunarp.databinding.ActivityMainBinding
import com.example.lunarp.item.ItemClassItem
import com.example.lunarp.item.ItemInterface
import com.example.lunarp.ui.main.SectionsPagerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!SessionManager.isLogin()){
            val login = Intent(this, Login::class.java)
            startActivity(login)
            finish()
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = binding.fab
        val test = binding.etTest

        test.text = SessionManager.userMail

        fab.setOnClickListener { view ->
            connectionTest()
            // RequestUtils.connection()
            Snackbar.make(view, "Roll Dice settings", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
    val BASE_URL_TEST = "https://jsonplaceholder.typicode.com/"
    val URL_API_TOTO = "http://192.168.1.60:3000/"
    fun connectionTest(){

        println(":::::::::: TEST ::::::::::")


        var retrofit = RequestUtils.retrofitBase.create(ItemInterface::class.java)
        val retrofitData = retrofit.getData()
        println("RESPONSE : ----------------------------->")

        retrofitData.enqueue(object : Callback<List<ItemClassItem>?> {
            override fun onResponse(
                call: Call<List<ItemClassItem>?>,
                response: retrofit2.Response<List<ItemClassItem>?>
            ) {
                val responseBody = response.body()!!

                val myStringBuilder = StringBuilder()
                for (myData in responseBody){
                    myStringBuilder.append(myData.id)
                    myStringBuilder.append("\n")
                }
                val test = binding.etTest
                test.text= myStringBuilder
            }

            override fun onFailure(call: Call<List<ItemClassItem>?>, t: Throwable) {
                Log.d("MainACtivity", "onfailure: "+ t.message )
            }
        })
    }

}