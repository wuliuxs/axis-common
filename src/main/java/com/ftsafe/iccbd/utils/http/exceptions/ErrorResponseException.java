package com.ftsafe.iccbd.utils.http.exceptions;

import java.net.URL;

/**
 * Created by qingyuan on 2/24/17.
 */
public class ErrorResponseException extends Exception {
    private URL url;

    public ErrorResponseException(URL url, String msg) {
        super(msg);
        this.url = url;
    }

    public URL getUrl() {
        return this.url;
    }

}
