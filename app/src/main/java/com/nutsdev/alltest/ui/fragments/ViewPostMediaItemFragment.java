package com.nutsdev.alltest.ui.fragments;

import android.media.MediaPlayer;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.nutsdev.alltest.R;
import com.nutsdev.alltest.data.entities.Media;
import com.nutsdev.alltest.ui.adapters.pagers.RecyclerViewPagerAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.Touch;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_view_post_media_item)
public class ViewPostMediaItemFragment extends BaseFragment {

    public static final int STATE_PHOTO = 0;
    public static final int STATE_VIDEO = 1;

    private RecyclerViewPagerAdapter.Action1<Media> onPhotoClickListener;

    private MediaPlayer mediaPlayer;

    @InstanceState
    protected boolean isAudioEnabled = true;

    @FragmentArg
    protected int state;

    @FragmentArg
    protected Media media;

/*    @FragmentArg
    protected ServerPhotoInfo serverPhotoInfo;

    @FragmentArg
    protected ServerVideoInfo serverVideoInfo; */

    @InstanceState
    protected boolean isVideoPrepared;

    @ViewById(R.id.videoContainer_view)
    protected View videoContainer_view;

    @ViewById(R.id.play_imageView)
    protected ImageView play_imageView;

 /*   @ViewById(R.id.audioState_imageView)
    protected ImageView audioState_imageView; */

    @ViewById(R.id.videoView)
    protected VideoView videoView;

    @ViewById(R.id.photo_view)
    protected View photo_view;

    @ViewById(R.id.photo_imageView)
    protected ImageView photo_imageView;


    /* lifecycle */

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        switch (state) {
            case STATE_VIDEO:
                if (videoView != null && videoView.isPlaying()) {
                    pauseVideo();
                }
                break;
        }
    }


    /* views */

    @AfterViews
    protected void initViews() {
        switch (state) {
            case STATE_PHOTO:
                photo_view.setVisibility(View.VISIBLE);
                if (media != null) {
                    Glide.with(getActivity()).load(media.url).centerCrop().into(photo_imageView);
                    // Picasso.with(getActivity()).load(media.url).centerCrop().fit().into(photo_imageView);
                }
                break;
            case STATE_VIDEO:
                videoContainer_view.setVisibility(View.VISIBLE);
                if (media != null) {
                    videoView.setVideoURI(Uri.parse(media.url));
                } else {
                    return;
                }

                videoView.setOnPreparedListener(preparedListener);
                break;
        }
    }

    @Click(R.id.photo_view)
    protected void photo_view_click() {
        if (onPhotoClickListener != null && media != null) {
            onPhotoClickListener.run(media);
        }
    }

    @Click(R.id.play_imageView)
    protected void play_imageView_click() {
        if (!videoView.isPlaying()) {
            playVideo();
        }
    }

 /*   @Click(R.id.audioState_imageView)
    protected void audioState_imageView_click() {
        checkAudioState(true);
    } */

    @Touch(R.id.videoView)
    protected void videoView_touch(MotionEvent motionEvent) {
        if (!isVideoPrepared) {
            return;
        }

        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            if (!videoView.isPlaying()) {
                playVideo();
            } else {
                pauseVideo();
            }
        }
    }


    /* public methods */

    public void setOnPhotoClickListener(RecyclerViewPagerAdapter.Action1<Media> onPhotoClickListener) {
        this.onPhotoClickListener = onPhotoClickListener;
    }


    /* private methods */

    private void playVideo() {
        if (!isVideoPrepared) {
            return;
        }

        play_imageView.setVisibility(View.GONE);
        videoView.seekTo(0);
        videoView.start();
    }

    private void pauseVideo() {
        if (!isVideoPrepared) {
            return;
        }

        play_imageView.setVisibility(View.VISIBLE);
        videoView.pause();
    }

 /*   private void checkAudioState(boolean revertState) {
        if (mediaPlayer == null) {
            return;
        }

        if (revertState) {
            isAudioEnabled = !isAudioEnabled;
        }

        if (isAudioEnabled) {
            audioState_imageView.setImageResource(R.drawable.ic_audio_on);
            mediaPlayer.setVolume(1, 1);
        } else {
            audioState_imageView.setImageResource(R.drawable.ic_audio_off);
            mediaPlayer.setVolume(0, 0);
        }
    } */


    /* callbacks */

    private final MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            mediaPlayer = mp;
            videoView.start(); // todo add imageView and load preview image there
            try {
                Thread.sleep(1); // show first frame of the video
            } catch (InterruptedException e) {
                // ignored
            }
            videoView.pause();
    //        audioState_imageView.setVisibility(View.VISIBLE);
            play_imageView.setVisibility(View.VISIBLE);
            isVideoPrepared = true;
    //        checkAudioState(false);
        }
    };

}
