package com.fabpps.mydeeplinktester

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.fabpps.deeplinkexecutor.ui.DeepLinkExecutorActivity
import com.fabpps.mydeeplinktester.databinding.ActivityMainBinding
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var receivedText: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenCreated {
            handlerReceivedText()
        }
    }

    private fun handlerReceivedText() {
        intent?.let {
            if (it.action == Intent.ACTION_SEND && "text/plain" == intent.type) {
                it.getStringExtra(Intent.EXTRA_TEXT)?.let { textReceived ->
                    receivedText = textReceived
                }
            }
        }
        startActivity(
            Intent(this@MainActivity, DeepLinkExecutorActivity::class.java).apply {
                putExtra("text_received", receivedText)
            }
        ).also { finish() }
    }
}