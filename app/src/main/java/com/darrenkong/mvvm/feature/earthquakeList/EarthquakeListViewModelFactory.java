package com.darrenkong.mvvm.feature.earthquakeList;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Inject;

/**
 * Created by darrenkong on 25/8/17.
 */

public class EarthquakeListViewModelFactory implements ViewModelProvider.Factory {

    private EarthquakeInteractor earthquakeInteractor;

    @Inject
    EarthquakeListViewModelFactory(EarthquakeInteractor earthquakeInteractor) {
        this.earthquakeInteractor = earthquakeInteractor;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(EarthquakeListViewModel.class)) {
            return (T) new EarthquakeListViewModel(earthquakeInteractor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
