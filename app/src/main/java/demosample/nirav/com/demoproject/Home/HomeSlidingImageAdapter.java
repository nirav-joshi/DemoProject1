package demosample.nirav.com.demoproject.Home;

import android.content.Context;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import demosample.nirav.com.demoproject.R;

/**
 * Created by Mitesh on 2/22/2018
 * .
 */

public class HomeSlidingImageAdapter extends PagerAdapter {
    private ArrayList<String> IMAGES;
    private LayoutInflater inflater;
    private Context context;


    public HomeSlidingImageAdapter(Context context, List<String> images) {
        this.context = context;
        this.IMAGES = (ArrayList<String>) images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.only_imageview, view, false);
        assert imageLayout != null;
        final ImageView imageView = imageLayout.findViewById(R.id.img);

        Uri uri = Uri.parse("http://192.168.1.5/jainImage/Original/" + IMAGES.get(position));
        Glide.with(context)
                .load(uri)
                .into(imageView);
        view.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

}
