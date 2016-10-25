package com.thaile.baitap_ailatrieuphu.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;
import com.thaile.baitap_ailatrieuphu.Adapter.MoneyAdapter;
import com.thaile.baitap_ailatrieuphu.Database.DBManager;
import com.thaile.baitap_ailatrieuphu.Dialog.DialogUserInformation;
import com.thaile.baitap_ailatrieuphu.HelpFunctionManager;
import com.thaile.baitap_ailatrieuphu.ImageControl;
import com.thaile.baitap_ailatrieuphu.MediaManager;
import com.thaile.baitap_ailatrieuphu.QuestionObject;
import com.thaile.baitap_ailatrieuphu.R;

import java.util.ArrayList;

/**
 * Created by Le on 8/14/2016.
 */
public class PlayGameActivity extends Activity implements View.OnClickListener, Runnable{
    private ListView listView;
    private DrawerLayout drawerLayout;
    private ImageView img_bar_logo;
    private HelpFunctionManager mHelp;
    private static final int MSG_TIME_COUNTDOWN = 101;
    private String strQuestion, strCaseA, strCaseB, strCaseC, strCaseD, strScore;
    private boolean isUse1 = false, isUse2 = false, isUse3 = false, isUse4 = false, isUse5 = false;
    private int trueCase;
    private ImageControl imageControl;
    private boolean isRunning = true;
    private String strNum = "Câu ";
    private DBManager dbManager;
    private int index = 1;
    private int numClick = 1;
    private ImageView img_fifty_fifty, img_phone_call, img_change_question, img_help_chart, img_stop;
    private ImageView img_logo;
    private ArrayList<QuestionObject> listQuestion;
    private TextView txtvQuestionNum, txtvQuestion, txtvCaseA, txtvCaseB, txtvCaseC, txtvCaseD;
    private TextView txtvTime;
    private TextView arrTxtv[] = new TextView[4];;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_TIME_COUNTDOWN:
                    txtvTime.setText(msg.arg1 + "");
                    if (txtvTime.getText().toString().equals("0")) {
                            (new MediaManager(PlayGameActivity.this, R.raw.out_of_time)).start();
                             Toast.makeText(PlayGameActivity.this, "Hết Giờ", Toast.LENGTH_SHORT).show();
                            numClick = 2;
                    }

