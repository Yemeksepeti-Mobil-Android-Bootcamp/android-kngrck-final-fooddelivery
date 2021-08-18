package com.kngrck.fooddeliveryfinal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kngrck.fooddeliveryfinal.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
    }
}