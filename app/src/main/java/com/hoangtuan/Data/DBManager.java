package com.hoangtuan.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


import com.hoangtuan.scanqrc.Model.HistoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atbic on 30/3/2018.
 */

public class DBManager extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME = "student_list";
    private static final String TABLE_NAME = "student";
    private static final String ID = "id";
    private static final String STYLE = "style";
    private static final String CONTENT = "content";
    private static final String URI = "url";

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " integer primary key, " +
                URI + " TEXT, " +
                STYLE + " TEXT, " +
                CONTENT + " TEXT "
                + ")";
        db.execSQL(sqlQuery);
        Toast.makeText(context, "Create successfylly", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Toast.makeText(context, "Drop successfylly", Toast.LENGTH_SHORT).show();
    }

    public void addStudent(HistoryModel historyModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(URI, historyModel.getImgStyleCode());
        values.put(STYLE, historyModel.getStyleCode());
        values.put(CONTENT, historyModel.getContent());

        //Neu de null thi khi value bang null thi loi

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public HistoryModel getSdtudentById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID,URI,
                        STYLE, CONTENT}, ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
//        Cursor cursor;
//        cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME +" WHERE id = '1'",null);
        if (cursor != null)
            cursor.moveToFirst();

        HistoryModel student = new HistoryModel(cursor.getInt(1), cursor.getString(2), cursor.getString(3));
        cursor.close();
        db.close();
        return student;
    }
    public List<HistoryModel> getAllStudent() {
        List<HistoryModel> listStudent = new ArrayList<HistoryModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HistoryModel student = new HistoryModel();
                student.setId(cursor.getInt(0));
                student.setImgStyleCode(cursor.getInt(1));
                student.setStyleCode(cursor.getString(2));
                student.setContent(cursor.getString(3));

                listStudent.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listStudent;
    }
}
