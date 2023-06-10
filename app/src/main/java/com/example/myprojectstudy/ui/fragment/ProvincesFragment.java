package com.example.myprojectstudy.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myprojectstudy.api.model.Province;
import com.example.myprojectstudy.api.service.ApiService;
import com.example.myprojectstudy.databinding.FragmentCoursesBinding;
import com.example.myprojectstudy.ui.adapter.ProvinceAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProvincesFragment extends Fragment {

    private FragmentCoursesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentCoursesBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Load list provinces from server (api)
        loadProvinceListFromServer();
    }

    private void loadProvinceListFromServer(){

        // Create retrofit client
        Retrofit httpClient = new Retrofit.Builder()
                .baseUrl("https://tests3bk.s3.ap-southeast-1.amazonaws.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create service object
        ApiService apiService = httpClient.create(ApiService.class);

        // Load province list from server
        Call<List<Province>> task = apiService.loadProvinceList();
        task.enqueue(new Callback<List<Province>>() {
            @Override
            public void onResponse(Call<List<Province>> call, Response<List<Province>> response) {

                if (response.isSuccessful()) {
                    showProvinceList(response.body());
                } else {
                    Toast.makeText(getContext(), "Load province list failed!", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Province>> call, Throwable t) {
                Toast.makeText(getContext(), "Load province list failed!", Toast.LENGTH_LONG).show();
                Log.e("[ProvincesFragment]", "Load province failed: " + t.getMessage());
                t.printStackTrace();
            }
        });


    }

    private void showProvinceList(List<Province> provinceList) {

        // Create layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(layoutManager);

        // Create adapter
        ProvinceAdapter adapter = new ProvinceAdapter();
        adapter.submitList(provinceList);
        binding.recyclerView.setAdapter(adapter);

    }

}