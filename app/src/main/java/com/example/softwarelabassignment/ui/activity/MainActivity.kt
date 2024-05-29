package com.example.softwarelabassignment.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.softwarelabassignment.databinding.ActivityMainBinding
import com.example.softwarelabassignment.ui.activity.onboarding.OnBoardingActivity
import com.example.softwarelabassignment.utils.SharedPrefUtil.getDeviceLoggedInToken

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val token:String = getDeviceLoggedInToken(this)
        if(token=="null"){
            // token not found, go to the onBoardScreen
            val intent = Intent(this, OnBoardingActivity::class.java)
             startActivity(intent)
        }else{
            // token found , go to home screen with login out button
            val intent = Intent(this, HomeScreenActivity::class.java)
            startActivity(intent)
        }
        finish()
    }
}