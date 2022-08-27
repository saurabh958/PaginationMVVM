package com.example.pagination.retrofit

import com.example.pagination.model.EmployeeData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface
{
    @GET("users")
    suspend fun getEmployeedata(@Query("page") page:Int): Response<EmployeeData>
}