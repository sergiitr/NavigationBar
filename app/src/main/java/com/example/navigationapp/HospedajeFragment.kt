package com.example.navigationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class HospedajeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflamos la vista usando el método tradicional (R.layout.fragment_hospedaje)
        val view = inflater.inflate(R.layout.fragment_hospedaje, container, false)

        return view
    }
}