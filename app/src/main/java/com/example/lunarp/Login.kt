package com.example.lunarp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.lunarp.user.UserClassItem
import com.example.lunarp.user.UserInterface
import com.example.lunarp.user.UserLoginClassItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var user_mail_ = findViewById<EditText>(R.id.et_email)
        var password_ = findViewById<EditText>(R.id.et_password)
        var btn_submit_ = findViewById<Button>(R.id.btn_submit)
        var link_register_ = findViewById<TextView>(R.id.link_register)
        // set on-click listener
        btn_submit_.setOnClickListener {
            val userName = user_mail_.text.toString();
            val userMail = user_mail_.text.toString();
            val password = password_.text.toString();
            if (userMail.isNotEmpty() && password.isNotEmpty()){
                signIn(userMail, password)
            }
            // your code to validate the user_name and password combination
            // and verify the same

        }

        link_register_.setOnClickListener{
            val register = Intent(this, Register::class.java)
            startActivity(register)
            finish()
        }
    }

    fun signIn(mail: String, password: String){
        println(":::::::::: TEST ::::::::::")

        var retrofit = RequestUtils.retrofitBase.create(UserInterface::class.java)
        val user = UserLoginClassItem(email = mail, password = password)
        println("BREFORE !!!!!!!!!!")
        var retrofitData = retrofit.login(user)
        retrofitData.enqueue(object: Callback<UserClassItem> {
            override fun onResponse(call: Call<UserClassItem>, response: Response<UserClassItem>) {
                println("REPONSE ----------> ${response.code()}" )
                if (response.code() == 401) {
                    Toast.makeText(this@Login, "Tu n'as pas de compte !", Toast.LENGTH_LONG).show()
                } else {
                    SessionManager.logIn(mail, password);
                    finish()
                }
            }

            override fun onFailure(call: Call<UserClassItem>, t: Throwable) {
                Log.d("LoginActivity", "onfailure: "+ t.message )
            }
        })
    }
}