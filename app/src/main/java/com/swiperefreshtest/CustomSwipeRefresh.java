package com.swiperefreshtest;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.swiperefreshtest.view.BothSwipeRefresh;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Even on 2017/8/4.
 * Description: 第三方实现下拉刷新和上拉加载的效果
 */

public class CustomSwipeRefresh extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MainRecyclerAdapter mAdapter;
    private BothSwipeRefresh swiperefresh;
    List<TestModel> result;
    private static final int REFRESH_TOP = 0X110;
    private static final int REFRESH_BOTTOM = 0X111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_main);
        swiperefresh = (BothSwipeRefresh) findViewById(R.id.swiperefresh);
        swiperefresh.setDirection(SwipyRefreshLayoutDirection.BOTH);
        swiperefresh.setProgressViewOffset(true, 0, (int) getResources().getDisplayMetrics().density * 60);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MainRecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        result = createTestDatas();
        mAdapter.setDatas(result);

        swiperefresh.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                if (direction == SwipyRefreshLayoutDirection.TOP) {//表示下拉
                    mHandler.sendEmptyMessageDelayed(REFRESH_TOP, 2000);
                } else if (direction == SwipyRefreshLayoutDirection.BOTTOM) {//表示上拉加载
                    mHandler.sendEmptyMessageDelayed(REFRESH_BOTTOM, 2000);
                }
            }
        });


    }

    private List<TestModel> createTestDatas() {
        List<TestModel> result = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TestModel testModel = new TestModel(i, "Item Swipe Action Button Container Width");
            result.add(testModel);
        }
        return result;
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_TOP:
                    for (int i = mAdapter.getItemCount(); i < mAdapter.getItemCount() + 5; i++) {
                        TestModel testModel = new TestModel(i, "This is top refresh");
                        result.add(testModel);
                    }
                    mAdapter.setDatas(result);
                    swiperefresh.setRefreshing(false);
                    break;
                case REFRESH_BOTTOM:
                    for (int i = mAdapter.getItemCount(); i < mAdapter.getItemCount() + 5; i++) {
                        TestModel testModel = new TestModel(i, "This is bottom refresh");
                        result.add(testModel);
                    }
                    mAdapter.setDatas(result);
                    swiperefresh.setRefreshing(false);
                    break;

            }
        }


    };
}
