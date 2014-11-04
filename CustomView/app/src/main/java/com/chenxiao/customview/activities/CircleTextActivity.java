package com.chenxiao.customview.activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.chenxiao.customview.R;
import com.chenxiao.customview.widgets.CircleTextView;


public class CircleTextActivity extends Activity {

    private CircleTextView mCustomText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_tv);
        /*mCustomText = (CircleTextView) findViewById(R.id.custom_text1);
        mCustomText.setBackGroundColor(Color.parseColor("#00ff00"));
        mCustomText.setTitleTextColor(Color.parseColor("#ffffff"));
        mCustomText.setTitleText("999");
        mCustomText.setTitleTextSize(72);*/
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
