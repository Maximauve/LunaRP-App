package com.example.lunarp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.lunarp.character.Character
import com.example.lunarp.character.CharacterInterface
import com.example.lunarp.user.UserClassItem
import com.example.lunarp.user.UserInterface
import com.example.lunarp.user.UserLoginClassItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class Login : AppCompatActivity() {
    var errorMessage = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        val main = Intent(this, MainActivity::class.java)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val user_mail_ = findViewById<EditText>(R.id.et_email)
        val password_ = findViewById<EditText>(R.id.et_password)
        val btn_submit_ = findViewById<Button>(R.id.btn_submit)
        val link_register_ = findViewById<TextView>(R.id.link_register)

        btn_submit_.setOnClickListener {
            val userMail = user_mail_.text.toString();
            val password = password_.text.toString();

            if (user_mail_.text.isEmpty() || password_.text.isEmpty()){
                Toast.makeText(this@Login, "Veillez remplir tous les champs", Toast.LENGTH_LONG).show()
            } else {

                ///

                println(":::::::::: SIGN IN ::::::::::")
                var retrofit = RequestUtils.retrofitBase.create(UserInterface::class.java)
                val user = UserLoginClassItem(email = userMail, password = password)

                println(user)
                var retrofitData = retrofit.login(user)

                retrofitData.enqueue(object: Callback<UserClassItem> {
                    override fun onResponse(call: Call<UserClassItem>, response: Response<UserClassItem>) {
                        val errorStr = response.errorBody()?.string()
                        if (response.isSuccessful){
                            println("|---> ${response.body()}")
                            SessionManager.logIn(response.body(), response.body()?.token ?: "")

                            println("Session manager is declare")


                            println("Image HERE : ${SessionManager.image}")
                            startActivity(main)
                            finish()
                        }
                        when (response.code()) {
                            400 ->{
                                Toast.makeText(this@Login, "email doit être un email" , Toast.LENGTH_LONG).show()
                            }
                            401 -> {
                                Toast.makeText(this@Login, "Mot de passe ou Email mauvais", Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<UserClassItem>, t: Throwable) {
                        Log.d("LoginActivity", "onfailure: "+ t.message )
                    }
                })
                ///
            }
        }

        link_register_.setOnClickListener{
            val register = Intent(this, Register::class.java)
            startActivity(register)
            finish()
        }
    }


}
/*fun signIn(mail: String, password: String){
    println(":::::::::: SIGN IN ::::::::::")
    var retrofit = RequestUtils.retrofitBase.create(UserInterface::class.java)
    val user = UserLoginClassItem(email = mail, password = password)

    println(user)
    var retrofitData = retrofit.login(user)

    retrofitData.enqueue(object: Callback<UserClassItem> {
        override fun onResponse(call: Call<UserClassItem>, response: Response<UserClassItem>) {
            val errorStr = response.errorBody()?.string()
            when (response.code()) {
                400 ->{
                    errorMessage = "email doit être un email"
                }
                401 -> {
                    errorMessage = "Mot de passe ou Email mauvais"
                }
                201 -> {
                    SessionManager.logIn(mail, password)
                };
            }
        }

        override fun onFailure(call: Call<UserClassItem>, t: Throwable) {
            Log.d("LoginActivity", "onfailure: "+ t.message )
        }
    })
}*/