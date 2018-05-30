package demosample.nirav.com.demoproject.application;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import javax.inject.Inject;

import demosample.nirav.com.demoproject.data.DataManager;
import demosample.nirav.com.demoproject.di.component.ApplicationComponent;
import demosample.nirav.com.demoproject.di.component.DaggerApplicationComponent;
import demosample.nirav.com.demoproject.di.module.ApplicationModule;


public class DemoApplication extends Application {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Inject
    DataManager dataManager;
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();


        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}

