package com.example.war_android.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.war_android.R;
import com.example.war_android.ViewModels.DashboardViewModel;
import com.example.war_android.adapters.DashboardAdapter;
import com.example.war_android.adapters.GamesAdapter;
import com.example.war_android.models.Game;
import com.example.war_android.models.Team;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    public ArrayList<Team> teams = new ArrayList<Team>();
    public ArrayList<Game> games = new ArrayList<Game>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        RecyclerView trv = root.findViewById(R.id.teams_recycler);
        RecyclerView grv = root.findViewById(R.id.games_recycler);

        LinearLayoutManager tLayoutManager = new LinearLayoutManager(getContext());
        LinearLayoutManager gLayoutManager = new LinearLayoutManager(getContext());

        trv.setLayoutManager(tLayoutManager);
        grv.setLayoutManager(gLayoutManager);

        final DashboardAdapter dashboardAdapter = new DashboardAdapter(teams);
        trv.setAdapter(dashboardAdapter);

        GamesAdapter gamesAdapter = new GamesAdapter(games);
        grv.setAdapter(gamesAdapter);

        dashboardViewModel.getTeams().observe(getViewLifecycleOwner(), teams -> {
            dashboardAdapter.addAll(teams);
        });

        dashboardViewModel.getGames().observe(getViewLifecycleOwner(), games -> {
            gamesAdapter.addAll(games);
        });




        return root;
    }
}