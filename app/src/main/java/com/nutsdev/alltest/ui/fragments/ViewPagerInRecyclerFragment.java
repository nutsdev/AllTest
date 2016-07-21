package com.nutsdev.alltest.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;

import com.nutsdev.alltest.R;
import com.nutsdev.alltest.data.entities.Media;
import com.nutsdev.alltest.ui.adapters.ViewPagerInRecyclerAdapter;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EFragment(R.layout.fragment_viewpager_in_recycler)
public class ViewPagerInRecyclerFragment extends Fragment {

    private OnItemClickListener itemClickListener;

    @ViewById(R.id.recyclerView)
    protected RecyclerView recyclerView;


    /* lifecycle */

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemClickListener) {
            itemClickListener = (OnItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnItemClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        itemClickListener = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<Media> mediaList = new ArrayList<>();
        mediaList.add(new Media(Media.MEDIA_TYPE_VIDEO, "http://67ffebe8b4d74e13168f-9cfc777b0f242f35a469d08318ce0985.r21.cf1.rackcdn.com/7e0nhFmDIK.mp4"));
        mediaList.add(new Media(Media.MEDIA_TYPE_PHOTO, "http://67ffebe8b4d74e13168f-9cfc777b0f242f35a469d08318ce0985.r21.cf1.rackcdn.com/CZ446mwnn1.jpeg"));
        mediaList.add(new Media(Media.MEDIA_TYPE_VIDEO, "http://67ffebe8b4d74e13168f-9cfc777b0f242f35a469d08318ce0985.r21.cf1.rackcdn.com/bP7hbEFzFG.mp4"));
        mediaList.add(new Media(Media.MEDIA_TYPE_PHOTO, "http://67ffebe8b4d74e13168f-9cfc777b0f242f35a469d08318ce0985.r21.cf1.rackcdn.com/a0tzsyICPv.jpeg"));
        mediaList.add(new Media(Media.MEDIA_TYPE_VIDEO, "http://67ffebe8b4d74e13168f-9cfc777b0f242f35a469d08318ce0985.r21.cf1.rackcdn.com/2gFvPn7ET1.mp4"));
        mediaList.add(new Media(Media.MEDIA_TYPE_PHOTO, "http://67ffebe8b4d74e13168f-9cfc777b0f242f35a469d08318ce0985.r21.cf1.rackcdn.com/bEuYpx6u6U.jpeg"));
        mediaList.add(new Media(Media.MEDIA_TYPE_PHOTO, "http://67ffebe8b4d74e13168f-9cfc777b0f242f35a469d08318ce0985.r21.cf1.rackcdn.com/11Kao3jyCZ.jpeg"));
        mediaList.add(new Media(Media.MEDIA_TYPE_PHOTO, "http://67ffebe8b4d74e13168f-9cfc777b0f242f35a469d08318ce0985.r21.cf1.rackcdn.com/mKHRz5YeY0.jpeg"));

        ArrayList<ViewPagerInRecyclerAdapter.ViewPagerItem> items = new ArrayList<>();
        FragmentManager childFragmentManager = getChildFragmentManager();
        for (int i = 0; i < 10; i++) {
            items.add(new ViewPagerInRecyclerAdapter.ViewPagerItem(mediaList));
        }

        recyclerView.setAdapter(new ViewPagerInRecyclerAdapter(items, itemClickListener, childFragmentManager));
    }

    /* inner types */

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnItemClickListener {
        void onItemClick(ViewPagerInRecyclerAdapter.ViewPagerItem item);
    }

}
