package com.shihab.kotlintoday.feature.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.shihab.kotlintoday.R
import com.shihab.kotlintoday.feature.mvvm.model.Note
import kotlinx.android.synthetic.main.item_adapter.view.*
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter(val notes: List<Note>) : Adapter<NoteAdapter.NoteHolder>() {

    class NoteHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var title = itemview.text_view_title
        var details = itemview.text_view_description
        var priority = itemview.text_view_priority
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {

        val itemview =
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteHolder(itemview)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {

        val note = notes[position]
        holder.title.text = note.title
        holder.details.text = note.description
        holder.priority.text = note.priority.toString()
    }
}