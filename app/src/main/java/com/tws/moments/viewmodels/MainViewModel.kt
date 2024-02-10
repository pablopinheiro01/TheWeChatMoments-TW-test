package com.tws.moments.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tws.moments.data.api.MomentService
import com.tws.moments.data.api.entry.TweetBean
import com.tws.moments.data.api.entry.UserBean
import com.tws.moments.data.repository.MomentRepository
import com.tws.moments.data.repository.MomentRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.min

private const val TAG = "MainViewModel##"

private const val PAGE_TWEET_COUNT = 5

@HiltViewModel
class MainViewModel @Inject constructor( private val repository: MomentRepositoryImpl) : ViewModel() {

    private var _userBean = MutableLiveData(UserBean())

    val userBean: LiveData<UserBean>
        get() = _userBean

    private var _tweets = MutableLiveData<List<TweetBean>>()

    val tweets: LiveData<List<TweetBean>>
        get() = _tweets

    private var allTweets: List<TweetBean>? = null

    init {
        loadUserInfo()
        loadTweets()
    }

    private fun loadUserInfo() {
        viewModelScope.launch(Dispatchers.IO){
            val result = try {
                repository.fetchUser()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
            _userBean.value = result
        }
    }


    private fun loadTweets() {
        viewModelScope.launch(Dispatchers.IO){
            val result = try {
                repository.fetchTweets()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }

            allTweets = result

            if ((allTweets?.size ?: 0) > PAGE_TWEET_COUNT) {
                _tweets.value = allTweets?.subList(0, PAGE_TWEET_COUNT)
            } else {
                _tweets.value = emptyList()
            }
        }
    }

    fun refreshTweets() {
        loadTweets()
    }

    val pageCount: Int
        get() {
            return allTweets?.let { listTweets ->
                if((listTweets.size % PAGE_TWEET_COUNT) == 0){
                    (listTweets.size / PAGE_TWEET_COUNT)
                }else{
                    (listTweets.size / PAGE_TWEET_COUNT) + 1
                }
            } ?: run{0}
//            return when {
//                allTweets.isNullOrEmpty() -> 0
//                allTweets!!.size % PAGE_TWEET_COUNT == 0 -> allTweets!!.size / PAGE_TWEET_COUNT
//                else -> allTweets!!.size / PAGE_TWEET_COUNT + 1
//            }
        }

    fun loadMoreTweets(pageIndex: Int, onLoad: (List<TweetBean>?) -> Unit) {
        if (pageIndex < 0) {
            throw IllegalArgumentException("page index must greater than or equal to 0.")
        }

        if (pageIndex > pageCount - 1) {
            return
        }

        viewModelScope.launch(Dispatchers.IO){
            val startIndex = PAGE_TWEET_COUNT * pageIndex
            allTweets?.let { listTweets ->
                val endIndex = min(listTweets.size, PAGE_TWEET_COUNT * (pageIndex + 1))
                val result = listTweets.subList(startIndex, endIndex)
                onLoad(result)
            }
        }

//        viewModelScope.launch {
//            val startIndex = PAGE_TWEET_COUNT * pageIndex
//            val endIndex = min(allTweets!!.size, PAGE_TWEET_COUNT * (pageIndex + 1))
//            val result = allTweets!!.subList(startIndex, endIndex)
//            onLoad(result)
//        }
    }

}
