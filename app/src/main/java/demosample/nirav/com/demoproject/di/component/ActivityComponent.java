/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package demosample.nirav.com.demoproject.di.component;


import dagger.Component;
import demosample.nirav.com.demoproject.Home.HomeFragment;
import demosample.nirav.com.demoproject.MainActivity;
import demosample.nirav.com.demoproject.Order.OrderFragment;
import demosample.nirav.com.demoproject.Profile.AccountFragment;
import demosample.nirav.com.demoproject.di.PerActivity;
import demosample.nirav.com.demoproject.di.module.ActivityModule;
import demosample.nirav.com.demoproject.login.LoginActivity;
import demosample.nirav.com.demoproject.login.OtpActivity;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);
    void inject(HomeFragment activity);
    void inject(AccountFragment activity);
    void inject(OrderFragment activity);
    void inject(LoginActivity activity);
    void inject(OtpActivity activity);


}
