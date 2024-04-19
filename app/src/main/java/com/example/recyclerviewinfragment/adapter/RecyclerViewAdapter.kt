package com.example.recyclerviewinfragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recyclerviewinfragment.R
import com.example.recyclerviewinfragment.data.LeagueGroup
import com.example.recyclerviewinfragment.data.ListTeam
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class RecyclerViewAdapter(private val leagueOrTeam: ArrayList<Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var onClickListener: OnClickListener

    //interface untuk custom onClickListener
    interface OnClickListener {
        fun onClickLeagueGroup(leagueGroup: LeagueGroup)
        fun onClickTeam(listTeam: ListTeam)
    }

    class League(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageLeague: ShapeableImageView = itemView.findViewById(R.id.img_league)
        val titleLeague: MaterialTextView = itemView.findViewById(R.id.title_league_group)
    }

    class Team(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageTeam: ShapeableImageView = itemView.findViewById(R.id.img_team)
        val titleTeam: MaterialTextView = itemView.findViewById(R.id.title_team)
        val cityTeam: MaterialTextView = itemView.findViewById(R.id.city_team)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_LEAGUE_GROUP -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_view_league_group, parent, false)
                League(view.rootView)
            }

            VIEW_TYPE_LIST_TEAM -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_view_team, parent, false)
                Team(view.rootView)
            }

            else -> throw IllegalAccessException("throw error $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_LEAGUE_GROUP -> {
                val holder = holder as League
                val dataLeague = leagueOrTeam[holder.adapterPosition] as LeagueGroup
                holder.imageLeague.setImageResource(dataLeague.image)
                holder.titleLeague.text = dataLeague.title

                //beri callback listener ya
                holder.itemView.setOnClickListener {
                    onClickListener?.onClickLeagueGroup(dataLeague)
                }

            }

            VIEW_TYPE_LIST_TEAM -> {
                val holder = holder as Team
                val (imgTeam, teamName, cityTeam) = leagueOrTeam[holder.adapterPosition] as ListTeam
                holder.imageTeam.setImageResource(imgTeam)
                holder.titleTeam.text = teamName
                holder.cityTeam.text = cityTeam

                holder.itemView.setOnClickListener {
                    onClickListener?.onClickTeam(leagueOrTeam[holder.absoluteAdapterPosition] as ListTeam)
                }


            }

            else -> throw IllegalAccessException("throw error ${holder.itemViewType}")
        }
    }

    override fun getItemCount(): Int {
        return leagueOrTeam.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (leagueOrTeam[position]) {
            is LeagueGroup -> VIEW_TYPE_LEAGUE_GROUP
            is ListTeam -> VIEW_TYPE_LIST_TEAM
            else -> throw IllegalAccessException("throw error $position")
        }
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    companion object {
        const val VIEW_TYPE_LEAGUE_GROUP = 0
        const val VIEW_TYPE_LIST_TEAM = 1
    }

}