package LibraryWebApp.Library.Controller;
import LibraryWebApp.Library.Entity.Client;
import LibraryWebApp.Library.Exception.ResourceNotFoundException;
import LibraryWebApp.Library.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;


    @GetMapping("/client")
    public String getAllClient(Model model){
        List<Client> clients = this.clientRepository.findAll();
        System.out.println(clients.get(1).getLogin());
        model.addAttribute("clients",clients);
        return "hello";
    }




    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/client/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable(value = "id")Long clientID) throws ResourceNotFoundException{
        Client client = clientRepository.findById(clientID).orElseThrow(()-> new ResourceNotFoundException("Client not found for this id"+clientID));
        return ResponseEntity.ok().body(client);
    }

    @PostMapping("client")
    public Client createClient(@RequestBody Client client){
        return this.clientRepository.save(client);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/client/{id}")
    public ResponseEntity<Client> updateClinet(@PathVariable(value = "id")Long clientID, @Validated @RequestBody Client clientDetails) throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientID).orElseThrow(()-> new ResourceNotFoundException("Client not found for this id"+clientID));
        client.setName(clientDetails.getName());
        client.setPassword(clientDetails.getPassword());
        client.setLogin(clientDetails.getLogin());
        client.setUserType(clientDetails.getUserType());
        return ResponseEntity.ok(this.clientRepository.save(client));
    }

    @DeleteMapping("client/{id}")
    public Map<String,Boolean> deleteClient(@PathVariable(value = "id")Long clientID) throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientID).orElseThrow(()-> new ResourceNotFoundException("Client not found for thsi id"+clientID));
        this.clientRepository.delete(client);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }
}
