package com.newsGuimero.newsApi.services;
import com.newsGuimero.newsApi.entities.news.News;
import com.newsGuimero.newsApi.entities.news.NewsRegisterData;
import com.newsGuimero.newsApi.entities.news.NewsShowData;
import com.newsGuimero.newsApi.entities.news.NewsUpdateData;
import com.newsGuimero.newsApi.repository.NewsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;


    public NewsShowData saveNews(NewsRegisterData newsRegisterData){
        News news = new News(newsRegisterData);
        this.newsRepository.save(news);
        return new NewsShowData(news);
    }

    public NewsShowData findNewsById(Long id) throws EntityNotFoundException {
            News news = this.newsRepository.findById(id)
                    .orElseThrow(EntityNotFoundException::new);
            return new NewsShowData(news);
        }

    public NewsShowData findNewsByTitle(String title) throws EntityNotFoundException {
        News news = this.newsRepository.findByTitle(title);
        return new NewsShowData(news);
    }

    public Page<NewsShowData> findAllNews(Pageable paging) {
        return this.newsRepository.findAll(paging).map(NewsShowData::new);
    }

    public NewsShowData updateNewsById(NewsUpdateData newsUpdateData) {
        News news = new News();
        Optional<News> info = this.newsRepository.findById(newsUpdateData.id());
        if (info.isPresent()) {
            news.setId(newsUpdateData.id());
            news.setTitle(newsUpdateData.title());
            news.setBody(newsUpdateData.body());
            this.newsRepository.save(news);
            return new NewsShowData(news);
        } return null;
    }

    public boolean hideNews(Long id){
        News news = this.newsRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        if (news != null){ news.setActive(false);
            return true; } else { return false; }
    }

    public boolean recoverNews(Long id) {
        News news = this.newsRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        if (news != null) {
            news.setActive(true);
            return true;
        } else {
            return false;
        }
    }

    public boolean toggleNews (Long id) {
        News nToggle = this.newsRepository.findById(id).orElse(null);
        if (nToggle != null) {
            nToggle.setActive(!nToggle.isActive());
            this.newsRepository.save(nToggle);
            return true;
        } else {
            return false;
        }
    }

}
