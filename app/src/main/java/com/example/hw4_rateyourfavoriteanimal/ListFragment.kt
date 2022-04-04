package com.example.hw4_rateyourfavoriteanimal

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RatingBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {
    private lateinit var viewModel: AnimalSelection
    private var position = 0
    private var selectedItem = ""
    private val animalList = listOf("Dog", "Cat", "Bear", "Rabbit")

    private fun setClick(position1: Int, selectedItem1: String) {
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

        val imageIdList = listOf(
            R.id.dog_imageButton,
            R.id.cat_imageButton,
            R.id.bear_imageButton,
            R.id.rabbit_imageButton
        )
        for (imageId in imageIdList) {
            view.findViewById<ImageButton>(imageId).setOnClickListener {
                when (imageId) {
                    R.id.dog_imageButton -> {
                        setClick(0, "Dog")
                    }
                    R.id.cat_imageButton -> {
                        setClick(1, "Cat")
                    }
                    R.id.bear_imageButton -> {
                        setClick(2, "Bear")
                    }
                    else -> {
                        setClick(3, "Rabbit")
                    }
                }
                allowRotation()
            }
        }

        var sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        var dog_rating = sharedPreferences.getString(animalList[0], "-")
        var cat_rating = sharedPreferences.getString(animalList[1], "-")
        var bear_rating = sharedPreferences.getString(animalList[2], "-")
        var rabbit_rating = sharedPreferences.getString(animalList[3], "-")

        view.findViewById<TextView>(R.id.dog_rating_text).text = "Your rating: $dog_rating"
        view.findViewById<TextView>(R.id.cat_rating_text).text = "Your rating: $cat_rating"
        view.findViewById<TextView>(R.id.bear_rating_text).text = "Your rating: $bear_rating"
        view.findViewById<TextView>(R.id.rabbit_rating_text).text = "Your rating: $rabbit_rating"

        return view
    }

    private fun allowRotation() {
        viewModel.setPosition(position)

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, RatingFragment())
                .addToBackStack(null)
                .commit()
        } else {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.rating_container, RatingFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}