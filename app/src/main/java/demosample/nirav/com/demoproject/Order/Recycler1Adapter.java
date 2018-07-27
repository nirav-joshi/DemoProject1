package demosample.nirav.com.demoproject.Order;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import demosample.nirav.com.demoproject.R;


/**
 * Created by wolfsoft1 on 17/2/18.
 */

public class Recycler1Adapter extends RecyclerView.Adapter<Recycler1Adapter.MyViewHolder> {


    Context context;
    int pos = -1;


    private List<RecyclerModel1> OfferList;


    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView month, date;
        LinearLayout ll1;

        public MyViewHolder(View view) {
            super(view);

            month = (TextView) view.findViewById(R.id.month);
            date = (TextView) view.findViewById(R.id.date);
            ll1 = (LinearLayout) view.findViewById(R.id.ll1);


        }

    }


    public Recycler1Adapter(Context mainActivityContacts, List<RecyclerModel1> offerList) {
        this.OfferList = offerList;
        this.context = mainActivityContacts;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calender, parent, false);


        return new MyViewHolder(itemView);


    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        RecyclerModel1 lists = OfferList.get(position);

        holder.month.setText(lists.getMonth());
        holder.date.setText(lists.getDate());


        if (pos == position) {
            holder.ll1.setBackgroundResource(R.drawable.rect2);
            holder.month.setTextColor(Color.parseColor("#ffffff"));
            holder.date.setTextColor(Color.parseColor("#ffffff"));

        } else {
            holder.ll1.setBackgroundColor(Color.parseColor("#00000000"));
            holder.month.setTextColor(Color.parseColor("#b7b7b7"));
            holder.date.setTextColor(Color.parseColor("#395177"));

        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos = position;

                notifyDataSetChanged();


            }
        });


    }

    @Override
    public int getItemCount() {
        return OfferList.size();

    }

}
