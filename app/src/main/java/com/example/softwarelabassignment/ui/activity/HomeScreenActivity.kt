package com.example.softwarelabassignment.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.softwarelabassignment.R
import com.example.softwarelabassignment.databinding.ActivityHomeScreenBinding
import com.example.softwarelabassignment.utils.SharedPrefUtil.putDeviceLoggedInToken

class HomeScreenActivity : AppCompatActivity() {

    lateinit var binding:ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btLogin.setOnClickListener {
            putDeviceLoggedInToken(this,"null")
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                )
            )
              finish()
        }
    }
}