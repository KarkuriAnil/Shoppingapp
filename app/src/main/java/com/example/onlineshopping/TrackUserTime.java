package com.example.onlineshopping;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class TrackUserTime extends Service {
    private int seconds=0;
    private boolean shouldFinish;
    private  GroceryItem item;
    private IBinder iBinder=new localBlinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        tracktime();
        return iBinder;
    }
    public class localBlinder extends Binder
    {
        TrackUserTime getservice()
        {
            return TrackUserTime.this;
        }}
    private void tracktime()
    {
    Thread thread=new Thread(new Runnable() {
        @Override
        public void run() {
            while ((!shouldFinish))
            {
                try{
                Thread.sleep(1000);
                seconds++;
            }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
        }
    }}});thread.start();

    }

    public void setItem(GroceryItem item) {
        this.item = item;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        shouldFinish=true;
        int minutes=seconds/60;
        if(minutes>0)
        {
            if(null!=item)
            {
            Utils.changeuserpoint(this,item,minutes);
        }
    }
}}
