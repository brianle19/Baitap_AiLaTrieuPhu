package com.thaile.baitap_ailatrieuphu;

import android.content.Context;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.thaile.baitap_ailatrieuphu.Dialog.DialogHelpChart;
import com.thaile.baitap_ailatrieuphu.Dialog.DialogHelpChooseCeleb;

import java.util.Random;

/**
 * Created by Le on 8/23/2016.
 */
public class HelpFunctionManager {
    private int trueCase;
    private int rd50F, rd50S;
    private TextView[] txtv;
    private Context context;
    private ImageControl imageControl;
    public HelpFunctionManager(TextView [] txtv, int trueCase, Context context){
        this.txtv = txtv;
        this.context = context;
        this.trueCase = trueCase;
        imageControl = new ImageControl(context);
    }

    public void changeQuestion(ImageView img){
        imageControl.setimgBitMap(img,"ic_false_change_question.png");
    }

    public void helpFiftyFifty(ImageView img) {
        //random.nextInt(max - min + 1) + min
        final int rdF = randomFirst();
        final int rdS = randomSecond();
        (new MediaManager(context, R.raw.sound5050)).start();
        imageControl.setimgBitMap(img, "ic_false_fifty_fifty.png");
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                txtv[rdF].setText("");
                txtv[rdS].setText("");
            }
        }, 3000);

    }

    private int randomFirst(){
        Random rd = new Random();
        int key = trueCase - 1;
        rd50F = rd.nextInt(txtv.length);
        if (rd50F == key){
            return randomFirst();
        } else{
            return rd50F;
        }
    }

    private int randomSecond(){
        Random rd = new Random();
        int key = trueCase - 1;
        rd50S = rd.nextInt(txtv.length);
        if (rd50S != key && rd50S != rd50F){
            return rd50S;
        } else{
            return randomSecond();
        }
    }

    public void helpPhoneCall(ImageView img){
        (new DialogHelpChooseCeleb(context, txtv[trueCase - 1].getText().toString())).show();
        (new MediaManager(context, R.raw.help_call_phone)).start();
        imageControl.setimgBitMap(img, "ic_false_phone_call.png");
    }


    public void helpAudience(ImageView img){
        (new DialogHelpChart(context)).show();
        imageControl.setimgBitMap(img, "ic_false_chart.png");

    }

    public void showToast(){
        Toast.makeText(context, "Hết Lượt", Toast.LENGTH_SHORT).show();
    }
}
