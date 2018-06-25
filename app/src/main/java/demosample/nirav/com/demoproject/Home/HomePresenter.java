package demosample.nirav.com.demoproject.Home;

import java.util.List;

import javax.inject.Inject;

import demosample.nirav.com.demoproject.api.IDemoWebAPI;
import demosample.nirav.com.demoproject.base.BasePresenter;
import demosample.nirav.com.demoproject.data.DataManager;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class HomePresenter<V extends IHomeView> extends BasePresenter<V>
        implements IHomePresenter<V> {

    private IDemoWebAPI mApiWebAPI;

    @Inject
    public HomePresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void getServices() {
        if (getMvpView().isNetworkConnected()) {

            if (mApiWebAPI == null)
                mApiWebAPI = provideNewApiService();

            Single<List<ServiceCateogryDTO>> listSingle = mApiWebAPI.getHomeServices();
            listSingle.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<List<ServiceCateogryDTO>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onSuccess(List<ServiceCateogryDTO> dtos) {
                            getMvpView().getServices(dtos);
                        }

                        @Override
                        public void onError(Throwable e) {
                            getMvpView().showthrow(e);
                        }
                    });

        } else {
            getMvpView().noInternetConnectionAvailable();
        }
    }

}
