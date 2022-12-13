package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplication.models.UserInfo
import com.example.myapplication.services.RestApiService
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {



    companion object {
        lateinit var mainActivity: MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivity = this

        val prefs = getSharedPreferences("storage", MODE_PRIVATE)
        val emailP = prefs.getString("email", "")
        val passwordP = prefs.getString("password", "")
        if(emailP!!.isNotEmpty() && passwordP!!.isNotEmpty()){
            addDummyUser(emailP, passwordP)
        }

        else {
            btnLogin.setOnClickListener {
                addDummyUser(email.text.toString(), password.text.toString())
            }
        }

    }
    fun addDummyUser(email: String, password: String) {
        //progress.progressTintList(ColorStateList.valueOf(Color.RED))
        progress.visibility = View.VISIBLE
        val apiService = RestApiService()
        val userInfo = UserInfo(
            userEmail = email,
            userPassword = password)

        apiService.addUser(userInfo) {
            progress.visibility = View.GONE
            if(it ==null){
                Toast.makeText(applicationContext, "Connection Error", Toast.LENGTH_SHORT).show()
            }
            else {
                println("This one ${it!!.userData}")
                println("This one ${it.userMsg}")
                println("This one ${it.userToken}")

                if (it.userToken == null) {
                    Toast.makeText(applicationContext, "${it.userMsg}", Toast.LENGTH_SHORT).show()
                } else {
                    //Shared prefs
                    val prefs = getSharedPreferences("storage", MODE_PRIVATE)
                    val editor = prefs.edit()
                    editor.putString("Token", it.userToken)
                    editor.putString("userData", it.userData.toString())
                    editor.putString("email", email)
                    editor.putString("password", password)
                    editor.apply()

                    //We Now navigate to Main menu
                    val intent = Intent(applicationContext, DriverMain::class.java)
                    startActivity(intent)
                    finish()
                    Toast.makeText(applicationContext, "${it.userMsg}", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

}