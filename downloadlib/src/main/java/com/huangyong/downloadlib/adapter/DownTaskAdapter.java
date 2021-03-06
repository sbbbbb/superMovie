package com.huangyong.downloadlib.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huangyong.downloadlib.R;

public class DownTaskAdapter extends RecyclerView.Adapter<TaskHolder> {


    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_layout,null);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
//        holder.taskTitile.setText("测试下载标题");
    }

    @Override
    public int getItemCount() {
        return 8;
    }
}
