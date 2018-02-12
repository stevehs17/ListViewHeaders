package com.ssimon.listviewheaders;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ItemAdapter(getItems()));
    }

    class ItemAdapter extends ArrayAdapter<Item> {
        final private List<Class<?>> viewTypes;

        ItemAdapter(List<Item> items) {
            super(MainActivity.this, 0, items);
            if (items.contains(null))
                throw new IllegalArgumentException("null item");
            viewTypes = getViewTypes(items);
        }

        private List<Class<?>> getViewTypes(List<Item> items) {
            Set<Class<?>> set = new HashSet<>();
            for (Item i : items) {
                set.add(i.getClass());
            }
            List<Class<?>> list = new ArrayList<>(set);
            return Collections.unmodifiableList(list);
        }

        @Override
        public int getViewTypeCount() {
            return viewTypes.size();
        }

        @Override
        public int getItemViewType(int position) {
            Item t = getItem(position);
            return viewTypes.indexOf(t.getClass());
        }

        @Override
        public View getView(int position, View v, ViewGroup unused) {
            return getItem(position).getView(getLayoutInflater(), v);
        }
    }

    abstract private class Item {
        final private String text;

        Item(String text) {
            this.text = text;
        }

        String text() { return text; }

        abstract View getView(LayoutInflater i, View v);
    }

    private class HeaderItem extends Item {
        HeaderItem(String text) {
            super(text);
        }

        @Override
        View getView(LayoutInflater i, View v) {
            ViewHolder h;
            if (v == null) {
                v = i.inflate(R.layout.header, null);
                h = new ViewHolder(v);
                v.setTag(h);
            } else {
                h = (ViewHolder) v.getTag();
            }
            h.category.setText(text());
            return v;
        }

        private class ViewHolder {
            final TextView category;

            ViewHolder(View v) {
                category = v.findViewById(R.id.category);
            }
        }
    }

    private class RowItem extends Item {
        RowItem(String text) {
            super(text);
        }

        @Override
        View getView(LayoutInflater i, View v) {
            ViewHolder h;
            if (v == null) {
                v = i.inflate(R.layout.row, null);
                h = new ViewHolder(v);
                v.setTag(h);
            } else {
                h = (ViewHolder) v.getTag();
            }
            h.option.setText(text());
            return v;
        }

        private class ViewHolder {
            final TextView option;

            ViewHolder(View v) {
                option = v.findViewById(R.id.option);
            }
        }
    }

    private List<Item> getItems() {
        List<Item> t = new ArrayList<>();
        t.add(new HeaderItem("Header 1"));
        t.add(new RowItem("Row 2"));
        t.add(new HeaderItem("Header 3"));
        t.add(new RowItem("Row 4"));

        t.add(new HeaderItem("Header 5"));
        t.add(new RowItem("Row 6"));
        t.add(new HeaderItem("Header 7"));
        t.add(new RowItem("Row 8"));

        t.add(new HeaderItem("Header 9"));
        t.add(new RowItem("Row 10"));
        t.add(new HeaderItem("Header 11"));
        t.add(new RowItem("Row 12"));

        t.add(new HeaderItem("Header 13"));
        t.add(new RowItem("Row 14"));
        t.add(new HeaderItem("Header 15"));
        t.add(new RowItem("Row 16"));

        t.add(new HeaderItem("Header 17"));
        t.add(new RowItem("Row 18"));
        t.add(new HeaderItem("Header 19"));
        t.add(new RowItem("Row 20"));

        t.add(new HeaderItem("Header 21"));
        t.add(new RowItem("Row 22"));
        t.add(new HeaderItem("Header 23"));
        t.add(new RowItem("Row 24"));

        t.add(new HeaderItem("Header 25"));
        t.add(new RowItem("Row 26"));
        t.add(new HeaderItem("Header 27"));
        t.add(new RowItem("Row 28"));
        t.add(new RowItem("Row 29"));
        t.add(new RowItem("Row 30"));

        t.add(new HeaderItem("Header 31"));
        t.add(new RowItem("Row 32"));
        t.add(new HeaderItem("Header 33"));
        t.add(new RowItem("Row 34"));
        t.add(new RowItem("Row 35"));
        t.add(new RowItem("Row 36"));

        return t;
    }

}
