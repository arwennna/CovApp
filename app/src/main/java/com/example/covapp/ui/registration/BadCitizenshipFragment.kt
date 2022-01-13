package com.example.covapp.ui.registration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.covapp.R
import com.example.covapp.databinding.FragmentBadCitizenshipBinding

class BadCitizenshipFragment : Fragment(), View.OnClickListener {

    var navController: NavController? = null
    private lateinit var binding: FragmentBadCitizenshipBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bad_citizenship, container, false)
        /*badCitizenshipViewModel =
                ViewModelProvider(this).get(BadCitizenshipViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_bad_citizenship, container, false)
        val string: String = getString(R.string.citizenship_age)
        badCitizenshipViewModel.updateActionBarTitle(string)
        return root*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBadCitizenshipBinding.bind(view)
        navController = Navigation.findNavController(view)

        binding.btnToHome.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!! .id) {
            R.id.btn_toHome ->  {

                val action = BadCitizenshipFragmentDirections.actionBadCitizenshipFragmentToHomeFragment()
                navController!!.navigate(action)
            }
        }
    }
}