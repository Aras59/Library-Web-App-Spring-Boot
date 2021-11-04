package LibraryWebApp.Library.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileUploadController {
    public static String uploadDirectory = "C:\\Users\\micha\\OneDrive\\Pulpit\\ZAI\\src\\main\\resources\\static\\img";

    @RequestMapping("/upload")
    public String Upload(Model model, @RequestParam("files") MultipartFile[] files)  {
        StringBuilder fileName = new StringBuilder();
        for(MultipartFile file: files){
            Path fileNameAndPath = Paths.get(uploadDirectory,file.getOriginalFilename());
            fileName.append(file.getOriginalFilename());
            try {
                Files.write(fileNameAndPath,file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            model.addAttribute("msg","Succesfully uploaded files"+fileName);
        }
        return "redirect:/books";
    }
}
