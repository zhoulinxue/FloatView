package org.zhx.floatView;



import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Copyright (C), 2015-2020
 * FileName: LifecycleListener2
 * Author: zx
 * Date: 2020/4/1 12:51
 * Description:
 */

public class Screen {
    public static final int width = 0;
    public static final int height = 1;

    @IntDef({width, height})
    @Retention(RetentionPolicy.SOURCE)
    @interface screenType {
    }
}
