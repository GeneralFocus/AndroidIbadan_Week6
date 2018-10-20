package net.unifyconcept.ucdairy;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by MD.ISRAFIL MAHMUD on 7/15/2017.
 */

public class NoteViewActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    EditText  date;
    Button btnUpdateDiary;
    String sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noteview);
        myDb = new DatabaseHelper(this);
      //  UpdateData();
        setDateTimeField();
        EditText dateview = (EditText)findViewById(R.id.date_view);
      //  sub.equals(sub_id);
        dateview.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fromDatePickerDialog.show();
                    }
                });
        btnUpdateDiary = (Button)findViewById(R.id.btnUpdateDiary);
        btnUpdateDiary.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText titleview,textview, dateview ;

                        titleview =(EditText)findViewById(R.id.title_view);
                        textview =(EditText)findViewById(R.id.text_view);
                        dateview = (EditText)findViewById(R.id.date_view);
                        TextView noteid = (TextView)findViewById(R.id.noteid);
                        boolean isUpdate = myDb.updateData(noteid.getText().toString(),dateview.getText().toString(),
                                titleview.getText().toString(),
                                textview.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(NoteViewActivity.this,"Data Updated Successfully",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(NoteViewActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
     //   UpdateData();
        cont();
      //  UpdateData();
       /* setDateTimeField();

        dateview.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fromDatePickerDialog.show();
                    }
                });*/
    }
    public void cont()
    {
        String  sub_id = getIntent().getStringExtra("ID");
        String title=getIntent().getStringExtra("TITLE");
        String text=getIntent().getStringExtra("MESSAGE");
        String date = getIntent().getStringExtra("DATE");


        EditText titleview =(EditText)findViewById(R.id.title_view);
        EditText textview =(EditText)findViewById(R.id.text_view);
        EditText dateview = (EditText)findViewById(R.id.date_view);
        TextView noteid = (TextView)findViewById(R.id.noteid);
        titleview.setText(title);
        textview.setText(text);
        dateview.setText(date);
        noteid.setText(sub_id);

      //  dateview = (EditText)findViewById(R.id.date_view);


    }
    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                date = (EditText)findViewById(R.id.date_view);
                date.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }
    public void UpdateData() {
        btnUpdateDiary.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText titleview,textview, dateview ;

                        titleview =(EditText)findViewById(R.id.title_view);
                        textview =(EditText)findViewById(R.id.text_view);
                        dateview = (EditText)findViewById(R.id.date_view);
                        TextView noteid = (TextView)findViewById(R.id.noteid);
                        boolean isUpdate = myDb.updateData(noteid.getText().toString(),dateview.getText().toString(),
                                titleview.getText().toString(),
                                textview.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(NoteViewActivity.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(NoteViewActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}