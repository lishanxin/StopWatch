package com.example.lishanxin.myapplication.presenter;

/**
 * 定义秒表的契约类，包含Presenter与View的基本职责
 */
public interface StopWatchContract {

    interface Presenter{
        //启动秒表计时
        void start();

        //获取当前秒表读数
        void getTime();

        //暂停秒表
        void stop();

        //重置秒表
        void refresh();

    }

    interface View{

        /**
         * 当前秒表数有变化时，会调用此方法
         * @param time 变化后的时间
         */
        void onTimeChange(String time);

        /**
         * 当用户点击计次时，会调用此方法
         * @param catchTime 返回点击时的秒表读数
         */
        void onGetTime(String catchTime);
    }
}
