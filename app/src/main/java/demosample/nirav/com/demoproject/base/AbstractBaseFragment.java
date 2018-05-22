package demosample.nirav.com.demoproject.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import demosample.nirav.com.demoproject.R;
import demosample.nirav.com.demoproject.data.DataManager;
import demosample.nirav.com.demoproject.di.component.ActivityComponent;


public abstract class AbstractBaseFragment extends Fragment implements BaseView {
    protected View abstractBaseFragmentView;
    @Inject
    DataManager mDataManager;
    private AbstractBaseActivity mActivity;
    private Unbinder mUnBinder;
    private ProgressDialog mProgressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        abstractBaseFragmentView = inflater.inflate(getContentView(), container, false);
        ButterKnife.bind(this, abstractBaseFragmentView);
        onViewReady(savedInstanceState);
        return abstractBaseFragmentView;
    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState) {
        resolveDaggerDependency();
        //To be used by child activities
    }

    protected void resolveDaggerDependency() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AbstractBaseActivity) {
            AbstractBaseActivity activity = (AbstractBaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }

    public ActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }

    public AbstractBaseActivity getBaseActivity() {
        return mActivity;
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }
/*
    public void showMessageForLocation(@NonNull CustomFTextView view) {
        if (SharedPrefUtil.readFromSharedPreferences(sharedPreferences, SharedPrefUtil.LOCATION) == null) {
            view.setText(getString(R.string.no_location_found));
            view.setVisibility(View.VISIBLE);
        }
    }
*/

/*
    public void showMessageForNullData(@NonNull CustomFTextView view) {
        view.setText(getString(R.string.no_items_in_list));
        view.setVisibility(View.VISIBLE);
    }
*/


    @Override
    public void showthrow(Throwable t) {
        hideDialog();
        showAlertDialog(t.getMessage());
    }

    @Override
    public void showErrorToast() {
        hideDialog();
        showToast("Something Went Wrong.");
    }

    protected void showAlertDialog(String msg) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle(null);
        dialogBuilder.setIcon(R.mipmap.ic_launcher);
        dialogBuilder.setMessage(msg);
        dialogBuilder.setPositiveButton("Yes", (dialog, which) -> dialog.cancel());

        dialogBuilder.setCancelable(false);
        dialogBuilder.show();
    }


    @Override
    public void showProgressDialog(String title, String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setOwnerActivity(getActivity());
            mProgressDialog.setTitle(title);
            mProgressDialog.setIcon(R.mipmap.ic_launcher);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(false);
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.setMessage(message);
            mProgressDialog.show();
        }
    }

    @Override
    public void hideDialog() {

        if (mProgressDialog != null) {
            Activity activity = mProgressDialog.getOwnerActivity();
            if (mProgressDialog.isShowing() && !activity.isFinishing())
                mProgressDialog.dismiss();
        }

    }

    @Override
    public boolean isNetworkConnected() {
        if (mActivity != null) {
            return mActivity.isNetworkConnected();
        }
        return false;
    }


    @Override
    public void noInternetConnectionAvailable() {
        showToast("No Network");
    }

    protected void showToast(String mToastMsg) {
        hideDialog();
        Toast.makeText(getActivity(), mToastMsg, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onDetach() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        hideDialog();
        mProgressDialog = null;
        super.onDetach();
    }

    public abstract int getContentView();

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}


