package com.example.lishanxin.myapplication;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class MyTimeAdapter extends RecyclerView.Adapter<MyTimeAdapter.ViewHolder> {

    private List<String> mDataList;

    public MyTimeAdapter(){
        this(new ArrayList<String>());
    }

    public MyTimeAdapter(List<String> mDataList) {
        this.mDataList = mDataList;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.cell_time_catch;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // 只有一种数据，所以不对viewType进行判断处理
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View root = inflater.inflate(viewType, viewGroup, false);

        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.bind(mDataList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    //添加新的数据
    public void add(String newTime){
        mDataList.add(newTime);
        notifyItemInserted(mDataList.size() - 1);
    }

    //重置所有数据
    public void reset(){
        mDataList.clear();
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView catchTime;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            catchTime = itemView.findViewById(R.id.catch_time_text);
        }

        //数据显示
        void bind(String time, int position) {
            @SuppressLint("DefaultLocale")
            String show = String.format("%s%d%16s", catchTime.getContext().getResources().getString(R.string.num_count), position, time);
            catchTime.setText(show);
        }
    }
}
