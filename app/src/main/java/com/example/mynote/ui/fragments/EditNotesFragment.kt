package com.example.mynote.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.mynote.R
import com.example.mynote.databinding.FragmentEditNotesBinding
import com.example.mynote.model.Notes
import com.example.mynote.viewmodel.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class EditNotesFragment : Fragment() {

    private lateinit var binding: FragmentEditNotesBinding
    private val notesFragmentArgs by navArgs<EditNotesFragmentArgs>()
    private lateinit var p: String
    private lateinit var menuHost: MenuHost

    private val viewModel: NotesViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        menuHost = requireActivity()
        menuHost.addMenuProvider(menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)

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


    private fun setPriority(priority: String) {
        when (priority) {
            "1" -> {
                binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pRed.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }
            "2" -> {
                binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pRed.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
            "3" -> {
                binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pGreen.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }
        }
    }


    /* override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
         inflater.inflate(R.menu.delete_menu, menu)
         super.onCreateOptionsMenu(menu, inflater)
     }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
         return super.onOptionsItemSelected(item)
     }*/


    private val menuProvider: MenuProvider = object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.delete_menu, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            //Toast.makeText(context, "id: "+menuItem.itemId, Toast.LENGTH_LONG).show()
            return when (menuItem.itemId) {
                R.id.delete_notes -> {
                    //Toast.makeText(context, "delete", Toast.LENGTH_LONG).show()
                    val bottomSheet: BottomSheetDialog = BottomSheetDialog(requireContext())
                    bottomSheet.setContentView(R.layout.dialog_delete)

                    val btnYes = bottomSheet.findViewById<TextView>(R.id.btnYes)
                    val btnNo = bottomSheet.findViewById<TextView>(R.id.btnNo)

                    btnYes?.setOnClickListener {
                        viewModel.deleteNotes(notesFragmentArgs.data.id!!)
                        navController.navigate(R.id.action_editNotesFragment_to_homeFragment)
                    }
                    btnNo?.setOnClickListener {
                        bottomSheet.dismiss()
                    }
                    bottomSheet.show()
                    true
                }
                else -> {
                    Toast.makeText(context, "back", Toast.LENGTH_LONG).show()
                    navController.navigate(R.id.action_editNotesFragment_to_homeFragment)
                    true
                }


            }
        }

    }




}