package com.example.hw4_rateyourfavoriteanimal
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {
    lateinit var viewModel: AnimalSelection
    var position = 0
    var selectedItem = ""
    private val animalList = listOf("Dog", "Cat", "Bear", "Rabbit")

    fun setClick(position1: Int, selectedItem1: String)
    {
         position = position1
         selectedItem = selectedItem1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(AnimalSelection::class.java)

        view.dog_imageButton.setOnClickListener {
            setClick(0, "dog")
            allowRotation()
        }

        view.cat_imageButton.setOnClickListener{
            setClick(1,"cat")
            allowRotation()
        }

        view.bear_imageButton.setOnClickListener{
            setClick(2,"bear")
            allowRotation()
        }

        view.rabbit_imageButton.setOnClickListener{
            setClick(3,"rabbit")
            allowRotation()
        }


        var sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        var dog_rating = sharedPreferences.getString(animalList[0], "-")
        var cat_rating = sharedPreferences.getString(animalList[1], "-")
        var bear_rating = sharedPreferences.getString(animalList[2], "-")
        var rabbit_rating = sharedPreferences.getString(animalList[3], "-")

        view.findViewById<TextView>(R.id.dog_rating).text = "Your rating: $dog_rating"
        view.findViewById<TextView>(R.id.cat_rating).text = "Your rating: $cat_rating"
        view.findViewById<TextView>(R.id.bear_rating).text = "Your rating: $bear_rating"
        view.findViewById<TextView>(R.id.rabbit_rating).text = "Your rating: $rabbit_rating"
        return view
    }

    fun allowRotation(){
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, RatingFragment())
                .addToBackStack(null)
                .commit()
        }
        else {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.rating_container, RatingFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}