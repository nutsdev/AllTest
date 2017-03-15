package com.nutsdev.alltest.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nutsdev.alltest.R;
import com.nutsdev.alltest.utils.views.SquareTransformer;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_square_move_test)
public class SquareMoveTestActivity extends AppCompatActivity {

    @ViewById(R.id.videoContainer_view)
    protected View videoContainer_view;


    /* lifecycle */

    @AfterViews
    protected void afterViews() {
        SquareTransformer.transformToSquareView(this, videoContainer_view);
    }

}
