package com.codeup.codeupspringblog.models;


import com.mysql.cj.protocol.ColumnDefinition;
import jakarta.persistence.*;

@Entity
@Table(name = "ad_images")
public class AdImages {
    @Id //establish primary key
    //Auto increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "TEXT")
    private String imageURL;

    @ManyToOne
    @JoinColumn(name = "ad_ad")
    private Ad ad;

    public AdImages(long id, String imageURL) {
        this.id = id;
        this.imageURL = imageURL;
    }

    public AdImages() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
