package com.example.mythirdform

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mythirdform.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        Log.i("IBEAPP", "Recién arranca la activity")
        Log.i("IBEAPP", supportFragmentManager.backStackEntryCount.toString())

        setContentView(binding.root)
    }

    override fun onBackPressed() {
//        var i = supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount)
        super.onBackPressedDispatcher.onBackPressed()
    }

}
