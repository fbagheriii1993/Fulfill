package com.example.fbagheri.fulfill;


import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import com.example.fbagheri.fulfill.database.MyDB;

import java.util.ArrayList;
import java.util.List;


public class TaskApp extends Application
{
    private List<Task> tasks;
    private MyDB mydb;
    SQLiteDatabase database;

    public List<Task> getTasks() {
        return tasks;
    }


    private void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }




    @Override
    public void onCreate() {
        super.onCreate();
        tasks = new ArrayList<Task>();
        mydb = new MyDB(this);
        database = mydb.getWritableDatabase();

        setTasks(readAll());
        // gettasks().addAll(readAll());

        //   Log.d("yes", "onCreate() is being executed");
        //  showToast("Application Enters",this);

    }

    /////////////create
    public  void create(Task task)
    {
        String sql = String.format("INSERT INTO tblTask (title , desc ,imageTagId,dif, due) VALUES ('%s','%s' ,'%s','%s','%s')",
                task.getTitle() , task.getDesc() ,task.getImageTagId() ,task.getDifficulty() , task.getDueDate());
        database.execSQL(sql);

    }



    ///////////////////////////////////////////**  Toast **///////////////////////////////////////////////
    public void showToast(String msg){
        Toast.makeText(this , msg , Toast.LENGTH_LONG).show();
    }


    /////////////update
    public void update(Task task)
    {
        String sql = String.format("UPDATE tblTask SET title = '%s' , desc = '%s' ,imageTagId =%d , dif =%d ,due ='%s' ,done = %d WHERE id = %d",
                task.getTitle() , task.getDesc() , task.getImageTagId() ,task.getDifficulty() ,task.getDueDate(), task.getDone(),task.getId() );
        database.execSQL(sql);
    }
    public void updateDone(int position , int done)
    {
        String sql = String.format("UPDATE tblTask SET done = %d WHERE id = %d",
                done , position);
        database.execSQL(sql);
    }


    /////////////delete
    public  void delete(int id){
        String sql = String.format("DELETE FROM tblTask WHERE id = %s",
                id+"");
        database.execSQL(sql);
    }

    /////////////readAll
    public  List<Task> readAll(){
        //  String sql = String.format("Delete tbltask");
        // database.execSQL(sql);

        List<Task> tasks = new ArrayList<Task>();
        Cursor cursor = database.rawQuery("SELECT id, title, desc , imageTagId  , dif , due,done ,score FROM tblTask;",null);
        if (cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                tasks.add(read(id));
            }while (cursor.moveToNext());
        }


/*
        if (cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String desc = cursor.getString(cursor.getColumnIndex("desc"));
                int imageTagId = cursor.getInt(cursor.getColumnIndex("tagId"));
                int difficulty = cursor.getInt(cursor.getColumnIndex("dif"));
                String due = cursor.getString(cursor.getColumnIndex("due"));
                int done = cursor.getInt(cursor.getColumnIndex("done"));
                int score = cursor.getInt(cursor.getColumnIndex("score"));

                tasks.add(new Task(title,desc,imageTagId,id,difficulty ,due,done,score));
            }while (cursor.moveToNext());
        }*/

        return tasks;
    }

    /////////////read
    public  Task read(int id ){

        Task task = new Task();
        Cursor cursor = database.rawQuery("SELECT id, title, desc, imageTagId From tb1Task WHERE id =" + id,null);

                String title = cursor.getString(cursor.getColumnIndex("title"));
                String desc = cursor.getString(cursor.getColumnIndex("desc"));
                int imageTagId = cursor.getInt(cursor.getColumnIndex("tagId"));
                task.setTitle(title);
                task.setDesc(desc);
                task.setImageTagId(imageTagId);
        return task;

    }

    @Override
    public void onTerminate() {

        database.close();
        mydb.close();
        mydb = null;
        super.onTerminate();
        //   Log.d("yes", "onTerminate() is being executed");
    }




}
