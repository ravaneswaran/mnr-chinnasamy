package com.mnrc.controllers.mvc;

import com.mnrc.controllers.BaseController;
import com.mnrc.enums.SessionAttribute;
import com.mnrc.enums.UserType;
import com.mnrc.models.UserProfile;
import com.mnrc.services.EmployeeService;
import com.mnrc.services.ImageService;
import com.mnrc.services.UserProfileService;
import com.mnrc.ui.forms.Employee;
import com.mnrc.ui.forms.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user/profile")
public class UserProfileController extends BaseController {

    Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ImageService imageService;

    @Override
    protected List<String> getMandatoryFields() {
        return null;
    }

    @GetMapping("/picture-upload")
    public ModelAndView redirectToEmployeeInfo(HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        HttpSession httpSession = httpServletRequest.getSession();
        Login login = (Login)httpSession.getAttribute(SessionAttribute.LOGGED_IN_USER.toString());

        if(UserType.ALMIGHTY.toString().equals(login.getType())){
            return new ModelAndView("redirect:/employee/list");
        } else {
            return new ModelAndView(String.format("redirect:/employee/info?uuid=%s", login.getUserId()));
        }
    }

    @PostMapping("/picture-upload")
    public ModelAndView saveProfilePicture(HttpServletRequest httpServletRequest, @RequestParam("userId") String userId, @RequestParam(name = "browseProfilePicture") MultipartFile profilePicture){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        try {
            byte[] content = profilePicture.getBytes();

            if(null != content && 0 == content.length){
                Employee employee = this.employeeService.getEmployee(userId);
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("employee-info");
                modelAndView.addObject("employee", employee);
                modelAndView.addObject("errorMessage", "Profile picture found to be empty...");
                return modelAndView;
            }

            BufferedImage bufferedImage = this.imageService.resizeImageTo_265_By_293(content);

            if(null == bufferedImage){
                String fileName = profilePicture.getOriginalFilename();
                Employee employee = this.employeeService.getEmployee(userId);
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("employee-info");
                modelAndView.addObject("employee", employee);
                modelAndView.addObject("errorMessage", String.format("Sorry!!! the file(%s) is not a valid one to be your profile pic", fileName));
                return modelAndView;
            }

            content = this.imageService.bufferedImageToByteArray(bufferedImage);
            UserProfile userProfile = this.userProfileService.saveProfilePicture(userId, content);

            if(null != userProfile){
                this.imageService.createTemporaryProfilePicture(userId, content);
                ModelAndView modelAndView = new ModelAndView(String.format("redirect:/employee/info?uuid=%s", userId));
                return modelAndView;
            } else {
                Employee employee = this.employeeService.getEmployee(userId);
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("employee-info");
                modelAndView.addObject("employee", employee);
                modelAndView.addObject("errorMessage", "Unable to save the profile picture...");
                return modelAndView;
            }

        } catch (IOException e) {
            this.logger.error(e.getMessage(), e);
            Employee employee = this.employeeService.getEmployee(userId);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("employee-info");
            modelAndView.addObject("employee", employee);
            modelAndView.addObject("errorMessage", "Unable to read profile picture...");
            return modelAndView;
        }
    }
}
