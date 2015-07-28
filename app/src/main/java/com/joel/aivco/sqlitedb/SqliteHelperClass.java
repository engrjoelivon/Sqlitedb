package com.joel.aivco.sqlitedb;

/**
 * Created by lenovo on 7/22/2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class SqliteHelperClass extends SQLiteOpenHelper
{
    private static final String TABLE_STUDENTS="students";
    private static int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="students database";
    private SQLiteDatabase sqLiteDatabase;
   // private static final String CREATE_TABLE_STUDENT="CREATE TABLE "+ TA

public SqliteHelperClass(Context context)
{
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.d(MainActivity.kEEP_TRACK,"......SqliteHelperClass instantiated......");
    sqLiteDatabase= this.getWritableDatabase();


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryString="CREATE TABLE "+TABLE_STUDENTS+"(_id INTEGER PRIMARY KEY,firstname TEXT,lastname TEXT)";
        sqLiteDatabase.execSQL(queryString);
        Log.d(MainActivity.kEEP_TRACK,"database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_STUDENTS);
        Log.d(MainActivity.kEEP_TRACK,"......Table dropped......");
        onCreate(sqLiteDatabase);
    }
public long insertData(String firstName,String lastName) throws SQLiteException
{

    sqLiteDatabase= this.getWritableDatabase();
    ContentValues cv=new ContentValues() ;
    cv.put("firstname",firstName);
    cv.put("lastname",lastName);
    long rowInserted=sqLiteDatabase.insert(TABLE_STUDENTS,null,cv);

    return rowInserted;

}
    public Cursor getData() throws SQLiteException
    {
        Log.d(MainActivity.kEEP_TRACK,".......in cursor....");

        sqLiteDatabase= this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.query(TABLE_STUDENTS,new String[]{"_id","firstname","lastname"},null,null,null,null,null);
        Log.d(MainActivity.kEEP_TRACK,".......cursor returned....");
        return cursor;

    }

public void deleteTable()
{
    DATABASE_VERSION++;
    Log.d(MainActivity.kEEP_TRACK,"......database version changed to...... "+ DATABASE_VERSION );
}

}
