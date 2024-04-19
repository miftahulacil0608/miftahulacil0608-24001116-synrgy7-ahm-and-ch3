package com.example.recyclerviewinfragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewinfragment.adapter.databind.DataBinder
import com.example.recyclerviewinfragment.data.LeagueGroup
import com.example.recyclerviewinfragment.data.Teams


class RecyclerViewAdapter<T>(private val listData : ArrayList<T>, private val layoutResId:Int, private val dataBinder: DataBinder, private val onClickListener: RecyclerviewClickListener<T>):RecyclerView.Adapter<RecyclerViewAdapter<T>.ViewHolder>() {
    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        fun bind(item: T){
            when(item){
                is LeagueGroup -> dataBinder.bindListLeague(itemView,item,onClickListener as RecyclerviewClickListener<LeagueGroup>)
                is Teams -> dataBinder.bindTeamItem(itemView,item,onClickListener as RecyclerviewClickListener<Teams>)
                else -> throw IllegalAccessException("throw error $item")
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(layoutResId,parent,false)
        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItem = listData[position]
        holder.bind(dataItem)
    }
    override fun getItemCount(): Int {
        return listData.size
    }

}