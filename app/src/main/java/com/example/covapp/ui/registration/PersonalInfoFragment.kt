package com.example.covapp.ui.registration

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.covapp.R
import com.example.covapp.databinding.FragmentPersonalInfoBinding

class PersonalInfoFragment : Fragment() {

    private lateinit var personalInfoViewModel: PersonalInfoViewModel
    private lateinit var binding: FragmentPersonalInfoBinding
    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        personalInfoViewModel =
            ViewModelProvider(this).get(PersonalInfoViewModel::class.java)
        return inflater.inflate(R.layout.fragment_personal_info, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPersonalInfoBinding.bind(view)
        navController = Navigation.findNavController(view)

        binding.btnToCitizenshipAge?.setOnClickListener { toCitizenshipAge() }
        binding.female?.setOnClickListener { onFemaleChecked() }
        binding.male?.setOnClickListener { onMaleChecked() }
    }


    private fun onMaleChecked() {
        personalInfoViewModel.onMaleChecked()
    }
    private fun onFemaleChecked() {
        personalInfoViewModel.onFemaleChecked()
    }

    private fun toCitizenshipAge() {
        next()
    }

    private fun updateData(A:String, B:String, C:String, D:String) {
        personalInfoViewModel.updateData(A, B, C, D)
    }

    private fun next() {
        updateData(
            binding.name.text.toString(), binding.lastname.text.toString(), binding.email.text.toString(),
            binding.phone.text.toString()
        )

        if (personalInfoViewModel.checkEmptyString(personalInfoViewModel.name!!) == false ) {
            Toast.makeText(activity, getString(R.string.toast_name), Toast.LENGTH_LONG).show()
            return
        } else if (personalInfoViewModel.checkEmptyString(personalInfoViewModel.lastname!!) == false) {
            Toast.makeText(activity, getString(R.string.toast_lastname), Toast.LENGTH_LONG).show()
            return
        } else if (personalInfoViewModel.checkEmptyString(personalInfoViewModel.email!!) == false) {
            Toast.makeText(activity, getString(R.string.toast_email), Toast.LENGTH_LONG).show()
            return
        } else if (personalInfoViewModel.checkEmptyString(personalInfoViewModel.phone!!) == false) {
            Toast.makeText(activity, getString(R.string.toast_phone), Toast.LENGTH_LONG).show()
            return
        } else if (personalInfoViewModel.checkNullValue(personalInfoViewModel.gender!!) == false) {
            Toast.makeText(activity, getString(R.string.toast_gender), Toast.LENGTH_LONG).show()
            return
        }

        val action = PersonalInfoFragmentDirections.actionPersonalInfoFragmentToCitizenshipAgeFragment(
            personalInfoViewModel.name!!.toString(), personalInfoViewModel.lastname!!.toString(), personalInfoViewModel.gender!!,0,
            personalInfoViewModel.email!!.toString(), "", 0, 0)

        navController!!.navigate(action)
    }

}