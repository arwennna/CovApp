package com.example.covapp.ui.registration

import androidx.lifecycle.ViewModel

class PersonalInfoViewModel : ViewModel() {

    var gender: Boolean? = null
    var lastname: String? = null
    var phone: String? = null
    var email: String? = null
    var name: String? = null

    fun checkEmptyString (s:String): Boolean{
        return s != ""
    }

    fun checkNullValue (b:Boolean): Boolean{
        return b != null
    }

    fun onMaleChecked() {
        gender = false
    }

    fun onFemaleChecked() {
        gender = true
    }

    fun updateData(A:String, B:String, C:String, D:String) {
        name = A
        lastname = B
        email = C
        phone = D
    }
}


