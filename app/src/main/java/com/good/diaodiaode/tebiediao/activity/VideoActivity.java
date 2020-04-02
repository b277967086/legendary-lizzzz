package com.good.diaodiaode.tebiediao.activity;

import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;

import com.good.diaodiaode.tebiediao.R;

import java.io.IOException;

public class VideoActivity extends AppCompatActivity
        implements TextureView.SurfaceTextureListener, MediaPlayer.OnPreparedListener{

    /**本地视频的路径*/
    public String videoPath = Environment.getExternalStorageDirectory().getPath() + "/ht.mp4";
    private TextureView textureView;
    private MediaPlayer mediaPlayer;

    private SurfaceTexture mTexture;
    private SurfaceHolder holder;
    private Surface surface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        textureView = (TextureView) findViewById(R.id.textureView);
        //注册一个SurfaceTexture，用于监听SurfaceTexure
        textureView.setSurfaceTextureListener(this);
    }
    /**
     * 播放视频的入口，当SurfaceTexure可得到时被调用
     */
    private void playVideo() {
        if (mediaPlayer == null) {
            mTexture = textureView.getSurfaceTexture();
            surface = new Surface(mTexture);
            initMediaPlayer();
        }
    }

    private void initMediaPlayer() {
        this.mediaPlayer = new MediaPlayer();
        try {
//            mediaPlayer.setDataSource(videoPath);
            mediaPlayer.setDataSource(this, Uri.parse("http://v2.ymatou.com/uploads/20180514/b3e8133acebc5aebe22b3fdca9a3b3dc.mp4"));
            mediaPlayer.setSurface(surface);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setLooping(true);
        } catch (IllegalArgumentException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IllegalStateException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        try {
            if (mp != null) {
                mp.start(); //视频开始播放
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (textureView.isAvailable()) {
            playVideo();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mTexture != null) {
            mTexture.release();  //停止视频的绘制线程
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer =null;
        }
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        playVideo();
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }
}