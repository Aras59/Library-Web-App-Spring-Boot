package LibraryWebApp.Library.Service;


import LibraryWebApp.Library.Entity.BookOrder;
import LibraryWebApp.Library.Entity.Client;
import LibraryWebApp.Library.Repository.BookOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookOrderService {

    private final BookOrderRepository bookOrderRepository;

    @Autowired
    public BookOrderService(BookOrderRepository bookOrderRepository) {
        this.bookOrderRepository = bookOrderRepository;
    }

    public BookOrder save(BookOrder bookOrder){
        return bookOrderRepository.save(bookOrder);
    }

    public List<BookOrder> getOrders(){
        return bookOrderRepository.findAll();
    }

    public void delete(Long id){
        bookOrderRepository.deleteById(id);
    }
}
