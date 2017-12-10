package com.example.easywallet;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addOrder2 extends AppCompatActivity {
    private DbHelper mHelper;
    private SQLiteDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order2);
        mHelper = new DbHelper(this);
        mDb = mHelper.getReadableDatabase();
        Button b = (Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editDetail = (EditText) findViewById(R.id.editDetail);
                EditText editMoney = (EditText) findViewById(R.id.editMoney);
                String detail = editDetail.getText().toString();
                int money = Integer.parseInt(editMoney.getText().toString());
                ContentValues cv = new ContentValues();
                cv.put(DbHelper.COL_PICTURE, R.drawable.ic_expense);
                cv.put(DbHelper.COL_DETAIL, detail);
                cv.put(DbHelper.COL_MONEY, money);
                cv.put(DbHelper.COL_TYPE, "EX");
                mDb.insert(DbHelper.TABLE_NAME, null, cv);
                Intent intent = new Intent(addOrder2.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
