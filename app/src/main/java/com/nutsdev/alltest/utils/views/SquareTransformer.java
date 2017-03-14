package com.nutsdev.alltest.utils.views;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

public class SquareTransformer {

    private SquareTransformer() throws InstantiationException {
        throw new InstantiationException("You shouldn't create instance to use this class.");
    }

    public static void transformToSquareView(Activity activity, View view) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = size.x;
        view.setLayoutParams(layoutParams);
    }

}
