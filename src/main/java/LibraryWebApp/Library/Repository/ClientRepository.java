package LibraryWebApp.Library.Repository;

import LibraryWebApp.Library.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface  ClientRepository extends JpaRepository<Client, Long> {

}
