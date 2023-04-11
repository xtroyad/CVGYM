package com.cvgym.CVGYM.error;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        // Get HTTP status code and error message
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        model.addAttribute("status", status);

        if(status.toString().equals("404")){
            model.addAttribute("message", "Página no encontrada");
        }else if (status.toString().equals("500")){
        model.addAttribute("message", "Error en el servidor");
        }else {
            model.addAttribute("message", "Error desconocido");
        }

        // Return the generic template for errorss
        return "error/error";
    }

}
