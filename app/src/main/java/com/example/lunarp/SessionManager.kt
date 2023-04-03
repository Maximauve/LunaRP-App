package com.example.lunarp

import com.example.lunarp.user.User
import com.example.lunarp.user.UserClassItem

object SessionManager {
    public var username : String = "";
    public var userMail = "";
    public var userId : Int = -1;
    public  var userToken: String = ""
    public var userRole: String = ""

    fun getUser(): User {
        return User(userId, username, userMail, userRole)
    }
    fun logIn(user: UserClassItem?,userToken: String){
        this.userMail = user!!.email ;
        this.username = user.username
        this.userRole = user!!.role
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