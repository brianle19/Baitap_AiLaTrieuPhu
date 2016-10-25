package com.thaile.baitap_ailatrieuphu.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

import com.thaile.baitap_ailatrieuphu.Adapter.UserScoreAdapter;
import com.thaile.baitap_ailatrieuphu.R;

/**
 * Created by Le on 8/25/2016.
 */
public class DialogListScore extends Dialog implements BaseDialog, View.OnClickListener {
    private ListView lv;
    private Button btn;

    public DialogListScore(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_list_score);

        initDialog();
        setCancelable(false);

        setOwnerActivity((Activity) context);

    }

    @Override
    public void initDialog() {
        lv = (ListView)findViewById(R.id.lv_list_score);
        btn = (Button) findViewById(R.id.btn_close);

        UserScoreAdapter userScoreAdapter = new UserScoreAdapter(getContext());
        lv.setAdapter(userScoreAdapter);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_close:
                dismiss();
                break;
            default:
                break;
        }
    }
}
