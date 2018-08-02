package com.test.niuhongbin.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "person1.db";
    //version>=1
    private static final int DB_VERSION = 1;

    private Context mContext;

    public DBHelper(Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, DB_VERSION);
        mContext = context;
    }



    //创建数据库中的表
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //SQLite数据库里面字段一般是不区分类型的，但是主键除外，主键必须是整形
        String sql = "create table person(_id integer primary key autoincrement not null,name char(10),nickname char(10))";
        sqLiteDatabase.execSQL(sql);
        Toast.makeText(mContext,"create success",Toast.LENGTH_LONG).show();
    }

    //升级的方法
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i1>i){
            String sql = "drop table if exists person";
            sqLiteDatabase.execSQL(sql);
            onCreate(sqLiteDatabase);
        }
    }
}
