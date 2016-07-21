package com.nutsdev.alltest.ui.adapters;

import android.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nutsdev.alltest.R;
import com.nutsdev.alltest.data.entities.Media;
import com.nutsdev.alltest.ui.adapters.pagers.RecyclerViewPagerAdapter;
import com.nutsdev.alltest.ui.fragments.ViewPagerInRecyclerFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerInRecyclerAdapter extends RecyclerView.Adapter<ViewPagerInRecyclerAdapter.ViewHolder> {

    private final List<ViewPagerItem> items;

    private final ViewPagerInRecyclerFragment.OnItemClickListener itemClickListener;

    private FragmentManager childFragmentManager;

    public ViewPagerInRecyclerAdapter(ViewPagerInRecyclerFragment.OnItemClickListener itemClickListener) {
        this.items = new ArrayList<>();
        this.itemClickListener = itemClickListener;
    }

    public ViewPagerInRecyclerAdapter(List<ViewPagerItem> items, ViewPagerInRecyclerFragment.OnItemClickListener listener) {
        this.items = items;
        itemClickListener = listener;
    }

    public ViewPagerInRecyclerAdapter(List<ViewPagerItem> items, ViewPagerInRecyclerFragment.OnItemClickListener itemClickListener, FragmentManager childFragmentManager) {
        this.items = items;
        this.itemClickListener = itemClickListener;
        this.childFragmentManager = childFragmentManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewpager, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.viewPagerItem = items.get(position);
        final RecyclerViewPagerAdapter viewPagerAdapter = new RecyclerViewPagerAdapter(childFragmentManager);
        viewPagerAdapter.setOnPhotoClickListener(new RecyclerViewPagerAdapter.Action1<Media>() {
            @Override
            public void run(Media var1) {
                itemClickListener.onItemClick(holder.viewPagerItem);
            }
        });
        viewPagerAdapter.items.addAll(items.get(position).mediaList);
        holder.viewPager.setAdapter(viewPagerAdapter);

    /*    holder.root_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != itemClickListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    itemClickListener.onItemClick(holder.viewPagerItem);
                }
            }
        }); */
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    /* inner types */

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View root_view;
        public final ViewPager viewPager;
        public ViewPagerItem viewPagerItem;

        public ViewHolder(View view) {
            super(view);
            root_view = view;
            viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        }
    }

    public static class ViewPagerItem {
        public ArrayList<Media> mediaList;

        public ViewPagerItem(ArrayList<Media> mediaList) {
            this.mediaList = mediaList;
        }
    }

}
