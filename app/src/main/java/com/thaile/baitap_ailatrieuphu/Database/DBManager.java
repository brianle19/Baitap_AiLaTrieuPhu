package com.thaile.baitap_ailatrieuphu.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.widget.Toast;

import com.thaile.baitap_ailatrieuphu.QuestionObject;
import com.thaile.baitap_ailatrieuphu.UserObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Le on 8/14/2016.
 */
public class DBManager {
    private static final String PATH = Environment.getDataDirectory().getPath()
            +"/data/com.thaile.baitap_ailatrieuphu/database/";
    private static final String DATABASE_NAME = "Question";
    private Context context;
    private ArrayList<UserObject> listUser;
    private ArrayList<QuestionObject> list;
    private SQLiteDatabase sqLiteDatabase;

    public DBManager(Context context){
        this.context = context;
        copyDataToInternal();
    }

    private void copyDataToInternal() {
        //tạo folder
        (new File(PATH)).mkdir();

        File file = new File(PATH + DATABASE_NAME);
        if (file.exists()){
            return;
        }

        try {
            DataInputStream input = new DataInputStream(context.getAssets().open(DATABASE_NAME));
            DataOutputStream output = new DataOutputStream(new FileOutputStream(PATH+DATABASE_NAME));

            byte[] b = new byte[1024];
            int length;

            while ((length = input.read(b)) != -1){
                output.write(b, 0, length);
            }

            output.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<QuestionObject> get15QuestionFromDatabase(){
          list = new ArrayList<>();
          for (int i = 1; i <= 15 ; i++) {
              QuestionObject question = rawSQL("SELECT * FROM Question"+i+" ORDER BY RANDOM() LIMIT 1");
              list.add(question);
          }
         return list;
      }

    private void open(){
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            sqLiteDatabase = SQLiteDatabase.openDatabase(PATH + DATABASE_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        }

    }

    private void close (){
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()){
            sqLiteDatabase.close();
        }
    }

    public QuestionObject rawSQL (String sql){
        open();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.getCount() == 0){
            return null;
        }

        QuestionObject question = null;
        cursor.moveToFirst();

        int indexQuestion = cursor.getColumnIndex("Question");
        int indexA = cursor.getColumnIndex("CaseA");
        int indexB = cursor.getColumnIndex("CaseB");
        int indexC = cursor.getColumnIndex("CaseC");
        int indexD = cursor.getColumnIndex("CaseD");
        int indexTruecase = cursor.getColumnIndex("TrueCase");
        int indexScore = cursor.getColumnIndex("ScoreMoney");

        while (!cursor.isAfterLast()){
            String ques = cursor.getString(indexQuestion);
            String caseA = cursor.getString(indexA);
            String caseB = cursor.getString(indexB);
            String caseC = cursor.getString(indexC);
            String caseD = cursor.getString(indexD);
            int trueCase = cursor.getInt(indexTruecase);
            String score = cursor.getString(indexScore);
            question = new QuestionObject(ques, trueCase, caseA, caseB, caseC, caseD, score);
            cursor.moveToNext();
        }

        cursor.close();
        close();
        return question;
    }

    public void insertScore(String name, String score) {
        open();
        ContentValues values = new ContentValues();
        values.put("UserName", name);
        values.put("UserScore", score);
        long result = sqLiteDatabase.insert("User", null, values);
        if (result == -1){
            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
        close();
    }

    public ArrayList<UserObject> listUserScore(){
        listUser = new ArrayList<>();
        open();
        String sql = "SELECT * FROM User";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.getCount() == 0){
            return null;
        }

        UserObject userObject = null;
        cursor.moveToFirst();

        int indexName = cursor.getColumnIndex("UserName");
        int indexScore = cursor.getColumnIndex("UserScore");

        while (!cursor.isAfterLast()){
            String uName = cursor.getString(indexName);
            String uScore = cursor.getString(indexScore);
            userObject = new UserObject(uName, uScore);

            listUser.add(userObject);
            cursor.moveToNext();
        }

        cursor.close();
        close();
        return listUser;
    }
}
