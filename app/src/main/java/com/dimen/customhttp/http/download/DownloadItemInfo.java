package com.dimen.customhttp.http.download;

import com.dimen.customhttp.http.DownloadStatus;
import com.dimen.customhttp.http.HttpTask;

/**
 * 文件名：com.dimen.customhttp.http.download
 * 描    述：
 * 作    者：Dimen
 * 时    间：2020/4/28
 */
public class DownloadItemInfo  extends  BaseEntity<DownloadItemInfo>{

    private long currentLength;
    private long totalLength;
    private String url;

    private String filePath;
    private transient HttpTask mHttpTask;
    //下载的状态
    private DownloadStatus status;

    public DownloadStatus getStatus() {
        return status;
    }

    public void setStatus(DownloadStatus status) {
        this.status = status;
    }

    public DownloadItemInfo(String url, String filePath) {
        this.url = url;
        this.filePath = filePath;
    }
    public long getCurrentLength() {
        return currentLength;
    }

    public void setCurrentLength(long currentLength) {
        this.currentLength = currentLength;
    }

    public long getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(long totalLength) {
        this.totalLength = totalLength;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public HttpTask getHttpTask() {
        return mHttpTask;
    }

    public void setHttpTask(HttpTask httpTask) {
        mHttpTask = httpTask;
    }
}
