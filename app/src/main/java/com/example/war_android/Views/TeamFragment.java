package com.example.war_android.Views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.war_android.R;
import com.example.war_android.ViewModels.HomeViewModel;
import com.example.war_android.models.Team;

public class TeamFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private DashboardFragment dashboardFragment;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_team, container, false);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });


        final EditText teamNumber = root.findViewById(R.id.team_number_input);
        final EditText teamHeight = root.findViewById(R.id.height_input);
        final EditText teamWeight = root.findViewById(R.id.weight_input);
        final EditText teamDriveTrain = root.findViewById(R.id.drive_train_input);
        final EditText autoNotes = root.findViewById(R.id.auto_input);
        final EditText additionalNotes = root.findViewById(R.id.notes_input);


        final Spinner climbDropdown = root.findViewById(R.id.climb_spinner);
        String[] climbItems = new String[]{"yes", "no"};
        ArrayAdapter<String> climbAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, climbItems);
        climbDropdown.setAdapter(climbAdapter);

        final Spinner balanceDropdown = root.findViewById(R.id.balance_spinner);
        String[] balanceItems = new String[]{"yes", "no"};
        ArrayAdapter<String> balanceAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, balanceItems);
        balanceDropdown.setAdapter(balanceAdapter);

        final Spinner colorWheelDropdown = root.findViewById(R.id.wheel_spinner);
        String[] colorWheelItems = new String[]{"rotate", "choose color", "none"};
        ArrayAdapter<String> colorWheelAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, colorWheelItems);
        colorWheelDropdown.setAdapter(colorWheelAdapter);

        final Spinner shooterDropdown = root.findViewById(R.id.shooter_spinner);
        String[] shooterItems = new String[]{"yes", "no"};
        ArrayAdapter<String> shooterAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, shooterItems);
        shooterDropdown.setAdapter(shooterAdapter);

        Button teamSubmit = root.findViewById(R.id.team_submit);
        teamSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Team team = new Team();
                team.setNumber(teamNumber.getText().toString());
                team.setHeight(teamHeight.getText().toString());
                team.setWeight(teamWeight.getText().toString());
                team.setDriveTrain(teamDriveTrain.getText().toString());
                team.setClimb(climbDropdown.getSelectedItem().toString());
                team.setColorWheel(colorWheelDropdown.getSelectedItem().toString());
                team.setBalance(balanceDropdown.getSelectedItem().toString());
                team.setShooter(shooterDropdown.getSelectedItem().toString());
                team.setAutoDescription(autoNotes.getText().toString());
                team.setAdditionalDescription(additionalNotes.getText().toString());
                dashboardFragment.teams.add(team);
            }
        });

        return root;
    }
}