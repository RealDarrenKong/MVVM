package com.darrenkong.mvvm.feature.earthquakeList;

import com.darrenkong.mvvm.feature.domain.Earthquake;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by darrenkong on 25/8/17.
 */

public class EarthquakeManager {

    private final EarthquakeListDomainMapper earthquakeInteractor;

    @Inject
    EarthquakeManager(EarthquakeListDomainMapper earthquakeInteractor) {
        //Interactor does the mapping from data to domain model
        this.earthquakeInteractor = earthquakeInteractor;
    }

    Single<List<Earthquake>> loadEarthquakeList() {
        return earthquakeInteractor.getEarthquakes();

        //rxjava chaining
        //business logic


    }
}