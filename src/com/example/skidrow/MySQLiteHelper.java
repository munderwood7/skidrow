package com.example.skidrow;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

  public static final String TABLE_EVENTS = "events";
  public static final String COLUMN_ID = "_id";
  
  public static final String COLUMN_NAME = "name";
  public static final String COLUMN_DESCRIPTION = "description";
  public static final String COLUMN_DRUG = "drug";
  public static final String COLUMN_PRICE_CHANGE = "priceChange";
  public static final String COLUMN_INITIAL_STEP = "initialStep";
  public static final String COLUMN_DURATION = "duration";
  public static final String COLUMN_CITY = "city";
  public static final String COLUMN_TERMINATION_MESSAGE= "terminatioMessage";

  private static final String DATABASE_NAME = "Events Database";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table "
      + TABLE_EVENTS + "(" + COLUMN_ID
      + " integer primary key autoincrement, " 
      + COLUMN_NAME + " text not null, " 
      + COLUMN_DESCRIPTION + " text not null, "
      + COLUMN_DRUG + " text not null, "
      + COLUMN_PRICE_CHANGE + " text not null, "
      + COLUMN_INITIAL_STEP + " text not null, "
      + COLUMN_DURATION + " text not null, "
      + COLUMN_CITY + " text not null, "
      + COLUMN_TERMINATION_MESSAGE + " text not null"
      +");";

  public MySQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(MySQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    dropTable(db);
    onCreate(db);
  }
  public void dropTable(SQLiteDatabase db){
	  db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
  }

} 