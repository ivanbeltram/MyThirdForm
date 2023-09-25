package com.example.mythirdform.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mythirdform.databinding.FragmentStartFormBinding

class StartFormFragment : Fragment() {

    private var _binding: FragmentStartFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.btnStartForm.setOnClickListener {
            val action = StartFormFragmentDirections.actionStartFormFragmentToQuestionFragment()
            findNavController().navigate(action)
        }

    }

}
