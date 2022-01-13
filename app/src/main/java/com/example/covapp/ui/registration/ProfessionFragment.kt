package com.example.covapp.ui.registration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.lifecycle.ViewModelProvider
import com.example.covapp.R
import com.example.covapp.databinding.FragmentProfessionBinding

class ProfessionFragment : Fragment() {

    private lateinit var professionViewModel: ProfessionViewModel
    var navController: NavController? = null
    private lateinit var binding: FragmentProfessionBinding
    private val args: CitizenshipAgeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        professionViewModel =
            ViewModelProvider(this).get(ProfessionViewModel::class.java)
        return inflater.inflate(R.layout.fragment_profession, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfessionBinding.bind(view)
        navController = Navigation.findNavController(view)

        binding.profession1.setOnClickListener { clicked() }
        binding.profession2.setOnClickListener { clicked() }
        binding.profession3.setOnClickListener { clicked() }
        binding.profession4.setOnClickListener { clicked() }
        binding.profession5.setOnClickListener { clicked() }
        binding.profession6.setOnClickListener { clicked() }
        binding.profession7.setOnClickListener { clicked() }
        binding.profession8.setOnClickListener { clickedOther() }
        binding.btnToCovid.setOnClickListener { toCovid() }
    }

    private fun clicked() {
        professionViewModel.incrementPriority()
        professionViewModel.incrementClicked()
    }

    private fun clickedOther() {
        professionViewModel.decrementPriority()
        professionViewModel.incrementClicked()
    }

    private fun toCovid() {
        next()
    }

    private fun next() {
        if (professionViewModel.checkZero(professionViewModel.clicked) == false) {
            Toast.makeText(activity, getString(R.string.toast_profession), Toast.LENGTH_LONG).show()
            return
        }
        val action = ProfessionFragmentDirections.actionProfessionFragmentToCovidFragment(args.name, args.lastName,
            args.gender, args.age, args.mail, "", 0, args.priority + professionViewModel.priorityAux)
        navController!!.navigate(action)
    }


}