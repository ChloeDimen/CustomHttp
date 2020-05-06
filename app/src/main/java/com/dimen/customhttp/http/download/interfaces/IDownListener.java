package com.dimen.customhttp.http.download.interfaces;

import com.dimen.customhttp.http.interfaces.IHttpListener;
import com.dimen.customhttp.http.interfaces.IHttpServer;

/**
 * 文件名：com.dimen.customhttp.http.download.interaces
 * 描    述：
 * 作    者：Dimen
 * 时    间：2020/4/28
 */
public interface IDownListener extends IHttpListener {

    void setHttpServer(IHttpServer httpServer);

    void setCanclCalle();

    void setPauseCallble();
}
