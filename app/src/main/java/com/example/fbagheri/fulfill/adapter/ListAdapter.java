package com.example.fbagheri.fulfill.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.fbagheri.fulfill.Task;
import com.example.fbagheri.fulfill.TaskApp;
import com.example.fbagheri.fulfill.R;

import java.util.List;

/**
 * Created by F.Bagheri on 30/12/2016.
 */
public class ListAdapter extends BaseAdapter
{
    List<Task> data;
    Context context;

    public ListAdapter(List<Task> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount(){
        return data.size(); //number of rows
    }

    @Override
    public Object getItem(int position) {
        return data.get(position); // an specific item
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //rendering

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listadapter_layout , null); //setContentView
        }

        ImageView imgThumbnail;
        TextView txtTitle;
        final Spinner spinnerDone;
        ArrayAdapter<CharSequence> dAdapter;

        imgThumbnail = (ImageView) convertView.findViewById(R.id.img_thumbnail);
        txtTitle = (TextView) convertView.findViewById(R.id.txt_title);
        //  Spinner =



        txtTitle.setText(data.get(position).getTitle()+ data.get(position).getDone()+"");
        switch (data.get(position).getDifficulty()){
            case 1:
                imgThumbnail.setBackgroundColor(Color.YELLOW);
                switch (data.get(position).getImageTagId())
                {
                    case 1:
                        imgThumbnail.setImageResource(R.drawable.study);
                        break;
                    case 2:
                        imgThumbnail.setImageResource(R.drawable.exercise);
                        break;
                    case 3:
                        imgThumbnail.setImageResource(R.drawable.work);
                        break;
                    case 4:
                        imgThumbnail.setImageResource(R.drawable.shopping);
                        break;
                    case 5:
                        imgThumbnail.setImageResource(R.drawable.home);
                        break;
                    case 6:
                        imgThumbnail.setImageResource(R.drawable.others);
                        break;

                }
                break;
            case 2:
                imgThumbnail.setBackgroundColor(Color.GREEN);
                switch (data.get(position).getImageTagId())
                {
                    case 1:
                        imgThumbnail.setImageResource(R.drawable.study);
                        break;
                    case 2:
                        imgThumbnail.setImageResource(R.drawable.exercise);
                        break;
                    case 3:
                        imgThumbnail.setImageResource(R.drawable.work);
                        break;
                    case 4:
                        imgThumbnail.setImageResource(R.drawable.shopping);
                        break;
                    case 5:
                        imgThumbnail.setImageResource(R.drawable.home);
                        break;
                    case 6:
                        imgThumbnail.setImageResource(R.drawable.others);
                        break;

                }
                break;

            case 3 :
                imgThumbnail.setBackgroundColor(Color.RED);
                switch (data.get(position).getImageTagId())
                {
                    case 1:
                        imgThumbnail.setImageResource(R.drawable.study);
                        break;
                    case 2:
                        imgThumbnail.setImageResource(R.drawable.exercise);
                        break;
                    case 3:
                        imgThumbnail.setImageResource(R.drawable.work);
                        break;
                    case 4:
                        imgThumbnail.setImageResource(R.drawable.shopping);
                        break;
                    case 5:
                        imgThumbnail.setImageResource(R.drawable.home);
                        break;
                    case 6:
                        imgThumbnail.setImageResource(R.drawable.others);
                        break;

                }
                break;
        }
        ///////////////////////////////////////////////////////////////
        spinnerDone = (Spinner) convertView.findViewById(R.id.spnr_wDone);
        dAdapter = ArrayAdapter
                .createFromResource(this.context , R.array.done_array,
                        android.R.layout.simple_spinner_item);
        dAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDone.setAdapter(dAdapter);

        switch (data.get(position).getDone()) {
            case 0:
                spinnerDone.setSelection(0);
                break;
            case 25:
                spinnerDone.setSelection(1);
                break;
            case 50:
                spinnerDone.setSelection(2);
                break;
            case 100:
                spinnerDone.setSelection(3);
                break;
            default:
                break;
        }


        spinnerDone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();





                switch (adapterView.getSelectedItemPosition()) {
                    case 0:
                        Log.d("yes","0");
                        //  data.get(position).setDone(0);
                        spinnerDone.setSelection(0);

                        break;
                    case 1:
                        Log.d("yes","1");
                        spinnerDone.setSelection(1);
                        break;
                    case 2:
                        Log.d("yes","2");
                        // data.get(position).setDone(50);
                        spinnerDone.setSelection(2);
                        break;
                    case 3:
                        Log.d("yes","3");
                        // data.get(position).setDone(100);
                        spinnerDone.setSelection(3);
                        break;

                    default:
                        break;
                }
                Log.d("yes",data.get(position ).getDone()+"" );

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });




        return convertView;
    }
}
