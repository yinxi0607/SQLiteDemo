package com.test.niuhongbin.sqlitedemo;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper dbHelper = new DBHelper(this);
//        需要调用一下两个方法之一，数据库和表才能真正创建出来
//        正常情况下getReadableDatabase()与getWritableDatabase()得到的结果是一样的
//        非正常情况下，比如明确要求以只读的方式来打开数据库，或者磁盘已满，此时getReadableDatabase()得到的是只读的数据库
//        SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();
        SQLiteDatabase writableDatabase = dbHelper.getWritableDatabase();
    }
}
