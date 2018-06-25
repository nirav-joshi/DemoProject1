package demosample.nirav.com.demoproject.Home;


import java.util.List;

import demosample.nirav.com.demoproject.base.BaseView;


public interface IHomeView extends BaseView {
    void getServices(List<ServiceCateogryDTO> cateogryDTOS);
}
