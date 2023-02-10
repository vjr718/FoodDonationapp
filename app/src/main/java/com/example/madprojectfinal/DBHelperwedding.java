package com.example.madprojectfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperwedding extends SQLiteOpenHelper {

    public static final String DBNAME="loginw.db";
    public DBHelperwedding(@Nullable Context context) {
        super(context,"loginw.db",null,1);
    }
   // final String str1="create table users(ngoid TEXT primary key,password TEXT,phone TEXT)";
    final String  str2="create table weddinghall(id TEXT primary key,password TEXT,name TEXT,phone TEXT)";
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       // sqLiteDatabase.execSQL("create table users(ngoid TEXT primary key,password TEXT,phone TEXT)");
        sqLiteDatabase.execSQL("create table weddinghall(id TEXT primary key,password TEXT,name TEXT,phone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //sqLiteDatabase.execSQL("drop table if exists users");
        sqLiteDatabase.execSQL("drop table if exists weddinghall");
        //onCreate(sqLiteDatabase);
    }

    public  boolean insertDataw(String id,String pass,String name,String phone)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues val=new ContentValues();
        val.put("id",id);
        val.put("password",pass);
        val.put("name",name);

        val.put("phone",phone);
        long result=db.insert("weddinghall",null,val);
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean checkuserw(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from weddinghall where id=?", new String[]{id});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean checkpassw(String id,String pass)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from weddinghall where id=? and password=? ", new String[]{id,pass});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
