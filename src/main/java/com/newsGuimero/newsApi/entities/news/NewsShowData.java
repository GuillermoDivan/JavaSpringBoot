package com.newsGuimero.newsApi.entities.news;

public record NewsShowData(Long id, String title, String body) {

    public NewsShowData(News news) {
        this(news.getId(), news.getTitle(), news.getBody());
    }
}
