package com.mnrc.administration.controllers.mvc;

import com.mnrc.administration.enums.SessionAttribute;
import com.mnrc.core.entities.UserProfile;
import com.mnrc.core.enums.UserType;
import com.mnrc.core.forms.LoginForm;
import com.mnrc.core.forms.UserForm;
import com.mnrc.core.services.ImageService;
import com.mnrc.core.services.UserProfileService;
import com.mnrc.core.services.UserService;
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
public class UserProfileController extends BaseMVCController {

    Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Override
    protected List<String> getMandatoryFields() {
        return null;
    }

    @GetMapping("/picture-upload")
    public ModelAndView redirectToUserInfo(HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        HttpSession httpSession = httpServletRequest.getSession();
        LoginForm login = (LoginForm)httpSession.getAttribute(SessionAttribute.LOGGED_IN_USER.toString());

        if(UserType.ALMIGHTY.toString().equals(login.getType())){
            return new ModelAndView("redirect:/user/list");
        } else {
            return new ModelAndView(String.format("redirect:/user/info?uuid=%s", login.getUserId()));
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
                UserForm userForm = this.userService.getUserForm(userId);
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("user-info");
                modelAndView.addObject("userForm", userForm);
                modelAndView.addObject("errorMessage", "Profile picture found to be empty...");
                return modelAndView;
            }

            BufferedImage bufferedImage = this.imageService.resizeImageTo_265_By_293(content);

            if(null == bufferedImage){
                String fileName = profilePicture.getOriginalFilename();
                UserForm userForm = this.userService.getUserForm(userId);
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("user-info");
                modelAndView.addObject("userForm", userForm);
                modelAndView.addObject("errorMessage", String.format("Sorry!!! the file(%s) is not a valid one to be your profile pic", fileName));
                return modelAndView;
            }

            content = this.imageService.bufferedImageToByteArray(bufferedImage);
            UserProfile userProfile = this.userProfileService.saveProfilePicture(userId, content);

            if(null != userProfile){
                this.imageService.createTemporaryProfilePicture(userId, content);
                ModelAndView modelAndView = new ModelAndView(String.format("redirect:/user/info?uuid=%s", userId));
                return modelAndView;
            } else {
                UserForm userForm = this.userService.getUserForm(userId);
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("user-info");
                modelAndView.addObject("userForm", userForm);
                modelAndView.addObject("errorMessage", "Unable to save the profile picture...");
                return modelAndView;
            }

        } catch (IOException e) {
            this.logger.error(e.getMessage(), e);
            UserForm userForm = this.userService.getUserForm(userId);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("user-info");
            modelAndView.addObject("userForm", userForm);
            modelAndView.addObject("errorMessage", "Unable to read profile picture...");
            return modelAndView;
        }
    }
}
