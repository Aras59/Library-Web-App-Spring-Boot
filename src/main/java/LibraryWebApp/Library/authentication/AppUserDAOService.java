package LibraryWebApp.Library.authentication;

import LibraryWebApp.Library.Entity.Client;
import LibraryWebApp.Library.Repository.ClientRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static LibraryWebApp.Library.Security.UserRole.ADMIN;
import static LibraryWebApp.Library.Security.UserRole.USER;

@Repository("DaoService")
public class AppUserDAOService implements AppUserDAO {
    @Autowired
    private ClientRepository clientRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserDAOService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<AppUser> selectAppUserByUserName(String UserName) {
        return getAppUser().stream().filter(appUser -> UserName.equals(appUser.getUsername())).findFirst();
    }

    private List<AppUser> getAppUser(){
        List<AppUser> appUsers = Lists.newArrayList();
        Client client;
        List<Client> clientList = clientRepository.findAll();
        for(Client c:clientList){
            AppUser appUser;
            if(c.getUserType().equals("Admin".toString())){
                appUser = new AppUser(ADMIN.getGrantedAuthorities(),c.getPassword(),c.getLogin(),true,true,true,true);
            }else{
                appUser = new AppUser(USER.getGrantedAuthorities(),c.getPassword(),c.getLogin(),true,true,true,true);
            }
            appUsers.add(appUser);
        }
        return appUsers;
    }
}
