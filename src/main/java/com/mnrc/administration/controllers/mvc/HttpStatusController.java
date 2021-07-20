package com.mnrc.administration.controllers.mvc;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HttpStatusController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            String view = "";

            switch (statusCode){
                case 404:
                    view = "404";
                    break;
                case 500:
                    view =  "500";
                    break;
                case 403:
                    view =  "403";
                    break;
            }
            return view;
        } else {
            return "error";
        }
    }
}