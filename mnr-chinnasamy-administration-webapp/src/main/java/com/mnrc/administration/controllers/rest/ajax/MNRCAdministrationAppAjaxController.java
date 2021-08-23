package com.mnrc.administration.controllers.rest.ajax;

import com.mnrc.core.forms.UserRoleForm;
import com.mnrc.core.services.UserRoleService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/administration/ajax/administration-app")
public class MNRCAdministrationAppAjaxController extends MNRCAdministrationAjaxController {

    Logger logger = LoggerFactory.getLogger(MNRCAdministrationAppAjaxController.class);

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/access/for-user-role/{userRoleId}")
    public ResponseEntity<?> toggleCanAccessAdministrationApp(
            @PathVariable(name = "userRoleId") String userRoleId,
            @RequestParam(name = "canAccessAdministrationApp") boolean canAccessAdministrationApp,
            HttpServletRequest httpServletRequest) {
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("response", "BAD-REQUEST");
            jsonObject.put("status", "failure");
            this.logger.error(jsonObject.toString());
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(jsonObject.toString());
        }
        try {
            String response = this.userRoleService.toggleCanAccessAdministrationApp(userRoleId, canAccessAdministrationApp);
            UserRoleForm userRoleForm = this.userRoleService.getUserRole(userRoleId);
            if(null != userRoleForm && "1".equals(response)){
                this.logger.info(String.format("the user role <%s> is able to access administration app now...", userRoleForm.getRoleName()));
            } else {
                this.logger.info(String.format("the user role <%s> is not able to access administration app now..", userRoleForm.getRoleName()));
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("response", response);
            jsonObject.put("status", "success");
            this.logger.info(jsonObject.toString());
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jsonObject.toString());
        } catch (Exception exception) {
            this.logger.error(exception.getMessage(), exception);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("response", exception.getMessage());
            jsonObject.put("status", "failure");
            this.logger.error(jsonObject.toString());
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(jsonObject);
        }
    }
}
