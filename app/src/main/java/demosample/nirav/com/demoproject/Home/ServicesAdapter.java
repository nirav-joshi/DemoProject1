package demosample.nirav.com.demoproject.Home;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import demosample.nirav.com.demoproject.R;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.CategoryViewHolder> {
    private List<String> listObjects;
    private Activity activity;

    ServicesAdapter(Activity activity, List<String> listObjects) {
        this.listObjects = listObjects;
        this.activity = activity;
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(activity).inflate(R.layout.service_lst, parent, false);
        return new CategoryViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
    /*    holder.txtCategoryName.setText(listObjects.get(position).getName());
        if (listObjects.get(position).getImage() != null) {
            Uri uri = Uri.parse(IMAGE_ORIGNAL_URL + listObjects.get(position).getImage());
            if (!activity.isFinishing() && uri != null)
                Glide.with(activity).asBitmap()
                        .load(uri).into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        holder.ivImage.setImageBitmap(resource);
                    }
                });

        }*/
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
