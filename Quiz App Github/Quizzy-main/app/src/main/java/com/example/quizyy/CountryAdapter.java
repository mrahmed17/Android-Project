package com.example.quizyy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private List<Country> countryList;
    private Context context; // Context to start the QuestionActivity

    public CountryAdapter(List<Country> countryList, Context context) {
        this.countryList = countryList;
        this.context = context; // Store the context passed from the fragment
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        Country country = countryList.get(position);
        holder.countryName.setText(country.getName());
        holder.countryImage.setImageResource(country.getImage());

        // Set click listener for itemView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start QuestionActivity with the selected country name
                Intent intent = new Intent(context, QuestionActivity.class);
                intent.putExtra("COUNTRY_NAME", country.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public static class CountryViewHolder extends RecyclerView.ViewHolder {
        TextView countryName;
        ImageView countryImage;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.countryName);
            countryImage = itemView.findViewById(R.id.countryImage);
        }
    }
}
