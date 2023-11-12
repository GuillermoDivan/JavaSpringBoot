package com.newsGuimero.newsApi.controllers;
import com.newsGuimero.newsApi.entities.news.*;
import com.newsGuimero.newsApi.services.NewsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    //@Autowired
    private final NewsService newsService;

    @PostMapping("/")
    @Transactional
    public NewsShowData registerNews (@RequestBody NewsRegisterData newsRegisterData) {
        return this.newsService.saveNews(newsRegisterData);
    }

    @GetMapping("/id/{id}")
    public NewsShowData showNewsById(@PathVariable Long id) {
        return this.newsService.findNewsById(id);
    }

    @GetMapping("/title/{title}")
    public NewsShowData showNewsByTitle(@PathVariable String title) {
        return this.newsService.findNewsByTitle(title);
    }

    @GetMapping("/all")
    public Page<NewsShowData> showAllNews(@PageableDefault(size = 3) Pageable paging) {
        return this.newsService.findAllNews(paging);
    }

    @PutMapping("/update/{id}")
    @Transactional
    public NewsShowData updateNewsById(@RequestBody NewsUpdateData newsUpdateData) {
        return this.newsService.updateNewsById(newsUpdateData);
    }

    @PutMapping("/hide/{id}")
    @Transactional
    public boolean hideNews(@PathVariable Long id) {
        boolean newsToHide = newsService.hideNews(id);
        return newsToHide;
    }

    @PutMapping("/reactive/{id}")
    @Transactional
    public boolean reactiveNews(@PathVariable Long id) {
        boolean newsToReactive = newsService.recoverNews(id);
        return newsToReactive;
    }

    @DeleteMapping("/id/{id}")
    @Transactional
    public boolean toggleNews(@PathVariable Long id) {
        boolean newsToToggle = newsService.toggleNews(id);
        return newsToToggle;
        }
    }
