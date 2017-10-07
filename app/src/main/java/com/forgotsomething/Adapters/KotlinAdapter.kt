package com.forgotsomething.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.forgotsomething.Beans.ForgotDetail
import com.forgotsomething.R
import kotlinx.android.synthetic.main.row_kotlin_adapter.view.*

/**
 * Created by Harsh on 5/10/17.
 */

class KotlinAdapter(var context: Context, var allSchedules: ArrayList<ForgotDetail>) : RecyclerView.Adapter<KotlinAdapter.VH>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH {
        val view = LayoutInflater.from(context).inflate(R.layout.row_kotlin_adapter, parent, false)

        return VH(view)
    }

    override fun getItemCount(): Int = allSchedules.size

    override fun onBindViewHolder(holder: VH?, position: Int) {

        var details = allSchedules[position]

        holder?.items?.txtNumber?.text = details.id.toString()
        holder?.items?.txtDetail?.text = details.details
        holder?.items?.txttPlace?.text = details.places
    }

    class VH(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var items = itemView
    }

}