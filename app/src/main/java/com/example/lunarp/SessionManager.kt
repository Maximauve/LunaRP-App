package com.example.lunarp

import android.util.Log
import com.example.lunarp.character.CharacterInterface
import com.example.lunarp.character.Character
import com.example.lunarp.user.User
import com.example.lunarp.user.UserClassItem
import com.example.lunarp.user.UserInterface
import com.example.lunarp.user.UserLoginClassItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object SessionManager {
    public var username : String = "";
    public var userMail = "";
    public var userId : Int? = null;
    public var Id : Int = -1;
    public  var userToken: String = ""
    public var userRole: String = ""
    public var characters : Array<com.example.lunarp.character.Character> = arrayOf()
    public var image : LocalFile? =null

    fun logIn(user: UserClassItem?,userToken: String){
        println("LOGIN HERE")
        this.userMail = user!!.email ;
        this.username = user.username
        this.userRole = user!!.role
        this.userToken = userToken
        this.Id = user.id
        this.userId = user.userId
        this.image = user.image
        println("My id $userId")

        RequestUtils.reloadClient()

    }

    fun isLogin() : Boolean {
        return userMail != ""
        //return userId != -1
    }
    fun logOut(){
        this.username = "";
        this.userMail = "";
        this.userToken = "";
        this.userId = -1;
        RequestUtils.reloadClient()
    }

    override fun toString(): String {
        return "SessionManager(user ID = $Id, username='$username', usermail='$userMail', userid=$userId, userCharacters=${characters.size})"
    }

    fun updateUser(){

        var retrofit = RequestUtils.retrofitBase.create(UserInterface::class.java)
        var retrofitData = retrofit.getOne(userId.toString())

        retrofitData.enqueue(object: Callback<UserClassItem> {
            override fun onResponse(call: Call<UserClassItem>, response: Response<UserClassItem>) {
                val errorStr = response.errorBody()?.string()
                if (response.isSuccessful) {
                    println("---> ${response.body()}")
                    userMail = response.body()!!.email ;
                    username = response.body()!!.username
                    userRole = response.body()!!.role
                    characters = response.body()!!.characters
                }
            }

            override fun onFailure(call: Call<UserClassItem>, t: Throwable) {
                Log.d("UPDATE user", "onfailure: " + t.message)
            }
        })
        RequestUtils.reloadClient()

        println("USER UPDATE:  ")
        println(SessionManager)
        println(SessionManager.characters)
    }



}