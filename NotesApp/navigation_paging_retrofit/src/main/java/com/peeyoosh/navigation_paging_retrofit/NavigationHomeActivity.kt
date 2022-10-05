package com.peeyoosh.navigation_paging_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_home)


    }
}