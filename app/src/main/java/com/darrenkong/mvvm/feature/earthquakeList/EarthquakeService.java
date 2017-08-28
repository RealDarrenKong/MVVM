package com.darrenkong.mvvm.feature.earthquakeList;

import com.darrenkong.mvvm.feature.data.EarthQuakeListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by darrenkong on 26/8/17.
 */

public interface EarthquakeService {

    //https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02&minmagnitude=5

    @GET("/fdsnws/event/1/query")
    Single<EarthQuakeListResponse> getEarthquakes(
            @Query("format") String format,
            @Query("starttime") String startDate,
            @Query("endtime") String endDate,
            @Query("minmagnitude") String magnitude
    );
}
