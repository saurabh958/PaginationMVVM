package com.example.pagination.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pagination.model.EmployeeData
import com.example.pagination.repository.EmployeeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeViewModel(private val repository: EmployeeRepository): ViewModel()
{
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getEmployeeData(1)
        }
    }

    val employee:LiveData<EmployeeData>
    get() = repository.employee
}