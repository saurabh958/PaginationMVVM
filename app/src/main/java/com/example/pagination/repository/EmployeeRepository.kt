package com.example.pagination.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.pagination.model.EmployeeData
import com.example.pagination.paging.EmployeePagingSource
import com.example.pagination.retrofit.ApiInterface

class EmployeeRepository(private val apiInterface: ApiInterface) {

    private val employeeLiveData = MutableLiveData<EmployeeData>()
    val employee: LiveData<EmployeeData>
    get() = employeeLiveData

    /*suspend fun getEmployeeData(page:Int){
        val result = apiInterface.getEmployeedata(page)
        if(result.body() != null){
            employeeLiveData.postValue(result.body())
        }
    }*/

    fun getEmployeData() = Pager(
        config = PagingConfig(pageSize = 6,maxSize = 50),
        pagingSourceFactory = {EmployeePagingSource(apiInterface = apiInterface)}
    ).liveData
}