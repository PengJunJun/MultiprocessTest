package com.multiprocesstest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by bykj003 on 2017/6/16.
 */

public class ImageLoaderService extends Service {
    private static final String TAG = "ImageLoaderService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ImageLoaderBinder(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
