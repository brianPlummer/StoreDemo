package com.nytimes.storedemo.di;

import com.nytimes.storedemo.ui.MainView;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Component to be used by SampleActivity/Application
 */
@Singleton
@Component(
        modules = {AppModule.class}
)
public interface AppComponent {

    void inject(MainView a);

}