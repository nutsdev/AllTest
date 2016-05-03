package com.nutsdev.alltest.ui.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nutsdev.alltest.R;
import com.nutsdev.alltest.ui.fragments.BottomSheetFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_bottom_sheet)
public class BottomSheetActivity extends AppCompatActivity {

    private BottomSheetBehavior bottomSheetBehavior;

    @ViewById(R.id.button1)
    protected Button button1;
    @ViewById(R.id.button2)
    protected Button button2;
    @ViewById(R.id.button3)
    protected Button button3;
    @ViewById(R.id.bottomSheet)
    protected View bottomSheet;


    @AfterViews
    protected void initViews() {
        button1.setOnClickListener(clickListener);
        button2.setOnClickListener(clickListener);
        button3.setOnClickListener(clickListener);

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setPeekHeight(300);
        bottomSheetBehavior.setBottomSheetCallback(bottomSheetCallback);
    //    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button1:
                    bottomSheetBehavior.setPeekHeight(300);
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    break;
                case R.id.button2:
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    break;
                case R.id.button3:
                    BottomSheetDialogFragment bottomSheetDialogFragment = new BottomSheetFragment();
                    bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
                    break;
            }
        }
    };

    private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.setPeekHeight(0);
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

}
