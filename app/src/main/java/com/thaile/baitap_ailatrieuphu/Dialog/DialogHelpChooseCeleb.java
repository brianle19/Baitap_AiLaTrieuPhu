package com.thaile.baitap_ailatrieuphu.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.thaile.baitap_ailatrieuphu.ImageControl;
import com.thaile.baitap_ailatrieuphu.R;

/**
 * Created by Le on 8/16/2016.
 */
public class DialogHelpChooseCeleb extends Dialog implements View.OnClickListener, BaseDialog {
    private Button btn_close;
    private ImageControl imageControl;
    private ImageView img1, img2, img3, img4, img5, img6;
    private String strKey;
    private Context context;

    public DialogHelpChooseCeleb(Context context, String strKey) {
        super(context);
        this.context = context;
        this.strKey = strKey;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_help_phonecall_celeb);
        imageControl = new ImageControl(context);
        initDialog();
        setCancelable(false);
        setOwnerActivity((Activity) context);
    }


    @Override
    public void initDialog() {

        btn_close = (Button) findViewById(R.id.btn_close);
        btn_close.setOnClickListener(this);

        img1 = (ImageView) findViewById(R.id.img_anhxtanh);
        imageControl.setimgBitMap(img1,"anhxtanh.png");
        img2 = (ImageView) findViewById(R.id.img_mark);
        imageControl.setimgBitMap(img2,"markzukerbeg.png");
        img3 = (ImageView) findViewById(R.id.img_obama);
        imageControl.setimgBitMap(img3,"barackobama.jpg");
        img4 = (ImageView) findViewById(R.id.img_billgate);
        imageControl. setimgBitMap(img4,"billgate.png");
        img5 = (ImageView) findViewById(R.id.img_binladen);
        imageControl.setimgBitMap(img5,"osamabinladen.jpg");
        img6 = (ImageView) findViewById(R.id.img_ngobaochau);
        imageControl. setimgBitMap(img6,"ngobaochau.png");

        ImageView[] img = new ImageView[6];
        img[0] = img1;
        img[1] = img2;
        img[2] = img3;
        img[3] = img4;
        img[4] = img5;
        img[5] = img6;

        for (int i = 0; i < img.length ; i++) {
            img[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    (new DialogHelpPhoneCall(context, strKey)).show();
                }
            });
        }

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
