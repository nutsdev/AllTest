package com.nutsdev.alltest.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.nutsdev.alltest.R;
import com.nutsdev.alltest.ui.adapters.ViewPagerInRecyclerAdapter;
import com.nutsdev.alltest.ui.fragments.DetailedViewPagerFragment;
import com.nutsdev.alltest.ui.fragments.DetailedViewPagerFragment_;
import com.nutsdev.alltest.ui.fragments.ViewPagerInRecyclerFragment;
import com.nutsdev.alltest.ui.fragments.ViewPagerInRecyclerFragment_;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_view_pager_in_recycler)
public class ViewPagerInRecyclerActivity extends AppCompatActivity implements ViewPagerInRecyclerFragment.OnItemClickListener {

    private static final String TAG_FRAGMENT_VIEWPAGER_IN_RECYCLER = "TAG_FRAGMENT_VIEWPAGER_IN_RECYCLER";
    private static final String TAG_FRAGMENT_DETAILED_VIEWPAGER = "TAG_FRAGMENT_DETAILED_VIEWPAGER";


    /* lifecycle */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            final ViewPagerInRecyclerFragment fragment = ViewPagerInRecyclerFragment_.builder().build();
            switchFragment(fragment, TAG_FRAGMENT_VIEWPAGER_IN_RECYCLER);
        }
    }


    /* public methods */

    @Override
    public void onItemClick(ViewPagerInRecyclerAdapter.ViewPagerItem item) {
        DetailedViewPagerFragment fragment = DetailedViewPagerFragment_.builder().mediaList(item.mediaList).build();
        switchFragment(fragment, TAG_FRAGMENT_DETAILED_VIEWPAGER);
    }


    /* private methods */

    private void switchFragment(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.container_view, fragment, tag).
                commit();
    }

}
