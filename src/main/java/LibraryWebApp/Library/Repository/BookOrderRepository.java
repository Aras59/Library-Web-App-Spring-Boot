package LibraryWebApp.Library.Repository;

import LibraryWebApp.Library.Entity.BookOrder;
import LibraryWebApp.Library.Entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookOrderRepository extends JpaRepository<BookOrder,Long> {
}
