package com.example.war_android.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.war_android.Views.DashboardFragment;
import com.example.war_android.models.Game;
import com.example.war_android.models.Team;

import java.util.ArrayList;
import java.util.List;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    public MutableLiveData<List<Team>> teams;
    public MutableLiveData<List<Game>> games;
    private DashboardFragment dashboardFragment = new DashboardFragment();

    public DashboardViewModel() {
        teams = new MutableLiveData<>();
        games = new MutableLiveData<>();
        teams.setValue(dashboardFragment.teams);
        games.setValue(dashboardFragment.games);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Team>> getTeams() {
        return teams;
    }

    public LiveData<List<Game>> getGames() {
        return games;
    }
}