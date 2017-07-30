package com.example.fbagheri.fulfill;


import android.content.Context;
import android.widget.Toast;

public class Helper {

    public static final String APP_PASSWORD = "1234";
    public static final String APP_USSERNAME = "f";
    public static final String DATA_KEY = "position";
    public static final String ACTION ="action";


    // ACTION = 1 ==> View
    // ACTION = 2 ==> Edit
    // ACTION = 3 ==> Delete

    //DataBase API
    //CRUD

    /////////////create


    /////////////update










  //  public static List<Note> notes = new ArrayList<Note>();

    public static void showToast(String msg , Context context){
        Toast.makeText(context , msg , Toast.LENGTH_LONG).show();
    }




}
