package com.fransunisoft.drivingguide.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fransunisoft.drivingguide.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Unisoft on 6/10/2016.
 */
public final class ProhibitoryAdapter extends BaseAdapter {
    private final List<Item> mItems = new ArrayList<Item>();
    private final LayoutInflater mInflater;

    public ProhibitoryAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

        mItems.add(new Item("",       R.drawable.pro1));
        mItems.add(new Item("",   R.drawable.pro2));
        mItems.add(new Item("", R.drawable.pro3));
        mItems.add(new Item("",     R.drawable.pro5));
        mItems.add(new Item("",      R.drawable.pro6));
        mItems.add(new Item("",       R.drawable.pro7));
        mItems.add(new Item("", R.drawable.pro9));
        mItems.add(new Item("",      R.drawable.pro10));
        mItems.add(new Item("",      R.drawable.pro12));
        mItems.add(new Item("",   R.drawable.pro13));
        mItems.add(new Item("", R.drawable.pro14));
        mItems.add(new Item("",      R.drawable.pro15));
        mItems.add(new Item("",     R.drawable.pro16));
        mItems.add(new Item("",      R.drawable.pro17));
        mItems.add(new Item("",   R.drawable.pro18));
        mItems.add(new Item("", R.drawable.pro19));
        mItems.add(new Item("",      R.drawable.pro20));
        mItems.add(new Item("",      R.drawable.pro21));
        mItems.add(new Item("",   R.drawable.pro23));
        mItems.add(new Item("", R.drawable.pro24));
        mItems.add(new Item("",      R.drawable.pro27));
        mItems.add(new Item("",   R.drawable.pro28));
        mItems.add(new Item("",      R.drawable.pro30));






    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Item getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mItems.get(i).drawableId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView name;

        if (v == null) {
            v = mInflater.inflate(R.layout.grid_item, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }

        picture = (ImageView) v.getTag(R.id.picture);
        name = (TextView) v.getTag(R.id.text);

        Item item = getItem(i);

        picture.setImageResource(item.drawableId);
        name.setText(item.name);

        return v;
    }

    private static class Item {
        public final String name;
        public final int drawableId;

        Item(String name, int drawableId) {
            this.name = name;
            this.drawableId = drawableId;
        }
    }
}