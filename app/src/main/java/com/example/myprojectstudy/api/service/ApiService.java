package com.example.myprojectstudy.api.service;

import com.example.myprojectstudy.api.model.Province;
import com.example.myprojectstudy.api.model.SeeAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/provinces.json")
    Call<List<Province>> loadProvinceList();

    // See All
    @GET("/course.json")
    Call<List<SeeAll>> loadSeeAllList();
}

