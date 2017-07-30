package com.example.fbagheri.fulfill.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDB extends SQLiteOpenHelper{






    public MyDB(Context context) {

        super(context, "dbname.db", null, 4);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {


       db.execSQL("CREATE TABLE tblTask('id' INTEGER NOT NULL PRIMARY KEY  AUTOINCREMENT,'title' TEXT ," +
                " 'desc' TEXT , 'due' TEXT, 'tagId' INTEGER , 'dif' INTEGER , 'done' INTEGER , 'score' INTEGER );");

         Log.d("yes", "onCreate() is being executed");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS tblTask");
        db.execSQL("DROP TABLE IF EXISTS tblNote");
        onCreate(db);
        Log.d("yes", "onUpgrade() is being executed");
    }

}
