package com.good.diaodiaode.tebiediao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);


        listview = (ListView) findViewById(R.id.listview);
        TextView textView = new TextView(Main2Activity.this);
        textView.setText("header11111111111111");
        TextView textView2 = new TextView(Main2Activity.this);
        textView2.setText("header22222222222222");
        listview.addHeaderView(textView);
        listview.addHeaderView(textView2);
        listview.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 100;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                TextView textView = new TextView(Main2Activity.this);
                textView.setText("item" + i);
                return textView;
            }
        });
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            int first = -1;

            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (i == SCROLL_STATE_IDLE) {
                    Log.e("first", first + "");
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                first = i;
            }
        });
    }
}
