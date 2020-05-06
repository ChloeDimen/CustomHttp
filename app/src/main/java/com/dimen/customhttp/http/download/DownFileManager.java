package com.dimen.customhttp.http.download;

import android.os.Environment;
import android.util.Log;

import com.dimen.customhttp.http.HttpTask;
import com.dimen.customhttp.http.RequestHodler;
import com.dimen.customhttp.http.ThreadPoolManager;
import com.dimen.customhttp.http.download.interfaces.IDownloadServiceCallable;
import com.dimen.customhttp.http.interfaces.IHttpListener;
import com.dimen.customhttp.http.interfaces.IHttpServer;

import java.io.File;
import java.util.Map;
import java.util.concurrent.FutureTask;

/**
 * 文件名：com.dimen.customhttp.http.download
 * 描    述：
 * 作    者：Dimen
 * 时    间：2020/4/28
 */
public class DownFileManager  implements IDownloadServiceCallable {
    private static final String TAG = "DownFileManager";
    //    private  static
    private byte[] lock=new byte[0];

    public void down(String url) {

        synchronized (lock)
        {
            String[] preFixs=url.split("/");
            String afterFix=preFixs[preFixs.length-1];

            File file=new File(Environment.getExternalStorageDirectory(),afterFix);
            //实例化DownloadItem
            DownloadItemInfo downloadItemInfo=new DownloadItemInfo(url,file.getAbsolutePath());

            RequestHodler requestHodler=new RequestHodler();
            //设置请求下载的策略
            IHttpServer httpService=new FileDownHttpServer();
            //得到请求头的参数 map
            Map<String,String> map=httpService.getHttpHeadMap();
            /**
             * 处理结果的策略
             */
            IHttpListener httpListener=new DownloadListener(downloadItemInfo,this,httpService);

            requestHodler.setHttpListener(httpListener);
            requestHodler.setHttpServer(httpService);
            requestHodler.setUrl(url);

            HttpTask httpTask=new HttpTask(requestHodler);
            try {
                ThreadPoolManager.getInstance().execute(new FutureTask<Object>(httpTask,null));
            } catch (InterruptedException e) {

            }

        }




    }


    @Override
    public void onDownloadStatusChanged(DownloadItemInfo downloadItemInfo) {

    }

    @Override
    public void onTotalLengthReceived(DownloadItemInfo downloadItemInfo) {

    }

    @Override
    public void onCurrentSizeChanged(DownloadItemInfo downloadItemInfo, double downLenth, long speed) {
        Log.i(TAG,"下载速度："+ speed/1000 +"k/s");
        Log.i(TAG,"-----路径  "+ downloadItemInfo.getFilePath()+"  下载长度  "+downLenth+"   速度  "+speed);
    }

    @Override
    public void onDownloadSuccess(DownloadItemInfo downloadItemInfo) {
        Log.i(TAG,"下载成功    路劲  "+ downloadItemInfo.getFilePath()+"  url "+ downloadItemInfo.getUrl());
    }

    @Override
    public void onDownloadPause(DownloadItemInfo downloadItemInfo) {

    }

    @Override
    public void onDownloadError(DownloadItemInfo downloadItemInfo, int var2, String var3) {

    }
}
