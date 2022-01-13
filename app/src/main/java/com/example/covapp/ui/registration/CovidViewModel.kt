package com.example.covapp.ui.registration

import androidx.lifecycle.ViewModel

class CovidViewModel : ViewModel() {
    var clicked = 0
    var covid = 0

    fun covidYes() {
        covid = 1
    }

    fun covidNo() {
        covid = 0
    }

    fun incrementClick() {
        clicked = 1
    }

    fun checkZero (i:Int): Boolean{
        return i != 0
    }

}


