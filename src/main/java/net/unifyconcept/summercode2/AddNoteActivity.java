package net.unifyconcept.ucdairy;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import android.view.View.OnClickListener;

/**
 * Created by Olabode Qudus on 5/27/2018.
 */

public class AddNoteActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editdate, edittitle, editmessage, editTextId;
    Button btnSaveAddNote, btnResetFields;
    //Copy from TODO remove when error occur
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.addnote);
        myDb = new DatabaseHelper(this);
        editdate = (EditText) findViewById(R.id.notedate);
        edittitle = (EditText) findViewById(R.id.txtTitle);
        editmessage = (EditText) findViewById(R.id.txtMessage);
        dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.US);
        //dd-MM-yyyy
        // editTextId = (EditText)findViewById(R.id.editText_id);
        btnSaveAddNote = (Button) findViewById(R.id.btnSaveAddNote);
        btnResetFields = (Button) findViewById(R.id.btnCloseAddNote);
        editdate.setInputType(InputType.TYPE_NULL);
        editdate.requestFocus();
        SimpleDateFormat dateF = new SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault());
        SimpleDateFormat timeF = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String date = dateF.format(Calendar.getInstance().getTime());
        editdate.setText(date);

        setDateTimeField();
            AddData();
        editdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fromDatePickerDialog.show();
                    }
                });
    }

    //Method setdatetimefield TODO remove when error occur
    private void setDateTimeField() {
     //  editdate.setOnClickListener(this);
      //  toDateEtxt.setOnClickListener(this);

                    Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                editdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

      /*  toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                toDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));*/

    }
   /* @Override
    public void onClick(View view) {
        if (view == editdate) {
            fromDatePickerDialog.show();
        } //else if (view == toDateEtxt) {
        //toDatePickerDialog.show();
        //  }
    }*/

        //Stops here
    public void AddData() {
        btnSaveAddNote.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editdate.getText().toString(),
                                edittitle.getText().toString(),
                                editmessage.getText().toString());
                            if (isInserted == true) {
                                Toast.makeText(AddNoteActivity.this, "Data Has Been Saved To Diary Successfully", Toast.LENGTH_LONG).show();
                                edittitle.setText("");//.toString();
                                editmessage.setText("");
                            } else {
                                Toast.makeText(AddNoteActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                            }
                    }
                }
        );
        btnResetFields.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                edittitle.setText("");
                editmessage.setText("");
            }
        });
    }
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //  getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

}
