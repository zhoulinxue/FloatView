package org.zhx.floatView.demo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.zhx.floatView.FloatWindow;
import org.zhx.floatView.MoveType;
import org.zhx.floatView.Screen;
import org.zhx.floatView.api.PermissionListener;
import org.zhx.floatView.api.ViewStateListener;

/**
 * Created by yhao on 2017/12/18.
 * https://github.com/yhaolpz
 * <p>
 * update by: zhx
 * <p>
 * 修复 application  实现了Application.ActivityLifecycleCallbacks 可能造成的bug
 */

public class BaseApplication extends Application implements Application.ActivityLifecycleCallbacks {


    private static final String TAG = "FloatWindow";

    @Override
    public void onCreate() {
        super.onCreate();

        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.icon);
        // 如果您的 application 实现了 Application.ActivityLifecycleCallbacks 接口 请先调用
        FloatWindow.init(this);// 0.3.0 版本新增

        FloatWindow
                .with(getApplicationContext())
                .setView(imageView)
                .setWidth(Screen.width, 0.2f) //设置悬浮控件宽高
                .setHeight(Screen.width, 0.2f)
                .setX(Screen.width, 0.8f)
                .setY(Screen.height, 0.3f)
                .setMoveType(MoveType.slide, 0, 0)
                .setMoveStyle(500, new BounceInterpolator())
                .setFilter(true, A_Activity.class, C_Activity.class)
                .setViewStateListener(mViewStateListener)
                .setPermissionListener(mPermissionListener)
                //新增动画 显示和隐藏
                .setAnimationStyle(R.style.windAnim)
                .setDesktopShow(true)
                .build();


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BaseApplication.this, "onClick", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private PermissionListener mPermissionListener = new PermissionListener() {
        @Override
        public void onSuccess() {
            Log.d(TAG, "onSuccess");
        }

        @Override
        public void onFail() {
            Log.d(TAG, "onFail");
        }
    };

    private ViewStateListener mViewStateListener = new ViewStateListener() {
        @Override
        public void onPositionUpdate(int x, int y) {
            //TODO 图标 坐标更新
            Log.d(TAG, "onPositionUpdate: x=" + x + " y=" + y);
        }

        @Override
        public void onViewUpdateMove() {
            //TODO 图标 移动
            Log.d(TAG, "onViewUpdateMove ");
        }

        @Override
        public void onShow() {
            //TODO 图标显示
            Log.d(TAG, "onShow");
        }

        @Override
        public void onHide() {
            //图标隐藏
            Log.d(TAG, "onHide");
        }

        @Override
        public void onDismiss() {
            //图标消失
            Log.d(TAG, "onDismiss");
        }

        @Override
        public void onMoveAnimStart() {
            //TODO 手指离开 图标动画 开始
            Log.d(TAG, "onMoveAnimStart");
        }

        @Override
        public void onMoveAnimEnd() {
            //TODO  图标动画 结束
            Log.d(TAG, "onMoveAnimEnd");
        }

        @Override
        public void onBackToDesktop() {
            // TODO 回到桌面  注：Application 注册了 Application.ActivityLifecycleCallbacks  该组件 可能会 出BUG
            Log.d(TAG, "onBackToDesktop");
        }
    };

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}
