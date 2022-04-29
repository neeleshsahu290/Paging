package com.scriptsbundle.paging

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.RoomDatabase
import com.scriptsbundle.paging.db.User
import com.scriptsbundle.paging.db.UserDatabase
import com.scriptsbundle.paging.modelclass.Item
import com.scriptsbundle.paging.utils.ApiService
import com.scriptsbundle.paging.utils.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewmodel : ViewModel() {



    private val service = RetrofitHelper().retrofit.create(ApiService::class.java)
    val error:MutableLiveData<String> = MutableLiveData()

    private val _data: MutableLiveData<ArrayList<User>> = MutableLiveData()
    val data: LiveData<ArrayList<User>> get() = _data

    fun getanswers(context: Context) {
        var list :ArrayList<Item> = ArrayList()
        val list1 :ArrayList<User> = ArrayList()
        val db: UserDatabase = UserDatabase.getInstance(context)

        viewModelScope.launch(Dispatchers.IO) {
            getdata(db)
            try {
                val response = service.getanswers(1, 20, "desc", "activity", "stackoverflow")
                if (response.isSuccessful) {
                    val result = response.body()
                    Log.d("responsebody", response.body().toString())
                    if (result != null) {
                        error.postValue("success")
                        list = result.items
                        for (i in 0 until list.size){
                            list1.add(list[i].owner)
                        }

                        db.userDao().nukeTable()
                        db.userDao().insertAll(list1)
                        _data.postValue(db.userDao().getAll() as ArrayList<User>?)
                    }else{
                        error.postValue("result is null")
                    }
                }else{
                    error.postValue("response failed")
                }
                Log.d("errorvalue",error.value.toString())
            } catch (e: Exception) {

                error.postValue(e.toString())
            }
        }


    }

    fun getdata(db: UserDatabase){
        try {
            _data.postValue(db.userDao().getAll() as ArrayList<User>?)
            (db.userDao().getAll() as ArrayList<User>?)
        }catch (e:java.lang.Exception){
            Log.d("database error",e.toString())
        }
    }
}