package com.testapp.data.network.utils;


public class NetworkState {

    public enum Status{
        RUNNING,
        SUCCESS,
        FAILED,
        NO_MORE_DATA
    }

    private final Status status;
    private final String msg;

    public static final NetworkState LOADED;
    public static final NetworkState LOADING;
    public static final NetworkState NO_MORE_DATA;

    public NetworkState(Status status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    static {
        LOADED = new NetworkState(Status.SUCCESS,"Success");
        LOADING = new NetworkState(Status.RUNNING,"Running");
        NO_MORE_DATA = new NetworkState(Status.NO_MORE_DATA, "No more data");
    }

    public Status getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
