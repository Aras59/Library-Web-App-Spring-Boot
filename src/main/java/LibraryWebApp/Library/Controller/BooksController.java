package LibraryWebApp.Library.Controller;

import LibraryWebApp.Library.Entity.Books;
import LibraryWebApp.Library.Service.BooksServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BooksController {
    private final BooksServiceImpl bookServiceImpl;

    @Autowired
    public BooksController(BooksServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

}
