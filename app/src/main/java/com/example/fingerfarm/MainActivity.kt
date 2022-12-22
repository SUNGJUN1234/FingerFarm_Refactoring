package com.example.fingerfarm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fingerfarm.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var  auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // FirebaseAuth 초기화
        auth = Firebase.auth

        val bnvMain = findViewById<BottomNavigationView>(R.id.bnvMain)

        // 초기화면 HomeFragment로 설정
        supportFragmentManager.beginTransaction().replace(
            // FragmentContainerView 에 어떤 값을 배치할지
            R.id.fcvMain,
            HomeFragment()
        ).commit()

        bnvMain.setOnItemSelectedListener {

            when(it.itemId){
                R.id.tab1 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fcvMain,
                        HomeFragment()
                    ).commit()
                }
                R.id.tab2 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fcvMain,
                        SeedFragment()
                    ).commit()
                }
                R.id.tab3 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fcvMain,
                        BoardFragment()
                    ).commit()
                }
                R.id.tab4 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fcvMain,
                        ChatFragment()
                    ).commit()
                }
            }

            // when 이벤트가 끝났다는걸 알려주는 장치
            true
        }

    }
}