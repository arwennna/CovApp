package com.example.covapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.covapp.R
import com.example.covapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), View.OnClickListener {
    var navController: NavController? = null
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        binding = FragmentHomeBinding.bind(view)

        binding.btnToPersonal!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!! .id) {
            R.id.btn_toPersonal -> navController!!.navigate(R.id.action_nav_home_to_personalInfoFragment)
        }
    }
}