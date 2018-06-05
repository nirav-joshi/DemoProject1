package demosample.nirav.com.demoproject;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import demosample.nirav.com.demoproject.Home.HomeFragment;
import demosample.nirav.com.demoproject.Order.OrderFragment;
import demosample.nirav.com.demoproject.Profile.AccountFragment;
import demosample.nirav.com.demoproject.base.AbstractBaseActivity;
import demosample.nirav.com.demoproject.di.component.ActivityComponent;

public class MainActivity extends AbstractBaseActivity {

    private TextView mTextMessage;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Fragment curFragment;
    private static final int TIME_INTERVAL = 1000;
    private long mBackPressed;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
        }

        setSupportActionBar(toolbar);
        replaceFragment(itemClick(R.id.navigation_home));
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            replaceFragment(itemClick(item.getItemId()));
            return true;
        });
    }


    private void updateNavigationBarState(int actionId) {
        Menu menu = bottomNavigationView.getMenu();
        boolean isBottomSelected = false;
        if (actionId > 0) {
            for (int i = 0, size = menu.size(); i < size; i++) {
                MenuItem item = menu.getItem(i);
                if (item.getItemId() == actionId) {
                    isBottomSelected = true;
                    item.setChecked(true);
                    break;
                } else {
                    isBottomSelected = false;
                }
            }
        }
        if (isBottomSelected)
            changeMenuItemCheckedStateColor(ContextCompat.getColor(this, R.color.colorAccent),
                    ContextCompat.getColor(this, R.color.primary_text_color));
        else
            changeMenuItemCheckedStateColor(ContextCompat.getColor(this, R.color.primary_text_color),
                    ContextCompat.getColor(this, R.color.primary_text_color));
    }

    private void changeMenuItemCheckedStateColor(int checkedColor, int uncheckedColor) {
        int[][] states = new int[][]{
                new int[]{-android.R.attr.state_checked}, // unchecked
                new int[]{android.R.attr.state_checked}, // checked
        };
        int[] colors = new int[]{
                uncheckedColor,
                checkedColor
        };
        ColorStateList colorStateList = new ColorStateList(states, colors);
        bottomNavigationView.setItemTextColor(colorStateList);
        bottomNavigationView.setItemIconTintList(colorStateList);
    }

    /**
     * get fragment object
     *
     * @param id FragmentId
     * @return Fragment
     */
    private Fragment itemClick(int id) {
        updateNavigationBarState(id);
        switch (id) {
            case R.id.navigation_home:
                return HomeFragment.newInstance();
            case R.id.navigation_order:
                return OrderFragment.newInstance();
            case R.id.navigation_account:
                return AccountFragment.newInstance();


            default:
                Toast.makeText(this, "Under Development", Toast.LENGTH_SHORT).show();
                return HomeFragment.newInstance();
        }
    }

    /**
     * Replace fragment
     *
     * @param fragment Fragment to replace
     */
    private void replaceFragment(@NonNull Fragment fragment) {
        /*if (!fragment.equals(curFragment))
        {*/
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tr = fm.beginTransaction();

        String backStackName = fragment.getClass().getName();
        boolean fragmentPopped = fm.popBackStackImmediate(backStackName, 0);
        // if (!fragmentPopped) {
        tr.replace(R.id.frame_container, fragment);
        tr.addToBackStack(null);
        // }
        //tr.replace(R.id.frame_container, fragment);
        tr.commitAllowingStateLoss();
        curFragment = fragment;
        //}
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        curFragment = getSupportFragmentManager().findFragmentById(R.id.frame_container);
    }


    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis() && getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();

        } else if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            showToast("Double Tap Back to Exit.");
            mBackPressed = System.currentTimeMillis();
        } else {
            super.onBackPressed();
            Fragment selectedFragment = getSupportFragmentManager().findFragmentById(R.id.frame_container);
            if (selectedFragment instanceof HomeFragment) {
                updateNavigationBarState(R.id.navigation_home);
            } else if (selectedFragment instanceof OrderFragment) {
                updateNavigationBarState(R.id.navigation_order);
            } else if (selectedFragment instanceof AccountFragment) {
                updateNavigationBarState(R.id.navigation_account);
            }
        }
    }


}
