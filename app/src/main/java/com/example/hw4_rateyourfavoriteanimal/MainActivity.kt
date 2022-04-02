package com.example.hw4_rateyourfavoriteanimal

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hw4_rateyourfavoriteanimal.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, ListFragment())
            .addToBackStack(null)
            .commit()

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.rating_container, RatingFragment())
                .addToBackStack(null)
                .commit()
        }
    }

}