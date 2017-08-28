package com.darrenkong.mvvm.feature.earthquakeList;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.darrenkong.mvvm.feature.domain.Earthquake;
import com.darrenkong.mvvm.feature.rx.SchedulersFacade;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by darrenkong on 25/8/17.
 */

class EarthquakeListViewModel extends ViewModel {

    private final EarthquakeManager manager;
    private final SchedulersFacade schedulersFacade;

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<List<Earthquake>> earthquakeList = new MutableLiveData<>();
    private final MutableLiveData<Throwable> error = new MutableLiveData<>();

    EarthquakeListViewModel(EarthquakeManager manager, SchedulersFacade schedulersFacade) {
        this.manager = manager;
        this.schedulersFacade = schedulersFacade;
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }

    LiveData<Boolean> getLoadingStatus() {
        return isLoading;
    }

    LiveData<List<Earthquake>> getEarthquakeList() {
        return earthquakeList;
    }

    LiveData<Throwable> getError() {
        return error;
    }

    void loadEarthQuakeList() {
        //do rx stuff here
        disposables.add(manager.loadEarthquakeList()
                .subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
                .doOnSubscribe(s -> isLoading.setValue(true))
                .doAfterTerminate(() -> isLoading.setValue(false))
                .subscribe(
                        earthquakeList::setValue,
                        error::setValue
                )
        );
    }
}
