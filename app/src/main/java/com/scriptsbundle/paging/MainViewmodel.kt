package com.scriptsbundle.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scriptsbundle.paging.modelclass.Answers
import com.scriptsbundle.paging.utils.ApiService
import com.scriptsbundle.paging.utils.RetrofitHelper
import kotlinx.coroutines.launch

class MainViewmodel : ViewModel() {

    private val service = RetrofitHelper().retrofit.create(ApiService::class.java)
    val error:MutableLiveData<String> = MutableLiveData()

    private val _data: MutableLiveData<Answers> = MutableLiveData()
    val data: LiveData<Answers> get() = _data

    fun getanswers() {
        viewModelScope.launch {
            try {
                //   https://api.stackexchange.com/2.3/answers?page=1&pagesize=20&order=desc&sort=activity&site=stackoverflow
                val response = service.getanswers(1, 20, "desc", "activity", "stackoverflow")
                if (response.isSuccessful) {
                    val result = response.body()
                    if (result != null) {
                        error.postValue("success")
                        _data.postValue(result!!)

                    }else{
                        error.postValue("result is null")
                    }
                }else{
                    error.postValue("response failed")
                }

            } catch (e: Exception) {
                error.postValue(e.toString())

            }
        }


    }
}