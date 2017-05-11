package ru.cav.medici.adapters;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

import ru.cav.medici.R;
import ru.cav.medici.WorkActivity;
import ru.cav.medici.models.HeadChainModel;

public class DataAdapter extends ArrayAdapter<HeadChainModel> {
    private final String TAG="DATA ADAPTER";

    private LayoutInflater mInflater;
    private int resLayout;

    private Context mContext;

    public DataAdapter(Context context, int resource, List<HeadChainModel> objects) {
        super(context, resource, objects);
        mContext = context;
        resLayout = resource;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View row=convertView;
        if(row==null) {
            row = mInflater.inflate(resLayout, parent, false);
            holder = new ViewHolder();
            holder.mTitle = (Button) row.findViewById(R.id.items_button);
            row.setTag(holder);
        }else{
            holder = (ViewHolder)row.getTag();
        }
        final HeadChainModel record = getItem(position);
        holder.mTitle.setText(record.getTitle());
        holder.mTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, String.valueOf(record.getId()));

                Toast.makeText(mContext, "Зачем вы нажали? "+record.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(mContext, WorkActivity.class);
                intent.putExtra("CHAIN_ID",record.getId());
                mContext.startActivity(intent);
            }
        });

        return row;
    }

    class ViewHolder {
        public Button mTitle;

    }
}
