package com.example.covapp.ui.registration

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.covapp.R
import com.example.covapp.databinding.FragmentSummaryBinding
import java.util.*


class SummaryFragment: Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentSummaryBinding
    var navController: NavController? = null
    private val args: VaccineFragmentArgs by navArgs()
    var gender:String? = null
    var vaccine:String? = null
    private var location:String? = null
    private var dayOfMonth:Int? = null
    private var month:Int? = null
    private var year:Int? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setTitle(R.string.summary)

       return inflater.inflate(R.layout.fragment_summary, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_share, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.share -> {
                sendMessage()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun sendMessage() {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(
            Intent.EXTRA_TEXT,
            getString(R.string.message_succ)+"\n"+"\n"+
                getString(R.string.message_date) +" "+ dayOfMonth.toString() + "." + month.toString() + "." + year.toString() + ". "+
                getString(R.string.message_time) + " " + "09:30" + ", " + location + "."
        )
        intent.type = "text/plain"
        startActivity(intent)
    }

    //ideja preuzeta sa Stackoverflow https://stackoverflow.com/questions/48335329/how-to-get-date-7-days-ago-to-today-in-kotlin/48335670
    private fun getTime(days: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, days)
        return calendar
    }

    private fun getVaccine(i: Int): String{
        when (i) {
            1 -> return "AstraZeneca"
            2 -> return "Pfizer"
            3 -> return "Sinopharm"
            else -> {
                return "Pogrešan unos"
            }
        }
    }

    private fun getLocation(s: String):String{
        when (s) {
            "Sarajevo" -> return "Zetra Sarajevo"
            "Tuzla" -> return "DZ Tuzla"
            "Banja Luka" -> return "DZ Banja Luka"
            "Mostar" -> return "DZ Mostar"
            else -> {
                return "Pogrešan unos"
            }
        }
    }

    private fun getGender(b: Boolean):String{
        when (b) {
            false -> return "M"
            true -> return "F"
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSummaryBinding.bind(view)
        navController = Navigation.findNavController(view)
        binding.btnToHome.setOnClickListener(this)
        var date:Calendar? = null

        gender = getGender(args.gender)
        vaccine = getVaccine(args.vaccine)
        location = getLocation(args.location)

        date = if (args.priority >= 6) {
            getTime(2)
        } else if (args.priority >= 3) {
            getTime(5)
        } else {
            getTime(10)
        }

        dayOfMonth = date.get(Calendar.DAY_OF_MONTH)
        month = date.get(Calendar.MONTH) + 1
        year = date.get(Calendar.YEAR)
        val dateString = dayOfMonth.toString() + "." + month.toString() + "." + year.toString()

        binding.summaryName.text = args.name
        binding.summaryLastName.text = args.lastName
        binding.summaryEmail.text = args.mail
        binding.summaryAge.text = args.age.toString()
        binding.summaryGender.text = gender
        binding.summaryVaccine.text = vaccine
        binding.summaryLocation.text = location
        binding.summaryDate.text = dateString
        binding.summaryTime.text = "09:30"
    }

    override fun onClick(v: View?) {
        when(v!! .id) {
            R.id.btn_toHome ->  {
                val action = SummaryFragmentDirections.actionSummaryFragmentToHomeFragment()
                navController!!.navigate(action)
            }

        }
    }
}