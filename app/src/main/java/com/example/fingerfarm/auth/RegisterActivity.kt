package com.example.fingerfarm.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fingerfarm.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        // FirebaseAuth 초기화
        auth = Firebase.auth

        val btnRegisterRegister = findViewById<Button>(R.id.btnRegisterRegister)
        btnRegisterRegister.setOnClickListener {
            toRegister()
        }
    }

    private fun toRegister(){
            // View 선언
            val etRegisterName = findViewById<EditText>(R.id.etRegisterName)
            val etRegisterEmail = findViewById<EditText>(R.id.etRegisterEmail)
            val etRegisterPw = findViewById<EditText>(R.id.etRegisterPw)

            // 입력 칸이 비어있다면 토스트 띄우고 함수 나가기
            if(etRegisterName.text.isEmpty() || etRegisterEmail.text.isEmpty()
                || etRegisterPw.text.isEmpty()) {
                Toast.makeText(this,"기입란을 전부 채워주세요",Toast.LENGTH_SHORT).show()
                return
            }

            val name = etRegisterName.text.toString()
            val email = etRegisterEmail.text.toString()
            val pw = etRegisterPw.text.toString()

        auth.createUserWithEmailAndPassword(email,pw)
            .addOnCompleteListener(this){task->
                if(task.isSuccessful){
                    // firebase 회원가입 형식에 만족하는 경우 계정 생성 성공
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this,"회원가입 성공",Toast.LENGTH_SHORT).show()
                }else{
                    // firebase 회원가입 형식에 만족하지 못하는 경우
                    Toast.makeText(this,"기입사항을 다시 입력해주세요",Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                // 어떠한 이유로 실패했는지 Toast 띄우기
                Toast.makeText(this, "실패 이유 : ${it.localizedMessage}",Toast.LENGTH_SHORT).show()
            }
    }
}