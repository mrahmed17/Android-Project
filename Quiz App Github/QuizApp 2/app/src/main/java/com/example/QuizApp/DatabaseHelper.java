package com.example.QuizApp;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;
    private static final String DATABASE_NAME = "quiz.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tbluser";
    private static final String COLUMN_ID = "id_user";
    private static final String COLUMN_NAME = "username";
    private static final String COLUMN_AGE = "password";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String createTableQuery = "CREATE TABLE tbluser (id_user INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)";
//        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Define the SQL upgrade statement here
        String UPGRADE_TABLE = "DROP TABLE IF EXISTS tbluser";
        db.execSQL(UPGRADE_TABLE);
        onCreate(db);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM tbluser", null);
    }
    public long addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        long result = db.insert("tbluser", null, values);
        db.close();
        return result;
    }
}
