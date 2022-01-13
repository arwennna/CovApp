package com.example.covapp.ui.statistics

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.covapp.R
import com.example.covapp.databinding.FragmentStatisticsBinding
import java.util.*

const val KEY_VACCINATED = "vaccinated_key"
const val KEY_PFIZER = "pfizer_key"
const val KEY_ASTRAZENECA = "astrazeneca_key"
const val KEY_SINOPHARM = "sinopharm_key"

class StatisticsFragment : Fragment() {

    private lateinit var binding : FragmentStatisticsBinding

    private lateinit var sharedPreference: SharedPreference

    var vaccinated = 0
    var pfizer = 0
    var astrazeneca = 0
    var sinopharm = 0

    var timer:Timer? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val act : Activity? = requireActivity()
        sharedPreference = SharedPreference(act!!)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_statistics , container, false)
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null) {
            vaccinated = savedInstanceState.getInt(KEY_VACCINATED, 0)
            pfizer = savedInstanceState.getInt(KEY_PFIZER, 0)
            astrazeneca = savedInstanceState.getInt(KEY_ASTRAZENECA, 0)
            sinopharm = savedInstanceState.getInt(KEY_SINOPHARM, 0)
        } else {
            vaccinated = 0
            pfizer = 0
            astrazeneca = 0
            sinopharm = 0
        }
    }

    override fun onStart() {
        super.onStart()
        vaccinated = sharedPreference.getValueInt("vaccinated")
        pfizer = sharedPreference.getValueInt("pfizer")
        astrazeneca = sharedPreference.getValueInt("astrazeneca")
        sinopharm = sharedPreference.getValueInt("sinopharm")
    }

    override fun onResume() {
        super.onResume()
        vaccinated = sharedPreference.getValueInt("vaccinated")
        pfizer = sharedPreference.getValueInt("pfizer")
        astrazeneca = sharedPreference.getValueInt("astrazeneca")
        sinopharm = sharedPreference.getValueInt("sinopharm")

        timer =  Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                activity!!.runOnUiThread {
                    vaccinate()
                }
            }
        }, 0, 1000)
    }

    override fun onPause() {
        super.onPause()
        sharedPreference.save("vaccinated", vaccinated)
        sharedPreference.save("pfizer", pfizer)
        sharedPreference.save("astrazeneca", astrazeneca)
        sharedPreference.save("sinopharm", sinopharm)
        timer!!.cancel()
    }

    override fun onStop() {
        super.onStop()
        sharedPreference.save("vaccinated", vaccinated)
        sharedPreference.save("pfizer", pfizer)
        sharedPreference.save("astrazeneca", astrazeneca)
        sharedPreference.save("sinopharm", sinopharm)
        timer!!.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        sharedPreference.save("vaccinated", vaccinated)
        sharedPreference.save("pfizer", pfizer)
        sharedPreference.save("astrazeneca", astrazeneca)
        sharedPreference.save("sinopharm", sinopharm)
        timer!!.cancel()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_VACCINATED, vaccinated)
        outState.putInt(KEY_PFIZER, pfizer)
        outState.putInt(KEY_ASTRAZENECA, astrazeneca)
        outState.putInt(KEY_SINOPHARM, sinopharm)
    }

    fun vaccinate() {
        var a = (Math.random() * ((3 + 1) - 1) + 1).toInt()

        vaccinated += 1

        if (a==1) pfizer += 1
        else if (a == 2) astrazeneca += 1
        else sinopharm += 1

        binding.vaccinatedPfizer?.setText(pfizer.toString())
        binding.vaccinatedAstrazeneca?.setText(astrazeneca.toString())
        binding.vaccinatedSinopharm?.setText(sinopharm.toString())
        binding.vaccinatedTotal?.setText(vaccinated.toString())
    }
}