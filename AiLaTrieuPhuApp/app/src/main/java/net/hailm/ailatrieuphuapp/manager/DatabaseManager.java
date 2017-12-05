package net.hailm.ailatrieuphuapp.manager;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import net.hailm.ailatrieuphuapp.model.Question;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hai_l on 27/11/2017.
 */

public class DatabaseManager {
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_QUESTION = "Question";
    private static final String COLUMN_CASE_A = "CaseA";
    private static final String COLUMN_CASE_B = "CaseB";
    private static final String COLUMN_CASE_C = "CaseC";
    private static final String COLUMN_CASE_D = "CaseD";
    private static final String COLUMN_LEVEL = "Level";
    private static final String COLUMN_TRUE_CASE = "TrueCase";
    private static final String TAG = "DatabaseManager";


    private Context context;
    private String databasePath;
    private String databaseName;

    private SQLiteDatabase sqLiteDatabase;

    public DatabaseManager(Context context) {
        this.context = context;
        databaseName = "database.db";

//        databasePath = context.getFilesDir().getPath() + File.separator + databaseName;

        databasePath = Environment.getDataDirectory().getAbsolutePath()
                + "/data/net.hailm.ailatrieuphuapp/" + databaseName;
        Log.d(TAG, "DatabaseManager: " + databasePath);

        copyDataBaseFromAssets();
    }

    private void copyDataBaseFromAssets() {
        // Db: --> Internal Storage
        // From: /assets/database.db
        // To: /data/data/$(pakageName)/database.db

        File file = new File(databasePath);

        if (file.exists()) {
            return;
        }

        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(databaseName);

            FileOutputStream fos = new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            int lenght = inputStream.read(buffer);

            while (lenght != -1) {
                fos.write(buffer, 0, lenght);
            }
            fos.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openDatabase() {
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            sqLiteDatabase = SQLiteDatabase.openDatabase(databasePath,
                    null,
                    SQLiteDatabase.OPEN_READWRITE);
        }
    }

    private void closeDatabase() {
        if (sqLiteDatabase != null || sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }

    public boolean addQuestion(String tableName, Question question) {
        openDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, question.getId());
        values.put(COLUMN_QUESTION, question.getQuestion());
        values.put(COLUMN_CASE_A, question.getCaseA());
        values.put(COLUMN_CASE_B, question.getCaseC());
        values.put(COLUMN_CASE_C, question.getCaseC());
        values.put(COLUMN_CASE_D, question.getCaseD());
        values.put(COLUMN_LEVEL, question.getLevel());
        values.put(COLUMN_TRUE_CASE, question.getTrueCase());

        long index = sqLiteDatabase.insert(tableName, null, values);
        closeDatabase();
        return index > -1;
    }

    public boolean updateQuestion(String tableName, Question question) {
        openDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, question.getId());
        values.put(COLUMN_QUESTION, question.getQuestion());
        values.put(COLUMN_CASE_A, question.getCaseA());
        values.put(COLUMN_CASE_B, question.getCaseC());
        values.put(COLUMN_CASE_C, question.getCaseC());
        values.put(COLUMN_CASE_D, question.getCaseD());
        values.put(COLUMN_LEVEL, question.getLevel());
        values.put(COLUMN_TRUE_CASE, question.getTrueCase());

        String id = String.valueOf(question.getId());
        int index = sqLiteDatabase.update(tableName, values, COLUMN_ID + "=?", new String[]{id});
        closeDatabase();
        return index >= 0;
    }

    public boolean deleteQuestion(int questionId, String tableName) {
        openDatabase();

        String id = String.valueOf(questionId);
        // index:
        long index = sqLiteDatabase.delete(tableName, COLUMN_ID + "=?", new String[]{id});
        closeDatabase();
        return index >= 0;
    }

    public List<Question> getQuestions() {
        openDatabase();
        List<Question> questions = new ArrayList<>();

        for (int i = 1; i < 15; i++) {
            String tableName = "Question" + i;
            List<Question> list = getQuestion(tableName, 1);
            if (list != null) {
                questions.addAll(list);
            }
        }
        closeDatabase();
        return questions;
    }

    public List<Question> getQuestion(String tableName, int limit) {
        String sql = "SELECT * FROM " + tableName + " " +
                "ORDER BY RANDOM() LIMIT " + limit;

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor == null) {
            return null;
        }

        if (cursor.getCount() == 0) {
            cursor.close();
            return new ArrayList<>();
        }

        List<Question> result = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            String question = cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION));
            String answerA = cursor.getString(cursor.getColumnIndex(COLUMN_CASE_A));
            String answerB = cursor.getString(cursor.getColumnIndex(COLUMN_CASE_B));
            String answerC = cursor.getString(cursor.getColumnIndex(COLUMN_CASE_C));
            String answerD = cursor.getString(cursor.getColumnIndex(COLUMN_CASE_D));
            int level = cursor.getInt(cursor.getColumnIndex(COLUMN_LEVEL));
            int trueCase = cursor.getInt(cursor.getColumnIndex(COLUMN_TRUE_CASE));

            result.add(new Question(id, question, answerA, answerB, answerC, answerD, trueCase, level));
            cursor.moveToNext();
        }
        cursor.close();
        return null;
    }
}