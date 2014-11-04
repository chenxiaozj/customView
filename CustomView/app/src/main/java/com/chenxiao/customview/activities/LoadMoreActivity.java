package com.chenxiao.customview.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;


import com.chenxiao.customview.R;
import com.chenxiao.customview.widgets.LoadMoreListView;

import java.util.ArrayList;


public class LoadMoreActivity extends Activity {

    private LoadMoreListView mListView;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> mList= new ArrayList<String>();
    private int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_more);
        mListView = (LoadMoreListView) findViewById(R.id.lv_listView);
        addData();
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,mList);
        //mListView.setSupportLoadMore(false);
        mListView.setAdapter(mAdapter);
        mListView.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                addData();
                mListView.loadCompleted();
                if (mCount == 300) {
                    mListView.setLastItem(true);
                }
                mAdapter.notifyDataSetChanged();
            }
        });
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });

    }

    private void addData() {
        int endCount = mCount + 20;
        for ( ; mCount < endCount; mCount++) {
            mList.add("Item"+ mCount);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
