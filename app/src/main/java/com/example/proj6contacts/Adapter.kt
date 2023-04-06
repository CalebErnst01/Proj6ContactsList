package com.example.proj6contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class Adapter(private val contactList: List<String>, private val fullNames: List<String>, private val phoneNumbers: List<String>): RecyclerView.Adapter<Adapter.viewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.viewHolder
    {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact, parent, false)

        return RecyclerView.ViewHolder(view)
    }

    override fun getItemCount() = contactList.size

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(contactList[position])
            .centerCrop()
            .into(holder.petImage)
    }

    class viewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val petImage: ImageView
        var Name: textView
        var Number: textView

        init {
            // Find our RecyclerView item's ImageView for future use
            petImage = view.findViewById(R.id.icon)
            Name     = view.findViewById(R.id.Name)
            Number   = view.findViewById(R.id.PhoneNum)

            Name = "John Doe"
            Number = "214-123-7890"
        }
    }
}