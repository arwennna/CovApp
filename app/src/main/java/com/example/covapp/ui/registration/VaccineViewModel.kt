package com.example.covapp.ui.registration

import androidx.lifecycle.ViewModel

class VaccineViewModel : ViewModel() {
    var vaccine = 0
    var location = ""

    fun vaccine1() {
        vaccine = 1
    }

    fun vaccine2() {
        vaccine = 2
    }

    fun vaccine3() {
        vaccine = 3
    }

    fun sarajevo() {
        location = "Sarajevo"
    }

    fun banjaluka() {
        location = "Banja Luka"
    }

    fun mostar() {
        location = "Mostar"
    }

    fun tuzla() {
        location = "Tuzla"
    }

}


