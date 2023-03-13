package com.example.lunarp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lunarp.character.CharacterListAdapter
import com.example.lunarp.databinding.ActivityMainBinding
import com.example.lunarp.fragment.CampaignFragment
import com.example.lunarp.fragment.CharacterFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout

private const val MENU_ID_SETTINGS= 1
private val MENU_ID_PROFIL = 2
private val MENU_ID_LOGOUT = 3
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
        val tabs: TabLayout = binding.tabs
        val viewPager = binding.viewPager

        setSupportActionBar(binding.toolbar)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(CharacterFragment(), "Personnages")
        adapter.addFragment(CampaignFragment(), "Campagnes")

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        val fab: FloatingActionButton = binding.fab
        val profileIcon = binding.profileImage

        profileIcon.setOnClickListener{view ->
            SessionManager.logOut()
            finish();
            startActivity(intent);

        }

        var adapterCharacter = CharacterListAdapter(this)
        val currentFragment = supportFragmentManager
            .findFragmentByTag("android:switcher:" + viewPager.id + ":" + tabs.selectedTabPosition)
        if (currentFragment is CharacterFragment) {
            val rvCharacter = currentFragment.view?.findViewById<RecyclerView>(R.id.rv)
            rvCharacter!!.adapter = adapterCharacter
            rvCharacter.layoutManager = GridLayoutManager(this,1)
        }


        fab.setOnClickListener { view ->
            println("Tab position")
            println(tabs.selectedTabPosition)

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


}