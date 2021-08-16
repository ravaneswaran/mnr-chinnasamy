package com.mnrc.administration.controllers.rest;

import com.mnrc.administration.controllers.mvc.MNRCAdministrationMvcController;

import java.util.List;

public abstract class MNRCAdministrationRESTController extends MNRCAdministrationMvcController {

    @Override
    protected List<String> getMandatoryFields() {
        return null;
    }
}
