package com.example.pagination.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pagination.model.EmployeeData
import com.example.pagination.repository.EmployeeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeViewModel(private val repository: EmployeeRepository): ViewModel()
{
    val list = repository.getEmployeData().cachedIn(viewModelScope)
}