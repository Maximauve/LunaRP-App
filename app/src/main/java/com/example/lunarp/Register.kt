package com.example.lunarp

import android.app.DownloadManager.Request
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
import com.example.lunarp.user.UserRegisterClassItem
import io.ktor.util.reflect.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.typeOf

class Register : AppCompatActivity() {
    var errorMessage = ""
    override fun onCreate(savedInstanceState: Bundle?) {

        val main = Intent(this, MainActivity::class.java)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        var user_name_ = findViewById<EditText>(R.id.et_user_name)
        var user_mail_ = findViewById<EditText>(R.id.et_email)
        var password_ = findViewById<EditText>(R.id.et_password)
        var confirmPassword_ = findViewById<EditText>(R.id.et_confirm_password)
        var btn_submit_ = findViewById<Button>(R.id.btn_submit)
        var link_login_ = findViewById<TextView>(R.id.link_login)

        // set on-click listener
        btn_submit_.setOnClickListener {
            errorMessage = ""
            val username = user_name_.text.toString();
            val userMail = user_mail_.text.toString();
            val password = password_.text.toString();
            val confirmPassword = confirmPassword_.text.toString();

            println(errorMessage.isEmpty())
            if (user_name_.text.isEmpty() && password_.text.isEmpty()){
                Toast.makeText(this@Register, "Remplissez tous les champs", Toast.LENGTH_LONG).show()
            } else {
                if (password != confirmPassword){
                    Toast.makeText(this@Register, "Ce ne sont pas les même mot de passe", Toast.LENGTH_LONG).show()
                } else {
                    ///

                    println(":::::::::: SIGN UP ::::::::::")

                    var retrofit = RequestUtils.retrofitBase.create(UserInterface::class.java)
                    val user = UserRegisterClassItem(email = userMail, username = username, password = password, role = "User")
                    var retrofitData = retrofit.register(user)

                    retrofitData.enqueue(object: Callback<UserClassItem>{
                        override fun onFailure(call: Call<UserClassItem>, t: Throwable) {
                            Log.d("RegisterActivity", "onfailure: "+ t.message )
                        }

                        override fun onResponse(call: Call<UserClassItem>, response: Response<UserClassItem>) {
                            val errorStr = response.errorBody()?.string()
                            if (response.isSuccessful) {
                                SessionManager.logIn(userMail, password);
                                startActivity(main)
                                finish()
                            }
                            when (response.code()){
                                400 -> {
                                    if (errorStr != null) {
                                        if (errorStr.contains("email must be an email")) {
                                            Toast.makeText(this@Register, "email doit être un email", Toast.LENGTH_LONG).show()
                                        } else if (errorStr.contains("password must be longer than or equal to 8 characters")){
                                            Toast.makeText(this@Register, "Mot de passe trop court: minimum 8 caracters", Toast.LENGTH_LONG).show()
                                        } else {
                                            Toast.makeText(this@Register, "Erreur inconnu", Toast.LENGTH_LONG).show()
                                        }
                                    }
                                }
                                500 -> {
                                    Toast.makeText(this@Register, "L'utilisateur exist déjà", Toast.LENGTH_LONG).show()
                                    }
                            }
                        }


                    })

                    ///
                }
            }
        }

        link_login_.setOnClickListener {
            val login = Intent(this, Login::class.java)
            startActivity(login)
            finish()
        }
    }

    fun signUp(name: String, mail: String, password: String){
        println(":::::::::: SIGN UP ::::::::::")

        var retrofit = RequestUtils.retrofitBase.create(UserInterface::class.java)
        val user = UserRegisterClassItem(email = mail, username = name, password = password, role = "User")
        var retrofitData = retrofit.register(user)

        retrofitData.enqueue(object: Callback<UserClassItem>{
            override fun onFailure(call: Call<UserClassItem>, t: Throwable) {
                Log.d("RegisterActivity", "onfailure: "+ t.message )
            }

            override fun onResponse(call: Call<UserClassItem>, response: Response<UserClassItem>) {
                val errorStr = response.errorBody()?.string()

                when (response.code()){
                    400 -> {
                        println(errorMessage.isEmpty())
                        if (errorStr != null && errorMessage.isEmpty()) {
                            errorMessage = if (errorStr.contains("email must be an email")) {
                                "email doit être un email"
                            } else if (errorStr.contains("password must be longer than or equal to 8 characters")){
                                "Mot de passe trop court: minimum 8 caracters"
                            } else {
                                "Erreur inconnu"
                            }
                        }
                    }
                    500 -> {
                        if (errorMessage.isEmpty()){
                            errorMessage = "L'utilisateur exist déjà"
                        }
                    }
                    201 -> SessionManager.logIn(mail, password);
                }
            }


        })
    }
}