package com.example.mynote.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mynote.R
import com.example.mynote.databinding.ItemNotesBinding
import com.example.mynote.model.Notes
import com.example.mynote.ui.fragments.HomeFragmentDirections

class NotesAdapter(val requireContext: Context, val list: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    class NotesViewHolder(val itemview: ItemNotesBinding): RecyclerView.ViewHolder(itemview.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder = NotesViewHolder(
        ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val data = list[position]
        holder.itemview.notesTitle.text = data.title
        holder.itemview.notesSubTitle.text = data.subTitle
        holder.itemview.notesDate.text = data.date

        when(data.priority){
            "1" ->{holder.itemview.viewPriority.setBackgroundResource(R.drawable.green_dot)}
            "2" ->{holder.itemview.viewPriority.setBackgroundResource(R.drawable.yellow_dot)}
            "3" ->{holder.itemview.viewPriority.setBackgroundResource(R.drawable.red_dot)}
        }

        holder.itemview.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data = data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int = list.size

}