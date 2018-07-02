package demosample.nirav.com.demoproject.login;

import android.content.Intent;
import android.os.Bundle;

import demosample.nirav.com.demoproject.R;
import demosample.nirav.com.demoproject.base.AbstractBaseActivity;
import demosample.nirav.com.demoproject.di.component.ActivityComponent;

public class LoginActivity extends AbstractBaseActivity{
    @Override
    protected int getContentView() {
        return R.layout.loginwithphone;
    }


    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
        }

        findViewById(R.id.btnnext).setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),OtpActivity.class)); finish();
        });
    }


}
