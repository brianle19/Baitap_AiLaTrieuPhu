package com.thaile.baitap_ailatrieuphu.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thaile.baitap_ailatrieuphu.ItemMoney;
import com.thaile.baitap_ailatrieuphu.R;

import java.util.ArrayList;

/**
 * Created by Le on 8/24/2016.
 */
public class MoneyAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private ArrayList<ItemMoney> arrayList = new ArrayList<>();
    private Context context;
    private int index;
    public MoneyAdapter(Context context, int index){
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.index = index;
        initData();
        notifyDataSetChanged();
    }
    public void initData(){

        arrayList.add(new ItemMoney("1","200"));
        arrayList.add(new ItemMoney("2","400"));
        arrayList.add(new ItemMoney("3","600"));
        arrayList.add(new ItemMoney("4","1000"));
        arrayList.add(new ItemMoney("5","2,000"));
        arrayList.add(new ItemMoney("6","3,000"));
        arrayList.add(new ItemMoney("7","6,000"));
        arrayList.add(new ItemMoney("8","10,000"));
        arrayList.add(new ItemMoney("9","14,000"));
        arrayList.add(new ItemMoney("10","22,000"));
        arrayList.add(new ItemMoney("11","30,000"));
        arrayList.add(new ItemMoney("12","40,000"));
        arrayList.add(new ItemMoney("13","60,000"));
        arrayList.add(new ItemMoney("14","100,000"));
        arrayList.add(new ItemMoney("15","200,000"));
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public ItemMoney getItem(int position) {
        return arrayList.get(getCount() - position - 1);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        position = getCount() - position - 1;
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.item_score, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.imgMoney = (ImageView) convertView.findViewById(R.id.img_money);
            viewHolder.txtvMoney = (TextView) convertView.findViewById(R.id.txtv_money);
            viewHolder.txtvStt = (TextView) convertView.findViewById(R.id.txtv_stt);
            viewHolder.backgroundItem = (TextView) convertView.findViewById(R.id.background_item);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.backgroundItem.setVisibility(View.INVISIBLE);
        ItemMoney itemMoney = arrayList.get(position);
        String gMoney = itemMoney.getMoney();
        String getStt = itemMoney.getStt();

        if (position == index - 1){
            viewHolder.backgroundItem.setVisibility(View.VISIBLE);
        }
        if (position == 4 || position == 9 || position == 14){
            viewHolder.txtvMoney.setText(gMoney);
            viewHolder.txtvStt.setText(getStt);
            viewHolder.txtvMoney.setTextColor(Color.WHITE);
            viewHolder.txtvStt.setTextColor(Color.WHITE);
        } else
        {
            viewHolder.txtvMoney.setText(gMoney);
            viewHolder.txtvStt.setText(getStt);
        }

        notifyDataSetChanged();
        return convertView;
    }

    class ViewHolder{
        TextView backgroundItem;
        ImageView imgMoney;
        TextView txtvStt;
        TextView txtvMoney;
    }
}
