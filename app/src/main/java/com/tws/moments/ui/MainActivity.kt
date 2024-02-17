package com.tws.moments.ui

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tws.moments.ui.adapters.MomentsAdapter
import com.tws.moments.databinding.ActivityMainBinding
import com.tws.moments.imageloader.GlideImageLoader
import com.tws.moments.imageloader.ImageLoader
import com.tws.moments.utils.ScreenAdaptiveUtil
import com.tws.moments.utils.dip
import com.tws.moments.ui.views.LoadMoreListener
import com.tws.moments.ui.views.itemdecoration.MomentDividerItemDecoration
import com.tws.moments.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainActivity##"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding get() = _binding!!

    private var _binding: ActivityMainBinding? = null

    private val viewModel: MainViewModel by viewModels()

    private val adapter = MomentsAdapter()

    private var reqPageIndex = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWindow()

        ScreenAdaptiveUtil.adaptive(this)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setupRecyclerView()
        subscribe()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(
            MomentDividerItemDecoration(
                offsets = dip(10),
                dividerColor = Color.parseColor("#dddddd"),
                startPosition = 1
            )
        )

        binding.recyclerView.adapter = this.adapter

        binding.swipeRefreshLayout.isRefreshing = true
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshTweets()
        }

        binding.recyclerView.addOnScrollListener(object : LoadMoreListener() {
            override fun onLoadMore() {
                if (reqPageIndex <= viewModel.pageCount - 1) {
                    viewModel.loadMoreTweets(reqPageIndex) {
                        reqPageIndex++
                        lifecycleScope.launch(Dispatchers.Main){
                            adapter.addMoreTweet(it)
                        }
                    }
                }
            }
        })
    }

    private fun subscribe() {
        viewModel.userBean.observe(this, Observer { user ->
            adapter.userBean = user
        })

        viewModel.tweets.observe(this, Observer { tweets ->
            binding.swipeRefreshLayout.isRefreshing = false
            reqPageIndex = 1
            adapter.tweets = tweets.toMutableList()
        })
    }

    private fun initWindow() {
        val flag = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.decorView.systemUiVisibility = flag
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
