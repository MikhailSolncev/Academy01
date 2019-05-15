package com.debugg3r.android.academy01.data;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InternetService {
    @GET("devfestapi/db.json")
    Call<DevfestResponse> getData();
}
