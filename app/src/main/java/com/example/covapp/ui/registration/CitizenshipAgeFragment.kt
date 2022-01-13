package com.example.covapp.ui.registration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.covapp.R
import com.example.covapp.databinding.FragmentCitizenshipAgeBinding

class CitizenshipAgeFragment : Fragment() {

    private lateinit var citizenshipAgeViewModel: CitizenshipAgeViewModel
    var navController: NavController? = null
    private lateinit var binding: FragmentCitizenshipAgeBinding
    private val args: PersonalInfoFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        citizenshipAgeViewModel =
            ViewModelProvider(this).get(CitizenshipAgeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_citizenship_age, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCitizenshipAgeBinding.bind(view)
        navController = Navigation.findNavController(view)

        binding.btnToProfession?.setOnClickListener { toProfession() }
        binding.citizenshipBH?.setOnClickListener { onBHChecked() }
        binding.citizenshipOther?.setOnClickListener { onOtherChecked() }
    }


    private fun onBHChecked() {
        citizenshipAgeViewModel.onBHChecked()
    }

    private fun onOtherChecked() {
        citizenshipAgeViewModel.onOtherChecked()
    }

    private fun toProfession() {
        next()
    }

    private fun updateData(A:String) {
        citizenshipAgeViewModel.updateData(A)
    }

    private fun next() {
        updateData(
            binding.age.text.toString()
        )

        if (citizenshipAgeViewModel.checkEmptyString(citizenshipAgeViewModel.age!!) == false ) {
            Toast.makeText(activity, getString(R.string.toast_age), Toast.LENGTH_LONG).show()
            return
        } else if (citizenshipAgeViewModel.ctzn == -1) {
            Toast.makeText(activity, getString(R.string.toast_citizenship), Toast.LENGTH_LONG).show()
            return
        }

        if (citizenshipAgeViewModel.ctzn == 0) {
            val action = CitizenshipAgeFragmentDirections.actionCitizenshipAgeFragmentToBadCitizenshipFragment()
            navController!!.navigate(action)
        } else if (citizenshipAgeViewModel.age.toInt() < 18) {
            val action = CitizenshipAgeFragmentDirections.actionCitizenshipAgeFragmentToBadAgeFragment()
            navController!!.navigate(action)
        } else {
            val action = CitizenshipAgeFragmentDirections.actionCitizenshipAgeFragmentToProfessionFragment(args.name, args.lastName,
                args.gender, citizenshipAgeViewModel.age.toInt(), args.mail, "", 0, citizenshipAgeViewModel.priority)
            navController!!.navigate(action)
        }
    }

}