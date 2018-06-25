package demosample.nirav.com.demoproject.api;


import java.util.List;

import demosample.nirav.com.demoproject.Home.ServiceCateogryDTO;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface IDemoWebAPI {


    //Home page
    @GET("https://polycyclic-unions.000webhostapp.com/category.json")
    Single<List<ServiceCateogryDTO>> getHomeServices();


}
