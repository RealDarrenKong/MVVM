package com.darrenkong.mvvm.feature.earthquakeList;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.darrenkong.mvvm.feature.rx.SchedulersFacade;

import javax.inject.Inject;

/**
 * Created by darrenkong on 25/8/17.
 */

public class EarthquakeListViewModelFactory implements ViewModelProvider.Factory {

    private EarthquakeManager manager;

    private SchedulersFacade schedulersFacade;

    @Inject
    EarthquakeListViewModelFactory(EarthquakeManager manager, SchedulersFacade schedulersFacade) {
        this.manager = manager;
        this.schedulersFacade = schedulersFacade;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(EarthquakeListViewModel.class)) {
            return (T) new EarthquakeListViewModel(manager, schedulersFacade);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
