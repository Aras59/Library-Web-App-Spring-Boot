package LibraryWebApp.Library.Service;

import LibraryWebApp.Library.Entity.Books;
import LibraryWebApp.Library.Repository.BookRepository;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BooksServiceImpl {
    private final BookRepository bookRepository;

    @Autowired
    public BooksServiceImpl(BookRepository bookRepository) {

        this.bookRepository = bookRepository;
    }

    public Page<Books> listALL(String keyword, Pageable pageable) {
        int size = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage*size;
        List<Books> booksList;
        if(keyword!=null){
            booksList = bookRepository.search(keyword);
        }else
        booksList=bookRepository.findAll();

        List<Books> list;

        if(booksList.size()<startItem){
            list = Collections.emptyList();
        }else{
            int toIndex = Math.min(startItem+size,booksList.size());
            list = booksList.subList(startItem,toIndex);
        }

        Page<Books> booksPage = new PageImpl<Books>(list,PageRequest.of(currentPage,size),booksList.size());

        return booksPage;
    }

    public Books getById(Long id) {
        List<Books> booksList = new ArrayList<>();
        bookRepository.findAll().forEach(booksList::add);
        for(Books b:booksList){
            if(b.getId()==id){
                return b;
            }
        }
        return new Books();
    }

    public Books saveOrUpdate(Books books) {
        bookRepository.save(books);
        return books;
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

}
