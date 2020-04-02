package org.zhx.floatView.demo;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import org.zhx.floatView.FloatWindow;
import org.zhx.floatView.MoveType;
import org.zhx.floatView.Screen;
import org.zhx.floatView.api.PermissionListener;
import org.zhx.floatView.api.ViewStateListener;

/**
 * Created by yhao on 2017/12/18.
 * https://github.com/yhaolpz
 */

public class BaseApplication extends Application {


    private static final String TAG = "FloatWindow";

    @Override
    public void onCreate() {
        super.onCreate();

        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.icon);

        FloatWindow
                .with(getApplicationContext())
                .setView(imageView)
                .setWidth(Screen.width, 0.2f) //设置悬浮控件宽高
                .setHeight(Screen.width, 0.2f)
                .setX(Screen.width, 0.8f)
                .setY(Screen.height, 0.3f)
                .setMoveType(MoveType.slide,100,-100)
                .setMoveStyle(500, new BounceInterpolator())
                .setFilter(true, A_Activity.class, C_Activity.class)
                .setViewStateListener(mViewStateListener)
                .setPermissionListener(mPermissionListener)
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
}
