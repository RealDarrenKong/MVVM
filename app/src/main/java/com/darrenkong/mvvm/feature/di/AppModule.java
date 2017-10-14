package com.darrenkong.mvvm.feature.di;

/**
 * Created by darrenkong on 26/8/17.
 */

import android.content.Context;

import com.darrenkong.mvvm.feature.MvvmApplication;
import com.darrenkong.mvvm.feature.earthquakeList.EarthquakeApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This is where you will inject application-wide dependencies.
 */
@Module
public class AppModule {

    @Provides
    Context provideContext(MvvmApplication application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    GsonConverterFactory providesJacksonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    EarthquakeApi provideEarthquakeService(GsonConverterFactory converterFactory) {
        return new Retrofit.Builder()
                .baseUrl("https://earthquake.usgs.gov")
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(EarthquakeApi.class);
    }
}
