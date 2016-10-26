package com.atsgg.recyclerviewtest.activity;

import android.app.Activity;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.atsgg.recyclerviewtest.R;
import com.atsgg.recyclerviewtest.adapterholder.SampleRecyclerAdapter;
import com.atsgg.recyclerviewtest.divider.DividerLayoutItemDecoration;

public class LinearLayoutActivity extends Activity {

    private ImageButton fabView;
    private FrameLayout mDeleteBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout);


        fabView = (ImageButton) findViewById(R.id.fab_add);
        mDeleteBar = (FrameLayout) findViewById(R.id.deleteBar);


        //  创建右下角圆形按钮

        //android 5.x新特性 clipping（剪裁）
        ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                //  获取按钮的尺寸
                int fabSize = getResources().getDimensionPixelSize(R.dimen.fab_size);
                //  设置轮廓的尺寸
                outline.setOval(-4, -4, fabSize + 2, fabSize + 2);
            }
        };

        //  进行剪切
        fabView.setOutlineProvider(viewOutlineProvider);

        //  获取RecyclerView对象
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //  创建线性布局管理器（默认是垂直方向）
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);


        //为RecyclerView指定布局管理对象
        recyclerView.setLayoutManager(layoutManager);


        //创建列表项分割线对象
        final RecyclerView.ItemDecoration itemDecoration = new DividerLayoutItemDecoration(this);

        //为RecyclerView空间指定分割线对象
        recyclerView.addItemDecoration(itemDecoration);

        //  适配
        final SampleRecyclerAdapter sampleRecyclerAdapter = new SampleRecyclerAdapter();
        recyclerView.setAdapter(sampleRecyclerAdapter);

        //  添加动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        //  处理添加按钮的单击事件
        fabView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  获取第一个可视的Item的位置

                //LinearLayout与GridLayout
                int positionToadd = layoutManager.findFirstCompletelyVisibleItemPosition();
                sampleRecyclerAdapter.addItem(positionToadd);

            }
        });

        //处理删除事件
        mDeleteBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  删除第一个可视的Item

                //LinearLayout与GridLayout
                int positionToRemove = layoutManager.findFirstCompletelyVisibleItemPosition();
                sampleRecyclerAdapter.removeData(positionToRemove);


            }
        });


        //  为RecyclerView控件设置滚动事件
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                super.onScrolled(recyclerView, dx, dy);
                //  dx：大于0，向右滚动    小于0，向左滚动
                //  dy：大于0，向上滚动    小于0，向下滚动

                if (dy >= 0) {
                    //  列表向上滚动，隐藏删除面板
                    if (mDeleteBar.getVisibility() == View.VISIBLE) {
                        hideDeleteBar();
                    }
                } else {
                    // 列表向下滚动，显示删除面板
                    if (mDeleteBar.getVisibility() == View.GONE) {
                        showDeleteBar();
                    }
                }

            }
        });
    }

    private void showDeleteBar() {

        mDeleteBar.startAnimation(AnimationUtils.loadAnimation(this,
                R.anim.translate_up_on));

        mDeleteBar.setVisibility(View.VISIBLE);
    }

    private void hideDeleteBar() {

        mDeleteBar.startAnimation(AnimationUtils.loadAnimation(this,
                R.anim.translate_up_off));

        mDeleteBar.setVisibility(View.GONE);
    }
}
















