package com.aksofts.mgamerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SymbolTable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class TournamentMatchesActivity extends AppCompatActivity {

    private RecyclerView matchesRecyclerView;
    private TournamentMatchesAdaptor matchesAdapter;
    private List<TournamentMatchesItem> matchesItemList;
    private LinearLayout loadinganim;

    private TextView ongoingTab;
    private TextView upcomingTab;
    private TextView resultsTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_matches);

        // Initialize views
        loadinganim = findViewById(R.id.loadinganim);
        matchesRecyclerView = findViewById(R.id.matches_recycler_view);
        matchesRecyclerView.setVisibility(View.GONE);
        matchesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ongoingTab = findViewById(R.id.ongoing_tab);
        upcomingTab = findViewById(R.id.upcoming_tab);
        resultsTab = findViewById(R.id.results_tab);

        // Sample data (replace with your actual data source)
        matchesItemList = new ArrayList<>();

        // Set up tab click listeners (optional, if you want to implement tab functionality)
        ongoingTab.setOnClickListener(v -> {
            // Handle ongoing tab click (e.g., filter data)
        });

        upcomingTab.setOnClickListener(v -> {
            // Handle upcoming tab click (e.g., filter data)
        });

        resultsTab.setOnClickListener(v -> {
            // Handle results tab click (e.g., filter data)
        });

        // Find and handle the back button click
        findViewById(R.id.header).findViewById(R.id.header).setOnClickListener(v -> onBackPressed());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        fetchTournamentMatches();
    }



    private void fetchTournamentMatches() {
        String get_user_data_qry = getResources().getString(R.string.app_url) + "/app-apis/tournaments/all-tournaments-matches.php?id=1";

        String finalget_user_data_qry = get_user_data_qry;
        class dbprocess extends AsyncTask<String, Void, String> implements com.aksofts.mgamerapp.dbprocess {
            @Override
            protected void onPostExecute(String data) {
                try {
                    JSONArray response = new JSONArray(data);

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);

                        String matchName = obj.getString("match_name");
                        String matchTime = obj.getString("match_time");
                        int entryFee = obj.getInt("entry_fee");
                        int winPrize = obj.getInt("win_prize");
                        int perKill = obj.getInt("per_kill");
                        String type = obj.getString("type");
                        String map = obj.getString("MAP");
                        String banner = obj.getString("match_banner");
                        String no_of_player = obj.getString("no_of_player");

                        TournamentMatchesItem item = new TournamentMatchesItem(
                                banner, matchName, matchTime, entryFee, winPrize, perKill, type, "1", map, no_of_player
                        );
                        matchesItemList.add(item);


                    }
                    // ✅ Now set the adapter only once
                    matchesAdapter = new TournamentMatchesAdaptor(matchesItemList);
                    matchesRecyclerView.setAdapter(matchesAdapter);
                    matchesAdapter.notifyDataSetChanged();
                    loadinganim.setVisibility(View.GONE);
                    matchesRecyclerView.setVisibility(View.VISIBLE);
                    System.out.println(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(TournamentMatchesActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    System.out.println(data);
                }
            }

            @Override
            protected String doInBackground(String... params) {
                String furl = params[0];
                try {
                    URL url = new URL(furl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    return br.readLine();
                } catch (Exception ex) {
                    return ex.getMessage();
                }
            }
        }

        dbprocess obj = new dbprocess();
        obj.execute(finalget_user_data_qry);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void goto_matchejoin(View view){
        Intent intent = new Intent(TournamentMatchesActivity.this, TournamentMatchesActivity.class);
    }
}