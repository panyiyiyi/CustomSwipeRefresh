package com.swiperefreshtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {

    private List<TestModel> mDatas;
    private Context mContext;

    public MainRecyclerAdapter(Context context) {
        mDatas = new ArrayList<>();
        mContext = context;
    }

    public void setDatas(List<TestModel> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.view_list_main_content, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextIndex.setText("#" + mDatas.get(position).position);
        holder.mTextTitle.setText(mDatas.get(position).title);

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextTitle;
        TextView mTextIndex;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextTitle = (TextView) itemView.findViewById(R.id.text_list_main_title);
            mTextIndex = (TextView) itemView.findViewById(R.id.text_list_main_index);
        }


    }
}
