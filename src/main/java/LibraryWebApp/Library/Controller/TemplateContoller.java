package LibraryWebApp.Library.Controller;

import LibraryWebApp.Library.Entity.BookOrder;
import LibraryWebApp.Library.Entity.Books;
import LibraryWebApp.Library.Entity.Client;
import LibraryWebApp.Library.Exception.ResourceNotFoundException;
import LibraryWebApp.Library.Repository.BookRepository;
import LibraryWebApp.Library.Service.BookOrderService;
import LibraryWebApp.Library.Service.BooksServiceImpl;
import LibraryWebApp.Library.Service.ClientService;
import LibraryWebApp.Library.authentication.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.print.Book;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/")
public class TemplateContoller {

    public static String uploadDirectory = "C:\\Users\\micha\\OneDrive\\Pulpit\\ZAI\\src\\main\\resources\\static\\img";

    @Autowired
    private ClientService clientService;

    @Autowired
    private BooksServiceImpl booksServiceimpl;

    @Autowired
    private BookOrderService bookOrderService;

    @Autowired
    private BookRepository bookRepository;



    @GetMapping("login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null &&
                (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))
                        || auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER"))))
                return "redirect:/books";
        return "login";
    }

    @GetMapping("books")
    public String books(Model model, @Param("keyword") String keyword,@RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size){

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Books> bookPage;

        if(keyword!=null){
            bookPage = booksServiceimpl.listALL(keyword,PageRequest.of(currentPage - 1, pageSize));
        }else
         bookPage= booksServiceimpl.listALL(null,PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("books",bookPage);
        model.addAttribute("keyword",keyword);

        int totalPage = bookPage.getTotalPages();
        if(totalPage>0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "books";
    }


    @ModelAttribute("Book")
    public Books newBooks(){
        return new Books();
    }

    @GetMapping("addBooks")
    public String showAddBooks(Model model){
        model.addAttribute("book",new Books());
        return "addBooks";
    }

    @GetMapping("deleteUsers")
    public String deleteUsers(Model model){
        List<Client> clientList = clientService.getClients();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser client = (AppUser) auth.getPrincipal();
        String username = client.getUsername();
        int x=0;
        for(Client c:clientList){
            if(c.getLogin().equals(username)){
                x=clientList.indexOf(c);
            }
        }
        clientList.remove(x);

        model.addAttribute("Users",clientList);
        return "deleteUsers";
    }

    @RequestMapping(value = "delete/{id}")
    public String deleteClient(@PathVariable(value = "id")Long clientID) throws ResourceNotFoundException {
        clientService.deleteClient(clientID);
        return "redirect:/deleteUsers";
    }

    @PostMapping("addBooks")
    public String saveBook(@ModelAttribute("Book") Books book,@RequestParam("files") MultipartFile[] files){
        booksServiceimpl.saveOrUpdate(book);
        StringBuilder fileName = new StringBuilder();

        for(MultipartFile file: files){
            Path fileNameAndPath = Paths.get(uploadDirectory,file.getOriginalFilename());
            try {
                Files.write(fileNameAndPath,file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  "redirect:/books";
    }

    @GetMapping("buy/{id}")
    public String showBuy(@PathVariable(value = "id")Long id){
        List<Client> clientList = clientService.getClients();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser client = (AppUser) auth.getPrincipal();
        String username = client.getUsername();
        int x=0;
        BookOrder bookOrder = new BookOrder();
        for(Client c:clientList){
            if(c.getLogin().equals(username)){
                x=clientList.indexOf(c);
            }
        }

        Client c = clientList.get(x);
        bookOrder.setClientId(c.getId());
        bookOrder.setBookId(id);
        System.out.println(bookOrder.getClientId()+bookOrder.getBookId());
        bookOrderService.save(bookOrder);
        return "redirect:/books";
    }

    @GetMapping("sell/{id}")
    public String sellBook(@PathVariable(value = "id")Long id){
        List<BookOrder> bookOrderList = bookOrderService.getOrders();

        Long x=0l;
        for(BookOrder bookO:bookOrderList){
            if(bookO.getBookId()==id){
                x=bookO.getOrderId();
                break;
            }
        }

        bookOrderService.delete(x);

        return "redirect:/myBooks?succes";
    }

    @GetMapping("book/buy/{id}")
    public String showBuy2(@PathVariable(value = "id")Long id){
        List<Client> clientList = clientService.getClients();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser client = (AppUser) auth.getPrincipal();
        String username = client.getUsername();
        int x=0;
        BookOrder bookOrder = new BookOrder();
        for(Client c:clientList){
            if(c.getLogin().equals(username)){
                x=clientList.indexOf(c);
            }
        }

        Client c = clientList.get(x);
        bookOrder.setClientId(c.getId());
        bookOrder.setBookId(id);
        System.out.println(bookOrder.getClientId()+bookOrder.getBookId());
        bookOrderService.save(bookOrder);
        return "redirect:/books";
    }


    @ModelAttribute("client")
    public Client newClient(){
        return new Client();
    }

    @GetMapping("register")
    public String showRegister(Model model)
    {
        model.addAttribute("client",new Client());
        return "register";
    }

    @GetMapping("myBooks")
    public String showMyBooks(Model model){
        List<Client> clientList = clientService.getClients();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser client = (AppUser) auth.getPrincipal();
        String username = client.getUsername();
        int x=0;

        BookOrder bookOrder = new BookOrder();
        for(Client c:clientList){
            if(c.getLogin().equals(username)){
                x=clientList.indexOf(c);
            }
        }
        Client client1 = clientList.get(x);
        Long id = client1.getId();
        List<BookOrder> bookOrderList = bookOrderService.getOrders();
        List<BookOrder> bookOrders = new ArrayList<>();
        for(BookOrder c: bookOrderList){
            if(c.getClientId()==id){
                bookOrders.add(c);
            }
        }
        List<Books> booksList = bookRepository.findAll();
        List<Books> books = new ArrayList<>();
        for(Books book: booksList){
            for(BookOrder bo:bookOrders){
                if(book.getId()==bo.getBookId()){
                    books.add(book);
                }
            }

        }
        model.addAttribute("books",books);
        return "mybooks";
    }

    @RequestMapping(value = "book/{id}")
    public String showBook(@PathVariable(value = "id")Long id,Model model) throws ResourceNotFoundException {
        Books book = booksServiceimpl.getById(id);
        model.addAttribute("Book",book);
        return "book";
    }

    @GetMapping(value = "edit/{id}")
    public String showEdit(@PathVariable(value = "id")Long id,Model model) throws ResourceNotFoundException {
        Client client = clientService.getClientById(id);
        model.addAttribute("user",client);
        model.addAttribute("client",newClient());
        return "editUser";
    }



    @PostMapping (value = "editUser/{id}")
    public String EditUser(@PathVariable(value = "id")Long id,@ModelAttribute("client") @Valid Client client) throws ResourceNotFoundException
    {
        List<Client> clientList = clientService.getClients();
        for(Client c:clientList){
            if(c.getLogin().equals(client.getLogin())){
                return "redirect:/books?errordata";
            }
        }
        if(client.getLogin().length()>15||client.getLogin().length()<5||client.getPassword().length()>15||client.getPassword().length()<5){
            return "redirect:/books?error";
        }
        Client user = clientService.getClientById(id);
        user.setPassword(client.getPassword());
        user.setLogin(client.getLogin());
        user.setName(client.getName());
        clientService.save(user);
        return "redirect:/books?succes";
    }



    @PostMapping("register")
    public String registerUser(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "redirect:/register?error";
        }
        if(client.getLogin().length()<5&&client.getPassword().length()<5){
            return "redirect:/register?data";
        }
        List<Client> clientList = clientService.getClients();
        for(Client c:clientList){
            if(c.getLogin().equals(client.getLogin())){
                return "redirect:/register?error";
            }
        }
        clientService.save(client);
        return "redirect:/register?success";
    }

}
