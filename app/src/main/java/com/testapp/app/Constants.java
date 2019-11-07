package com.testapp.app;

public final class Constants {
    public static final String DB_NAME = "app.db";
    public static final Long DB_CHACHE_LIFETIME_SEC = 120L;

    public static final String API_BASE_URL = "https://newsapi.org/";
    public static final String API_KEY = "26eddb253e7840f988aec61f2ece2907";

    public static final Integer PREFETCH_SIZE = 5;
    public static final Integer PAGE_SIZE = 20;
    public static final Integer THREADS = 3;

    public static final Long MAX_PAGES = 5L;

    public static final Integer NETWORK_TIMEOUT_SEC = 5;

    Constants() {}
}
