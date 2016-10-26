package com.atsgg.recyclerviewtest.divider;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 自定义定制分隔条
 * Created by Mr.W on 2016/8/23.
 */
public class DividerLayoutItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = {android.R.attr.listDivider};
    //  分隔线
    private Drawable mDriver;

    public DividerLayoutItemDecoration(Context context) {
        //  自定义属性
        TypedArray array = context.obtainStyledAttributes(ATTRS);
        //  获取系统提供的分隔条Drawable对象
        mDriver = array.getDrawable(0);
        //  回收TypedArray所占的空间
        array.recycle();
    }

    //  在该方法中绘制了所有列表项之间的分隔线
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent) {
        //  获取列表项距离左边缘的距离
        int left = parent.getPaddingLeft();

        //  获取列表项距离右边缘的距离(分隔线的长度)
        int right = parent.getWidth() - parent.getPaddingRight();

        //  获取列表项的总数
        int childCOunt = parent.getChildCount();

        //  开始绘制所有列表项之间的分割线
        for (int i = 0; i < childCOunt; i++) {
            //  获取当前列表项
            View child = parent.getChildAt(i);

            //  获取当前列表项的布局参数信息
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            //  计算分隔条左上角的纵坐标
            int top = child.getBottom() + params.bottomMargin;

            //  计算分隔条右下角的纵坐标
            int bottom = top + mDriver.getIntrinsicHeight();

            //  设置分隔条绘制位置
            mDriver.setBounds(left,top,right,bottom);

            //  开始绘制当前列表项下方的分隔条
            mDriver.draw(c);
        }
    }
}













