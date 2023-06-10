package com.example.myprojectstudy.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myprojectstudy.api.model.SeeAll;
import com.example.myprojectstudy.api.service.ApiService;
import com.example.myprojectstudy.databinding.FragmentHomeBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Load list provinces from server (api)
        loadSeeAllListFromServer();
    }

    private void loadSeeAllListFromServer(){

        // Create retrofit client
        Retrofit httpClient = new Retrofit.Builder()
                .baseUrl("https://ferupp.s3.ap-southeast-1.amazonaws.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create service object
        ApiService apiService = httpClient.create(ApiService.class);

        // Load province list from server
        Call<List<SeeAll>> task = apiService.loadSeeAllList();
        task.enqueue(new Callback<List<SeeAll>>() {
            @Override
            public void onResponse(Call<List<SeeAll>> call, Response<List<SeeAll>> response) {
                if (response.isSuccessful()) {

                } else {
                    Toast.makeText(getContext(), "Load province list failed!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<SeeAll>> call, Throwable t) {

            }
        });


    }
}