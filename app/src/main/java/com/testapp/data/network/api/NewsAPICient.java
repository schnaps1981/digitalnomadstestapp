package com.testapp.data.network.api;

import javax.inject.Inject;

public class NewsAPICient {
    @Inject
    NewsAPICient newsAPICient;

    public NewsAPIInterface create() {
        return newsAPICient.create();
    }
}
