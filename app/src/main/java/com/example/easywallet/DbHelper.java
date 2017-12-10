package com.example.easywallet;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MSI-GL62 on 10/12/2560.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db.wallet";
    private static final int DATABASE_VERSION = 7;

    public static final String TABLE_NAME = "history";
    public static final String COL_ID = "_id";
    public static final String COL_PICTURE = "picture";
    public static final String COL_MONEY = "money";
    public static final String COL_DETAIL = "name";
    public static final String COL_TYPE = "inorex";


    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_PICTURE + " INTEGER,"
            + COL_DETAIL + " TEXT,"
            + COL_MONEY + " INTEGER,"
            + COL_TYPE + " TEXT)";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

        insertInitialData(db);
    }

    private void insertInitialData(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(COL_PICTURE, R.drawable.ic_income);
        cv.put(COL_DETAIL, "คุณพ่อให้เงิน");
        cv.put(COL_MONEY, 8000);
        cv.put(COL_TYPE, "IN");
        db.insert(TABLE_NAME, null, cv);
        cv = new ContentValues();
        cv.put(COL_PICTURE, R.drawable.ic_expense);
        cv.put(COL_DETAIL, "จ่ายค่าหอ");
        cv.put(COL_MONEY, 2500);
        cv.put(COL_TYPE, "EX");
        db.insert(TABLE_NAME, null, cv);
        cv = new ContentValues();
        cv.put(COL_PICTURE, R.drawable.ic_expense);
        cv.put(COL_DETAIL, "ซื้อล็อตเตอรี่ 1 ชุด");
        cv.put(COL_TYPE, "EX");
        cv.put(COL_MONEY, 700);
        db.insert(TABLE_NAME, null, cv);
        cv = new ContentValues();
        cv.put(COL_PICTURE, R.drawable.ic_income);
        cv.put(COL_DETAIL, "ถูกล็อตเตอรี่รางวัลที่ 1");
        cv.put(COL_TYPE, "IN");
        cv.put(COL_MONEY, 30000000);
        db.insert(TABLE_NAME, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int i, int i1){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
