package com.wrk.materialanimation;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;

public class SharedElementActivity extends AppCompatActivity {

    public static final String SE_FRAGMENT = "SEFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element);
        setupWindowAnimations();

        setFragTransition();


    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setFragTransition() {
        ShareElementFragment1 sharedElementFragment1 = new ShareElementFragment1();
        Slide slideTransition = new Slide(Gravity.LEFT);
        slideTransition.setDuration(500);
        sharedElementFragment1.setReenterTransition(slideTransition);
        sharedElementFragment1.setExitTransition(slideTransition);
        sharedElementFragment1.setSharedElementEnterTransition(new ChangeBounds());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.test_content1, sharedElementFragment1)
                .commit();
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        // We are not interested in defining a new Enter Transition. Instead we change default transition duration
        getWindow().getEnterTransition().setDuration(500);
    }
}