package com.thaile.baitap_ailatrieuphu;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Le on 8/23/2016.
 */
public class ImageControl {
    private Context context;

    public ImageControl(Context context){
        this.context = context;
    }

    public void setimgBitMap(ImageView imageView, String fileName){
       imageView.setImageBitmap(showImageBitmap(fileName));
    }

    public void setTrueAnswerBackground(TextView txtv){
        txtv.setBackground(new BitmapDrawable(context.getResources(), showImageBitmap("background_question_true.png")));
    }

    public void setFalseAnswerBackground(TextView txtv){
        txtv.setBackground(new BitmapDrawable(context.getResources(), showImageBitmap("background_question_wrong.png") ));
    }

    public void setBackgroundQuestion(TextView txtv){
        txtv.setBackground(new BitmapDrawable(context.getResources(), showImageBitmap("background_case.png") ));
    }

    public Bitmap showImageBitmap(String fileName){
        Bitmap src = null;
        AssetManager assetManager = context.getAssets();
        try {
            InputStream input = assetManager.open("img/"+fileName);
            BitmapFactory.decodeStream(input);
            src = BitmapFactory.decodeStream(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return src;
    }

    public void setKeyAnswerBackground(TextView txtv) {
        txtv.setBackground(new BitmapDrawable(context.getResources(), showImageBitmap("background_question_correct.png") ));
    }
}
