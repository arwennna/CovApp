package com.example.covapp.ui.registration

import androidx.lifecycle.ViewModel

class CitizenshipAgeViewModel : ViewModel() {

    var ctzn = -1
    var priority = -1
    var age = ""

    fun onBHChecked() {
        ctzn = 1
    }

    fun onOtherChecked() {
        ctzn = 0
    }

    fun updateData(A:String) {
        age = A
        if (A == "")
            return
        var aux = A.toInt()

        if (aux < 35) {
           priority = 1
        } else if (aux < 65) {
            priority = 3
        } else {
            priority = 5
        }
    }

    fun checkEmptyString (s:String): Boolean{
        return s != ""
    }
}


