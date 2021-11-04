package LibraryWebApp.Library.Repository;


import LibraryWebApp.Library.Entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface BookRepository extends JpaRepository<Books,Long> {
    @Query(value="SELECT * FROM books WHERE books.author LIKE %?1%"
            + " OR books.title LIKE %?1%", nativeQuery=true)
    public List<Books> search(String keyword);
}
