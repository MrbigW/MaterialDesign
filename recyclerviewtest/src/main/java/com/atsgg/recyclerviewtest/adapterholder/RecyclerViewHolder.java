package com.atsgg.recyclerviewtest.adapterholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.atsgg.recyclerviewtest.R;

/**
 * Created by HP on 2016/8/25.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private final TextView textViewSample ;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        textViewSample = (TextView) itemView.findViewById(R.id.textViewSample);
    }

    public TextView getTextViewSample() {
        return textViewSample;
    }
}
