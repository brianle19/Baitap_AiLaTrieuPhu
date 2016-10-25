package com.thaile.baitap_ailatrieuphu.Activity;

        import android.app.Activity;
        import android.content.Intent;
        import android.content.pm.ActivityInfo;
        import android.os.Bundle;
        import android.view.View;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.thaile.baitap_ailatrieuphu.Dialog.DialogListScore;
        import com.thaile.baitap_ailatrieuphu.MediaManager;
        import com.thaile.baitap_ailatrieuphu.R;

/**
 * Created by Le on 8/14/2016.
 */
public class MenuActivity extends Activity implements View.OnClickListener {
    private ImageView img;
    private TextView txtvStart, txtvInstruction, txtvHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        initView();
    }


    private void initView() {
        img = (ImageView)findViewById(R.id.img_start_logo);
        txtvStart = (TextView)findViewById(R.id.txtv_start);
        txtvStart.setOnClickListener(this);
        txtvInstruction = (TextView)findViewById(R.id.txtv_guide);
        txtvInstruction.setOnClickListener(this);
        txtvHighScore = (TextView)findViewById(R.id.txtv_high_score);
        txtvHighScore.setOnClickListener(this);

        Animation anim = AnimationUtils.loadAnimation(MenuActivity.this, R.anim.rotate_logo);
        img.setAnimation(anim);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtv_start:
                (new MediaManager(MenuActivity.this, R.raw.bg_music)).stop();
                startActivity(new Intent(MenuActivity.this, PlayGameActivity.class));
                break;
            case R.id.txtv_guide:
                break;
            case R.id.txtv_high_score:
                (new DialogListScore(MenuActivity.this)).show();
                break;
            default:
                break;
        }
    }
}
