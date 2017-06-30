package com.multiprocesstest;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bykj003 on 2017/6/16.
 */

public class ImageLoaderBinder extends IImageLoader.Stub {

    private Context context;
    private ExecutorService executorService;

    public ImageLoaderBinder(Context context) {
        this.context = context;
        executorService = Executors.newSingleThreadExecutor();

    }

    @Override
    public Bitmap getBitmap(String imageUrl) throws RemoteException {
        try {
            return executorService.submit(new ImageLoaderTask(new WeakReference<Context>(context), imageUrl)).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class ImageLoaderTask implements Callable<Bitmap> {
        private WeakReference<Context> context;
        private String url;

        public ImageLoaderTask(WeakReference<Context> context, String url) {
            this.context = context;
            this.url = url;
        }

        @Override
        public Bitmap call() throws Exception {
            return Picasso.with(context.get()).load(url).get();
        }
    }
}
