package ru.cav.medici.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ru.cav.medici.R;
import ru.cav.medici.WorkActivity;
import ru.cav.medici.models.HeadChainModel;
import ru.cav.medici.models.SpecChainModel;

public class ChainAdapter extends ArrayAdapter<SpecChainModel> {

    private LayoutInflater mInflater;
    private Context mContext;
    private int resLayout;

    public ChainAdapter(Context context, int resource, List<SpecChainModel> objects) {
        super(context, resource, objects);
        mContext = context;
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
            holder.mItem = (TextView) row.findViewById(R.id.chain_item_text);
            row.setTag(holder);
        }else{
            holder = (ViewHolder)row.getTag();
        }
        final SpecChainModel record = getItem(position);
        holder.mItem.setText(record.getChainItem());

        return row;
    }

    class ViewHolder{
        public TextView mItem;
    }

}
