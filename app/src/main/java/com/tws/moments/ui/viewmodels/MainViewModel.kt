package com.tws.moments.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tws.moments.data.api.entry.UserBean
import com.tws.moments.data.repository.MomentRepositoryImpl
import com.tws.moments.ui.model.Tweet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.min

private const val TAG = "MainViewModel##"

private const val PAGE_TWEET_COUNT = 5

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MomentRepositoryImpl
) : ViewModel() {

    private var _userBean = MutableLiveData(UserBean())

    val userBean: LiveData<UserBean>
        get() = _userBean

    private var _tweets = MutableLiveData<List<Tweet>>()

    val tweets: LiveData<List<Tweet>>
        get() = _tweets

    private var _allTweets: List<Tweet>? = null

    init {
        loadUserInfo()
        loadTweets()
    }

    private fun loadUserInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = try {
                repository.fetchUser()
            } catch (e: Exception) {
                null
            }
            _userBean.postValue(result)
        }
    }


    private fun loadTweets() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = try {
                repository.fetchTweets()
            } catch (e: Exception) {
                null
            }

            _allTweets = result

            if ((_allTweets?.size ?: 0) > PAGE_TWEET_COUNT) {
                _tweets.postValue(_allTweets?.subList(0, PAGE_TWEET_COUNT))
            } else {
                _tweets.postValue(emptyList())
            }
        }

    }

    fun refreshTweets() {
        loadTweets()
    }

    val pageCount: Int
        get() {
            return _allTweets?.let { listTweets ->
                if ((listTweets.size % PAGE_TWEET_COUNT) == 0) {
                    (listTweets.size / PAGE_TWEET_COUNT)
                } else {
                    (listTweets.size / PAGE_TWEET_COUNT) + 1
                }
            } ?: run { 0 }
        }

    fun loadMoreTweets(pageIndex: Int, onLoad: (List<Tweet>?) -> Unit) {
        if (pageIndex < 0) {
            throw IllegalArgumentException("page index must greater than or equal to 0.")
        }

        if (pageIndex > pageCount - 1) {
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val startIndex = PAGE_TWEET_COUNT * pageIndex
            _allTweets?.let { listTweets ->
                val endIndex = min(listTweets.size, PAGE_TWEET_COUNT * (pageIndex + 1))
                val result = listTweets.subList(startIndex, endIndex)
                onLoad(result)
            }
        }
    }

}
