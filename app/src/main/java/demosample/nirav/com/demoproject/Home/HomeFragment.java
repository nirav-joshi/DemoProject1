package demosample.nirav.com.demoproject.Home;

import android.os.Bundle;

import javax.inject.Inject;

import demosample.nirav.com.demoproject.R;
import demosample.nirav.com.demoproject.base.AbstractBaseFragment;
import demosample.nirav.com.demoproject.data.DataManager;
import demosample.nirav.com.demoproject.di.component.ActivityComponent;


public class HomeFragment extends AbstractBaseFragment {


    @Inject
    DataManager dataManager;



    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public int getContentView() {
        return R.layout.home;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
        }
    }

}
