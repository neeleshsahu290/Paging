package com.scriptsbundle.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.scriptsbundle.paging.databinding.ActivityMainBinding
import com.scriptsbundle.paging.db.UserDatabase

class MainActivity : AppCompatActivity() {
    lateinit var viewmodel: MainViewmodel
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewmodel= ViewModelProvider(this)[MainViewmodel::class.java]
        binding.recycleruser.layoutManager = LinearLayoutManager(this@MainActivity)
        getusers()

    }
    fun getusers(){
        viewmodel.getanswers(this@MainActivity)
        viewmodel.data.observe(this){

            val list = it

            if (list!=null ){
                if (list.size>0) {

                    binding.recycleruser.adapter = UserAdapter(list,this@MainActivity)
                    binding.progressbar.visibility = View.GONE
                    binding.recycleruser.visibility = View.VISIBLE
                }

            }
        }
    }
}