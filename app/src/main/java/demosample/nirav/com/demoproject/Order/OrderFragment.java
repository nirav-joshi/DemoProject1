package demosample.nirav.com.demoproject.Order;

import android.os.Bundle;

import javax.inject.Inject;

import demosample.nirav.com.demoproject.R;
import demosample.nirav.com.demoproject.base.AbstractBaseFragment;
import demosample.nirav.com.demoproject.data.DataManager;
import demosample.nirav.com.demoproject.di.component.ActivityComponent;


public class OrderFragment extends AbstractBaseFragment {


    @Inject
    DataManager dataManager;

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Override
    public int getContentView() {
        return R.layout.orders;
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
