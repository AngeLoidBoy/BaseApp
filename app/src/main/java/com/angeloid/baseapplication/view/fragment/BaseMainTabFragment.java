package com.angeloid.baseapplication.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.angeloid.baseapplication.R;
import com.angeloid.baseapplication.adapter.MainTabAdapter;
import com.angeloid.baseapplication.base.BaseFragment;
import com.angeloid.baseapplication.bean.TabType;
import com.angeloid.baseapplication.bean.response.SearchResponseDetail;
import com.angeloid.baseapplication.presenter.MainTabFragmentPresenter;
import com.angeloid.baseapplication.view.method.MainTabFragmentView;
import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yunjw
 *         Created at 2018/1/27.
 *         Time is 12:41 now.
 *         (#^.^#)
 */

public abstract class BaseMainTabFragment<P extends MainTabFragmentPresenter> extends BaseFragment<P> implements MainTabFragmentView
{
    @Override
    protected abstract P initPresenter();

    protected  abstract TabType initTabType();

    @BindView(R.id.srl_container)
    protected SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.rv_container)
    protected RecyclerView mainTabRecyclerView;

    private MainTabAdapter mainTabAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab, container, false);
        ButterKnife.bind(this, v);
        initView();
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.setTabType(initTabType());
    }

    private void initView() {
        mainTabAdapter = new MainTabAdapter(getContext());
        mainTabRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        mainTabRecyclerView.setAdapter(mainTabAdapter);
//        smartRefreshLayout.setEnableLoadmore(false);
        smartRefreshLayout.setEnableOverScrollBounce(true);
        smartRefreshLayout.setEnableOverScrollDrag(true);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (checkPresenter()) {
                    fetchOriginData();
                }
            }
        });
    }


    public void fetchOriginData() {
        presenter.fetchOriginData();
    }


    public void fetchMoreData() {
        presenter.fetchMoreData();
    }

    @Override
    public void setOriginData(List<SearchResponseDetail> responseDetails) {

        ToastUtils.showShort("有数据辣！");
        smartRefreshLayout.finishRefresh();
    }

    @Override
    public void setOriginDataFailed(String code, String errorMsg) {
        ToastUtils.showShort("失败了：" + errorMsg);
        smartRefreshLayout.finishRefresh();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        Logger.i("onHiddenChanged");
        if (hidden) {
            smartRefreshLayout.finishRefresh();
            smartRefreshLayout.finishLoadmore();
        }
        super.onHiddenChanged(hidden);
    }
}
