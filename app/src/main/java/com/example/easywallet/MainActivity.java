package com.example.easywallet;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<detailItem> mDetialItemList;
    private DbHelper mHelper;
    private SQLiteDatabase mDb;
    TextView allmoney;
    Button inButton;
    Button exButton   ;
    private itemListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        allmoney = (TextView)findViewById(R.id.textAllMoney);
         inButton = (Button)findViewById(R.id.inButton);
         exButton = (Button)findViewById(R.id.exButton);
        mDetialItemList = new ArrayList<>();
        mHelper = new DbHelper(this);
        mDb = mHelper.getReadableDatabase();
        loadDataFromDb();
        mAdapter = new itemListAdapter(
                this,
                R.layout.item,
                mDetialItemList
        );
        ListView lv = (ListView) findViewById(R.id.list_item);
        lv.setAdapter(mAdapter);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                detailItem item = mDetialItemList.get(position);
                dialog.setMessage("ยืนยันการลบรายการ \n'" + item.detail + item.money + " บาท'?");
                 final int itemId = item.id;
                dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mDb.delete(
                                mHelper.TABLE_NAME,
                                mHelper.COL_ID + "=?",
                                new String[]{String.valueOf(itemId)}
                        );

                        mAdapter.notifyDataSetChanged();
                        loadDataFromDb();
                    }
                });
                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                dialog.show();
                return true;
            }
        });
            inButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this,addOrder.class);
                    startActivity(intent);
                }
            });
            exButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this,addOrder2.class);
                    startActivity(intent);
                }
            });
    }

    private void loadDataFromDb() {
        Cursor cursor = mDb.query(
                mHelper.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
        mDetialItemList.clear();
        int sum = 0;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(mHelper.COL_ID));
            int pic = cursor.getInt(cursor.getColumnIndex(mHelper.COL_PICTURE));
            String detail = cursor.getString(cursor.getColumnIndex(mHelper.COL_DETAIL));
            int money = cursor.getInt(cursor.getColumnIndex(mHelper.COL_MONEY));
            String type = cursor.getString(cursor.getColumnIndex(mHelper.COL_TYPE));
            detailItem item = new detailItem(id,pic, detail, money, type);
            if(type.equals("IN")){
                sum+=money;
            }else{
                sum-=money;
            }

            mDetialItemList.add(item);
        }
        allmoney.setText("คงเหลือ "+String.valueOf(sum)+" บาท");
    }
}
