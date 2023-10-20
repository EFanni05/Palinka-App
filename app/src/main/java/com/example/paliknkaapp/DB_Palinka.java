package com.example.paliknkaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Palinka extends SQLiteOpenHelper {
    private static final String DB_NAME = "Palinka.db";
    private static final Integer DB_VERSION = 1;
    private static final String TABLE_NAME = "Palinka";
    private static final String COL_ID = "id";
    private static final String COL_FOZO = "Fozo";
    private static final String COL_GYUMOLCS = "Gyumolcs";
    private static final String COL_ALKOHOL = "Alkohol";
    public DB_Palinka(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + " ( " +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_FOZO + " TEXT NOT NULL, " +
                COL_GYUMOLCS + " TEXT NOT NULL, " +
                COL_ALKOHOL + " INTEGER NOT NULL" + " );" ;
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(
                "DROP TABLE IF EXISTS " + TABLE_NAME
        );
        onCreate(sqLiteDatabase);
    }

    public Cursor Adatlekerdezes(String fozo, String gyumolcs){
        SQLiteDatabase database = this.getReadableDatabase();
        return  database.rawQuery("SELECT " + COL_ALKOHOL + " FROM " + TABLE_NAME +
                " WHERE " + COL_FOZO + " = ? AND " + COL_GYUMOLCS + " = ? "
                , new String[]{fozo, gyumolcs});
    }

    public boolean Add(String fozo, String gyumolcs, int alkohol){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FOZO, fozo);
        values.put(COL_GYUMOLCS, gyumolcs);
        values.put(COL_ALKOHOL, alkohol);
        long results = database.insert(TABLE_NAME, null, values);
        return results != -1;
    }

    public Cursor lekerdezes(){
        SQLiteDatabase database = this.getReadableDatabase();
        return database.query(TABLE_NAME,
                new String[] {COL_ID, COL_FOZO, COL_GYUMOLCS, COL_ALKOHOL},
                null, null, null, null, null);
    }
}
