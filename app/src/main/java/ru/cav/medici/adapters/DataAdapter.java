package ru.cav.medici.adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.List;

import ru.cav.medici.R;
import ru.cav.medici.models.HeadChainModel;

public class DataAdapter extends ArrayAdapter<HeadChainModel> {
    private final String TAG="DATA ADAPTER";

    private LayoutInflater mInflater;
    private int resLayout;

    public DataAdapter(Context context, int resource, List<HeadChainModel> objects) {
        super(context, resource, objects);
        resLayout = resource;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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
        HeadChainModel record = getItem(position);
        holder.mTitle.setText(record.getTitle());

        return row;
    }

    class ViewHolder {
        public Button mTitle;

    }
}
