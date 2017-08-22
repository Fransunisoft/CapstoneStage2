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
    public final class InformationAdapter extends BaseAdapter {
    private final List<Item> mItems = new ArrayList<Item>();
    private final LayoutInflater mInflater;

    public InformationAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

        mItems.add(new Item("",       R.drawable.inf1));
        mItems.add(new Item("",   R.drawable.inf2));
        mItems.add(new Item("", R.drawable.inf3));
        mItems.add(new Item("",      R.drawable.inf4));
        mItems.add(new Item("",     R.drawable.inf15));
        mItems.add(new Item("",      R.drawable.inf6));
        mItems.add(new Item("",       R.drawable.inf7));
        mItems.add(new Item("",   R.drawable.inf8));
        mItems.add(new Item("", R.drawable.inf9));
        mItems.add(new Item("",      R.drawable.inf10));
        mItems.add(new Item("",     R.drawable.inf11));
        mItems.add(new Item("",      R.drawable.inf12));
        mItems.add(new Item("",   R.drawable.inf13));
        mItems.add(new Item("", R.drawable.inf14));
        mItems.add(new Item("",      R.drawable.inf15));
        mItems.add(new Item("",     R.drawable.inf16));
        mItems.add(new Item("",      R.drawable.inf17));
        mItems.add(new Item("",   R.drawable.inf18));
        mItems.add(new Item("", R.drawable.inf19));
        mItems.add(new Item("",      R.drawable.inf20));
        mItems.add(new Item("",     R.drawable.inf1));
        mItems.add(new Item("",      R.drawable.inf22));
        mItems.add(new Item("",   R.drawable.inf23));
        mItems.add(new Item("", R.drawable.inf24));





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