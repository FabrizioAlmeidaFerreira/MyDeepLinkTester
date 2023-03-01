package com.fabpps.mydeeplinktester

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.fabpps.deeplinkexecutor.ui.DeepLinkExecutorActivity
import com.fabpps.mydeeplinktester.databinding.ActivityMainBinding
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {

    //private val mainViewModel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenCreated {
            delay(1000)
            startActivity(
                Intent(this@MainActivity, DeepLinkExecutorActivity::class.java)
            ).also { finish() }
        }
    }

    /*private fun getCurrentDeepLink() {
        binding.mdltBtnSendDeepLink.setOnClickListenerWithDelay {
            println("Texto digitado = ${binding.txtInputDeepLink.text}")
        }
    }*/
}