package com.fabpps.deeplinkexecutor.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.fabpps.deeplinkexecutor.R
import com.fabpps.deeplinkexecutor.databinding.DeepLinkExecutorActivityBinding
import com.fabpps.deeplinkexecutor.ui.base.BaseInjectActivity
import com.fabpps.deeplinkexecutor.utils.extensions.setOnClickListenerWithDelay
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeepLinkExecutorActivity: BaseInjectActivity() {

    private lateinit var binding: DeepLinkExecutorActivityBinding

    private val viewModel: DeepLinkExecutorViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DeepLinkExecutorActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        binding.mdltBtnSendDeepLink.setOnClickListenerWithDelay {
            viewModel.saveDeepLink()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}