package com.example.madprojectfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelperWeddingFood extends SQLiteOpenHelper {
    public static final String DBNAME="food.db";
    public DBHelperWeddingFood(@Nullable Context context) {
        super(context,"food.db",null,1);
    }
    TextView t;
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // sqLiteDatabase.execSQL("create table users(ngoid TEXT primary key,password TEXT,phone TEXT)");
        sqLiteDatabase.execSQL("create table food(id TEXT PRIMARY KEY,type TEXT,items TEXT,qty int,pdate TEXT,ptime TEXT,loc TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //sqLiteDatabase.execSQL("drop table if exists users");
        sqLiteDatabase.execSQL("drop table if exists food");
        //onCreate(sqLiteDatabase);
    }

    public  boolean insertDataw(String id,String type ,String items ,int qty,String pdate,String ptime,String loc)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues val=new ContentValues();
        val.put("id",id);
        val.put("type",type);
        val.put("items",items);
        val.put("qty",qty);
        val.put("pdate",pdate);
        val.put("ptime",ptime);
        val.put("loc",loc);
        long result=db.insert("food",null,val);
        if(result==-1)
            return false;
        else
            return true;
    }

    public ArrayList<FoodDetail> checkuserw(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from food where id=?", new String[]{id});
        ArrayList<FoodDetail> arr=new ArrayList<>();

        while(cursor.moveToNext())
        {
            FoodDetail model=new FoodDetail();
            model.sid=cursor.getString(0);
            model.type=cursor.getString(1);
            model.items=cursor.getString(2);
            model.qty=cursor.getInt(3);
            model.pdate=cursor.getString(4);
            model.ptime=cursor.getString(5);
            model.loc=cursor.getString(6);
            arr.add(model);

        }

        cursor.close();
        return arr;
    }
    public ArrayList<FoodDetail> checkuserwall()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from food ", null);
        ArrayList<FoodDetail> arr=new ArrayList<>();

        while(cursor.moveToNext())
        {
            FoodDetail model=new FoodDetail();
            model.sid=cursor.getString(0);
            model.type=cursor.getString(1);
            model.items=cursor.getString(2);
            model.qty=cursor.getInt(3);
            model.pdate=cursor.getString(4);
            model.ptime=cursor.getString(5);
            model.loc=cursor.getString(6);
            arr.add(model);

        }

        cursor.close();
        return arr;
    }
    public boolean deletew(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        long res= db.delete("food","id=?",new String[]{id});
        if(res==-1)
            return false;
        else
            return true;

    }


}
