package com.test.niuhongbin.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "person.db";
    //version>=1
    private static final int DB_VERSION = 1;


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //创建数据库中的表
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //SQLite数据库里面字段一般是不区分类型的，但是主键除外，主键必须是整形
        String sql = "create table persion(_id integer primary key autoincrement not null,name char(10),nickname char(10))";
        sqLiteDatabase.execSQL(sql);
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
