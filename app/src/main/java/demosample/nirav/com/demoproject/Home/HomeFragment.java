package demosample.nirav.com.demoproject.Home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import demosample.nirav.com.demoproject.R;
import demosample.nirav.com.demoproject.base.AbstractBaseFragment;
import demosample.nirav.com.demoproject.data.DataManager;
import demosample.nirav.com.demoproject.di.component.ActivityComponent;


public class HomeFragment extends AbstractBaseFragment {


    @Inject
    DataManager dataManager;
    @BindView(R.id.pager)
    ViewPager mImageViewPager;
    @BindView(R.id.rvData)
    RecyclerView rvData;
    @BindView(R.id.tabDots)
    TabLayout tabLayout;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.lblLocation)
    TextView lblLocation;
    private Timer timer;
    private int currentPage = -1;


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

        lblLocation.setText("Your Location");
        tvLocation.setText("Ahmedabad");
        List<String> imgUrls = new ArrayList<>();
        imgUrls.add("47c72eef-22bc-45c0-8e58-2ba3b9b75fd0.png");
        imgUrls.add("47382cd4-693d-4422-8f49-e09d903b8187.jpg");
        imgUrls.add("40f77796-548d-4c11-9aa9-639eb021e77d.jpg");
        imgUrls.add("47c72eef-22bc-45c0-8e58-2ba3b9b75fd0.png");
        imgUrls.add("47382cd4-693d-4422-8f49-e09d903b8187.jpg");
        imgUrls.add("40f77796-548d-4c11-9aa9-639eb021e77d.jpg");
        PagerAdapter adapter = new HomeSlidingImageAdapter(getActivity(), imgUrls);

        rvData.setNestedScrollingEnabled(false);
        //rvData.addItemDecoration(new ItemDecorationAlbumColumns(getResources()
        // .getDimensionPixelSize(R.dimen.divider_size), getResources().getInteger(R.integer.grid_size)));
        rvData.setLayoutManager(new GridLayoutManager(getContext(), 3));
        ServicesAdapter servicesAdapter = new ServicesAdapter(getActivity(), imgUrls);
        rvData.setAdapter(servicesAdapter);
        mImageViewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(mImageViewPager, true);


        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                mImageViewPager.post(new Runnable(){

                    @Override
                    public void run() {
                        mImageViewPager.setCurrentItem(++currentPage,true);
                        if (currentPage == imgUrls.size() -1) {
                            currentPage = -1;
                        }
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 3000, 3000);
    }

}
