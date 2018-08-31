package com.crisolapp.util;

import android.content.Context;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jesusesmipastor on 19/02/2015.
 */
public  class Util {
    static  Context context;
    static Timer t =null;
    public Util(Context context) {
        this.context = context;
    }


    public static void SyncData(Context con,int milis){
        context = con;
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
            final Sync sync = new Sync(context);
            sync.start();
            }
        };
        if(t==null){
            t = getTimer();
            t.schedule(task,0,milis);
        }

    }

    public  static Timer getTimer(){
        if(t==null){
            return new Timer();
        }else{
            return t;
        }

    }
}
