package LibraryWebApp.Library.Controller;

import LibraryWebApp.Library.Entity.Client;
import LibraryWebApp.Library.Service.RegistratrionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {
    @Autowired
    private RegistratrionService service;

//    @PostMapping("registerUser")
//    public Client registerUser(@RequestBody Client client) throws Exception {
//        String tempLogin = client.getLogin();
//        Client clientobj = null;
//        if(tempLogin!=null && !tempLogin.equals("")){
//            clientobj = service.fetchClientByLogin(tempLogin);
//            if(clientobj!=null){
//                throw new  Exception("User "+tempLogin+" already exist!");
//            }
//        }
//        clientobj = null;
//        clientobj = service.saveClient(client);
//        if(clientobj.getUserType() == null) clientobj.setUserType("User");
//        return clientobj;
//
//    }

    @PostMapping("loginUser")
    public Client loginUser(@RequestBody Client client) throws Exception {
        String tempLogin = client.getLogin();
        String tempPassword = client.getPassword();
        Client tempClient = null;
        if(tempLogin != null && tempPassword != null){
            tempClient = service.fetchClientByLoginAndPassword(tempLogin,tempPassword);
        }
        if(tempClient == null) throw new Exception("User "+tempLogin+" not exist!");
        return tempClient;

    }
}
