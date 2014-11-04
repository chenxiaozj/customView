package com.chenxiao.customview.models;

import android.app.Activity;

/**
 * Created by chenxiao on 14-8-21.
 */
public class ActivityInfo {
    public Class<? extends Activity> activityClass;
    public String title;

    public ActivityInfo(Class<? extends Activity> activityClass, String titleResourceId) {
        this.activityClass = activityClass;
        this.title = titleResourceId;
    }
}
