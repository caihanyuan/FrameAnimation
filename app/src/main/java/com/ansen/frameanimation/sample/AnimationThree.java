package com.ansen.frameanimation.sample;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.ansen.frameanimation.AdvanceFrameAnimation;
import com.ansen.frameanimation.FrameAnimation;
import com.ansen.frameanimation.R;

public class AnimationThree extends Activity {
    private static final String TAG = "ansen";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two);
        final ImageView image = (ImageView) findViewById(R.id.image);

        // 每50ms一帧 循环播放动画
        final AdvanceFrameAnimation frameAnimation = new AdvanceFrameAnimation(image, getRes(), 50, false);
        frameAnimation.setAnimationListener(new AdvanceFrameAnimation.AnimationListener() {
            @Override
            public void onAnimationStart() {
                Log.d(TAG, "start");
            }

            @Override
            public void onAnimationEnd() {
                Log.d(TAG, "end");
                System.gc();
                finish();
            }

            @Override
            public void onAnimationRepeat() {
                Log.d(TAG, "repeat");
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 实现点击 暂停和继续播放动画
                if (frameAnimation.isPause()) {
                    Log.d(TAG, "restart");
                    frameAnimation.restartAnimation();
                } else {
                    Log.d(TAG, "pause");
                    frameAnimation.pauseAnimation();
                }
            }
        });
    }

    /**
     * 获取需要播放的动画资源
     */
    private int[] getRes() {
        TypedArray typedArray = getResources().obtainTypedArray(R.array.c);
        int len = typedArray.length();
        int[] resId = new int[len];
        for (int i = 0; i < len; i++) {
            resId[i] = typedArray.getResourceId(i, -1);
        }
        typedArray.recycle();
        return resId;
    }
}
