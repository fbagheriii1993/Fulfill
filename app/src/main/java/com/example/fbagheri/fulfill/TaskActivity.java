package com.example.fbagheri.fulfill;



import android.annotation.TargetApi;
import android.util.Log;
import android.widget.*;
import android.view.*;
import android.os.*;
import android.app.*;
import android.content.*;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.fbagheri.fulfill.Helper.*;

public class TaskActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSave, btnList;
    private int imageTagId, difficulty;


    private EditText edtTitle, edtDesc, edtDate;
    private boolean isInEditMode = false;
    int position = -1;
    TaskApp myApp;
    private DatePickerDialog datePDialog;
    private SimpleDateFormat dateFormatter, now_date;
    private Spinner spinnerDifficulty ,spinnerCategory;
    ArrayAdapter<CharSequence> difAdapter;


    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_layout);
        myApp = (TaskApp) getApplication();

        findViewsById();


        edtTitle.setText("");
        edtDesc.setText("");
        btnList.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        setDateTimeField();
        edtDate.setOnClickListener(this);

     /*   edtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edtDate.getWindowToken(), 0);
                    datePDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                    datePDialog.getDatePicker().setMaxDate(System.currentTimeMillis() + 12 * 30 * 24 * 60 * 60 * 1000L);
                    datePDialog.show();
                }
            }
        });*/


        difAdapter = ArrayAdapter
                .createFromResource(this , R.array.difficulty_array,
                        android.R.layout.simple_spinner_item);
        difAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(difAdapter);

        spinnerDifficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();

                switch (item) {
                    case "Easy":
                        difficulty = 1;
                        break;
                    case "Medium":
                        difficulty = 2;
                        break;
                    case "Hard":
                        difficulty = 3;
                        break;

                    default:
                        break;
                }

                Log.d("yes",item );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });



        ArrayAdapter<CharSequence> catAdapter = ArrayAdapter
                .createFromResource(this , R.array.category_array,
                        android.R.layout.simple_spinner_item);
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(catAdapter);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                String item = adapterView.getItemAtPosition(position).toString();

                switch (item) {
                    case "Study":
                        imageTagId = 1;
                        break;
                    case "Exersize":
                        imageTagId = 2;
                        break;
                    case "Work":
                        imageTagId = 3;
                        break;
                    case "Shopping":
                        imageTagId = 4;
                        break;
                    case "Home":
                        imageTagId = 5;
                        break;
                    case "Others":
                        imageTagId = 6;
                        break;

                    default:
                        break;
                }

                Log.d("yes",item );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

    }


    @TargetApi(Build.VERSION_CODES.N)
    private void findViewsById () {
        btnList = (Button) findViewById(R.id.btn_cancel);
        btnSave = (Button) findViewById(R.id.btn_save);

        edtTitle = (EditText) findViewById(R.id.edt_title);
        edtDesc = (EditText) findViewById(R.id.edt_desc);
        edtDate = (EditText) findViewById(R.id.edt_date);
        now_date = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        String currentDTime = now_date.format(new Date());
        edtDate.setText(currentDTime );
        edtDate.setInputType(InputType.TYPE_NULL);


        spinnerDifficulty = (Spinner) findViewById(R.id.spnr_difficulty);
        spinnerCategory =  (Spinner) findViewById(R.id.spnr_Category );
    }


    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        datePDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @TargetApi(Build.VERSION_CODES.N)
            public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                v.setMinDate(System.currentTimeMillis());
                v.setMaxDate(System.currentTimeMillis() + 12 * 30 * 24 * 60 * 60 * 1000L);
                Calendar newDate = Calendar.getInstance();

                newDate.set(year, monthOfYear, dayOfMonth);
                edtDate.setText(dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


    }


    @Override
    protected void onResume() {
        super.onResume();
        isInEditMode = false;
        if (getIntent().getExtras() != null) {
            position = getIntent().getExtras().getInt(Helper.DATA_KEY);
            int actionId = getIntent().getExtras().getInt(Helper.ACTION);
            Task myTask = myApp.getTasks().get(position);
            switch (actionId) {
                case 1: //View only
                    edtTitle.setFocusable(false);
                    edtDesc.setFocusable(false);
                    spinnerCategory.setEnabled(false);
                    spinnerDifficulty.setEnabled(false);

                    // btnSave.setVisibility(View.INVISIBLE);
                    // edtDate.setFocusable(false);

                    break;

                case 2: //Edit
                    edtTitle.setFocusable(true);
                    edtDesc.setFocusable(true);
                    edtTitle.requestFocus();
                    // btnSave.setVisibility(View.VISIBLE);
                    isInEditMode = true;
                    break;
            }
            edtTitle.setText(myTask.getTitle());
            edtDesc.setText(myTask.getDesc());
            edtDate.setText(myTask.getDueDate());
            // edtDate.setText("ok");
            spinnerDifficulty.setSelection(myTask.getDifficulty()-1);
            spinnerCategory.setSelection(myTask.getImageTagId()-1);
            //  int  d = myNote.getDifficulty() -1;




        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                saveTask();
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_cancel:
                Intent i = new Intent(this, HomeActivity.class);
                startActivity(i);
                break;
            case R.id.edt_date:
                datePDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePDialog.getDatePicker().setMaxDate(System.currentTimeMillis() + 12 * 30 * 24 * 60 * 60 * 1000L);
                datePDialog.show();

        }
    }


    private void saveTask() {
        if (edtTitle.getText().toString().equals("")) {
            showToast("Please enter a title", this);
            return;
        }
        if (edtDesc.getText().toString().equals("")) {
            showToast("Please enter a description", this);
            return;
        }

        if (isInEditMode) //update
        {

            myApp.getTasks().get(position).setTitle(edtTitle.getText().toString());
            myApp.getTasks().get(position).setDesc(edtDesc.getText().toString());
            myApp.getTasks().get(position).setImageTagId(imageTagId);
            myApp.getTasks().get(position).setDifficulty(difficulty);
            Log.d("yes", edtDate.getText().toString());

            myApp.getTasks().get(position).setDueDate(edtDate.getText().toString());

            Task updatedTask = new Task(edtTitle.getText().toString(), edtDesc.getText().toString(),position,imageTagId, difficulty);
            myApp.update(updatedTask);
            Helper.showToast("Edit completed", this);
            /*
            myApp.getTasks().get(position).setTitle(edtTitle.getText().toString());
            myApp.getTasks().get(position).setDesc(edtDesc.getText().toString());
            myApp.getTasks().get(position).setImageTagId(imageTagId);
            myApp.getTasks().get(position).setDifficulty(difficulty);
            myApp.getTasks().get(position).setDueDate(dueDate);
            Log.d("yes", edtDate.getText().toString());

            myApp.getTasks().get(position).setDueDate(edtDate.getText().toString());

            Task updatedTask = new Task(edtTitle.getText().toString(), edtDesc.getText().toString(),imageTagId, position, difficulty);
            myApp.update(updatedTask);
            Helper.showToast("Edit completed", this);*/
        }
        else   //insert
        {

            Task task = new Task(edtTitle.getText().toString(), edtDesc.getText().toString(),position, imageTagId, difficulty );
           // Task task = new Task(edtTitle.getText().toString(), edtDesc.getText().toString(),position, imageTagId, difficulty ,edtDate.getText().toString());
            myApp.getTasks().add(task);
            myApp.create(task);
            edtTitle.setText("");
            edtDesc.setText("");
            edtTitle.requestFocus();
            Log.d("yes", imageTagId + "" + difficulty + "");
            Log.d("yes",edtDate.getText().toString());


        }
    }
}
