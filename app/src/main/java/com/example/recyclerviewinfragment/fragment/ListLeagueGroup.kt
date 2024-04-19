package com.example.recyclerviewinfragment.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewinfragment.Helper
import com.example.recyclerviewinfragment.R
import com.example.recyclerviewinfragment.adapter.RecyclerViewAdapter
import com.example.recyclerviewinfragment.adapter.RecyclerviewClickListener
import com.example.recyclerviewinfragment.adapter.databind.DataBinder
import com.example.recyclerviewinfragment.data.LeagueGroup
import com.example.recyclerviewinfragment.databinding.FragmentListLeagueGroupBinding


class ListLeagueGroup : Fragment(), RecyclerviewClickListener<LeagueGroup> {
    private lateinit var binding: FragmentListLeagueGroupBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as AppCompatActivity).supportActionBar?.title = "League"
        return FragmentListLeagueGroupBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvListLeagueGroup.setHasFixedSize(true)

        showRecyclerView()

    }

    override fun onClickListener(data: LeagueGroup) {
        /*//safeArg
        val dataTitle = data.title
        val dataImages = data.imgLeague

        val dataSafeArgs = ListLeagueGroupDirections.actionListLeagueGroupToDetailLeagueTeams(
            name = dataTitle,
            images = dataImages
        )*/
        val bundle = Bundle()
        bundle.putParcelable("EXTRA", data)
        findNavController().navigate(R.id.action_listLeagueGroup_to_detailLeagueTeams, bundle)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_option, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.list_layout -> {
                binding.rvListLeagueGroup.layoutManager = LinearLayoutManager(context)
                true
            }

            R.id.grid_layout -> {
                binding.rvListLeagueGroup.layoutManager = GridLayoutManager(context, 2)
                true
            }

            else -> super.onOptionsItemSelected(item)

        }
    }

    private fun showRecyclerView() {
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val adapter =
            RecyclerViewAdapter(
                addDataRecyclerView(),
                R.layout.item_view_league,
                DataBinder(),
                this
            )
        binding.rvListLeagueGroup.layoutManager = layoutManager
        binding.rvListLeagueGroup.adapter = adapter
        binding.rvListLeagueGroup.itemAnimator = DefaultItemAnimator()
        adapter.notifyDataSetChanged()
    }

    private fun addDataRecyclerView(): ArrayList<LeagueGroup> {
        val listLeague = ArrayList<LeagueGroup>()

        val listTeamPremierLeague = Helper.addListTeams(
            resources = resources,
            resImg = R.array.list_image_premier_league,
            resTitle = R.array.list_premier_league,
            resCity = R.array.list_city_team_premier_league
        )
        val listBundesliga = Helper.addListTeams(
            resources,
            R.array.list_image_bundesliga,
            R.array.list_bundesliga,
            R.array.list_city_team_bundesliga
        )
        val listSerieA = Helper.addListTeams(
            resources,
            R.array.list_image_serie_a,
            R.array.list_serie_a,
            R.array.list_city_team_serie_a
        )
        val listLaLigaSantander = Helper.addListTeams(
            resources,
            R.array.data_image_league_group,
            R.array.data_league_group,
            R.array.data_league_group
        )
        val listEredivisie = Helper.addListTeams(
            resources,
            R.array.data_image_league_group,
            R.array.data_league_group,
            R.array.data_league_group
        )

        val listTeams = arrayListOf(
            listTeamPremierLeague,
            listBundesliga,
            listSerieA,
            listLaLigaSantander,
            listEredivisie
        )


        listLeague.clear()
        listLeague.addAll(
            Helper.addListLeagueGroup(
                resources,
                R.array.data_image_league_group,
                R.array.data_league_group,
                listTeams
            )
        )
        return listLeague
    }


}