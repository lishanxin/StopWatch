package com.example.lishanxin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lishanxin.myapplication.presenter.StopWatchContract;
import com.example.lishanxin.myapplication.presenter.StopWatchPresenter;

//MVP模式V层实现类，实现了StopWatchContract.View的相关职责。
public class MainActivity extends AppCompatActivity implements View.OnClickListener, StopWatchContract.View {


    private TextView mTimeShow;
    private RecyclerView mRecycler;

    private StopWatchContract.Presenter mPresenter;
    private MyTimeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
        initPresenter();
    }

    private void initPresenter() {
        mPresenter = new StopWatchPresenter(this);
    }

    //初始化视图组件
    private void initLayout() {
        Button mStartBtn = findViewById(R.id.start);
        Button mStopBtn = findViewById(R.id.stop);
        Button mRefreshBtn = findViewById(R.id.refresh);
        mTimeShow = findViewById(R.id.time_show);
        Button mCatchBtn = findViewById(R.id.catch_time);
        mRecycler = findViewById(R.id.recycler);


        mStartBtn.setOnClickListener(this);
        mStopBtn.setOnClickListener(this);
        mRefreshBtn.setOnClickListener(this);
        mCatchBtn.setOnClickListener(this);

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setStackFromEnd(true);
        manager.setReverseLayout(true);
        mRecycler.setLayoutManager(manager);
        mAdapter = new MyTimeAdapter();
        mRecycler.setAdapter(mAdapter);
    }


    @Override
    public void onClick(View v) {
        if (mPresenter == null)
            return;
        switch (v.getId()) {
            case R.id.start:
                mPresenter.start();
                break;
            case R.id.stop:
                mPresenter.stop();
                break;
            case R.id.refresh:
                mPresenter.refresh();
                mAdapter.reset();
                mTimeShow.setText(R.string.start_time);
                break;
            case R.id.catch_time:
                mPresenter.getTime();
                break;
            default:
                break;
        }
    }

    @Override
    public void onTimeChange(String time) {
        mTimeShow.setText(time);
    }

    @Override
    public void onGetTime(String catchTime) {
        mAdapter.add(catchTime);
        mRecycler.scrollToPosition(mAdapter.getItemCount() - 1);
    }
}
