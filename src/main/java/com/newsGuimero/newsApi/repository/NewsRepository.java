package com.newsGuimero.newsApi.repository;
import com.newsGuimero.newsApi.entities.news.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository <News, Long> {

    @Query("Select N From News N where N.title =:title")
    News findByTitle(String title);

}
