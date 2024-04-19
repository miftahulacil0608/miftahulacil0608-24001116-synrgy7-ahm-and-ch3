package com.example.recyclerviewinfragment.fragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewinfragment.R
import com.example.recyclerviewinfragment.adapter.RecyclerViewAdapter
import com.example.recyclerviewinfragment.data.LeagueGroup
import com.example.recyclerviewinfragment.Helper
import com.example.recyclerviewinfragment.data.ListTeam
import com.example.recyclerviewinfragment.databinding.FragmentListLeagueGroupBinding

class ListLeagueGroupFragment : Fragment() {

    private lateinit var listLeagueGroupFragmentBinding: FragmentListLeagueGroupBinding
    private var listLeague = ArrayList<Any>()
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listLeagueGroupFragmentBinding =
            FragmentListLeagueGroupBinding.inflate(inflater, container, false)
        return listLeagueGroupFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listLeagueGroupFragmentBinding.rvContainer.setHasFixedSize(true)
        showRecycleList()
    }


    private fun showRecycleList() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = RecyclerViewAdapter(getListLeagueGroup())
        listLeagueGroupFragmentBinding.rvContainer.layoutManager = layoutManager
        listLeagueGroupFragmentBinding.rvContainer.adapter = adapter
        adapter.notifyDataSetChanged()
        onClickListener()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_option,menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.list_layout -> {
                listLeagueGroupFragmentBinding.rvContainer.layoutManager = LinearLayoutManager(context)
                true
            }
            R.id.grid_layout -> {
                listLeagueGroupFragmentBinding.rvContainer.layoutManager = GridLayoutManager(context,2)
                true
            }
            else-> super.onOptionsItemSelected(item)

        }
    }

    private fun onClickListener() {
        val fragmentManager = parentFragmentManager
        val detailRecyclerViewFragment = DetailRecyclerViewFragment()
        adapter.setOnClickListener(object : RecyclerViewAdapter.OnClickListener {
                override fun onClickLeagueGroup(leagueGroup: LeagueGroup) {
                    fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container,detailRecyclerViewFragment,DetailRecyclerViewFragment::class.java.simpleName)
                        .addToBackStack(null)
                        .commit()
                    val bundle = Bundle()
                    bundle.putParcelable(DetailRecyclerViewFragment.EXTRA_PARCELABLE, leagueGroup)
                    detailRecyclerViewFragment.arguments = bundle
                }

                override fun onClickTeam(listTeam: ListTeam) {
                    //nothing happen
                }

            })

    }

    private fun getListLeagueGroup(): ArrayList<Any> {
        val listDataTeamPremierLeague = Helper.listTeam(
            resources,
            R.array.list_image_premier_league,
            R.array.list_premier_league,
            R.array.list_city_team_premier_league
        )
        val listDataTeamBundesLiga = Helper.listTeam(
            resources,
            R.array.list_image_bundesliga,
            R.array.list_bundesliga,
            R.array.list_city_team_bundesliga
        )
        val listDataTeamSerieA = Helper.listTeam(
            resources,
            R.array.list_image_serie_a,
            R.array.list_serie_a,
            R.array.list_city_team_serie_a
        )
        val listDataTeamLaliga = Helper.listTeam(
            resources,
            R.array.data_image_league_group,
            R.array.data_league_group,
            R.array.data_league_group
        )
        val listDataTeamEradivisie = Helper.listTeam(
            resources,
            R.array.data_image_league_group,
            R.array.data_league_group,
            R.array.data_league_group
        )

        val listDataTeamLeagueGroup = arrayListOf(
            listDataTeamPremierLeague,
            listDataTeamBundesLiga,
            listDataTeamSerieA,
            listDataTeamLaliga,
            listDataTeamEradivisie
        )


        Log.d("isi dari listDataTeamGroup","${listDataTeamLeagueGroup.size}")

        Helper.listLeagueGroup(
            resources,
            R.array.data_image_league_group,
            R.array.data_league_group,
            listDataTeamLeagueGroup
        ).also {
            listLeague.clear()
            listLeague.addAll(it)
        }
        return listLeague
    }


}