package net.unifyconcept.summercode2;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//import android.support.v4.app.FragmentActivity;

public class MainActivity extends AppCompatActivity {
    //	The	TextView	where	we	will	show	results
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);                            //		Initialize	mTextView
        mTextView = (TextView) findViewById(R.id.textView1);
    }

    public void startTask(View view) {
        //	Put	a	message	in	the	text	view
        mTextView.setText("Napping...	");
        //	Start	the	AsyncTask.
        // 	The	AsyncTask	has	a	callback	that	will	update	the	text	view.
        new SimpleAsyncTask(mTextView).execute();
    }

    public void alertMe(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Alert");
        builder.setMessage("Do You Know I Like You Jamilah?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Let Start Dating", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Abdul Rahman Loves You Incase You Don't Know", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Okay o", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

    private void dialNumber() {
      /*  EditText editText = findViewById(R.id.edttrial);
        String	mPhoneNum	=	null;
        if	(editText	!=	null)	mPhoneNum	=	"tel:"	+	editText.getText().toString();
        //Log.d(TAG,	"dialNumber:	"	+	mPhoneNum);
        Intent	intent	=	new	Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(mPhoneNum));
        if	(intent.resolveActivity(getPackageManager())	!=	null)
        {startActivity(intent);
        }	else{
            Log.d("ImplicitIntents",	"Can't	handle	this!");
        } }*/
    }
}