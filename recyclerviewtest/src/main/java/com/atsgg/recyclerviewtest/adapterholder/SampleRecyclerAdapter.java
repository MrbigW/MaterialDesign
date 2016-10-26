package com.atsgg.recyclerviewtest.adapterholder;


import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atsgg.recyclerviewtest.R;
import com.atsgg.recyclerviewtest.data.SampleModel;
import com.atsgg.recyclerviewtest.data.DemoApp;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by HP on 2016/8/25.
 */
public class SampleRecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    private final ArrayList<SampleModel> sampleData = DemoApp.getSampleDate(50);

    //  用于创建控件
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //获得列表项控件（LinearLayout）

        //item_layout.xml布局文件中只包含一个<LinearLayout>标签，在该标签中包含了一个<TextView>标签
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        return new RecyclerViewHolder(item);
    }

    //  把数据绑定到控件中
    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        //  获取当前item中显示的数据
        final SampleModel rowData = sampleData.get(position);

        //  设置要显示的数据
        holder.getTextViewSample().setText(rowData.getSampleText());


        // 如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, pos);
                    removeData(pos);
                    return true;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return sampleData.size();
    }

    //  删除指定的Item
    public void removeData(int position) {
        sampleData.remove(position);
        //  通知RecycleView控件某个Item被删除了
        notifyItemRemoved(position);
    }

    //  在指定位置添加一个新的Item
    public void addItem(int positionToadd) {
        sampleData.add(positionToadd, new SampleModel("new" + new Random().nextInt(1000)));
        //   通知RecycleView控件增加了某个Item
        notifyItemInserted(positionToadd);
    }

}

















