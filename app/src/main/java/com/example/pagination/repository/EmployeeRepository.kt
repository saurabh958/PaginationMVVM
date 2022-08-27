package com.example.pagination.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pagination.model.EmployeeData
import com.example.pagination.retrofit.ApiInterface

class EmployeeRepository(private val apiInterface: ApiInterface) {

    private val employeeLiveData = MutableLiveData<EmployeeData>()
    val employee: LiveData<EmployeeData>
    get() = employeeLiveData

    suspend fun getEmployeeData(page:Int){
        val result = apiInterface.getEmployeedata(page)
        if(result.body() != null){
            employeeLiveData.postValue(result.body())
        }
    }
}