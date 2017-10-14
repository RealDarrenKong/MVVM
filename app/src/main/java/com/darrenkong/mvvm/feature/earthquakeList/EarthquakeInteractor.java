package com.darrenkong.mvvm.feature.earthquakeList;

import com.darrenkong.mvvm.feature.domain.Earthquake;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by darrenkong on 25/8/17.
 */

public class EarthquakeInteractor {
    //This class is where Business Logic sits

    private final EarthquakeService earthquakeService;

    @Inject
    EarthquakeInteractor(EarthquakeService earthquakeService) {
        this.earthquakeService = earthquakeService;
    }

    Single<List<Earthquake>> loadEarthquakeList() {
        //show examples of business logic manipulations here
        return earthquakeService.getEarthquakes();
    }
}