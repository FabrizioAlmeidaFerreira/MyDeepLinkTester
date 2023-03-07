package com.fabpps.deeplinkexecutor.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener
import com.fabpps.data.dto.DeepLinkVO
import com.fabpps.deeplinkexecutor.R
import com.fabpps.deeplinkexecutor.databinding.DeepLinkExecutorActivityBinding
import com.fabpps.deeplinkexecutor.domain.interfaces.DeepLinkAdapterListeners
import com.fabpps.deeplinkexecutor.ui.adapter.favorite.DeepLinkFavoritesAdapter
import com.fabpps.deeplinkexecutor.ui.base.BaseInjectActivity
import com.fabpps.extensions.executeDeepLinkIntent
import com.fabpps.extensions.setOnClickListenerWithDelay
import com.fabpps.extensions.nonNullObserver
import com.fabpps.extensions.onQueryTextChanged
import com.fabpps.utils.TEXT_INTENT_RECEIVED
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
        handlerReceiveIntentText()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelAllJobs()
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
        handlerChipFavorites()
    }

    private fun initButtonExecutor() {
        changeStateInputEditText()
        binding.mdltBtnSendDeepLink.setOnClickListenerWithDelay {
            if (binding.txtInputDeepLink.text?.isNotEmpty() == true)
                executeDeepLinkIntent(
                    deepLink = binding.txtInputDeepLink.text.toString(),
                    actionOnExecute = {
                        viewModel.saveDeepLink(binding.txtInputDeepLink.text.toString())
                        setError(false)
                    },
                    actionOnError = {
                        setError(true)
                    },
                    finish = binding.checkBox.isChecked
                )
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
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchMenu = menu.findItem(R.id.action_search)
        val searchView = searchMenu.actionView as SearchView

        searchView.onQueryTextChanged {
            viewModel.searchQuery.value = Pair(it, false)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite -> {
                viewModel.searchQuery.value = Pair("", true)
                binding.chipFavorites.visibility = View.VISIBLE
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun handlerChipFavorites() {
        binding.chipFavorites.apply {
            setOnCloseIconClickListener {
                viewModel.searchQuery.value = Pair("", false)
                visibility = View.GONE
            }
        }
    }

    private fun handlerReceiveIntentText() {
        intent?.let {
            it.extras?.run {
                getString(TEXT_INTENT_RECEIVED, null)?.let {textReceived ->
                    binding.txtInputDeepLink.setText(textReceived)
                }
            }
        }
    }

    override fun onDeepLinkItemSelected(deepLinkVO: DeepLinkVO) {
        binding.txtInputDeepLink.setText(deepLinkVO.deepLink)
    }

    override fun onDeepLinkUpdate(deepLinkVO: DeepLinkVO) {
        viewModel.updateDeepLink(deepLinkVO)
    }

    override fun onDeleteDeepLink(deepLinkVO: DeepLinkVO) {
        viewModel.deleteDeepLink(deepLinkVO)
    }
}