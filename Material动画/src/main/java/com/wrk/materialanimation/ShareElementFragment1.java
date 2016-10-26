package com.wrk.materialanimation;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import static com.wrk.materialanimation.SharedElementActivity.SE_FRAGMENT;

/**
 * Created by MrbigW on 2016/10/26.
 * weChat:1024057635
 * Github:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class ShareElementFragment1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sharedelement_layout1, container, false);

        final ImageView squareBlue = (ImageView) view.findViewById(R.id.square_blue);

        view.findViewById(R.id.sample2_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNextFragment(squareBlue, false);
            }
        });

        view.findViewById(R.id.sample2_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNextFragment(squareBlue, true);
            }
        });

        return view;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void addNextFragment(ImageView squareBlue, boolean overlap) {
        ShareElementFragment2 sharedElementFragment = new ShareElementFragment2();
        Slide slideTransition = new Slide(Gravity.RIGHT);
        slideTransition.setDuration(500);

        ChangeBounds changeBoundsTransition = new ChangeBounds();
        changeBoundsTransition.setDuration(500);

        // 设置进入过渡动画
        sharedElementFragment.setEnterTransition(slideTransition);
        // ture是缓慢加载其他视图，false是尽快加载其他视图
        sharedElementFragment.setAllowEnterTransitionOverlap(overlap);
        sharedElementFragment.setAllowReturnTransitionOverlap(overlap);
        sharedElementFragment.setSharedElementEnterTransition(changeBoundsTransition);

        getFragmentManager().beginTransaction().replace(R.id.test_content1, sharedElementFragment, SE_FRAGMENT)
                .addToBackStack(null)
                .addSharedElement(squareBlue, "square_name")
                .commit();

    }
}
