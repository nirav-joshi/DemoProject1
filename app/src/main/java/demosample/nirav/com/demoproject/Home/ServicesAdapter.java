package demosample.nirav.com.demoproject.Home;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;

import demosample.nirav.com.demoproject.R;
import io.reactivex.annotations.Nullable;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.CategoryViewHolder> {
    private List<ServiceCateogryDTO> listObjects;
    private Activity activity;

    ServicesAdapter(Activity activity) {
        this.activity = activity;
        listObjects = new ArrayList<>();
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(activity).inflate(R.layout.service_lst, parent, false);
        return new CategoryViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.txtCategoryName.setText(listObjects.get(position).getName());
        if (listObjects.get(position).getImage() != null) {
            Uri uri = Uri.parse("http://192.168.1.171/Xjailwsproduction/icons/"+listObjects.get(position).getImage());
            if (!activity.isFinishing() && uri != null)
                Glide.with(activity).asBitmap()
                        .load(uri).into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        holder.ivImage.setImageBitmap(resource);
                    }
                });

        }
    }

    public void addAll(List<ServiceCateogryDTO> mValues) {
        listObjects.addAll(mValues);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listObjects.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView txtCategoryName;
        ImageView ivImage;

        CategoryViewHolder(View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivService);
            txtCategoryName = itemView.findViewById(R.id.tvService);
        }
    }
}
