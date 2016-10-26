package com.atsgg.recyclerviewtest.activity;

import android.app.Activity;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageButton;
import android.widget.Toast;

import com.atsgg.recyclerviewtest.R;
import com.atsgg.recyclerviewtest.adapterholder.RandomStaggeredRecyclerAdapter;
import com.atsgg.recyclerviewtest.adapterholder.StaggeredRecyclerAdapter;
import com.atsgg.recyclerviewtest.divider.DividerGridItemDecoration;

public class RandomStaggeredLayoutActivity extends Activity {

    private ImageButton fabView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout);


        fabView = (ImageButton) findViewById(R.id.fab_add);


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
//        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        //  创建GridView管理器
//        final GridLayoutManager layoutManager = new GridLayoutManager(this, 4);


        //  创建StaggeredGridLayout（瀑布流）管理器
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);

        //为RecyclerView指定布局管理对象
        recyclerView.setLayoutManager(layoutManager);


        //创建列表项分割线对象
//        final RecyclerView.ItemDecoration itemDecoration = new DividerLayoutItemDecoration(this);
//        final RecyclerView.ItemDecoration itemDecoration = new DividerGridItemDecoration(this);

        //为RecyclerView空间指定分割线对象
//        recyclerView.addItemDecoration(itemDecoration);

        //  适配
        final RandomStaggeredRecyclerAdapter adapter = new RandomStaggeredRecyclerAdapter();
        recyclerView.setAdapter(adapter);

        //  添加动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter.setOnItemClickListener(new RandomStaggeredRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RandomStaggeredLayoutActivity.this,
                        position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(RandomStaggeredLayoutActivity.this,
                        position + " long click", Toast.LENGTH_SHORT).show();
            }
        });


        //  处理添加按钮的单击事件
        fabView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  获取第一个可视的Item的位置

                //LinearLayout与GridLayout
//                int positionToadd = layoutManager.findFirstCompletelyVisibleItemPosition();
//                sampleRecyclerAdapter.addItem(positionToadd);

                //StaggeredGridLayout瀑布流
                int[] items = new int[4];   //4是new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL);中的4
                int[] positions = layoutManager.findFirstCompletelyVisibleItemPositions(items);
                adapter.addItem(positions[1]);
            }
        });


    }

}
















