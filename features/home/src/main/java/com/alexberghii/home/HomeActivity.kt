package com.alexberghii.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        supportFragmentManager.beginTransaction().add(R.id.container, HomeFragment()).commit()
    }
}