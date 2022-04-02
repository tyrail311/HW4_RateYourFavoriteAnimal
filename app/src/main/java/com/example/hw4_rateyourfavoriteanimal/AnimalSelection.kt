package com.example.hw4_rateyourfavoriteanimal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AnimalSelection: ViewModel(){

    val position = MutableLiveData<Int>(0)

    fun setPosition(positionPassed: Int) {
        position.value = positionPassed
    }

    fun getPosition(): Int? {
        return position.value
    }

}