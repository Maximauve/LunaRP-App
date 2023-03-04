package com.example.lunarp

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const val URL_API_HOME =  "http://192.168.133.1:3000/"
const val URL_API_YNOV =  "http://10.33.17.71:3000/"
const val URL_API_TOTO = "http://toto.gouv.fr:3000/"
const val URL_API_2 = "http://10.0.0.2:3000"

object RequestUtils {
    fun getURL_API() : String{
        return URL_API_HOME
    }
    val client = OkHttpClient()

    val retrofitBase = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(URL_API_HOME)
        .build()

    fun extractErrorMessage(errorBody: String?): String {
        println("Extract error message is processing")
        println("errorBody is null?  ${errorBody.isNullOrBlank()}")
        if (errorBody.isNullOrBlank()){
            return ""
        } else {
            println(Gson().fromJson(errorBody, Error::class.java))
            return Gson().fromJson(errorBody, Error::class.java).message.toString()
        }
    }
}

data class Error(
    val statusCode: Int?,
    val message: String?,
    val error: String?
)