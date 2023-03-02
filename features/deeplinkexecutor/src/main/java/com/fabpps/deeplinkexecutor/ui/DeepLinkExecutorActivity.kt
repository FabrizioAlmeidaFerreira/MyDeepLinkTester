package com.fabpps.deeplinkexecutor.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.ParseException
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.widget.addTextChangedListener
import com.fabpps.data.dto.DeepLinkVO
import com.fabpps.deeplinkexecutor.R
import com.fabpps.deeplinkexecutor.databinding.DeepLinkExecutorActivityBinding
import com.fabpps.deeplinkexecutor.domain.interfaces.DeepLinkAdapterListeners
import com.fabpps.deeplinkexecutor.ui.adapter.favorite.DeepLinkFavoritesAdapter
import com.fabpps.deeplinkexecutor.ui.base.BaseInjectActivity
import com.fabpps.deeplinkexecutor.utils.extensions.setOnClickListenerWithDelay
import com.fabpps.extensions.nonNullObserver
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeepLinkExecutorActivity : BaseInjectActivity(), DeepLinkAdapterListeners {

    private lateinit var binding: DeepLinkExecutorActivityBinding

    private val deepLinkFavoritesAdapter by lazy { DeepLinkFavoritesAdapter() }

    private val viewModel: DeepLinkExecutorViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
        observeAllDeepLinks()
    }

    private fun observeAllDeepLinks() {
        viewModel.allDeepLinks.nonNullObserver(this) {
            deepLinkFavoritesAdapter.apply {
                setListeners(this@DeepLinkExecutorActivity)
                setItemsList(
                    it.map { listToMap ->
                        listToMap.toDeepLinkVO()
                    }.toMutableList()
                )
            }
        }
    }

    private fun initViews() {
        binding = DeepLinkExecutorActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.rvDeepLinkList.adapter = deepLinkFavoritesAdapter

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        initButtonExecutor()
    }

    private fun initButtonExecutor() {
        changeStateInputEditText()
        binding.mdltBtnSendDeepLink.setOnClickListenerWithDelay {
            if (binding.txtInputDeepLink.text?.isNotEmpty() == true)
                try {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(binding.txtInputDeepLink.text.toString())
                        )
                    )
                    viewModel.saveDeepLink(binding.txtInputDeepLink.text.toString())
                    setError(false)
                } catch (e: ActivityNotFoundException) {
                    setError(true)
                } catch (e: ParseException) {
                    setError(true)
                }
        }
    }

    private fun setError(setError: Boolean = false) {
        binding.txtInputLayoutDeepLink.error =
            if (setError) getString(R.string.mdlt_button_error)
            else null
    }

    private fun changeStateInputEditText() {
        binding.txtInputDeepLink.addTextChangedListener(
            onTextChanged = { _, _, _, count ->
                if (count > 0) {
                    setError(false)
                }
            }
        )
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

    override fun onDeepLinkItemSelected(deepLinkVO: DeepLinkVO) {
        binding.txtInputDeepLink.setText(deepLinkVO.deepLink)
    }
}