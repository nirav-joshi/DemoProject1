package demosample.nirav.com.demoproject.Order;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import demosample.nirav.com.demoproject.R;
import demosample.nirav.com.demoproject.base.AbstractBaseActivity;

public class SchedulePickupActivity extends AbstractBaseActivity {
    private ArrayList<RecyclerModel1> homeListModelClassArrayList;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private Recycler1Adapter mAdapter;

    String month[]={"S","M","T","W","T","F","S"};
    String date[]={"23","24","25","26","27","28","29"};
    @Override
    protected int getContentView() {
        return R.layout.pickup;
    }

    public void onClickofDays(View view) {

    }
}
