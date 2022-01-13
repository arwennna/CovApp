package com.example.covapp.ui.registration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.covapp.R
import com.example.covapp.databinding.FragmentBadCovidBinding

class BadCovidFragment : Fragment(), View.OnClickListener {

    var navController: NavController? = null
    private lateinit var binding: FragmentBadCovidBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bad_covid, container, false)
        /*badCovidViewModel =
            ViewModelProvider(this).get(BadCovidViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_bad_covid, container, false)
        val string: String = getString(R.string.citizenship_age)
        badCovidViewModel.updateActionBarTitle(string)
        return root*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBadCovidBinding.bind(view)
        navController = Navigation.findNavController(view)

        binding.btnToHome.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!! .id) {
            R.id.btn_toHome ->  {

                val action = BadCovidFragmentDirections.actionBadCovidFragmentToHomeFragment()
                navController!!.navigate(action)
            }
        }
    }
}