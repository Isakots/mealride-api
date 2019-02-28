package hu.student.projlab.mealride_api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/swagger-ui")
    public String SwaggerUI() {
        return "redirect:swagger-ui.html";
    }
}