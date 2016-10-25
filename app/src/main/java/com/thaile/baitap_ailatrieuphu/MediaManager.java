package com.thaile.baitap_ailatrieuphu;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Le on 8/24/2016.
 */
public class MediaManager {
    private MediaPlayer mediaPlayer;
    private Context context;
    public MediaManager(Context context, int resId){
        this.context = context;
        mediaPlayer = MediaPlayer.create(context, resId);
        mediaPlayer.setLooping(false);

    }

    public void start(){
        mediaPlayer.start();
    }

    public void stop(){
        mediaPlayer.stop();
    }

}
