package com.example.lishanxin.myapplication.presenter;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.SystemClock;

//MVP模式P层实现类，实现了StopWatchContract.Presenter的相关职责。
public class StopWatchPresenter implements StopWatchContract.Presenter {

    private static final int WATCH_STOP = 0;
    private static final int WATCH_START = 1;

    private static int mWatchStatus = WATCH_STOP;

    private Handler mHandler = new Handler();

    private StopWatchContract.View mView;

    private TimeRunnable mTimeRunnable;

    public StopWatchPresenter(StopWatchContract.View mView) {
        this.mView = mView;

        mTimeRunnable = new TimeRunnable();
    }

    @Override
    public void start() {
        if (mWatchStatus == WATCH_START)
            return;
        // 启动计时
        mWatchStatus = WATCH_START;
        mTimeRunnable.start();
    }

    @Override
    public void getTime() {
        if (mWatchStatus == WATCH_STOP){
            return;
        }
        String curTime = mTimeRunnable.getCurrentTime();
        mView.onGetTime(curTime);
    }

    @Override
    public void stop() {
        mWatchStatus = WATCH_STOP;
    }

    @Override
    public void refresh() {
        mWatchStatus = WATCH_STOP;
        mTimeRunnable.refresh();
    }

    /**
     * 秒表显示数据的来源，通过Runnable对象的循环调用，来更新秒表显示
     */
    class TimeRunnable implements Runnable{

        // 点击开始时的系统时间
        private long startTime = -1;

        // 从某次点击开始后，经历的时间长度
        private long periodTime = 0;

        // 点击stop时，对之前计时的统计
        private long lastTotalTime = 0;

        /**
         * 开始计时，初始化时间初始值
         */
        public void start(){
            this.startTime = SystemClock.elapsedRealtime();
            mHandler.post(this);
        }


        //用户点击重置时调用的方法
        public void refresh(){
            initTimeStatus();
        }

        //重置所有缓存数据
        private void initTimeStatus() {
            startTime = -1;
            periodTime = 0;
            lastTotalTime = 0;
        }

        //获取当前秒表读数
        private String getCurrentTime(){
            long currentTime = periodTime + lastTotalTime;
            return formatTime(currentTime);
        }

        // run()方法获取系统时间数据，并传入onTimeChange方法中，进行界面展示时间。
        // 最后调用mHandler.postDelayed方法：在指定时间间隔后，再重新调用run()方法。
        @Override
        public void run() {
            if (mWatchStatus == WATCH_START){
                long curTime = SystemClock.elapsedRealtime();
                periodTime = curTime - startTime;
                String time = formatTime(periodTime + lastTotalTime);
                mView.onTimeChange(time);
                mHandler.postDelayed(this, 20);
            }else {
                //如果被停止，则保存停止时的秒表总时间
                lastTotalTime += periodTime;
            }
        }


        //将long数据转换为时间格式的字符串
        @SuppressLint("DefaultLocale")
        private String formatTime(long time){
            long hundreds, seconds, minutes;

            hundreds = (time % 1000) / 10;
            seconds = time / 1000;
            minutes = seconds / 60;
            seconds = seconds % 60;
            minutes = minutes % 60;


            return String.format("%02d:%02d:%02d", minutes, seconds, hundreds);
        }
    }
}
