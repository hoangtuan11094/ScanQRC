package com.hoangtuan.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.hoangtuan.scanqrc.Model.HistoryFindModel;
import com.hoangtuan.scanqrc.Model.HistoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atbic on 30/3/2018.
 */

public class DBManagerFind extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME = "find_list";
    private static final String TABLE_NAME = "find";
    private static final String ID = "id";
    private static final String NAME = "namepro";
    private static final String MOTA = "mota";
    private static final String URI = "url";

    public DBManagerFind(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " integer primary key, " +
                URI + " TEXT, " +
                NAME + " TEXT, " +
                MOTA + " TEXT "
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

    public void addStudent(HistoryFindModel historyModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(URI, historyModel.getUrl());
        values.put(NAME, historyModel.getName());
        values.put(MOTA, historyModel.getMota());

        //Neu de null thi khi value bang null thi loi

        db.insert(TABLE_NAME, null, values);
Log.d("add",historyModel.getName());
        db.close();
    }

    public HistoryFindModel getSdtudentById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID,URI,
                        NAME, MOTA}, ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
//        Cursor cursor;
//        cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME +" WHERE id = '1'",null);
        if (cursor != null)
            cursor.moveToFirst();
        HistoryFindModel student = null;
        if( cursor != null && cursor.moveToFirst() ){
             student = new HistoryFindModel(cursor.getString(1), cursor.getString(2), cursor.getString(3));
            cursor.close();
        }
        
        db.close();
        return student;
    }
    public HistoryFindModel getMaxID(){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery= "SELECT * FROM " + TABLE_NAME+" ORDER BY "+ ID+" DESC LIMIT 1";
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToLast();

        HistoryFindModel student = new HistoryFindModel(cursor.getString(1), cursor.getString(2), cursor.getString(3));
    return student;
    }
    public List<HistoryFindModel> getAllStudent() {
        List<HistoryFindModel> listStudent = new ArrayList<HistoryFindModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HistoryFindModel student = new HistoryFindModel();
                student.setId(cursor.getInt(0));
                student.setUrl(cursor.getString(1));
                student.setName(cursor.getString(2));
                student.setMota(cursor.getString(3));

                listStudent.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listStudent;
    }
}
