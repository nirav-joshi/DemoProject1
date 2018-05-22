package demosample.nirav.com.demoproject.data;

import android.content.Context;
import javax.inject.Inject;
import javax.inject.Singleton;

import demosample.nirav.com.demoproject.di.ApplicationContext;


@Singleton
public class DataManager {

    private Context mContext;
    private SharedPrefsHelper mSharedPrefsHelper;


    @Inject
    public DataManager(@ApplicationContext Context context,
                       SharedPrefsHelper sharedPrefsHelper) {
        mContext = context;
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    public void saveAccessToken(String accessToken) {
        mSharedPrefsHelper.put(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, accessToken);
    }

    public String getAccessToken() {
        return mSharedPrefsHelper.get(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, null);
    }

    public void setWelcomeScreen() {
        mSharedPrefsHelper.put(SharedPrefsHelper.IS_FIRST_TIME, false);
    }

    public boolean checkForWelcomeScreen() {
        return mSharedPrefsHelper.get(SharedPrefsHelper.IS_FIRST_TIME, true);
    }


    //Double can not store in sharepreference, So we can add as string value
    public void setLocationData(String name, Double latitude, Double longitude) {
        mSharedPrefsHelper.put(SharedPrefsHelper.LOCATION_NAME, name);
        mSharedPrefsHelper.put(SharedPrefsHelper.LONGITUDE, longitude+"");
        mSharedPrefsHelper.put(SharedPrefsHelper.LATITUDE, latitude+"");
    }

    public String getSearchKeyword() {
        return mSharedPrefsHelper.get(SharedPrefsHelper.SEARCH_KEYWORD, "");
    }

    public void setSearchKeyword(String keyword) {
        mSharedPrefsHelper.put(SharedPrefsHelper.SEARCH_KEYWORD, keyword);
    }


    public String getLocationName() {
        return mSharedPrefsHelper.get(SharedPrefsHelper.LOCATION_NAME, "");
    }

    public double getLatitude() {
        String latitude = mSharedPrefsHelper.get(SharedPrefsHelper.LATITUDE, "");
        return Double.parseDouble(latitude);
    }

    public double getLongitude() {
        String longitude = mSharedPrefsHelper.get(SharedPrefsHelper.LONGITUDE, "");
        return Double.parseDouble(longitude);
    }

    public String getLoginObject() {
        return mSharedPrefsHelper.get(SharedPrefsHelper.OBJECT_JSON, "");
    }
    public void setLoginObject(String objectJson) {
        mSharedPrefsHelper.put(SharedPrefsHelper.OBJECT_JSON, objectJson);
    }
}
