package com.nutsdev.alltest.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

import com.nutsdev.alltest.R;
import com.nutsdev.alltest.utils.views.SquareTransformer;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_square_move_test)
public class SquareMoveTestActivity extends AppCompatActivity {

    @ViewById(R.id.scrollView)
    protected ScrollView scrollView;
    @ViewById(R.id.horizontalScrollView)
    protected HorizontalScrollView horizontalScrollView;


    /* lifecycle */

    @AfterViews
    protected void afterViews() {
        SquareTransformer.transformToSquareView(this, scrollView);
    }

}
