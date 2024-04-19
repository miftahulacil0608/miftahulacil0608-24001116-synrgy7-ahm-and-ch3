package com.example.recyclerviewinfragment.adapter.databind

import android.view.View
import com.example.recyclerviewinfragment.R
import com.example.recyclerviewinfragment.adapter.RecyclerviewClickListener
import com.example.recyclerviewinfragment.data.LeagueGroup
import com.example.recyclerviewinfragment.data.Teams

import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class DataBinder {
    fun bindListLeague(itemView: View, league: LeagueGroup, onClickListener: RecyclerviewClickListener<LeagueGroup>){
        val imageLeague = itemView.findViewById<ShapeableImageView>(R.id.img_league)
        val titleLeague = itemView.findViewById<MaterialTextView>(R.id.title_league_group)
        imageLeague.setImageResource(league.imgLeague)
        titleLeague.text = league.title

        itemView.setOnClickListener {
            onClickListener.onClickListener(league)
        }
    }
    fun bindTeamItem(itemView: View, listTeam: Teams,onClickListener: RecyclerviewClickListener<Teams>) {
        val imageTeam: ShapeableImageView = itemView.findViewById(R.id.img_team)
        val titleTeam: MaterialTextView = itemView.findViewById(R.id.title_team)
        val cityTeam: MaterialTextView = itemView.findViewById(R.id.city_team)
        imageTeam.setImageResource(listTeam.imgTeams)
        titleTeam.text = listTeam.titleTeams
        cityTeam.text = listTeam.cityTeam

        itemView.setOnClickListener {
            onClickListener.onClickListener(listTeam)
        }
    }
}