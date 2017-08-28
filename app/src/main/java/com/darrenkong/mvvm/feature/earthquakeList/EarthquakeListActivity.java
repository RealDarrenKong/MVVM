package com.darrenkong.mvvm.feature.earthquakeList;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.darrenkong.mvvm.feature.domain.Earthquake;
import com.darrenkong.mvvm.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class EarthquakeListActivity extends LifecycleActivity {

    @Inject
    EarthquakeListViewModelFactory viewModelFactory;

    private EarthquakeListViewModel viewModel;

    @BindView(R.id.loading_indicator)
    ProgressBar loadingIndicator;

    @BindView(R.id.list_earthquakes)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        //  or
        //AndroidSupportInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake_list);

        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(EarthquakeListViewModel.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        observeLoadingStatus();

        observeResponse();

        observeError();
    }

    @OnClick(R.id.main_button)
    public void onMainButtonClick() {
        viewModel.loadEarthQuakeList();
    }

    private void observeLoadingStatus() {
        viewModel.getLoadingStatus().observe(this, this::processLoadingStatus);
    }

    private void observeResponse() {
        viewModel.getEarthquakeList().observe(this, this::processList);
    }

    private void observeError() {
        viewModel.getError().observe(this, this::processError);
    }

    private void processLoadingStatus(boolean isLoading) {
        loadingIndicator.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    private void processList(List<Earthquake> list) {
        //Simplified example that creates a new adapter every time a new list is retrieved.

        EarthquakesListAdapter adapter = new EarthquakesListAdapter(list);

        //insert into adapter
        recyclerView.setAdapter(adapter);

        //if empty, show empty screen

        //else show list
    }

    private void processError(Throwable error) {
        //deal with errors
    }
}
