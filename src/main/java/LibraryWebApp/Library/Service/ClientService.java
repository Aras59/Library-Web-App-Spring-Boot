package LibraryWebApp.Library.Service;

import LibraryWebApp.Library.Entity.Client;
import LibraryWebApp.Library.Exception.ResourceNotFoundException;
import LibraryWebApp.Library.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    private final PasswordEncoder passwordEncoder;
    public String passwordCode(String password){
        return passwordEncoder.encode(password);
    }

    @Autowired
    public ClientService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Client save(Client client){
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        return clientRepository.save(client);
    }

    public List<Client> getClients(){
        return clientRepository.findAll();
    }

    public Map<String,Boolean> deleteClient(@PathVariable(value = "id")Long clientID) throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientID).orElseThrow(()-> new ResourceNotFoundException("Client not found for thsi id"+clientID));
        this.clientRepository.delete(client);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }

    public Client getClientById(Long id) throws ResourceNotFoundException {
        Client client = clientRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Client not found for thsi id"+id));
        return client;
    }
}
