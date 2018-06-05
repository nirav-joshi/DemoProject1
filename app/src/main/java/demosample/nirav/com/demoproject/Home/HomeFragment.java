package demosample.nirav.com.demoproject.Home;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import demosample.nirav.com.demoproject.R;
import demosample.nirav.com.demoproject.base.AbstractBaseFragment;
import demosample.nirav.com.demoproject.data.DataManager;
import demosample.nirav.com.demoproject.di.component.ActivityComponent;
import demosample.nirav.com.demoproject.utils.ToolbarUtil;


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

        View toolbarView = ToolbarUtil.addRemoveViewFromToolbar(getActivity(), R.layout.toolbar_temple_list);
        if (toolbarView != null) {
            TextView lblLocation = toolbarView.findViewById(R.id.tv_location);
            lblLocation.setText("Ahmedabad");
        }
    }

}
