package LibraryWebApp.Library.authentication;

import java.util.Optional;

public interface AppUserDAO {
    public Optional<AppUser> selectAppUserByUserName(String UserName);
}
