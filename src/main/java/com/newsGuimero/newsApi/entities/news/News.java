package com.newsGuimero.newsApi.entities.news;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Table (name = "noticias")
@Data
@NoArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;
    private boolean active;

    public News(NewsRegisterData newsRegisterData) {
        this.title = newsRegisterData.title();
        this.body = newsRegisterData.body();
        this.active = true;
    }

}
