package com.example.covapp.ui.registration

import androidx.lifecycle.ViewModel

class ProfessionViewModel : ViewModel() {

    var clicked = 0
    var priorityAux = 0

    fun incrementPriority() {
        priorityAux = 1
    }

    fun decrementPriority() {
        priorityAux = 0
    }

    fun incrementClicked() {
        clicked = 1
    }

    fun checkZero (i:Int): Boolean{
        return i != 0
    }

}


