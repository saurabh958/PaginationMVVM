package com.example.pagination.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagination.model.Data
import com.example.pagination.retrofit.ApiInterface

class EmployeePagingSource(val apiInterface: ApiInterface): PagingSource<Int,Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {

        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
       return try {
            val posi = params.key ?: 1
            val response = apiInterface.getEmployeedata(posi)
           Log.e("SAU","API data -> ${response.body()} error is${response.errorBody()}")
            return response.body()?.let {
                LoadResult.Page(
                    data = it.data,
                    prevKey = if(posi == 1) null else posi -1,
                    nextKey = if(posi == response.body()!!.total_pages) null else posi + 1
                )
            }!!
        }
        catch (e:Exception)
        {
            LoadResult.Error(e)
        }
    }
}