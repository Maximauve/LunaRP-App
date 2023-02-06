package com.example.lunarp

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.lunarp.databinding.ActivityLoginBinding
import com.example.lunarp.ui.main.SectionsPagerAdapter
import com.example.lunarp.databinding.ActivityMainBinding
import com.example.lunarp.databinding.ActivityRegisterBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginBinding = Login()

        var user_mail = findViewById(R.id.et_email) as EditText
        var password = findViewById(R.id.et_password) as EditText
        var btn_reset = findViewById(R.id.btn_reset) as Button
        var btn_submit = findViewById(R.id.btn_submit) as Button

        btn_reset.setOnClickListener {
            // clearing user_name and password edit text views on reset button click
            user_mail.setText("")
            password.setText("")
        }

        // set on-click listener
        btn_submit.setOnClickListener {
            val user_mail = user_mail.text;
            val password = password.text;
            Toast.makeText(this@MainActivity, user_mail, Toast.LENGTH_LONG).show()

            // your code to validate the user_name and password combination
            // and verify the same

        }

        /*val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = binding.fab*/

        /*fab.setOnClickListener { view ->
            Snackbar.make(view, "Roll Dice settings", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
    }
}