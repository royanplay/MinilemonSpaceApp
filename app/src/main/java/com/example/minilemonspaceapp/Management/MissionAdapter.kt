package com.example.minilemonspaceapp.Management

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minilemonspaceapp.R

class MissionAdapter(private val missions: List<MissionManagement.Mission>) : RecyclerView.Adapter<MissionAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val missionNameTextView: TextView = itemView.findViewById(R.id.missionNameTextView)
        // Tambahkan elemen UI lainnya untuk menampilkan detail misi
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mission_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mission = missions[position]
        holder.missionNameTextView.text = mission.name
        // Isi elemen UI lainnya dengan data misi sesuai kebutuhan
    }

    override fun getItemCount(): Int {
        return missions.size
    }
}
