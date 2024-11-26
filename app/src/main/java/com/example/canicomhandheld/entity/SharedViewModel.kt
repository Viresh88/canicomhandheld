package com.example.canicomhandheld.entity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel (){
    val selectedMonth = MutableLiveData<Int>()
    val selectedYear = MutableLiveData<Int>()
}