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

public final class RegulatoryAdapter extends BaseAdapter {
    private final List<Item> mItems = new ArrayList<Item>();
    private final LayoutInflater mInflater;

    public RegulatoryAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

        mItems.add(new Item("",       R.drawable.man1));
        mItems.add(new Item("",   R.drawable.man2));
        mItems.add(new Item("", R.drawable.man3));
        mItems.add(new Item("",      R.drawable.man4));
        mItems.add(new Item("",     R.drawable.man5));
        mItems.add(new Item("",      R.drawable.man6));
        mItems.add(new Item("",       R.drawable.man7));
        mItems.add(new Item("",   R.drawable.man8));

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