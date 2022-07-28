package com.example.mynote.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.mynote.R
import com.example.mynote.databinding.FragmentEditNotesBinding
import com.example.mynote.model.Notes
import com.example.mynote.viewmodel.NotesViewModel
import java.util.*

class EditNotesFragment : Fragment() {

    private lateinit var binding: FragmentEditNotesBinding
    private val notesFragmentArgs by navArgs<EditNotesFragmentArgs>()
    private lateinit var p: String

    private val viewModel:NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)

        binding.edtTitle.setText(notesFragmentArgs.data.title)
        binding.edtSubtitle.setText(notesFragmentArgs.data.subTitle)
        binding.edtNotes.setText(notesFragmentArgs.data.notes)
        p = notesFragmentArgs.data.priority

        setPriority(p)



        binding.pGreen.setOnClickListener {
            p = "1"
            setPriority(p)

        }
        binding.pYellow.setOnClickListener {
            p = "2"
            setPriority(p)

        }
        binding.pRed.setOnClickListener {
            p = "3"
            setPriority(p)

        }

        binding.btnSaveNotes.setOnClickListener {
            updateNotes(it)
        }

        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.edtTitle.text.toString()
        val subTitle = binding.edtSubtitle.text.toString()
        val notes = binding.edtNotes.text.toString()

        val date = Date()
        val notesDate = DateFormat.format("MMMM d, yyyy", date.time).toString()

        val data = Notes(
            id = notesFragmentArgs.data.id,
            title = title,
            subTitle = subTitle,
            notes = notes,
            date = notesDate,
            priority = p
        )
        viewModel.updateNotes(data)
        Toast.makeText(activity, "Notes updated successfully", Toast.LENGTH_LONG).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)
    }


    private fun setPriority(priority: String){
        when(priority){
            "1" ->{
                binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pRed.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }
            "2" ->{
                binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pRed.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
            "3" ->{
                binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pGreen.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }
        }



    }
}