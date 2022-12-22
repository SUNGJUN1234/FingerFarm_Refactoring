package com.example.fingerfarm.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.fingerfarm.MainActivity
import com.example.fingerfarm.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // 상태 표시줄 숨기기
        supportActionBar?.hide()
        // Firebase 연동
        auth = Firebase.auth

        toLogin()

    }

    private fun toLogin(){
        val tvLoginRegister = findViewById<TextView>(R.id.tvLoginRegister)
        // Register 버튼 클릭 시 RegisterActivity 로 이동
        tvLoginRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

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

        // xml View 선언
        val etLoginEmail = findViewById<EditText>(R.id.etLoginEmail)
        val etLoginPw = findViewById<EditText>(R.id.etLoginPw)
        val btnLoginLogin = findViewById<Button>(R.id.btnLoginLogin)

        btnLoginLogin.setOnClickListener {
            val email = etLoginEmail.text.toString()
            val pw = etLoginPw.text.toString()

            auth.signInWithEmailAndPassword(email,pw)
                .addOnCompleteListener(this){ task->
                    // 만약 로그인에 성공했다면
                    if(task.isSuccessful == true){
                        // 해당 아이디를 SharedPreference에 저장해서
                        // 로그인 후에도 그 정보를 사용할 수 있도록 하자
                        val editorLoginInfoSpf = loginInfoSpf.edit()
                        editorLoginInfoSpf.putString("loginId",email)
                        editorLoginInfoSpf.commit()

                        // 로그인 성공 시 MainActivity로 이동
                        val intent = Intent(this , MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        // 로그인 실패 시 토스트
                        Toast.makeText(this,"로그인 실패",Toast.LENGTH_SHORT).show()
                    }

                }

        }
    }



}