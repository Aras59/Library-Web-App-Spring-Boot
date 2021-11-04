package LibraryWebApp.Library.Entity;

import javax.persistence.*;

@Entity
@Table(name = "bookorder")
public class BookOrder {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long orderId;
    @Column(name = "bookId")
    private Long bookId;
    @Column(name = "clientId")
    private Long clientId;

    public BookOrder(){

    }

    public BookOrder(Long orderId, Long bookId, Long clientId) {
        this.orderId = orderId;
        this.bookId = bookId;
        this.clientId = clientId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
