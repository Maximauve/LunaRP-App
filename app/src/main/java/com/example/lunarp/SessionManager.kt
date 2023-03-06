package com.example.lunarp

import java.sql.Connection

object SessionManager {
    public var username : String = "";
    public var userMail = "";
    public var userId : Int = -1;
    public var userPassword: String = ""
    public  var userToken: String = ""

    fun logIn(userMail : String, userPassword : String, userToken: String){
        this.userMail = userMail;
        this.userPassword = userPassword;
        this.userToken = userToken
        RequestUtils.reloadClient()
        // ssearch with API the ID of the user
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
        return "SessionManager(username='$username', user_mail='$userMail', user_id=$userId)"
    }



}