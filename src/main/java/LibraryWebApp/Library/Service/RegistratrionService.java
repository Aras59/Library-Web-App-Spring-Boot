package LibraryWebApp.Library.Service;

import LibraryWebApp.Library.Entity.Client;
import LibraryWebApp.Library.Repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistratrionService {

    @Autowired
    private RegistrationRepository registrationRepository;

    public Client saveClient(Client client){
         return registrationRepository.save(client);
    }


    public Client fetchClientByLogin(String login){
        List<Client> client =  registrationRepository.findAll();
        for(Client t : client){
            if(t.getLogin().equals(login)){
                return t;
            }
        }
        return null;
    }

    public Client fetchClientByLoginAndPassword(String login,String password){
        List<Client> client =  registrationRepository.findAll();
        for(Client t : client){
            if(t.getLogin() != null && t.getPassword() != null){
                if(login.equals(t.getLogin()) && password.equals(t.getPassword())){
                    return t;
                }
            }

        }
        return null;
    }

}
