package tunyaa.desab.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author sergey
 */
@Controller
@RequestMapping("/")
public class HomeController {
    
    @GetMapping
    public String getHome(){
        return "home";
    }
}
