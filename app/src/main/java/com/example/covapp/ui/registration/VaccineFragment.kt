package com.example.covapp.ui.registration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.covapp.R
import com.example.covapp.databinding.FragmentVaccineBinding

class VaccineFragment : Fragment(){

    private lateinit var vaccineViewModel: VaccineViewModel
    private lateinit var binding: FragmentVaccineBinding
    private val args: HealthConditionsFragmentArgs by navArgs()
    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vaccineViewModel =
            ViewModelProvider(this).get(VaccineViewModel::class.java)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setTitle(R.string.vaccine)
        return inflater.inflate(R.layout.fragment_vaccine, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentVaccineBinding.bind(view)
        navController = Navigation.findNavController(view)

        binding.btnToSummary.setOnClickListener{ toSummary() }

        binding.vaccine1.setOnClickListener{ vaccine1() }
        binding.vaccine2.setOnClickListener{ vaccine2() }
        binding.vaccine3.setOnClickListener{ vaccine3() }
        binding.sarajevo.setOnClickListener{ sarajevo() }
        binding.banjaluka.setOnClickListener{ banjaluka() }
        binding.mostar.setOnClickListener{ mostar() }
        binding.tuzla.setOnClickListener{ tuzla() }
    }

    private fun toSummary() {
        next()
    }

    private fun vaccine1(){
        vaccineViewModel.vaccine1()
    }

    private fun vaccine2(){
        vaccineViewModel.vaccine2()
    }

    private fun vaccine3(){
        vaccineViewModel.vaccine3()
    }

    private fun sarajevo(){
        vaccineViewModel.sarajevo()
    }

    private fun banjaluka(){
        vaccineViewModel.banjaluka()
    }

    private fun mostar(){
        vaccineViewModel.mostar()
    }

    private fun tuzla(){
        vaccineViewModel.tuzla()
    }

    private fun next() {
        if (vaccineViewModel.vaccine == 0) {
            Toast.makeText(activity, getString(R.string.toast_vaccine), Toast.LENGTH_LONG).show()
            return
        } else if (vaccineViewModel.location == "") {
            Toast.makeText(activity, getString(R.string.toast_location), Toast.LENGTH_LONG).show()
            return
        }

        val action = VaccineFragmentDirections.actionVaccineFragmentToSummaryFragment(args.name, args.lastName, args.gender, args.age,
            args.mail, vaccineViewModel.location, vaccineViewModel.vaccine, args.priority)

        navController!!.navigate(action)
    }
}