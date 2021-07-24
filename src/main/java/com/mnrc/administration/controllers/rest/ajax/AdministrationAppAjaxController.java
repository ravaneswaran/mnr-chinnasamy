package com.mnrc.administration.controllers.rest.ajax;

import com.mnrc.administration.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/ajax/administration-app")
public class AdministrationAppAjaxController extends AbstractAjaxController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/access/for-user-role/{userRoleId}")
    public ResponseEntity<?> toggleCanAccessAdministrationApp(
            @PathVariable(name = "userRoleId") String userRoleId,
            @RequestParam(name = "canAccessAdministrationApp") boolean canAccessAdministrationApp,
            HttpServletRequest httpServletRequest) {

        System.out.println("-----------------------------------------------------------------");

        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(String.format("{\"response\":\"%s\", \"status\":\"failure\"}", "BAD-REQUEST"));
        }

        try {
            String response = this.userRoleService.toggleCanAccessAdministrationApp(userRoleId, canAccessAdministrationApp);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(String.format("{\"response\":\"%s\", \"status\":\"success\"}",response));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(String.format("{\"response\":\"%s\", \"status\":\"failure\"}", exception.getMessage()));
        }
    }
}
