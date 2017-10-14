package com.darrenkong.mvvm.feature.earthquakeList;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.darrenkong.mvvm.feature.domain.Earthquake;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by darrenkong on 25/8/17.
 */

class EarthquakeListViewModel extends ViewModel {

    // The ViewModel class is reacts to actions triggered by the view and calls the interactor class to perform business logic actions.
    // It outlives the Activity/Fragment so will survive orientation changes and application backgrounding. Results from an long running operations, like a
    // network call, will come back but the view won't react until it comes back to the foreground. No more weird situations like long running operations
    // finishes and tries to update the view but the view is no longer available causing apps to crash. This is also useful because it effectively "caches"
    // network call results when app goes in the background and returns.

    private final EarthquakeInteractor earthquakeInteractor;

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<List<Earthquake>> earthquakeList = new MutableLiveData<>();
    private final MutableLiveData<Throwable> error = new MutableLiveData<>();

    @Inject
    public EarthquakeListViewModel(EarthquakeInteractor earthquakeInteractor) {
        this.earthquakeInteractor = earthquakeInteractor;
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
        disposables.add(
                earthquakeInteractor.loadEarthquakeList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(s -> isLoading.setValue(true))
                        .doAfterTerminate(() -> isLoading.setValue(false))
                        .subscribe(
                                earthquakeList::setValue,
                                error::setValue
                        )
        );
    }
}
