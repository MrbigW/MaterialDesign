package com.wrk.materialanimation;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView tv_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWindowAnimations();

        tv_test = (TextView) findViewById(R.id.tv_test);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void enterSharedElementActivity(View v) {
        Intent intent = new Intent(this, SharedElementActivity.class);
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(MainActivity.this, false,
                new Pair<>(tv_test, "testText"));
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
        startActivity(intent, transitionActivityOptions.toBundle());
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void enterTransitionActivity(View v) {
        Intent intent = new Intent(this, TransitionActivity.class);
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(this, true);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
        startActivity(intent, transitionActivityOptions.toBundle());
    }

    public void enterRevealActivity(View v) {
        startActivity(new Intent(this, RevealActivity.class));
    }

    private void setupWindowAnimations() {
        // 当进入该activity时执行此过渡
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Slide slideTransition = new Slide();
            slideTransition.setSlideEdge(Gravity.START);
            slideTransition.setDuration(500);
            getWindow().setReenterTransition(slideTransition);
            getWindow().setExitTransition(slideTransition);
        }
    }
}
