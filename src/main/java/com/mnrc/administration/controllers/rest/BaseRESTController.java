package com.mnrc.administration.controllers.rest;

import com.mnrc.administration.controllers.mvc.BaseMVCController;

import java.util.List;

public abstract class BaseRESTController extends BaseMVCController {

    @Override
    protected List<String> getMandatoryFields() {
        return null;
    }
}
