package com.wrk.materialanimation;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class RevealActivity extends AppCompatActivity {

    private RelativeLayout root_reveal;
    private ImageButton iv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal);

        root_reveal = (RelativeLayout) findViewById(R.id.root_reveal);
        iv_show = (ImageButton) findViewById(R.id.iv_show);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void animateRevealShow(View v) {

        int cx = (root_reveal.getLeft() + root_reveal.getRight()) / 2;
        int cy = (root_reveal.getTop() + root_reveal.getBottom()) / 2;
        int finalRadius = Math.max(root_reveal.getWidth(), root_reveal.getHeight());

        Log.e("111", cx + "-----" + cy);

        Animator anim = ViewAnimationUtils.createCircularReveal(root_reveal, cx/2, cy/2, 0, finalRadius);
        iv_show.setVisibility(View.GONE);
        root_reveal.setBackgroundColor(Color.BLUE);
        anim.setDuration(500);
        anim.setInterpolator(new AccelerateInterpolator());
        anim.start();
    }


}
