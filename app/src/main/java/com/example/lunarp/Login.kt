package com.example.lunarp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var user_mail = findViewById<EditText>(R.id.et_email)
        var password = findViewById<EditText>(R.id.et_password)
        var btn_submit = findViewById<Button>(R.id.btn_submit)
        var link_register = findViewById<TextView>(R.id.link_register)
        // set on-click listener
        btn_submit.setOnClickListener {
            val userName = user_mail.text;
            val userMail = user_mail.text;
            val password = password.text;
            if (userMail.isNotEmpty() && password.isNotEmpty()){
                SessionManager.logIn(userMail.toString(), password.toString());
            }
            Toast.makeText(this@Login, SessionManager.toString(), Toast.LENGTH_LONG).show()

            finish()
            // your code to validate the user_name and password combination
            // and verify the same

        }

        link_register.setOnClickListener{
            val register = Intent(this, Register::class.java)
            startActivity(register)
            finish()
        }
    }
}