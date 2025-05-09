package com.aksofts.mgamerapp;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class TournamentMatchDetail extends AppCompatActivity {

    // Declare views
    private TextView matchTypeOverlay, matchTitle, matchTypeText, matchMapText, entryFeeText, perKillText, matchScheduleText, prizeDetailsText, matchDescriptionText;
    private Button joinButton;
    private ImageView topImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_match_detail);

        // Initialize views
        topImage = findViewById(R.id.top_image);
        matchTypeOverlay = findViewById(R.id.match_type_overlay);
        matchTitle = findViewById(R.id.match_title);
        matchTypeText = findViewById(R.id.match_type_text);
        matchMapText = findViewById(R.id.match_map_text);
        entryFeeText = findViewById(R.id.entry_fee_text);
        perKillText = findViewById(R.id.per_kill_text);
        matchScheduleText = findViewById(R.id.match_schedule_text);
        prizeDetailsText = findViewById(R.id.prize_details_text);
        matchDescriptionText = findViewById(R.id.match_description_text);
        joinButton = findViewById(R.id.join_button);

        // Example: Update views dynamically
        updateMatchDetails();
    }

    private void updateMatchDetails() {
        // Image change (if drawable from resources)
        topImage.setImageResource(R.drawable.freefire); // Replace with your drawable

        // Text Updates
        matchTypeOverlay.setVisibility(View.VISIBLE);
        matchTypeOverlay.setText("DUO");

        matchTitle.setText("FF MAX - Match #999");
        matchTypeText.setText("Duo");
        matchMapText.setText("Kalahari");
        entryFeeText.setText("5");
        perKillText.setText("3");
        matchScheduleText.setText("06/05/2025 06:00 PM");
        prizeDetailsText.setText("Winning Prize: 1500\n1st prize: 800\n2nd prize: 500\n3rd prize: 200");
        matchDescriptionText.setText(Html.fromHtml("This is a Duo live match with exciting rewards!<br><b>pGamer</b>"));


        // Button text update (optional)
        joinButton.setText("Join Now");
    }
}
