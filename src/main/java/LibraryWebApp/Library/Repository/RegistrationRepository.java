package LibraryWebApp.Library.Repository;

import LibraryWebApp.Library.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Client, Long> {
}
