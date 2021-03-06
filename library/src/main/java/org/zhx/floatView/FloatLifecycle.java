package org.zhx.floatView;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;

import org.zhx.floatView.api.LifecycleListener;
import org.zhx.floatView.api.ResumedListener;


/**
 * Copyright (C), 2015-2020
 * FileName: LifecycleListener2
 * Author: zx
 * Date: 2020/4/1 12:51
 * Description:
 * 用于控制悬浮窗显示周期
 * 使用了三种方法针对返回桌面时隐藏悬浮按钮
 * 1.startCount计数，针对back到桌面可以及时隐藏
 * 2.监听home键，从而及时隐藏
 * 3.resumeCount计时，针对一些只执行onPause不执行onStop的奇葩情况
 */

class FloatLifecycle extends BroadcastReceiver implements Application.ActivityLifecycleCallbacks {

    private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
    private static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
    private static final long delay = 300;
    private Handler mHandler;
    private Class[] activities;
    private boolean showFlag;
    private int startCount;
    private int resumeCount;
    private boolean appBackground;
    private LifecycleListener mLifecycleListener;
    private static ResumedListener sResumedListener;
    private static int num = 0;
    Application.ActivityLifecycleCallbacks activityLifecycleCallbacks;

    FloatLifecycle(Context applicationContext, boolean showFlag, Class[] activities, Application.ActivityLifecycleCallbacks activityLifecycleCallbacks, LifecycleListener lifecycleListener) {
        this.showFlag = showFlag;
        this.activities = activities;
        num++;
        mLifecycleListener = lifecycleListener;
        mHandler = new Handler();
        this.activityLifecycleCallbacks = activityLifecycleCallbacks;
        ((Application) applicationContext).registerActivityLifecycleCallbacks(this);
        applicationContext.registerReceiver(this, new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
    }

    public static void setResumedListener(ResumedListener resumedListener) {
        sResumedListener = resumedListener;
    }

    private boolean needShow(Activity activity) {
        if (activities == null) {
            return true;
        }
        for (Class a : activities) {
            if (a.isInstance(activity)) {
                return showFlag;
            }
        }
        return !showFlag;
    }


    @Override
    public void onActivityResumed(Activity activity) {
        if (sResumedListener != null) {
            num--;
            if (num == 0) {
                sResumedListener.onResumed();
                sResumedListener = null;
            }
        }
        resumeCount++;
        if (needShow(activity)) {
            mLifecycleListener.onShow();
        } else {
            mLifecycleListener.onHide();
        }
        if (appBackground) {
            appBackground = false;
        }
        if (activityLifecycleCallbacks != null) {
            activityLifecycleCallbacks.onActivityResumed(activity);
        }
    }

    @Override
    public void onActivityPaused(final Activity activity) {
        resumeCount--;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (resumeCount == 0) {
                    appBackground = true;
                    mLifecycleListener.onAppBackground();
                }
            }
        }, delay);
        if (activityLifecycleCallbacks != null) {
            activityLifecycleCallbacks.onActivityPaused(activity);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (mLifecycleListener != null && startCount == 0) {
            mLifecycleListener.onAppforeground();
        }
        startCount++;
        if (activityLifecycleCallbacks != null) {
            activityLifecycleCallbacks.onActivityStarted(activity);
        }
    }


    @Override
    public void onActivityStopped(Activity activity) {
        startCount--;
        if (startCount == 0) {
            mLifecycleListener.onAppBackground();
        }
        if (activityLifecycleCallbacks != null) {
            activityLifecycleCallbacks.onActivityStopped(activity);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null && action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
            String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
            if (SYSTEM_DIALOG_REASON_HOME_KEY.equals(reason)) {
                mLifecycleListener.onAppBackground();
            }
        }
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (activityLifecycleCallbacks != null) {
            activityLifecycleCallbacks.onActivityCreated(activity, savedInstanceState);
        }
    }


    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        if (activityLifecycleCallbacks != null) {
            activityLifecycleCallbacks.onActivitySaveInstanceState(activity, outState);
        }

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (activityLifecycleCallbacks != null) {
            activityLifecycleCallbacks.onActivityDestroyed(activity);
        }
    }


}
