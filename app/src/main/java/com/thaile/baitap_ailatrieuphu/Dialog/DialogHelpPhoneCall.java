package com.thaile.baitap_ailatrieuphu.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.thaile.baitap_ailatrieuphu.R;

/**
 * Created by Le on 8/16/2016.
 */
public class DialogHelpPhoneCall extends Dialog implements View.OnClickListener, BaseDialog {
    private TextView txtv_title, txtv_key;
    private Button btn_close;
    private String strKey;

    public DialogHelpPhoneCall(Context context, String strKey) {
        super(context);
        this.strKey = strKey;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_help_phone_call);

        initDialog();
        setCancelable(false);

        setOwnerActivity((Activity) context);
    }


    @Override
    public void initDialog() {
        txtv_title = (TextView) findViewById(R.id.txtv_title);
        txtv_key = (TextView) findViewById(R.id.txtv_key);

        btn_close = (Button) findViewById(R.id.btn_close);
        btn_close.setOnClickListener(this);

        txtv_key.setText("Tôi chọn đáp án "+strKey.substring(0,1));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_close:
                dismiss();
                break;
            default:
                dismiss();
                break;

        }
    }
}
