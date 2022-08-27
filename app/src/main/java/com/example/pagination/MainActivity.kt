package com.example.pagination

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pagination.databinding.ActivityMainBinding
import com.example.pagination.repository.EmployeeRepository
import com.example.pagination.retrofit.ApiInterface
import com.example.pagination.retrofit.RetrofitClient
import com.example.pagination.viewmodels.EmployeeViewModel
import com.example.pagination.viewmodels.EmployeeViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var employeeViewModel:EmployeeViewModel
    private lateinit var recyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val apiService = RetrofitClient.getRetrofitInstance().create(ApiInterface::class.java)
        val repository = EmployeeRepository(apiService)
        recyclerView = binding.recyclerEmployee
        initRecyclerView()

        employeeViewModel = ViewModelProvider(this,EmployeeViewModelFactory(repository))
            .get(EmployeeViewModel::class.java)

        employeeViewModel.employee.observe(this, {
            Log.d("responsedata", it.data.toString())
            binding.tvCounter.text = it.total.toString()
        })


    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoratn = DividerItemDecoration(applicationContext,DividerItemDecoration.VERTICAL)
            addItemDecoration(decoratn)
            
        }
    }
}