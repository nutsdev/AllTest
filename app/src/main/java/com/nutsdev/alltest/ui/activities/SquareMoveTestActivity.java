package com.nutsdev.alltest.ui.activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.view.Surface;
import android.view.View;
import android.widget.ScrollView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import com.nutsdev.alltest.R;
import com.nutsdev.alltest.utils.views.SquareTransformer;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_square_move_test)
public class SquareMoveTestActivity extends AppCompatActivity {

    private MappingTrackSelector trackSelector;
    private SimpleExoPlayer simpleExoPlayer;

    @Extra
    @InstanceState
    protected Uri videoUri;
    @InstanceState
    protected int videoPlaybackState = ExoPlayer.STATE_IDLE;

    @ViewById(R.id.videoContainer_view)
    protected View videoContainer_view;
    @ViewById(R.id.scrollView)
    protected ScrollView scrollView; // if video horizontal then should use HorizontalScrollView
    @ViewById(R.id.simpleExoPlayerView)
    protected SimpleExoPlayerView simpleExoPlayerView;


    /* lifecycle */

    @AfterViews
    protected void afterViews() {
        SquareTransformer.transformToSquareView(this, videoContainer_view);
        initExoPlayer();
    }


    /* private methods */

    private void initExoPlayer() {
        // need READ_EXTERNAL_STORAGE permission for correct work
        if (videoUri == null) {
            videoUri = Uri.parse("content://media/external/video/media/33081");
            // content://media/external/video/media/32943 // local horizontal FullHD video for testing
            // content://media/external/video/media/33083 // local horizontal SD video for testing

            // content://media/external/video/media/33081 // local vertical FullHD video for testing
            // content://media/external/video/media/33082 // local vertical SD video for testing
        }

        // 1. Create a default TrackSelector
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
        trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        // 2. Create a default LoadControl
        LoadControl loadControl = new DefaultLoadControl();

        // 3. Create the player
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);

        // Produces DataSource instances through which media data is loaded.
        final String userAgent = Util.getUserAgent(this, getString(R.string.app_name));
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, userAgent);

        // Produces Extractor instances for parsing the media data.
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        // This is the MediaSource representing the media to be played.
        MediaSource videoSource = new ExtractorMediaSource(videoUri, dataSourceFactory, extractorsFactory, null, null);

        // Loops the video indefinitely.
        LoopingMediaSource loopingSource = new LoopingMediaSource(videoSource);

        simpleExoPlayer.addListener(eventListener);
        simpleExoPlayer.setVideoDebugListener(videoRendererEventListener);
        simpleExoPlayerView.setUseController(true);
        simpleExoPlayerView.setPlayer(simpleExoPlayer);

        // Prepare the player with the source.
        simpleExoPlayer.prepare(loopingSource);
    }


    /* callbacks */

    private final ExoPlayer.EventListener eventListener = new ExoPlayer.EventListener() {
        @Override
        public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
            // ignored
        }

        @Override
        public void onLoadingChanged(boolean isLoading) {
            // ignored
        }

        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            videoPlaybackState = playbackState;
            if (playbackState == ExoPlayer.STATE_READY) {
                // ignored ?
            } else if (playbackState == ExoPlayer.STATE_ENDED) {
                // ignored ?
            }
        }

        @Override
        public void onTimelineChanged(Timeline timeline, Object manifest) {
            // ignored
        }

        @Override
        public void onPlayerError(ExoPlaybackException error) {
        //    Crashlytics.logException(error);
        }

        @Override
        public void onPositionDiscontinuity() {
            // ignored
        }
    };

    private final VideoRendererEventListener videoRendererEventListener = new VideoRendererEventListener() {
        @Override
        public void onVideoEnabled(DecoderCounters counters) {
            // ignored
        }

        @Override
        public void onVideoDecoderInitialized(String decoderName, long initializedTimestampMs, long initializationDurationMs) {
            // ignored
        }

        @Override
        public void onVideoInputFormatChanged(Format format) {
            // ignored
        }

        @Override
        public void onDroppedFrames(int count, long elapsedMs) {
            // ignored
        }

        @Override
        public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
            if (height > width) {
                simpleExoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH);
            } else {
                simpleExoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_HEIGHT);
            }
        }

        @Override
        public void onRenderedFirstFrame(Surface surface) {
            // ignored
        }

        @Override
        public void onVideoDisabled(DecoderCounters counters) {
            // ignored
        }
    };

}
