package com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {

    public DBhelper(@Nullable Context context) {
        super(context, "login DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table users(username Text primary key,password Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldvertion, int newvertion) {
        myDB.execSQL("drop table if exists users");
    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("password", password);
        long result = myDB.insert("users", null, cv);
        if(result==-1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean checkUser(String username){
    SQLiteDatabase myDB=this.getWritableDatabase();
        Cursor cursor=myDB.rawQuery("select * from users where username = ?", new String[] {username});
        if(cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }

    public Boolean checkPassword(String username,String password){
        SQLiteDatabase myDB=this.getWritableDatabase();
        Cursor cursor=myDB.rawQuery("select * from users where username = ? and password=?", new String[] {username,password});
        if(cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }

}
