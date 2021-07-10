package com.mnrc.controllers.mvc;

import com.mnrc.controllers.BaseController;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserProfileController extends BaseController {

    @Override
    protected List<String> getMandatoryFields() {
        return null;
    }

}
