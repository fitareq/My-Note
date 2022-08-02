package com.example.mynote.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mynote.R
import com.example.mynote.databinding.ItemNotesBinding
import com.example.mynote.model.Notes
import com.example.mynote.ui.fragments.HomeFragmentDirections

class NotesAdapter(val requireContext: Context, val dataList: MutableList<Notes>)
    :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>(), Filterable {
    val dataListFull: MutableList<Notes> = ArrayList()
    init {
        dataListFull.addAll(dataList)
    }


    class NotesViewHolder(val itemview: ItemNotesBinding): RecyclerView.ViewHolder(itemview.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder = NotesViewHolder(
        ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val data = dataList[position]
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

    override fun getItemCount(): Int = dataList.size


    override fun getFilter(): Filter = notesFilter

    private val notesFilter:Filter = object : Filter(){

        override fun performFiltering(p0: CharSequence?): FilterResults {


            val filterData: MutableList<Notes> = arrayListOf()

            if (p0 == null || p0.isEmpty()) {
                    filterData.addAll(dataListFull)
            }else{
                    val filterPattern = p0.toString().trim()
                    for (n in dataListFull){
                        if (n.priority == filterPattern)
                            filterData.add(n)
                    }
            }
            val result: FilterResults = FilterResults()
            result.values = filterData
            return result

        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            dataList.clear()
            dataList.addAll(p1?.values as MutableList<Notes>)
            notifyDataSetChanged()
        }

    }


}