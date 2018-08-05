package com.test.niuhongbin.sqlitedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SimpleCursorAdapter simpleCursorAdapter;
    private ListView lv;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper dbHelper = new DBHelper(this, null, null, 1);
//        需要调用一下两个方法之一，数据库和表才能真正创建出来
//        正常情况下getReadableDatabase()与getWritableDatabase()得到的结果是一样的
//        非正常情况下，比如明确要求以只读的方式来打开数据库，或者磁盘已满，此时getReadableDatabase()得到的是只读的数据库
//        SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();
        db = dbHelper.getWritableDatabase();
        lv = findViewById(R.id.lv_01);

        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.activity_list_view, null, new String[]{"name", "nickname"}, new int[]{R.id.tv_name, R.id.tv_nickname}, SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        lv.setAdapter(simpleCursorAdapter);
    }

    public void click(View view) {
        ContentValues values;
        switch (view.getId()) {
            case R.id.btn_insert:
//                String sql = "insert into person(name,nickname) values('宋江','及时雨')";
                for (int i = 0; i < 10; i++) {
                    values = new ContentValues();
                    values.put("name", "宋江");
                    values.put("nickname", "及时雨");
                    db.insert("person", null, values);

                }


//                返回值：最近插入的哪一行的行号
//                long insert_result = db.insert("person", null, values);
//                if(insert_result>0){
//                    Toast.makeText(this,"插入成功", Toast.LENGTH_SHORT).show();
//                }
                break;
            case R.id.btn_select:
                Cursor cursor = db.query("person", null, "name=?", new String[]{"宋江"}, null, null, null);
//                while (cursor.moveToNext()){
//                    int columnIndex = cursor.getColumnIndex("name");
//                    String nameValue = cursor.getString(columnIndex);
//                    String nickname = cursor.getString(cursor.getColumnIndex("nickname"));
//                    Toast.makeText(this,"name="+nameValue+"nickname="+nickname,Toast.LENGTH_SHORT).show();
//                }
//                将simpleCursorAdapter中旧的cursor替换成新的cursor
//                simpleCursorAdapter中查询必须有"_id"这个参数
                simpleCursorAdapter.swapCursor(cursor);
                simpleCursorAdapter.notifyDataSetChanged();
                break;

            case R.id.btn_update:
                values = new ContentValues();
                values.put("name", "李逵");
                // 返回值:受这条修改语句影响的行数
                int update_result = db.update("person", values, "nickname=?", new String[]{"及时雨"});
                if (update_result > 0) {
                    Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_delete:
                // 返回值:受这条语句影响的行数
                int delete_result = db.delete("person", "nickname=?", new String[]{"及时雨"});
                if (delete_result > 0) {
                    Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}
