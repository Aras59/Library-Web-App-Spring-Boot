package LibraryWebApp.Library.Entity;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Books {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Title")
    private String Title;
    @Column(name = "Author")
    private String Author;
    @Column(name = "AmoutBorrow")
    private Integer Borrow;
    @Column(name = "AmoutTotal")
    private Integer Total;
    @Column(name = "img")
    private String Img;
    @Column(name = "Describe")
    private String Describe;

    public Books(){

    };

    public Books(Long id, String title, String author, Integer borrow, Integer total, String describe, String Img1) {
        this.id = id;
        Title = title;
        Author = author;
        Borrow = borrow;
        Total = total;
        Describe = describe;
        Img = Img1;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Integer getBorrow() {
        return Borrow;
    }

    public void setBorrow(Integer borrow) {
        Borrow = borrow;
    }

    public Integer getTotal() {
        return Total;
    }

    public void setTotal(Integer total) {
        Total = total;
    }

    public String getDescribe() {
        return Describe;
    }

    public void setDescribe(String describe) {
        Describe = describe;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }
}
