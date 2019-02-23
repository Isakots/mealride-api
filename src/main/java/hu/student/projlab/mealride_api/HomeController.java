package hu.student.projlab.mealride_api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/swagger-ui")
    public String SwaggerUI() {
        return "redirect:swagger-ui.html";
    }
}