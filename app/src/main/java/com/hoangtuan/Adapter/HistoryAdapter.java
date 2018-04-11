package com.hoangtuan.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.hoangtuan.scanqrc.Model.HistoryModel;
import com.hoangtuan.scanqrc.R;

import java.util.List;

/**
 * Created by atbic on 29/3/2018.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHodle> {
    List<HistoryModel> historyModels;
    Context context;

    public HistoryAdapter() {
    }

    public HistoryAdapter(List<HistoryModel> historyModels, Context context) {
        this.historyModels = historyModels;
        this.context = context;
    }

    @Override
    public HistoryViewHodle onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history,parent,false);
        return new HistoryViewHodle(view);
    }

    @Override
    public void onBindViewHolder(HistoryViewHodle holder, int position) {
HistoryModel historyModel =historyModels.get(position);
holder.mTxtStyle.setText(historyModel.getStyleCode());
holder.mTxtContent.setText(historyModel.getContent());
holder.mImgStyle.setImageResource(historyModel.getImgStyleCode());

    }

    @Override
    public int getItemCount() {
        return historyModels.size();
    }

    public class HistoryViewHodle extends RecyclerView.ViewHolder{
        private TextView mTxtContent,mTxtStyle;
        private ImageView mImgStyle;
        public HistoryViewHodle(View itemView) {
            super(itemView);
            mImgStyle = (ImageView) itemView.findViewById(R.id.mImgHistory);
            mTxtContent = (TextView) itemView.findViewById(R.id.mTxtContent);
            mTxtStyle = (TextView) itemView.findViewById(R.id.mTxtStyle);
        }
    }
}
