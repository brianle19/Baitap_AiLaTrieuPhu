package com.thaile.baitap_ailatrieuphu.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.thaile.baitap_ailatrieuphu.Database.DBManager;
import com.thaile.baitap_ailatrieuphu.R;

/**
 * Created by Le on 8/23/2016.
 */
public class DialogUserInformation extends Dialog implements View.OnClickListener, BaseDialog {
    private TextView txtvTitle, txtvUserScore;
    private EditText edtName;
    private Context context;
    private String sCore;
    private Button btnSave, btnCancel;

    public DialogUserInformation(Context context, String sCore) {
        super(context);
        this.context = context;
        this.sCore = sCore;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_user_information);
        initDialog();
        setCancelable(false);
        setOwnerActivity((Activity) context);
    }

    @Override
    public void initDialog() {
        txtvTitle = (TextView) findViewById(R.id.txtv_title_user_dialog);
        txtvUserScore = (TextView) findViewById(R.id.txtv_user_score);
        edtName = (EditText)findViewById(R.id.edt_user_name);

        btnSave = (Button) findViewById(R.id.btn_cancel);
        btnSave.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.btn_save);
        btnCancel.setOnClickListener(this);
        txtvUserScore.setText("Điểm của bạn: "+sCore);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.btn_save:
                (new DBManager(context)).insertScore(edtName.getText().toString(), sCore);
                dismiss();
                break;
            default:
                break;
        }
    }
}
