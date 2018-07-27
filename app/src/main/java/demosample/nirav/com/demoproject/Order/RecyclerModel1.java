package demosample.nirav.com.demoproject.Order;

/**
 * Created by wolfsoft1 on 22/2/18.
 */

public class RecyclerModel1 {

    String month,date;

    public RecyclerModel1(String month, String date) {
        this.month = month;
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