                    break;
                default:
                    break;
            }
        }
    };

    private void setUserScoreDependOnIndex() {
        if (index < 5) {
            (new DialogUserInformation(PlayGameActivity.this, "0,0")).show();
        }else if (index > 5 && index < 10) {
            (new DialogUserInformation(PlayGameActivity.this, "2,000")).show();
        } else if (index > 10 && index < 15){
            (new DialogUserInformation(PlayGameActivity.this, "22,000")).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        initView();
        setQuestion();
        checkAnswer();
        (new MediaManager(PlayGameActivity.this, R.raw.ready)).start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        isRunning = false;
        (new MediaManager(PlayGameActivity.this, R.raw.ready)).stop();
    }

    private void checkAnswer(){
            for (int i = 0; i < arrTxtv.length; i++) {
                final int finalI = i;
                    arrTxtv[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            isRunning = false;
                            if (numClick == 1){
                                if (finalI == (trueCase - 1)) {
                                    mHandler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            //Do something after 100ms
                                            setQuestionTrue(finalI);
                                            if (index == 15){
                                                Toast.makeText(PlayGameActivity.this, "Chúc mừng bạn đã trở thành triệu phú", Toast.LENGTH_SHORT).show();
                                            }
                                            if (index == 5){
                                                drawerLayout.openDrawer(Gravity.LEFT);
                                                (new MediaManager(PlayGameActivity.this, R.raw.win_moc01)).start();
                                                mHandler.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        drawerLayout.closeDrawers();
                                                    }
                                                }, 3000);
                                            } else if (index == 10){
                                                drawerLayout.openDrawer(Gravity.LEFT);
                                                (new MediaManager(PlayGameActivity.this, R.raw.win_moc02)).start();
                                            }
                                        }
                                    }, 3000);
                                    numClick++;
                                } else {
                                    mHandler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            //Do something after 100ms
                                            setQuestionFalse(finalI);
                                            setQuestionCorrect(trueCase - 1);
                                        }
                                    }, 3000);
                                    numClick++;
                                }
                        } else {
                                return;
                            }
                        }
                    });
            }
    }

    private void setQuestionFalse(int i) {
        imageControl.setFalseAnswerBackground(arrTxtv[i]);
        YoYo.with(Techniques.Flash).delay(20).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setUserScoreDependOnIndex();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).playOn(arrTxtv[i]);
    }

    private void setQuestionTrue(int i) {
        imageControl.setTrueAnswerBackground(arrTxtv[i]);
        YoYo.with(Techniques.Flash).delay(20).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                nextQuestion();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).playOn(arrTxtv[i]);
    }

    private void setQuestionCorrect(int i) {
        imageControl.setKeyAnswerBackground(arrTxtv[i]);
        YoYo.with(Techniques.Tada).delay(10).playOn(arrTxtv[i]);

    }

    private void nextQuestion(){
        index++;
        numClick = 1;
        if (index <= 15){
            setQuestion();
            ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
        } else {
            return;
        }
    }


    private void initView() {
        //anh xa
        img_change_question = (ImageView)findViewById(R.id.img_change_question);
        img_change_question.setOnClickListener(this);

        img_fifty_fifty = (ImageView)findViewById(R.id.img_fifty_fifty);
        img_fifty_fifty.setOnClickListener(this);

        img_help_chart = (ImageView)findViewById(R.id.img_chart);
        img_help_chart.setOnClickListener(this);

        img_phone_call = (ImageView)findViewById(R.id.img_phone_call);
        img_phone_call.setOnClickListener(this);

        img_stop = (ImageView)findViewById(R.id.img_stop_play);
        img_stop.setOnClickListener(this);

        img_logo = (ImageView)findViewById(R.id.img_logo);

        txtvQuestion = (TextView)findViewById(R.id.txtv_question);
        txtvQuestionNum = (TextView)findViewById(R.id.txtv_num_question);

        txtvCaseA = (TextView)findViewById(R.id.txtv_case_a);
        txtvCaseB = (TextView)findViewById(R.id.txtv_case_b);
        txtvCaseC = (TextView)findViewById(R.id.txtv_case_c);
        txtvCaseD = (TextView)findViewById(R.id.txtv_case_d);

        arrTxtv[0] = txtvCaseA;
        arrTxtv[1] = txtvCaseB;
        arrTxtv[2] = txtvCaseC;
        arrTxtv[3] = txtvCaseD;

        txtvTime = (TextView)findViewById(R.id.txtv_time);

        //
        listView = (ListView)findViewById(R.id.lv_menu);
        img_bar_logo = (ImageView)findViewById(R.id.img_bar_logo);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);

        //
    }

    private void setQuestion() {
        dbManager = new DBManager(PlayGameActivity.this);
        listQuestion = dbManager.get15QuestionFromDatabase();
        int size = dbManager.get15QuestionFromDatabase().size();

        if (index <= size) {
            strQuestion = listQuestion.get(index).getQuestion().toString();
            strCaseA = "A. " +listQuestion.get(index).getCaseA().toString();
            strCaseB = "B. " +listQuestion.get(index).getCaseB().toString();
            strCaseC = "C. " +listQuestion.get(index).getCaseC().toString();
            strCaseD = "D. " +listQuestion.get(index).getCaseD().toString();
            strScore = listQuestion.get(index-1).getScore().toString();
            trueCase = listQuestion.get(index).getTrueCase();
        } else {
            return;
        }

        txtvQuestion.setText(strQuestion);
        txtvQuestionNum.setText(strNum + index+"");
        txtvCaseA.setText(strCaseA);
        txtvCaseB.setText(strCaseB);
        txtvCaseC.setText(strCaseC);
        txtvCaseD.setText(strCaseD);

        mHelp = new HelpFunctionManager(arrTxtv, trueCase, PlayGameActivity.this);

        imageControl = new ImageControl(PlayGameActivity.this);

        for (int i = 0; i < arrTxtv.length ; i++) {
            imageControl.setBackgroundQuestion(arrTxtv[i]);
        }

        MoneyAdapter moneyAdapter = new MoneyAdapter(PlayGameActivity.this, index);
        listView.setAdapter(moneyAdapter);
        isRunning = true;
        new Thread(this).start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_change_question:
                if (isUse1 == false && numClick == 1 ){
                    isRunning = false;
                    mHelp.changeQuestion(img_change_question);
                    setQuestion();
                } else {
                    mHelp.showToast();
                }
                isUse1 = true;
                break;

            case R.id.img_fifty_fifty:
                if (isUse2 == false && numClick == 1){
                    mHelp.helpFiftyFifty(img_fifty_fifty);
                } else {
                    mHelp.showToast();
                }
                isUse2 = true;
                break;

            case R.id.img_phone_call:
                if (isUse3 == false && numClick == 1){
                    mHelp.helpPhoneCall(img_phone_call);
                } else {
                    mHelp.showToast();
                }
                isUse3 = true;
                break;
            case R.id.img_chart:
                if (isUse4 == false && numClick == 1){
                    mHelp.helpAudience(img_help_chart);
                } else {
                    mHelp.showToast();
                }
                isUse4 = true;
                break;
            case R.id.img_stop_play:
                if (isUse5 == false && numClick == 1){
                    if (index == 1){
                        (new DialogUserInformation(PlayGameActivity.this, "0"))
                                .show();
                    } else {
                        isRunning = false;
                        (new DialogUserInformation(PlayGameActivity.this, listQuestion.get(index - 2).getScore().toString()))
                                .show();
                    }

                } else {
                    mHelp.showToast();
                }
                isUse5 = true;
                break;


            default:
                break;
        }
    }

    @Override
    public void run() {
        for (int i = 30; i >= 0; i--) {
            if (isRunning && !isFinishing()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Message message = new Message();
                message.what = MSG_TIME_COUNTDOWN;
                message.arg1 = i;

                //send to Handler
                message.setTarget(mHandler);
                message.sendToTarget();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isRunning = false;
        startActivity(new Intent(PlayGameActivity.this, MenuActivity.class));
    }
}
