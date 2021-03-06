package com.huangyong.downloadlib.fragment;
/**
 * Created by HuangYong on 2018/9/19.
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huangyong.downloadlib.R;
import com.huangyong.downloadlib.adapter.DownTaskAdapter;

/**
 * @author Huangyong
 * @version 1.0
 * @title
 * @description 已完成
 * @company 北京奔流网络信息技术有线公司
 * @created 2018/9/19
 * @changeRecord [修改记录] <br/>
 * 2018/9/19 ：created
 */
public class DownloadedTaskFragment extends Fragment {


    private RecyclerView downed;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.task_list_ed,null);

        downed = v.findViewById(R.id.rv_downed_task);
        downed.setLayoutManager(new LinearLayoutManager(getContext()));
        DownTaskAdapter adapter = new DownTaskAdapter();
        downed.setAdapter(adapter);
        return v;
    }
}
