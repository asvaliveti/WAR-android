package com.example.war_android.Views;

import android.os.Bundle;
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
import com.example.war_android.ViewModels.NotificationsViewModel;
import com.example.war_android.models.Game;

public class GameFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private DashboardFragment dashboardFragment = new DashboardFragment();
    private int autoLower = 0;
    private int autoOuter = 0;
    private int autoInner = 0;
    private int lower = 0;
    private int outer = 0;
    private int inner = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_game, container, false);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        final Spinner gameDropdown = root.findViewById(R.id.game_spinner);
        String[] gameItems = new String[]{"practice", "qualifier"};
        ArrayAdapter<String> gameAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, gameItems);
        gameDropdown.setAdapter(gameAdapter);

        final Spinner climbDropdown = root.findViewById(R.id.climb_spinner_game);
        String[] climbItems = new String[]{"park", "climb", "climb and balance", "none"};
        final ArrayAdapter<String> climbAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, climbItems);
        climbDropdown.setAdapter(climbAdapter);

        final Spinner colorDropdown = root.findViewById(R.id.wheel_spinner_game);
        String[] colorItems = new String[]{"stage 2", "stage 3", "none"};
        ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, colorItems);
        colorDropdown.setAdapter(colorAdapter);

        Button aLP = root.findViewById(R.id.auto_lower_plus);
        Button aOP = root.findViewById(R.id.auto_outer_plus);
        Button aIP = root.findViewById(R.id.auto_inner_plus);
        Button aLM = root.findViewById(R.id.auto_lower_minus);
        Button aOM = root.findViewById(R.id.auto_outer_minus);
        Button aIM = root.findViewById(R.id.auto_inner_minus);

        Button lP = root.findViewById(R.id.lower_plus);
        Button oP = root.findViewById(R.id.outer_plus);
        Button iP = root.findViewById(R.id.inner_plus);
        Button lM = root.findViewById(R.id.lower_minus);
        Button oM = root.findViewById(R.id.outer_minus);
        Button iM = root.findViewById(R.id.inner_minus);

        final TextView aLower = root.findViewById(R.id.auto_lower_number);
        final TextView aOuter = root.findViewById(R.id.auto_outer_number);
        final TextView aInner = root.findViewById(R.id.auto_inner_number);
        final TextView tLower = root.findViewById(R.id.lower_number);
        final TextView tOuter = root.findViewById(R.id.outer_number);
        final TextView tInner = root.findViewById(R.id.inner_number);

        aLP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoLower++;
                aLower.setText(autoLower + "");
            }
        });

        aOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoOuter++;
                aOuter.setText(autoOuter + "");
            }
        });

        aIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoInner++;
                aInner.setText(autoInner + "");
            }
        });

        aLM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoLower--;
                aLower.setText(autoLower + "");
            }
        });

        aOM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoOuter--;
                aOuter.setText(autoOuter + "");
            }
        });

        aIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoInner--;
                aInner.setText(autoInner + "");
            }
        });

        lP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lower++;
                tLower.setText(lower + "");
            }
        });

        oP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outer++;
                tOuter.setText(outer + "");
            }
        });

        iP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inner++;
                tInner.setText(inner + "");
            }
        });

        lM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lower--;
                tLower.setText(lower + "");
            }
        });

        oM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outer--;
                tOuter.setText(outer + "");

            }
        });

        iM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inner--;
                tInner.setText(inner + "");
            }
        });

        final EditText teamNo = root.findViewById(R.id.team_number_game);

        aLower.setText(autoLower + "");
        aOuter.setText(autoOuter + "");
        aInner.setText(autoInner + "");
        tLower.setText(lower + "");
        tOuter.setText(outer + "");
        tInner.setText(inner + "");

        Button submit = root.findViewById(R.id.game_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game game = new Game();
                game.setType(gameDropdown.getSelectedItem().toString());
                game.setTeamNumber(teamNo.getText().toString());
                game.setLower(lower + "");
                game.setOuter(outer + "");
                game.setInner(inner + "");
                game.setAutoLower(autoLower + "");
                game.setAutoOuter(autoOuter + "");
                game.setAutoInner(autoInner + "");
                game.setColorWheel(colorDropdown.getSelectedItem().toString());
                game.setEndgame(climbDropdown.getSelectedItem().toString());
                dashboardFragment.games.add(game);
                teamNo.setText("");
                resetNumbers();
                aLower.setText(autoLower + "");
                aOuter.setText(autoOuter + "");
                aInner.setText(autoInner + "");
                tLower.setText(lower + "");
                tOuter.setText(outer + "");
                tInner.setText(inner + "");

            }
        });

        return root;
    }

    public void resetNumbers() {
        autoLower = 0;
        autoOuter = 0;
        autoInner = 0;
        lower = 0;
        outer = 0;
        inner = 0;
    }

}