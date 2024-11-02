package com.example.quizyy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CountriesFragment extends Fragment {

    private RecyclerView recyclerView;
    private CountryAdapter adapter;
    private List<Country> countryList;

    public CountriesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_countries, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewCountries);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        countryList = new ArrayList<>();
        countryList.add(new Country("India", R.drawable.india));
        countryList.add(new Country("USA", R.drawable.usa));
        countryList.add(new Country("Canada", R.drawable.canada));

        // Pass the fragment's context to the adapter
        adapter = new CountryAdapter(countryList, getActivity());

        recyclerView.setAdapter(adapter);
        return view;
    }
}
