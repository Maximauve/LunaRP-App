package com.example.lunarp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
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
import java.util.*

private const val MENU_ID_SETTINGS= 1
private val MENU_ID_PROFIL = 2
private val MENU_ID_LOGOUT = 3
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0,MENU_ID_SETTINGS,0,"Paramètres")
        menu.add(0,MENU_ID_PROFIL,0,"Profile")
        menu.add(0,MENU_ID_LOGOUT,0,"Se déconnecter")
        return super.onCreateOptionsMenu(menu)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = binding.fab
        val test = binding.etTest

        if (!SessionManager.isLogin()){
            val login = Intent(this, Login::class.java)
            startActivity(login)
            finish()
        }

        test.text = SessionManager.userToken

        fab.setOnClickListener { view ->
            SessionManager.logOut()
            finish();
            startActivity(intent);
            Snackbar.make(view, "Roll Dice settings", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            MENU_ID_SETTINGS-> {
                Toast.makeText(this@MainActivity, "Accès au paramètre bientôt disponible", Toast.LENGTH_LONG).show()

            }
            MENU_ID_PROFIL -> {
                Toast.makeText(this@MainActivity, "Accès au profil bientôt disponible", Toast.LENGTH_LONG).show()

            }
            MENU_ID_LOGOUT -> {
                SessionManager.logOut()
                finish();
                startActivity(intent);
                Toast.makeText(this@MainActivity, "Accès à la déconnexion bientôt disponible", Toast.LENGTH_LONG).show()

            }
        }
        return super.onOptionsItemSelected(item)
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
                val responseBody = response.body()

                val myStringBuilder = StringBuilder()
                if (responseBody != null) {
                    for (myData in responseBody){
                        myStringBuilder.append(myData.id)
                        myStringBuilder.append("\n")
                    }
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