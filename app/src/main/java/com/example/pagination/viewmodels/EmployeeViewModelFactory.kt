package com.example.pagination.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pagination.repository.EmployeeRepository

class EmployeeViewModelFactory(private val repository: EmployeeRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EmployeeViewModel(repository) as T
    }
}