package com.example.covapp.ui.registration

import androidx.lifecycle.ViewModel

class HealthConditionsViewModel : ViewModel() {
    var priorityAux = 0

    fun incrementPriority() {
        priorityAux += 1
    }
}


