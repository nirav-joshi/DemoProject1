package demosample.nirav.com.demoproject.Profile;

import android.os.Bundle;

import javax.inject.Inject;

import demosample.nirav.com.demoproject.R;
import demosample.nirav.com.demoproject.base.AbstractBaseFragment;
import demosample.nirav.com.demoproject.data.DataManager;
import demosample.nirav.com.demoproject.di.component.ActivityComponent;


public class AccountFragment extends AbstractBaseFragment {


    @Inject
    DataManager dataManager;



    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public int getContentView() {
        return R.layout.account;
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
