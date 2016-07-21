package com.nutsdev.alltest.ui.fragments;

import android.app.Fragment;

import com.nutsdev.alltest.R;
import com.nutsdev.alltest.data.entities.Media;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

import java.util.ArrayList;

@EFragment(R.layout.fragment_detailed_view_pager)
public class DetailedViewPagerFragment extends Fragment {

    @FragmentArg
    protected ArrayList<Media> mediaList;

}
