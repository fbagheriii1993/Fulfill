package com.example.fbagheri.fulfill.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.fbagheri.fulfill.R;
import com.example.fbagheri.fulfill.Task;

import java.util.List;

/**
 * Created by F.Bagheri on 30/12/2016.
 */
public class CustomAdapter extends BaseAdapter
{
    List<Task> data;
    Context context;

    private int done;


    public CustomAdapter(List<Task> data, Context context) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        //rendering

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_layout, null); //setContentView
        }
        ImageView img_Thumbnail;
        TextView txt_Title;
        final SeekBar sb_done;
        final Button btn_done;


        //ImageView img_Thumbnail;
        //TextView txt_Title;
        //SeekBar sb_done;

        img_Thumbnail = (ImageView) convertView.findViewById(R.id.img_thumbnail);
        txt_Title = (TextView) convertView.findViewById(R.id.txt_title);
        sb_done = (SeekBar) convertView.findViewById(R.id.sb_done);
        sb_done.setVisibility(View.GONE);
        btn_done = (Button) convertView.findViewById(R.id.btn_done);
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sb_done.getVisibility()== View.VISIBLE )
                         sb_done.setVisibility(View.GONE);
                else if(sb_done.getVisibility()== View.GONE )
                    sb_done.setVisibility(View.VISIBLE);



            }
        });
        btn_done.setText(sb_done.getProgress()+"%");
        sb_done.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                done = progress;
                Log.d("pro",progress+"");
                btn_done.setText(progress + "%");
                data.get(position).setDone(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //  Spinner =


        txt_Title.setText(data.get(position).getTitle() + data.get(position).getDone() + "");
        switch (data.get(position).getDifficulty()) {
            case 1:
                img_Thumbnail.setBackgroundColor(Color.YELLOW);
                switch (data.get(position).getImageTagId()) {
                    case 1:
                        img_Thumbnail.setImageResource(R.drawable.study);
                        break;
                    case 2:
                        img_Thumbnail.setImageResource(R.drawable.exercise);
                        break;
                    case 3:
                        img_Thumbnail.setImageResource(R.drawable.work);
                        break;
                    case 4:
                        img_Thumbnail.setImageResource(R.drawable.shopping);
                        break;
                    case 5:
                        img_Thumbnail.setImageResource(R.drawable.home);
                        break;
                    case 6:
                        img_Thumbnail.setImageResource(R.drawable.others);
                        break;

                }
                break;
            case 2:
                img_Thumbnail.setBackgroundColor(Color.GREEN);
                switch (data.get(position).getImageTagId()) {
                    case 1:
                        img_Thumbnail.setImageResource(R.drawable.study);
                        break;
                    case 2:
                        img_Thumbnail.setImageResource(R.drawable.exercise);
                        break;
                    case 3:
                        img_Thumbnail.setImageResource(R.drawable.work);
                        break;
                    case 4:
                        img_Thumbnail.setImageResource(R.drawable.shopping);
                        break;
                    case 5:
                        img_Thumbnail.setImageResource(R.drawable.home);
                        break;
                    case 6:
                        img_Thumbnail.setImageResource(R.drawable.others);
                        break;

                }
                break;

            case 3:
                img_Thumbnail.setBackgroundColor(Color.RED);
                switch (data.get(position).getImageTagId()) {
                    case 1:
                        img_Thumbnail.setImageResource(R.drawable.study);
                        break;
                    case 2:
                        img_Thumbnail.setImageResource(R.drawable.exercise);
                        break;
                    case 3:
                        img_Thumbnail.setImageResource(R.drawable.work);
                        break;
                    case 4:
                        img_Thumbnail.setImageResource(R.drawable.shopping);
                        break;
                    case 5:
                        img_Thumbnail.setImageResource(R.drawable.home);
                        break;
                    case 6:
                        img_Thumbnail.setImageResource(R.drawable.others);
                        break;

                }
                break;
        }
        //////////////////////////////////////////////////////////
        return convertView;
    }




}

//////////////////////////////////////////////////////////////////////



