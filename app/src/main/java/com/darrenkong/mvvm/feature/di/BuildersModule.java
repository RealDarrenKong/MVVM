package com.darrenkong.mvvm.feature.di;

import com.darrenkong.mvvm.feature.earthquakeList.EarthquakeListActivity;
import com.darrenkong.mvvm.feature.earthquakeList.EarthquakeModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by darrenkong on 27/8/17.
 */

/**
 * Binds all sub-components within the app.
 */
@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector(modules = EarthquakeModule.class)
    abstract EarthquakeListActivity bindEarthquakeListActivity();

    // Add bindings for other sub-components here
}
