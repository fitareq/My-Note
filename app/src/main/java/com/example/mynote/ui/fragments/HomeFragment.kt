package com.example.mynote.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mynote.R
import com.example.mynote.databinding.FragmentHomeBinding
import com.example.mynote.ui.adapter.NotesAdapter
import com.example.mynote.viewmodel.NotesViewModel

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)


        viewModel.getAllNotes().observe(viewLifecycleOwner) {
            binding.rcvAllNotes.layoutManager = GridLayoutManager(requireActivity(), 2)
            binding.rcvAllNotes.adapter = NotesAdapter(requireContext(), it)
        }


        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNoteFragment)
        }

        binding.chipHigh.setOnClickListener {
            if (binding.chipHigh.isChecked) Toast.makeText(context, "High Checked", Toast.LENGTH_LONG)
            else Toast.makeText(context, "High unchecked", Toast.LENGTH_LONG)
        }

        return binding.root
    }
}