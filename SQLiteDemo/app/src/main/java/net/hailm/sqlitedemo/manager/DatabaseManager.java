package net.hailm.sqlitedemo.manager;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.hailm.sqlitedemo.model.Question;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hai_l on 23/11/2017.
 */

public class DatabaseManager {
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_QUESTION = "Question";
    private static final String COLUMN_CASE_A = "CaseA";
    private static final String COLUMN_CASE_B = "CaseB";
    private static final String COLUMN_CASE_C = "CaseC";
    private static final String COLUMN_CASE_D = "CaseD";
    private static final String COLUMN_TRUE_CASE = "TrueCase";


    private final String databasePath;
    private final String databaseName;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public DatabaseManager(Context context) {
        this.context = context;
        databaseName = "database.db";
        databasePath = context.getFilesDir().getPath()
                + File.separator + databaseName;


        copyDataBaseFromAssets();
    }

    private void copyDataBaseFromAssets() {
        // Db --> internal storage
        // from: /assets/database .db
        // to: /data/data/$(packageName)/database.db

        File file = new File(databasePath);
        if (file.exists()) {
            return;
        }

        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(databaseName);

            FileOutputStream fos = new FileOutputStream(file);

            // Create outputStream
            byte[] buffer = new byte[1024];
            int length;

            while ((length = inputStream.read(buffer)) != -1) {
                fos.write(buffer, 0, length);
            }

            fos.close();
            inputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openDatabae() {
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            sqLiteDatabase = SQLiteDatabase.openDatabase(databasePath,
                    null,
                    SQLiteDatabase.OPEN_READWRITE);
        }


    }

    private void closeDatabae() {
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }

    public boolean addQuestion(String tableName, Question question) {
        openDatabae();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, question.getId());
        values.put(COLUMN_QUESTION, question.getQuestion());
        values.put(COLUMN_CASE_A, question.getAnswerA());
        values.put(COLUMN_CASE_B, question.getAnswerB());
        values.put(COLUMN_CASE_C, question.getAnswerC());
        values.put(COLUMN_CASE_D, question.getAnswerD());
        values.put(COLUMN_TRUE_CASE, question.getTrueCase());

        long index = sqLiteDatabase.insert(tableName, null, values);
        closeDatabae();

        return index != -1;
    }

    public boolean updateQuestion(String tableName, Question question) {
        openDatabae();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, question.getId());
        values.put(COLUMN_QUESTION, question.getQuestion());
        values.put(COLUMN_CASE_A, question.getAnswerA());
        values.put(COLUMN_CASE_B, question.getAnswerB());
        values.put(COLUMN_CASE_C, question.getAnswerC());
        values.put(COLUMN_CASE_D, question.getAnswerD());
        values.put(COLUMN_TRUE_CASE, question.getTrueCase());

        String id = String.valueOf(question.getId());
        long index = sqLiteDatabase.update(tableName, values, COLUMN_ID + "=?", new String[]{id});

        closeDatabae();
        return index >= 0;
    }

    public boolean deleteQuestion(int questionId, String tableName) {
        openDatabae();

        String id = String.valueOf(questionId);
        // index:
        long index = sqLiteDatabase.delete(tableName, COLUMN_ID + "=?", new String[]{id});
        closeDatabae();
        return index >= 0;
    }

    public List<Question> getQuestions() {
        openDatabae();
        List<Question> questions = new ArrayList<>();
        for (int i = 1; i < 15; i++) {
            String tableName = "Question" + i;
            List<Question> list = getQuestion(tableName, 2);
            if (list != null) {
                questions.addAll(list);
            }
        }
        closeDatabae();
        return questions;
    }

    private List<Question> getQuestion(String tableName, int limit) {
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

        cursor.moveToFirst(); // Dua con tro ve ban ghi dau tien
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            String question = cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION));
            String answerA = cursor.getString(cursor.getColumnIndex(COLUMN_CASE_A));
            String answerB = cursor.getString(cursor.getColumnIndex(COLUMN_CASE_B));
            String answerC = cursor.getString(cursor.getColumnIndex(COLUMN_CASE_C));
            String answerD = cursor.getString(cursor.getColumnIndex(COLUMN_CASE_D));
            int trueCase = cursor.getInt(cursor.getColumnIndex(COLUMN_TRUE_CASE));

            result.add(new Question(id, question, answerA, answerB, answerC, answerD, trueCase));
            cursor.moveToNext();
        }
        cursor.close();
        return null;
    }
}
