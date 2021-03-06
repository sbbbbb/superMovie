package dev.baofeng.com.supermovie.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import dev.baofeng.com.supermovie.R;
import dev.baofeng.com.supermovie.adapter.MainAdapter;
import dev.baofeng.com.supermovie.domain.BtInfo;
import dev.baofeng.com.supermovie.domain.MovieInfo;
import dev.baofeng.com.supermovie.domain.RecentUpdate;
import dev.baofeng.com.supermovie.presenter.GetRecpresenter;
import dev.baofeng.com.supermovie.presenter.iview.IMoview;
import dev.baofeng.com.supermovie.utils.NetworkUtils;

/**
 * Created by huangyong on 2018/1/31.
 */

public class BtListFragment extends Fragment implements IMoview, BGARefreshLayout.BGARefreshLayoutDelegate {
    @BindView(R.id.rvlist)
    RecyclerView rvlist;
    @BindView(R.id.bga_refresh)
    BGARefreshLayout bgaRefresh;
    private GetRecpresenter recpresenter;
    private MainAdapter adapter;
    private static BtListFragment btlistFragment;
    private Unbinder bind;
    private String type;
    private MovieInfo infos;
    private int index;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_frag_layout, null);
        bind = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    public static BtListFragment newInstance(String type) {
        btlistFragment = new BtListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Type", type);
        btlistFragment.setArguments(bundle);
        return btlistFragment;

    }

    private void initView() {
        recpresenter = new GetRecpresenter(getContext(), this);
        bgaRefresh.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getContext(), true);
        // 设置下拉刷新和上拉加载更多的风格
        bgaRefresh.setRefreshViewHolder(refreshViewHolder);

        Bundle bundle = getArguments();
        type = bundle.getString("Type");
        index = 1;
        recpresenter.getBtRecommend(type, index, 18);
    }

    @Override
    public void loadData(RecentUpdate info) {

    }

    @Override
    public void loadData(MovieInfo info) {


    }

    @Override
    public void loadError(String msg) {

    }

    @Override
    public void loadMore(MovieInfo result) {
        infos.getData().addAll(result.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadMore(RecentUpdate result) {

    }

    @Override
    public void loadBtData(MovieInfo result) {
        infos = result;
        adapter = new MainAdapter(getContext(), infos);

        rvlist.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvlist.setAdapter(adapter);
    }

    @Override
    public void loadDetail(BtInfo result) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    /**
     * 下拉刷新,网络异常时重新加载数据
     * @param refreshLayout
     */
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        if (NetworkUtils.isNetAvailable(getContext())) {
            //网络可用。异步加载后停止刷新
            // 加载完毕后在 UI 线程结束加载更多
            new Handler().postDelayed(()->  {
                    recpresenter.getBtRecommend(type, index, 18);
                    bgaRefresh.endRefreshing();
            }, 2500);
        } else {
            // 网络不可用，结束下拉刷新
            Toast.makeText(getActivity(), "网络不可用", Toast.LENGTH_SHORT).show();
            bgaRefresh.endRefreshing();

        }
    }

    /**
     * 上拉加载更多
     * @param refreshLayout
     * @return
     */
    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        new Handler().postDelayed(()-> {
            bgaRefresh.endLoadingMore();
            recpresenter.getBtMoreData(type, ++index, 18);
        }, 2000);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
