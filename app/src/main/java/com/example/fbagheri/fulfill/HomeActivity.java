package com.example.fbagheri.fulfill;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.fbagheri.fulfill.adapter.CustomAdapter;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    ListView lst_Notes;
    G myApp;
    ImageView mainImg;
    ProgressBar pb_hp = null , pb_st= null , pb_hlth= null , pb_exp= null ;
    TextView txt_hp ,txt_st , txt_hlth ,txt_exp;
    SharedPreferences shared ;
    SharedPreferences.Editor editor ;
    ImageButton btn_add , btn_today , btn_filter , btn_exit;

    TextView txt_coin ,txt_heart ,txt_name ,txt_age ;
    CustomAdapter adapter;
    FloatingActionButton floating_btn;
    View headerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        myApp = (G) getApplication();

        shared = getSharedPreferences("Pref", MODE_PRIVATE);
        editor = shared.edit();
        editor.commit();


        findViewsById();
        setListener();
        setViews();
    }


    public void setListener(){
        floating_btn.setOnClickListener(this);
       /* btn_exit.setOnClickListener(this);
        btn_filter.setOnClickListener(this);
        btn_today.setOnClickListener(this);*/
        listListener();

    }

   public void listListener(){
       Log.d("yes","11111");

       lst_Notes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               Log.d("yes","yeeeeeeees");
               Intent intent = new Intent(HomeActivity.this , TaskActivity.class);
               intent.putExtra(Helper.DATA_KEY,position);
               startActivity(intent);
               finish();
           }
       });

       lst_Notes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
               final AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(HomeActivity.this);
               myAlertDialog.setTitle("Delete");
               myAlertDialog.setMessage("Are you sure you want to delete this task?");
               myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                   public void onClick(DialogInterface dialog, int arg1) {
                       myApp.getTasks().remove(position);
                       myApp.delete(position);
                       adapter.notifyDataSetChanged();
                       dialog.dismiss();
                   }});
               myAlertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                   public void onClick(DialogInterface dialog, int arg1) {
                       dialog.dismiss();
                   }});
               myAlertDialog.show();
               return true;
           }
       });


   }


    @Override
    public void onClick(View view) {
            switch (view.getId()) {
                case R.id.floatingbtn:
                    Intent intent = new Intent(HomeActivity.this , TaskActivity.class);
                    startActivity(intent);
                  //  finish();
                    break;
                /*case R.id.btn_today:
                    Intent intent1 = new Intent(HomeActivity.this , FilterActivity.class);
                    startActivity(intent1);
                   // finish();
                    break;
                case R.id.btn_filter:
                    Intent intent2 = new Intent(HomeActivity.this , FilterActivity.class);
                    startActivity(intent2);
                   // finish();

                    break;
                case R.id.btn_exit:
                    finish();

                    break;*/
                default:
                    break;

            }

    }


    public void setViews(){
       pb_hp.setSecondaryProgress(shared.getInt("happiness" ,0));
        pb_st.setSecondaryProgress(shared.getInt("satisfaction",0));
        pb_hlth.setSecondaryProgress(shared.getInt("health",0));
        pb_exp.setSecondaryProgress(shared.getInt("experience",0));
        pb_hp.setProgress(0);//initially progress is 0 
        pb_hp.setMax(100);

        pb_st.setProgress(0);
        pb_st.setMax(100);

        pb_hlth.setProgress(20);
        pb_hlth.setMax(100);

      pb_exp.setProgress(0);
        pb_exp.setMax(100);


        txt_hp.setText(shared.getInt("happiness" ,0) + "/100");
        txt_st.setText(shared.getInt("satisfaction" ,0) + "/100");
        txt_hlth.setText(shared.getInt("health" ,0) + "/100");
        txt_exp.setText(shared.getInt("experience" ,0) + "/100");

        txt_heart.setText(shared.getInt("heart" ,0) + "");
        txt_coin.setText(shared.getInt("coin" ,0) + "");

        txt_age.setText(shared.getInt("age" ,0)+" years old");
        txt_name.setText(shared.getString("name" ,""));


    }


    private void findViewsById(){
       // mainImg = (ImageView) findViewById(R.id.main_img);


      /*  btn_exit = (ImageButton) findViewById(R.id.btn_exit);
        btn_add = (ImageButton) findViewById(R.id.btn_add);
        btn_filter = (ImageButton) findViewById(R.id.btn_filter);
        btn_today = (ImageButton) findViewById(R.id.btn_today);*/

        lst_Notes = (ListView) findViewById(R.id.lst_notes);
        adapter = new CustomAdapter(myApp.getTasks(),this);
        lst_Notes.setAdapter(adapter);
       // headerView= ((LayoutInflater)HomeActivity.this.getSystemService(HomeActivity.this.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.header, null, false);
       LayoutInflater inflaterHeader = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflaterHeader.inflate(
                R.layout.header, lst_Notes, false);
        lst_Notes.addHeaderView(header);



        pb_hp = (ProgressBar) findViewById(R.id.pb_hp);
        pb_st = (ProgressBar) findViewById(R.id.pb_st);
        pb_hlth = (ProgressBar) findViewById(R.id.pb_hlth);
        pb_exp = (ProgressBar) findViewById(R.id.pb_exp);


        txt_hp  = (TextView) findViewById(R.id.hp_txt);
        txt_st  = (TextView) findViewById(R.id.st_txt);
        txt_hlth  = (TextView) findViewById(R.id.hlth_txt);
        txt_exp  = (TextView) findViewById(R.id.exp_txt);


        txt_coin = (TextView) findViewById(R.id.coin_txt);
        txt_heart = (TextView) findViewById(R.id.heart_txt);

        txt_name = (TextView) findViewById(R.id.txt_name);
        txt_age = (TextView) findViewById(R.id.txt_age);
        floating_btn = (FloatingActionButton) findViewById(R.id.floatingbtn);
    }



}
