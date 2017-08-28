package com.darrenkong.mvvm.feature.earthquakeList;

import com.darrenkong.mvvm.feature.data.EarthQuakeListResponse;
import com.darrenkong.mvvm.feature.domain.Earthquake;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.Observable;

/**
 * Created by darrenkong on 26/8/17.
 */

public class EarthquakeListDomainMapper {

    private EarthquakeService earthquakeService;

    @Inject
    EarthquakeListDomainMapper(EarthquakeService service) {
        earthquakeService = service;
    }

    public Single<List<Earthquake>> getEarthquakes() {

        // Here earthquakeService.getEarthquakes call would return the raw response. We want to convert the response to the domain models and return that
        // Also, uses hardcoded parameters that should have been passed in for a non demo real app
        Single<EarthQuakeListResponse> apiResult = earthquakeService.getEarthquakes("geojson", "2014-01-01", "2014-01-02", "4");

        return apiResult.flatMap(earthQuakeListResponse -> Observable.fromIterable(earthQuakeListResponse.features)
                .map(item -> {
                    Earthquake eq = new Earthquake();
                    eq.setDetail(item.properties.detail);
                    eq.setPlace(item.properties.place);
                    eq.setMagnitude(item.properties.mag);
                    eq.setTime(item.properties.time);
                    eq.setUrl(item.properties.url);
                    eq.setDetail(item.properties.detail);
                    eq.setStatus(item.properties.status);
                    eq.setType(item.properties.type);
                    return eq;
                }).toList());
    }
}
