package org.zhx.floatView;

import android.view.View;

/**
 * Copyright (C), 2015-2020
 * FileName: LifecycleListener2
 * Author: zx
 * Date: 2020/4/1 12:51
 * Description:
 */

public abstract class IFloatWindow {
    public abstract void show();

    public abstract void hide();

    public abstract boolean isShowing();

    public abstract int getX();

    public abstract int getY();

    public abstract void updateX(int x);

    public abstract void updateX(@Screen.screenType int screenType, float ratio);

    public abstract void updateY(int y);

    public abstract void updateY(@Screen.screenType int screenType, float ratio);

    public abstract View getView();

    abstract void dismiss();
}
