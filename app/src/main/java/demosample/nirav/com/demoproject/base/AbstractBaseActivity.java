/*
 * Copyright (c) 2016 Filippo Engidashet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package demosample.nirav.com.demoproject.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import demosample.nirav.com.demoproject.R;
import demosample.nirav.com.demoproject.application.DemoApplication;
import demosample.nirav.com.demoproject.di.component.ActivityComponent;
import demosample.nirav.com.demoproject.di.component.DaggerActivityComponent;
import demosample.nirav.com.demoproject.di.module.ActivityModule;


/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/24/2016
 */
@SuppressWarnings("squid:MaximumInheritanceDepth")
public abstract class AbstractBaseActivity extends AppCompatActivity implements BaseView, AbstractBaseFragment.Callback {


    private static final String STATE_BACK_STACK_MANAGER = "back_stack_manager";
    private int onStartCount;
    private ProgressDialog mProgressDialog;
    private ActivityComponent mActivityComponent;

    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((DemoApplication) getApplication()).getComponent())
                .build();

        onViewReady(savedInstanceState, getIntent());
    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        resolveDaggerDependency();
        //To be used by child activities
    }

    @Override
    protected void onDestroy() {
        ButterKnife.bind(this);
        hideDialog();
        mProgressDialog = null;
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();

    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    public void showthrow(Throwable t) {
        hideDialog();
        showAlertDialog(t.getMessage());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    protected LinearLayoutManager getLayoutManager() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return layoutManager;
    }

    //Backstack management
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    protected void hideKeyboard() {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (getCurrentFocus() != null)
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            Log.e("MultiBackStack", "Failed to add fragment to back stack", e);
        }
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }


    @Override
    public void noInternetConnectionAvailable() {
        showToast("No Network");
    }

    protected void resolveDaggerDependency() {

    }


    protected void showBackArrow() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public void showProgressDialog(String title, @NonNull String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            if (title != null)
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
        if (!isFinishing() && mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showErrorToast() {
        hideDialog();
        showToast("Something Went Wrong.");
    }
    protected void showAlertDialog(String msg) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle(null);
        dialogBuilder.setIcon(R.mipmap.ic_launcher);
        dialogBuilder.setMessage(msg);
        dialogBuilder.setPositiveButton("Yes", (dialog, which) -> dialog.cancel());

        dialogBuilder.setCancelable(false);
        dialogBuilder.show();
    }

    protected void showToast(String mToastMsg) {
        Toast.makeText(this, mToastMsg, Toast.LENGTH_LONG).show();
    }

    protected abstract int getContentView();

/*    @Override
    protected void onStart() {
        super.onStart();
        if (onStartCount > 1) {
            this.overridePendingTransition(R.anim.anim_slide_in_right,
                    R.anim.anim_slide_out_right);

        } else if (onStartCount == 1) {
            onStartCount++;
        }
    }*/

    public void hideSoftKeyboard() {
        if (getApplicationContext() != null) {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context
                    .INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(new View(this).getWindowToken(), 0);
        }
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }


    @Override
    public boolean isNetworkConnected() {
        return true;//NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    public void logger(String tag,String message)
    {
        Log.e(tag,message);
    }
}
