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
 * Created by Unisoft on 6/12/2016.
 */

public final class WarningAdapter extends BaseAdapter {
    private final List<Item> mItems = new ArrayList<Item>();
    private final LayoutInflater mInflater;

    public WarningAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

        mItems.add(new Item("",       R.drawable.war1));
        mItems.add(new Item("",   R.drawable.war2));
        mItems.add(new Item("", R.drawable.war3));
        mItems.add(new Item("",      R.drawable.war4));
        mItems.add(new Item("",     R.drawable.war5));
        mItems.add(new Item("",      R.drawable.war6));
        mItems.add(new Item("",       R.drawable.war7));
        mItems.add(new Item("",   R.drawable.war8));
        mItems.add(new Item("", R.drawable.war9));
        mItems.add(new Item("",      R.drawable.war10));
        mItems.add(new Item("",     R.drawable.war11));
        mItems.add(new Item("",      R.drawable.war12));
        mItems.add(new Item("",       R.drawable.war13));
        mItems.add(new Item("",   R.drawable.war14));
        mItems.add(new Item("", R.drawable.war15));
        mItems.add(new Item("",      R.drawable.war16));
        mItems.add(new Item("",     R.drawable.war17));
        mItems.add(new Item("",      R.drawable.war18));
        mItems.add(new Item("",       R.drawable.war19));
        mItems.add(new Item("",   R.drawable.war20));
        mItems.add(new Item("", R.drawable.war21));
        mItems.add(new Item("",      R.drawable.war22));;
        mItems.add(new Item("",       R.drawable.war25));

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