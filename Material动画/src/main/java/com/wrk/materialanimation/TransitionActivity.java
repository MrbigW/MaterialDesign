package com.wrk.materialanimation;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Visibility;
import android.view.View;

public class TransitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        setupWindowAnimations();
        setupLayout();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupLayout() {

        findViewById(R.id.sample1_button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Visibility returnTransition = buildReturnTransition();
                getWindow().setReturnTransition(returnTransition);

                finishAfterTransition();
            }
        });
        findViewById(R.id.sample1_button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 如果没有返回过渡动画那么系统自动重用进入过渡
                 * 在这个点击事件是，返回动画将会复用Slide
                 */
                finishAfterTransition();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        // 设置进入过渡动画
        Visibility enterTransition = buildEnterTransition();
        getWindow().setEnterTransition(enterTransition);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private Visibility buildEnterTransition() {
        Fade enterTransition = new Fade();
        enterTransition.setDuration(500);
        return enterTransition;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private Visibility buildReturnTransition() {
        Visibility returnTransition = new Slide();
        returnTransition.setDuration(500);
        return returnTransition;
    }
}
