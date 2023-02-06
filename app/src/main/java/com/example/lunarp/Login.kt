package com.example.lunarp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
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
            val user_name = user_mail.text;
            val user_mail = user_mail.text;
            val password = password.text;
            Toast.makeText(this@Login, user_mail, Toast.LENGTH_LONG).show()

            // your code to validate the user_name and password combination
            // and verify the same

        }
    }
}