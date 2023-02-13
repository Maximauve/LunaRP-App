package com.example.lunarp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var user_name = findViewById<EditText>(R.id.et_user_name)
        var user_mail = findViewById<EditText>(R.id.et_email)
        var password = findViewById<EditText>(R.id.et_password)
        var confirmPassword = findViewById<EditText>(R.id.et_confirm_password)
        var btn_submit = findViewById<Button>(R.id.btn_submit)
        var link_login = findViewById<TextView>(R.id.link_login)

        // set on-click listener
        btn_submit.setOnClickListener {
            val user_name = user_name.text;
            val user_mail = user_mail.text;
            val password = password.text;
            val confirmPassword = confirmPassword.text;
            Toast.makeText(this@Register, user_name, Toast.LENGTH_LONG).show()
            Toast.makeText(this@Register, user_mail, Toast.LENGTH_LONG).show()

            // your code to validate the user_name and password combination
            // and verify the same

        }

        link_login.setOnClickListener {
            val login = Intent(this, Login::class.java)
            startActivity(login)
            finish()
        }
    }
}