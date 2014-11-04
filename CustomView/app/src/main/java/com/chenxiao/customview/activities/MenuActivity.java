package com.chenxiao.customview.activities;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.chenxiao.customview.R;
import com.chenxiao.customview.models.ActivityInfo;

import java.util.Arrays;
import java.util.List;


public class MenuActivity extends ListActivity {

    private List<ActivityInfo> mClassList = Arrays.asList(
            new ActivityInfo(CircleTextActivity.class, "Circle Text View"),
            new ActivityInfo(LoadMoreActivity.class, "LoadMore ListView"),
            new ActivityInfo(CustomViewActivity.class, "Custom View with onDraw, many method introduction of Paint and Canvas provide")
    );

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mListView = (ListView) findViewById(android.R.id.list);
        String[] titles = getActivityTitles();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(mOnItemClickListener);
    }


    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            //Class<? extends Activity> activity = mClassList.get((int) l).activityClass;
            Class<? extends Activity> activity = mClassList.get(position - mListView.getHeaderViewsCount()).activityClass;
            if (activity != null) {
                Intent intent = new Intent();
                intent.setClass(MenuActivity.this, activity);
                startActivity(intent);
            }

        }
    };

    private String[] getActivityTitles() {
        String[] result = new String[mClassList.size()];
        int i = 0;
        for (ActivityInfo info : mClassList) {
            result[i++] = info.title;
        }
        return result;
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
