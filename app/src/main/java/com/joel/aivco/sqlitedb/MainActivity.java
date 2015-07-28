package com.joel.aivco.sqlitedb;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;





public class MainActivity extends ActionBarActivity implements View.OnClickListener{
private SQLiteDatabase sqLiteDatabase;
private SqliteHelperClass sqliteHelperClass;
public static final String kEEP_TRACK="keep_track";
private TextView display;
private Button showDatas;
private Button setDatas;
private ContentValues contentValues;
private Button delete;
private EditText fName;
private EditText lName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fName=(EditText)findViewById(R.id.fname);
        lName=(EditText)findViewById(R.id.lname);
        display=(TextView)findViewById(R.id.display);
        showDatas=(Button)findViewById(R.id.getdata);
        setDatas=(Button)findViewById(R.id.savedate);
        delete=(Button)findViewById(R.id.delete);
        delete.setOnClickListener(this);
        showDatas.setOnClickListener(this);
        setDatas.setOnClickListener(this);

        Log.d(kEEP_TRACK,"....oncreate......");

        sqliteHelperClass =new SqliteHelperClass(this);










    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
  public void onClick(View view)
  {
      if(view==showDatas)
      {
          Log.d(kEEP_TRACK,"....show datas has been clicked");

       Cursor cursor= sqliteHelperClass.getData();
          Log.d(kEEP_TRACK,"....cursor has been returned");
          cursor.moveToFirst();
          if(cursor.getCount()>0)
          { Log.d(kEEP_TRACK,"....move to first called");
          String ans=cursor.getString(2);
          int pos=cursor.getInt(0);
          Log.d(kEEP_TRACK,"....String returned");
         display.setText(ans + pos);
          cursor.close();}

      }
      else if(view==setDatas)
      {
         Long ans= sqliteHelperClass.insertData(fName.getText().toString(),lName.getText().toString());
          Log.d(kEEP_TRACK,"The column where the value was stored is "+ans);


      }

     else  if(view==delete)
          {

              Log.d(kEEP_TRACK,"....dropping table called......");
              sqliteHelperClass.deleteTable();
          }

  }

}
