package com.example.google_tasks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.google_tasks.databinding.ActivityMainBinding
import com.example.google_tasks.views.list.ListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, ListFragment())
                .commit()
        }


    }
}