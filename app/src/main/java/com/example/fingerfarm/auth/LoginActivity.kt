package com.example.fingerfarm.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.fingerfarm.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    val tvLoginRegister = findViewById<TextView>(R.id.tvLoginRegister)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // 상태 표시줄 숨기기
        supportActionBar?.hide()
        // Firebase 연동
        auth = Firebase.auth
        // layout View 선언

        toRegister()

    }

    private fun toRegister(){
//        tvLoginRegister.setOnClickListener {
//            startActivity(Intent(this, RegisterActivity::class.java))
//        }

        // RealtimeDB url로 연동
        val url = "https://fingerfarm-d65ab-default-rtdb.firebaseio.com/"
        val db = Firebase.database(url)

        val loginInfoSpf = getSharedPreferences(
            "loginInfo",
            Context.MODE_PRIVATE
        )
        val loginId = loginInfoSpf.getString("loginId","")

        // FirebaseAuth 초기화
        auth = Firebase.auth
        
    }



}