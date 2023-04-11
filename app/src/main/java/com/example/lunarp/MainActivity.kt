package com.example.lunarp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lunarp.character.CharacterListAdapter
import com.example.lunarp.databinding.ActivityMainBinding
import com.example.lunarp.fragment.CampaignFragment
import com.example.lunarp.fragment.CharacterFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

private const val MENU_ID_SETTINGS= 1
private val MENU_ID_PROFIL = 2
private val MENU_ID_LOGOUT = 3
class MainActivity : AppCompatActivity() {

    var isOpen = false
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!SessionManager.isLogin()){
            val login = Intent(this, Login::class.java)
            startActivity(login)
            finish()
        }

        val fab_close = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_close);
        val fab_open = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_open);

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
        val fab_throw = binding.fabThrow
        val fab_reset = binding.fabReset
        val fab_dice4 = binding.fabDice4
        val fab_dice6= binding.fabDice6;
        val fab_dice8 = binding.fabDice8
        val fab_dice10 = binding.fabDice10
        val fab_dice12 = binding.fabDice12
        val fab_dice20 = binding.fabDice20
        val fab_dice100 = binding.fabDice100
        var total : Int = 0
        var diceChosen = (mutableListOf<Int>())
        val profileIcon = binding.profileImage

        profileIcon.setOnClickListener{view ->
            SessionManager.logOut()
            finish();
            startActivity(intent);

        }

        var adapterCharacter = CharacterListAdapter(this, MainActivity())
        val currentFragment = supportFragmentManager
            .findFragmentByTag("android:switcher:" + viewPager.id + ":" + tabs.selectedTabPosition)
        if (currentFragment is CharacterFragment) {
            val rvCharacter = currentFragment.view?.findViewById<RecyclerView>(R.id.rv)
            rvCharacter!!.adapter = adapterCharacter
            rvCharacter.layoutManager = GridLayoutManager(this,1)
        }


        fab.setOnClickListener {
            if (isOpen) {
                fab_dice4.startAnimation(fab_close);
                fab_dice6.startAnimation(fab_close);
                fab_dice8.startAnimation(fab_close)
                fab_dice10.startAnimation(fab_close)
                fab_dice12.startAnimation(fab_close)
                fab_dice20.startAnimation(fab_close)
                fab_dice100.startAnimation(fab_close)
                fab_throw.startAnimation(fab_close)
                fab_reset.startAnimation(fab_close)
                //fab.startAnimation(fab_anticlock);
                fab_dice4.isClickable = false;
                fab_dice6.isClickable = false;
                fab_dice8.isClickable = false
                fab_dice10.isClickable = false
                fab_dice12.isClickable = false
                fab_dice20.isClickable = false
                fab_dice100.isClickable = false
                fab_throw.isClickable = false
                fab_reset.isClickable = false
                isOpen = false
            } else {
                fab_dice4.startAnimation(fab_open);
                fab_dice6.startAnimation(fab_open);
                fab_dice8.startAnimation(fab_open)
                fab_dice10.startAnimation(fab_open)
                fab_dice12.startAnimation(fab_open)
                fab_dice20.startAnimation(fab_open)
                fab_dice100.startAnimation(fab_open)
                fab_throw.startAnimation(fab_open)
                fab_reset.startAnimation(fab_open)
                //fab.startAnimation(fab_anticlock);
                fab_dice4.isClickable = true;
                fab_dice6.isClickable = true;
                fab_dice8.isClickable = true;
                fab_dice10.isClickable = true;
                fab_dice12.isClickable = true;
                fab_dice20.isClickable = true;
                fab_dice100.isClickable = true;
                fab_throw.isClickable = true
                fab_reset.isClickable = true
                isOpen = true
            }
        }
        fab_dice4.setOnClickListener {
            diceChosen+=4
            Toast.makeText(applicationContext, "Un dé 4 en plus", Toast.LENGTH_SHORT).show()
        }
        fab_dice6.setOnClickListener {
            diceChosen+=6
            Toast.makeText(applicationContext, "Un dé 6 en plus", Toast.LENGTH_SHORT).show()
        }
        fab_dice8.setOnClickListener {
            diceChosen+=8
            Toast.makeText(applicationContext, "Un dé 8 en plus", Toast.LENGTH_SHORT).show()
        }
        fab_dice10.setOnClickListener {
            diceChosen+=10
            Toast.makeText(applicationContext, "Un dé 10 en plus", Toast.LENGTH_SHORT).show()
        }
        fab_dice12.setOnClickListener {
            diceChosen+=12
            Toast.makeText(applicationContext, "Un dé 12 en plus", Toast.LENGTH_SHORT).show()
        }
        fab_dice20.setOnClickListener {
            diceChosen+=20
            Toast.makeText(applicationContext, "Un dé 20 en plus", Toast.LENGTH_SHORT).show()
        }
        fab_dice100.setOnClickListener {
            diceChosen+=100
            Toast.makeText(applicationContext, "Un dé 100 en plus", Toast.LENGTH_SHORT).show()
        }
        fab_throw.setOnClickListener {
            var randomNumbers = mutableListOf<Int>()
            for (nb in diceChosen) {
                var x = (1..nb).random()
                randomNumbers.add(x)
                total += x
            }
            Toast.makeText(applicationContext, "Dé choisis : ${diceChosen.joinToString(" - ")} \n" +
                    "${randomNumbers.joinToString(" + ")} = $total", Toast.LENGTH_SHORT).show()
            total = 0
            diceChosen.clear()
        }
        fab_reset.setOnClickListener {
            diceChosen.clear()
            Toast.makeText(applicationContext, "Effacement des dé sélectionnés ! ", Toast.LENGTH_SHORT).show()
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