package com.thaile.baitap_ailatrieuphu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thaile.baitap_ailatrieuphu.Database.DBManager;
import com.thaile.baitap_ailatrieuphu.R;
import com.thaile.baitap_ailatrieuphu.UserObject;

/**
 * Created by Le on 8/24/2016.
 */
public class UserScoreAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private DBManager dbManager;
    public UserScoreAdapter(Context context){
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        dbManager = new DBManager(context);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dbManager.listUserScore().size();
    }

    @Override
    public UserObject getItem(int position) {
        return dbManager.listUserScore().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.item_list_score, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.txtvName = (TextView) convertView.findViewById(R.id.txtv_user_name);
            viewHolder.txtvScore = (TextView) convertView.findViewById(R.id.txtv_user_list_score);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        UserObject userObject = dbManager.listUserScore().get(position);
        String gName = userObject.getUserName();
        String gMoney = userObject.getScore();

        viewHolder.txtvName.setText(gName);
        viewHolder.txtvScore.setText(gMoney);

        notifyDataSetChanged();
        return convertView;
    }

    class ViewHolder{
        TextView txtvName;
        TextView txtvScore;
    }
}

