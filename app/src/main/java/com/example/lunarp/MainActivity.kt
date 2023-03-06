package com.example.lunarp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        setupViewPager(binding.viewPager)
        binding.tabs.setupWithViewPager(binding.viewPager)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = binding.fab
        val profileIcon = binding.profileImage

        if (!SessionManager.isLogin()){
            val login = Intent(this, Login::class.java)
            startActivity(login)
            finish()
        }

        profileIcon.setOnClickListener{view ->

            SessionManager.logOut()
            finish();
            startActivity(intent);

        }
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

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(CharacterFragment(), "Personnages")
        adapter.addFragment(CampaignFragment(), "Mes Campagnes")
        viewPager.adapter = adapter
    }

    internal class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private val mFragmentList = mutableListOf<Fragment>()
        private val mFragmentTitleList = mutableListOf<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }
    }

}