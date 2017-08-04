package com.swiperefreshtest;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.swiperefreshtest.view.SwipeRefresh;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Even on 2017/8/4.
 * Description:系统的下拉刷新显示效果
 */

public class SystemSwipeRefresh extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MainRecyclerAdapter mAdapter;
    private SwipeRefresh swiperefresh;
    List<TestModel> result;
    private static final int REFRESH_COMPLETE = 0X110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_main);
        swiperefresh = (SwipeRefresh) findViewById(R.id.swiperefresh);
        swiperefresh.setNestedScrollingEnabled(true);
        swiperefresh.setProgressViewOffset(true, 0, (int) getResources().getDisplayMetrics().density * 60);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MainRecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        result = createTestDatas();
        mAdapter.setDatas(result);

        swiperefresh.setOnRefreshListener(new SwipeRefresh.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
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
                case REFRESH_COMPLETE:
                    for (int i = mAdapter.getItemCount(); i < mAdapter.getItemCount() + 5; i++) {
                        TestModel testModel = new TestModel(i, "Item Swipe Action Button Container Width");
                        result.add(testModel);
                    }
                    mAdapter.setDatas(result);
                    swiperefresh.setRefreshing(false);
                    break;

            }
        }


    };
}
