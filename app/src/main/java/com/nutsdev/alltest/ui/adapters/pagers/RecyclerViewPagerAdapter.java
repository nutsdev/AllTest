package com.nutsdev.alltest.ui.adapters.pagers;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.nutsdev.alltest.data.entities.Media;
import com.nutsdev.alltest.ui.fragments.ViewPostMediaItemFragment;
import com.nutsdev.alltest.ui.fragments.ViewPostMediaItemFragment_;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewPagerAdapter extends FragmentStatePagerAdapter {

    public final List<Object> items;

    private Action1<Media> onPhotoClickListener;

    public RecyclerViewPagerAdapter(FragmentManager fm) {
        super(fm);
        items = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        Object item = items.get(position);
        ViewPostMediaItemFragment fragment;

        if (item instanceof Media) {
            Media media = (Media) item;
            if (media.mediaType == Media.MEDIA_TYPE_PHOTO) {
                fragment = ViewPostMediaItemFragment_.builder().state(ViewPostMediaItemFragment.STATE_PHOTO).media(media).build();
                if (onPhotoClickListener != null) {
                    fragment.setOnPhotoClickListener(onPhotoClickListener);
                }
            } else if (media.mediaType == Media.MEDIA_TYPE_VIDEO) {
                fragment = ViewPostMediaItemFragment_.builder().state(ViewPostMediaItemFragment.STATE_VIDEO).media(media).build();
            } else {
                throw new RuntimeException("This item type not supported");
            }
        } else {
            throw new RuntimeException("This item type not supported");
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void setOnPhotoClickListener(Action1<Media> onPhotoClickListener) {
        this.onPhotoClickListener = onPhotoClickListener;
    }


    /* inner types */

    public interface Action1<T> {
        void run(T var1);
    }

}
