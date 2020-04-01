package org.zhx.floatView.api;

/**
 * Copyright (C), 2015-2020
 * FileName: LifecycleListener2
 * Author: zx
 * Date: 2020/4/1 12:51
 * Description:
 */
public interface ViewStateListener {
    void onPositionUpdate(int x, int y);

    void onViewUpdateMove();

    void onShow();

    void onHide();

    void onDismiss();

    void onMoveAnimStart();

    void onMoveAnimEnd();

    void onBackToDesktop();
}
