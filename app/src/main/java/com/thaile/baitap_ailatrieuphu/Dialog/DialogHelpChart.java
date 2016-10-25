package com.thaile.baitap_ailatrieuphu.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.thaile.baitap_ailatrieuphu.R;

import java.util.ArrayList;

/**
 * Created by Le on 8/16/2016.
 */
public class DialogHelpChart extends Dialog implements View.OnClickListener, BaseDialog {
    private BarChart bar;
    private Button btn_close;

    public DialogHelpChart(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_help_chart);

        initDialog();
        setCancelable(false);

        setOwnerActivity((Activity) context);
    }


    @Override
    public void initDialog() {
        bar = (BarChart) findViewById(R.id.bar_chart);
        btn_close = (Button) findViewById(R.id.btn_close_chart);
        btn_close.setOnClickListener(this);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(30f, 0));
        barEntries.add(new BarEntry(19f, 1));
        barEntries.add(new BarEntry(11f, 2));
        barEntries.add(new BarEntry(40f, 3));
        BarDataSet barDataSet = new BarDataSet(barEntries, "Dữ liệu (%)");

        ArrayList<String> key = new ArrayList<>();
        key.add("Đáp án A");
        key.add("Đáp án B");
        key.add("Đáp án C");
        key.add("Đáp án D");

        BarData barData = new BarData(key, barDataSet);
        bar.setData(barData);

        bar.setTouchEnabled(true);
        bar.setDragEnabled(true);

        YAxis y = bar.getAxisLeft();
        y.setAxisMaxValue(100);
        y.setAxisMinValue(0);

        Legend legend = bar.getLegend();
        legend.setTextSize(18f);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_close_chart:
                dismiss();
                break;
            default:
                dismiss();
                break;

        }
    }
}
