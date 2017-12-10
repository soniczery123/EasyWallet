package com.example.easywallet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MSI-GL62 on 10/12/2560.
 */

public class itemListAdapter extends ArrayAdapter<detailItem>{
    private Context mContext;
    private int mLayoutResId;
    private ArrayList<detailItem> mItemList;

    public itemListAdapter(Context mContext, int mLayoutResId, ArrayList<detailItem> mItemList) {
        super(mContext,mLayoutResId,mItemList);
        this.mContext = mContext;
        this.mLayoutResId = mLayoutResId;
        this.mItemList = mItemList;
    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemLayout = inflater.inflate(mLayoutResId, null);

        detailItem item = mItemList.get(position);
        ImageView picImageView = itemLayout.findViewById(R.id.imageView);
        TextView textViewDetail = itemLayout.findViewById(R.id.textDetail);
        TextView textViewMoney = itemLayout.findViewById(R.id.textMoney);
        picImageView.setImageResource(item.picture);
        textViewDetail.setText(item.detail);
        textViewMoney.setText(String.valueOf(item.money));

        return itemLayout;
    }
}
