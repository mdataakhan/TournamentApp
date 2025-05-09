    package com.aksofts.mgamerapp;

    import android.content.Context;
    import android.content.Intent;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.ImageView;
    import android.widget.TextView;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import com.bumptech.glide.Glide;

    import java.util.List;

    public class TournamentMatchesAdaptor extends RecyclerView.Adapter<TournamentMatchesAdaptor.ViewHolder> {

        private List<TournamentMatchesItem> matchesList;

        public TournamentMatchesAdaptor(List<TournamentMatchesItem> matchesList) {
            this.matchesList = matchesList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tournament_match, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            TournamentMatchesItem currentItem = matchesList.get(position);
            holder.matchImageView.setImageResource(currentItem.getImageResource());
            holder.matchTitleTextView.setText(currentItem.getTitle());
            holder.matchTimeTextView.setText(currentItem.getTime());
            holder.prizePoolTextView.setText(String.valueOf(currentItem.getPrizePool()));
            holder.perKillTextView.setText(String.valueOf(currentItem.getPerKill()));
            holder.entryFeeTextView.setText(String.valueOf(currentItem.getEntryFee()));
            holder.typeTextView.setText(currentItem.getType());
            holder.versionTextView.setText(currentItem.getVersion());
            holder.mapTextView.setText(currentItem.getMap());
            holder.slotsTextView.setText(currentItem.getSlots());

            // Use Glide or Picasso to load the image from banner URL
            Glide.with(holder.itemView.getContext())
                    .load(currentItem.getImageUrl()) // You need to create getImageUrl()
                    .into(holder.matchImageView);


            // You can set OnClickListener for the joinButton here if needed
            holder.joinButton.setOnClickListener(v -> {
                // Handle join button click
                // Start new activity or pass data
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, TournamentMatchDetail.class);
                intent.putExtra("title", currentItem.getTitle());
                intent.putExtra("entryFee", currentItem.getEntryFee());
                intent.putExtra("matchTime", currentItem.getTime());
                intent.putExtra("prizePool", currentItem.getPrizePool());
                intent.putExtra("perKill", currentItem.getPerKill());
                intent.putExtra("type", currentItem.getType());
                intent.putExtra("version", currentItem.getVersion());
                intent.putExtra("map", currentItem.getMap());
                intent.putExtra("slots", currentItem.getSlots());
                intent.putExtra("banner", currentItem.getImageUrl());

                // Add more data as needed
                context.startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return matchesList.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView matchImageView;
            TextView matchTitleTextView;
            TextView matchTimeTextView;
            TextView prizePoolTextView;
            TextView perKillTextView;
            TextView entryFeeTextView;
            TextView typeTextView;
            TextView versionTextView;
            TextView mapTextView;
            TextView slotsTextView;
            Button joinButton;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                matchImageView = itemView.findViewById(R.id.match_image_view);
                matchTitleTextView = itemView.findViewById(R.id.match_title_text_view);
                matchTimeTextView = itemView.findViewById(R.id.match_time_text_view);
                prizePoolTextView = itemView.findViewById(R.id.prize_pool_text_view);
                perKillTextView = itemView.findViewById(R.id.per_kill_text_view);
                entryFeeTextView = itemView.findViewById(R.id.entry_fee_text_view);
                typeTextView = itemView.findViewById(R.id.type_text_view);
                versionTextView = itemView.findViewById(R.id.version_text_view);
                mapTextView = itemView.findViewById(R.id.map_text_view);
                slotsTextView = itemView.findViewById(R.id.slots_text_view);
                joinButton = itemView.findViewById(R.id.join_button);
            }
        }
    }