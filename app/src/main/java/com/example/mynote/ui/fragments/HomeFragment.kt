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
import com.example.mynote.model.Notes
import com.example.mynote.ui.adapter.NotesAdapter
import com.example.mynote.viewmodel.NotesViewModel

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private val viewModel: NotesViewModel by viewModels()
    private lateinit var notesAdapter: NotesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)




        viewModel.getAllNotes().observe(viewLifecycleOwner) {
            binding.rcvAllNotes.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

            notesAdapter = NotesAdapter(requireContext(), it as MutableList<Notes>)
            binding.rcvAllNotes.adapter = notesAdapter
        }


        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNoteFragment)
        }

        binding.chipGroup.setOnCheckedStateChangeListener { group, _ ->

            when (group.checkedChipId) {
                R.id.chipHigh -> {
                    notesAdapter.filter.filter("3")
                }
                R.id.chipMedium -> {
                    notesAdapter.filter.filter("2")
                }
                R.id.chipLow -> {
                    notesAdapter.filter.filter("1")
                }
                else -> {
                    notesAdapter.filter.filter("")
                }
            }

        }


        return binding.root
    }
}