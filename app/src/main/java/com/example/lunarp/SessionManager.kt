package com.example.lunarp

import java.sql.Connection

object SessionManager {
    public var username : String = "";
    public var userMail = "";
    public var userId : Int = -1;

    fun logIn(userMail : String, userPassword : String){
        this.userMail = userMail;

        // ssearch with API the ID of the user
    }

    fun isLogin() : Boolean {
        return userMail != ""
        //return userId != -1
    }
    fun logOut(){
        this.username = "";
        this.userMail = "";
        this.userId = -1;
    }

    override fun toString(): String {
        return "SessionManager(username='$username', user_mail='$userMail', user_id=$userId)"
    }


}