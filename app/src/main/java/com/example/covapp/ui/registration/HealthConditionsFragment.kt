package com.example.covapp.ui.registration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.covapp.R
import com.example.covapp.databinding.FragmentHealthConditionsBinding


class HealthConditionsFragment : Fragment() {

    private lateinit var healthConditionsViewModel: HealthConditionsViewModel
    var navController: NavController? = null
    private lateinit var binding: FragmentHealthConditionsBinding
    private val args: CovidFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setTitle(R.string.health_con)
        healthConditionsViewModel =
            ViewModelProvider(this).get(HealthConditionsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_health_conditions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHealthConditionsBinding.bind(view)
        navController = Navigation.findNavController(view)

        val pom = args.gender

        if (!pom) {
            binding.illness2.visibility = View.GONE
        }

        binding.illness1.setOnClickListener { clicked() }
        binding.illness2.setOnClickListener { clicked() }
        binding.illness3.setOnClickListener { clicked() }
        binding.illness4.setOnClickListener { clicked() }
        binding.illness5.setOnClickListener { clicked() }
        binding.illness6.setOnClickListener { clicked() }
        binding.illness7.setOnClickListener { clicked() }
        binding.illness8.setOnClickListener { clicked() }
        binding.illness9.setOnClickListener { clicked() }
        binding.illness10.setOnClickListener { clicked() }
        binding.btnToVaccine.setOnClickListener { toVaccine() }
    }

    private fun clicked() {
        healthConditionsViewModel.incrementPriority()
    }

    private fun toVaccine() {
        next()
    }

    private fun next() {
        val priority = args.priority + healthConditionsViewModel.priorityAux
        val action = HealthConditionsFragmentDirections.actionHealthConditionsFragmentToVaccineFragment(args.name, args.lastName,
            args.gender, args.age, args.mail, "", 0, priority)
        navController!!.navigate(action)
    }
}