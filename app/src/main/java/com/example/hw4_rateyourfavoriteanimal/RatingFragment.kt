package com.example.hw4_rateyourfavoriteanimal

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.hw4_rateyourfavoriteanimal.R


private const val ARG_POSITION = "positionList"

class RatingFragment : Fragment() {
    lateinit var viewModel: AnimalSelection
    private val animalNames = listOf("Dog", "Cat", "Bear", "Rabbit")
    private var position = 0

    fun saveRating(view: View) {
        val ratingBar = view.findViewById<RatingBar>(R.id.rating_bar)
        val rating = ratingBar.rating.toString()
        val sharedPreferences = requireActivity().getPreferences(MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        if (editor != null) {
            editor.putString(animalNames[position], rating)
            editor.apply()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, ListFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rating, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(AnimalSelection::class.java)
        val position = viewModel.getPosition() ?: 0
        val imageId = when (position) {
            0 -> R.drawable.dog
            1 -> R.drawable.cat
            2 -> R.drawable.bear
            else -> R.drawable.rabbit
        }
        view.findViewById<ImageView>(R.id.animal_image).setImageResource(imageId)
        view.findViewById<TextView>(R.id.animal_name).text = animalNames[position]
        view.findViewById<Button>(R.id.save_rating_button).setOnClickListener{saveRating(view)}

        return view
    }
}