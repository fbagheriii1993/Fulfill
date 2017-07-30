package com.example.fbagheri.fulfill;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.fbagheri.fulfill.adapter.ListAdapter;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    ListView lstNotes;
    TaskApp myApp;
    ImageView mainImg;
    ProgressBar pb_hp = null , pb_st= null , pb_hlth= null , pb_exp= null ;
    TextView txt_hp ,txt_st , txt_hlth ,txt_exp;
    String name ="star";
    SharedPreferences shared ;
    SharedPreferences.Editor editor ;
    ImageButton btn_add , btn_today , btn_filter , btn_exit;

    TextView txt_coin ,txt_heart ,txt_name ,txt_age ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        myApp = (TaskApp) getApplication();

        shared = getSharedPreferences("Pref", MODE_PRIVATE);
        editor = shared.edit();
        editor.commit();

        findViewsById();

        pb_hp.setProgress(0);//initially progress is 0
        pb_hp.setMax(100);

        pb_st.setProgress(0);
        pb_st.setMax(100);

        pb_hlth.setProgress(0);
        pb_hlth.setMax(100);

        pb_exp.setProgress(0);
        pb_exp.setMax(100);




        final ListAdapter adapter = new ListAdapter(myApp.getTasks(),this);
        lstNotes.setAdapter(adapter);


        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this , TaskActivity.class);
                startActivity(intent);

                finish();
            }
        });

        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this , HomeActivity.class);
                startActivity(intent);
                // finish();
            }
        });
        btn_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this , FilterActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //////////////////////////////////////////
        lstNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(HomeActivity.this , TaskActivity.class);
                intent.putExtra(Helper.DATA_KEY,position);
                intent.putExtra(Helper.ACTION ,2);
                startActivity(intent);
                finish();
            }
        });

        // lstNotes.getSelectedItem().


        /////////////////////////////////////////
        lstNotes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                LayoutInflater layoutInflater
                        = (LayoutInflater)getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                final View popupView = layoutInflater.inflate(R.layout.popup, null);
                final PopupWindow popupWindow = new PopupWindow(
                        popupView,
                        ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.WRAP_CONTENT);


                Button btnCancel,btnDelete;
                btnCancel = (Button) popupView.findViewById(R.id.btn_cancel);
                btnDelete = (Button) popupView.findViewById(R.id.btn_delete);

                //////////////btnCancel
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();

                    }
                });



                ////////////btnDelete
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myApp.getTasks().remove(position);
                        myApp.delete(position);
                        adapter.notifyDataSetChanged();
                        popupWindow.dismiss();

                    }
                });



                popupWindow.showAsDropDown(txt_age,50,0);
                return true;
            }
        });





        // int i = shared.getInt("happiness" ,2);
        // Log.d("yes" , i+"" );
        pb_hp.setSecondaryProgress(shared.getInt("happiness" ,0));
        pb_st.setSecondaryProgress(shared.getInt("satisfaction",0));
        pb_hlth.setSecondaryProgress(shared.getInt("health",0));
        pb_exp.setSecondaryProgress(shared.getInt("experience",0));

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
        mainImg = (ImageView) findViewById(R.id.main_img);


        btn_exit = (ImageButton) findViewById(R.id.btn_exit);
        btn_add = (ImageButton) findViewById(R.id.btn_add);
        btn_filter = (ImageButton) findViewById(R.id.btn_filter);
        btn_today = (ImageButton) findViewById(R.id.btn_today);
        lstNotes = (ListView) findViewById(R.id.lst_notes);



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



    }


    @Override
    public void onClick(View view) {



    }
}
