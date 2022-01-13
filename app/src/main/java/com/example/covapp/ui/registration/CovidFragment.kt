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
import com.example.covapp.databinding.FragmentCovidBinding

class CovidFragment : Fragment(){

    private lateinit var covidViewModel: CovidViewModel
    var navController: NavController? = null
    private lateinit var binding: FragmentCovidBinding
    private val args: ProfessionFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setTitle(R.string.covid)
        covidViewModel =
                ViewModelProvider(this).get(CovidViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_covid, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCovidBinding.bind(view)
        navController = Navigation.findNavController(view)

        binding.btnToHealthConditions.setOnClickListener { toHealthConditions() }
        binding.covidYes.setOnClickListener { covidYes() }
        binding.covidNo.setOnClickListener { covidNo() }
    }

    private fun covidYes() {
        covidViewModel.covidYes()
        covidViewModel.incrementClick()
    }

    private fun covidNo() {
        covidViewModel.covidNo()
        covidViewModel.incrementClick()
    }

    private fun toHealthConditions() {
        next()
    }

    private fun next() {
        if (covidViewModel.checkZero(covidViewModel.clicked) == false) {
            Toast.makeText(activity, getString(R.string.toast_covid), Toast.LENGTH_LONG).show()
            return
        }
        if (covidViewModel.covid == 1) {
            val action = CovidFragmentDirections.actionCovidFragmentToBadCovidFragment()
            navController!!.navigate(action)
        } else {
            val action = CovidFragmentDirections.actionCovidFragmentToHealthConditionsFragment(args.name, args.lastName,
                args.gender, args.age, args.mail, "", 0, args.priority)
            navController!!.navigate(action)
        }
    }
}