package com.nutsdev.alltest.ui.fragments;


import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nutsdev.alltest.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_glide)
public class GlideFragment extends BaseFragment {

    public static final String GITHUB_IMAGE_URL = "https://cloud.githubusercontent.com/assets/8561004/13547324/07fda3b0-e2d7-11e5-8b89-6f758a8dd163.png";

    @ViewById
    protected ImageView imageView;


    /* views */

    @AfterViews
    protected void afterView() {
        Glide.with(this)
                .load(GITHUB_IMAGE_URL)
                .centerCrop()
                .placeholder(R.raw.gif_round_progress_2)
                .into(imageView);
    }

}
