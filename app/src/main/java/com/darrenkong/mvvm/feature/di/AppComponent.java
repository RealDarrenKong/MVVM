package com.darrenkong.mvvm.feature.di;

import com.darrenkong.mvvm.feature.MvvmApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by darrenkong on 27/8/17.
 */

@Singleton
@Component(modules = {
        /* Use AndroidInjectionModule.class if you're not using support library */
        AndroidSupportInjectionModule.class,
        AppModule.class,
        BuildersModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(MvvmApplication application);

        AppComponent build();
    }

    void inject(MvvmApplication app);
}