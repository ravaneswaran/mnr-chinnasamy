package com.mnrc.administration.controllers.mvc;

import com.mnrc.administration.enums.MNRCAdministrationSessionAttribute;
import com.mnrc.core.forms.LoginForm;
import com.mnrc.core.forms.PaymentGatewayForm;
import com.mnrc.core.services.PaymentGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MNRCAdministrationPaymentGatewayController extends MNRCAdministrationMvcController {

    @Autowired
    private PaymentGatewayService paymentGatewayService;

    @Override
    protected List<String> getMandatoryFields() {
        List<String> mandatoryFields = new ArrayList<>();
        mandatoryFields.add("name");
        mandatoryFields.add("merchantId");
        mandatoryFields.add("paymentGatewayKey");
        mandatoryFields.add("paymentGatewaySecret");
        mandatoryFields.add("callbackUrl");
        return mandatoryFields;
    }

    @GetMapping("/administration/payment-gateway")
    public ModelAndView paymentGatewayLandingPage(HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/administration");
        }

        List<PaymentGatewayForm> paymentGatewayForms = this.paymentGatewayService.getPaymentGateways();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/payment-gateway");
        modelAndView.addObject("paymentGatewayForms", paymentGatewayForms);
        return modelAndView;
    }

    @PostMapping("/administration/payment-gateway/add")
    public ModelAndView addPaymentGateway(@Valid PaymentGatewayForm paymentGatewayForm, BindingResult bindingResult, HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/administration");
        }

        if(!bindingResult.hasErrors()){
            LoginForm loginForm = (LoginForm) httpServletRequest.getSession().getAttribute(MNRCAdministrationSessionAttribute.LOGGED_IN_USER.toString());
            String userFullName = String.format("%s %s", loginForm.getFirstName(), loginForm.getLastName()).trim();
            try {
                this.paymentGatewayService.addPaymentGateway(paymentGatewayForm.getName(), paymentGatewayForm.getMerchantId(), paymentGatewayForm.getPaymentGatewayKey(), paymentGatewayForm.getPaymentGatewaySecret(), paymentGatewayForm.getCallbackUrl(), userFullName);

                List<PaymentGatewayForm> paymentGatewayForms = this.paymentGatewayService.getPaymentGateways();
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("/payment-gateway");
                modelAndView.addObject("paymentGatewayForms", paymentGatewayForms);
                return modelAndView;
            } catch (Exception e) {
                List<PaymentGatewayForm> paymentGatewayForms = this.paymentGatewayService.getPaymentGateways();
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("/payment-gateway");
                modelAndView.addObject("paymentGatewayForms", paymentGatewayForms);
                modelAndView.addObject("errorMessage", "Unable to save payment gateway...");
                return modelAndView;
            }
        } else {
            List<PaymentGatewayForm> paymentGatewayForms = this.paymentGatewayService.getPaymentGateways();
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/payment-gateway");
            modelAndView.addObject("paymentGatewayForm", paymentGatewayForm);
            modelAndView.addObject("paymentGatewayForms", paymentGatewayForms);
            modelAndView.addObject("errorMessage", this.getError(bindingResult));
            return modelAndView;
        }
    }

    @GetMapping("/administration/payment-gateway/edit")
    public ModelAndView editPaymentGateway(@RequestParam(name = "uuid") String paymentGatewayUUID, HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/administration");
        }

        List<PaymentGatewayForm> paymentGatewayForms = this.paymentGatewayService.getPaymentGateways();

        if(null == paymentGatewayUUID || "".equals(paymentGatewayUUID)){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/payment-gateway");
            modelAndView.addObject("paymentGatewayForms", paymentGatewayForms);
            modelAndView.addObject("errorMessage", "Invalid uuid parameter...");

            return modelAndView;
        }

        try {
            PaymentGatewayForm paymentGatewayForm = this.paymentGatewayService.getPaymentGateway(paymentGatewayUUID);
            paymentGatewayForm.setAction("/payment-gateway/edit");

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/payment-gateway");
            modelAndView.addObject("paymentGatewayForm", paymentGatewayForm);
            modelAndView.addObject("paymentGatewayForms", paymentGatewayForms);

            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/payment-gateway");
            modelAndView.addObject("paymentGatewayForms", paymentGatewayForms);
            modelAndView.addObject("errorMessage", e.getMessage());

            return modelAndView;
        }
    }

    @PostMapping("/administration/payment-gateway/edit")
    public ModelAndView editPaymentGateway(@Valid PaymentGatewayForm paymentGatewayForm, BindingResult bindingResult, HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/administration");
        }

        if(!bindingResult.hasErrors()){
            LoginForm login = (LoginForm) httpServletRequest.getSession().getAttribute(MNRCAdministrationSessionAttribute.LOGGED_IN_USER.toString());
            try {
                String userFullName = String.format("%s, %s", login.getFirstName(), login.getLastName());
                this.paymentGatewayService.editPaymentGateway(paymentGatewayForm.getPaymentGatewayUUID(), paymentGatewayForm.getName(), paymentGatewayForm.getMerchantId(), paymentGatewayForm.getPaymentGatewayKey(), paymentGatewayForm.getPaymentGatewaySecret(), paymentGatewayForm.getCallbackUrl(), userFullName);
                List<PaymentGatewayForm> paymentGatewayForms = this.paymentGatewayService.getPaymentGateways();
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("/payment-gateway");
                modelAndView.addObject("paymentGatewayForm", paymentGatewayForm);
                modelAndView.addObject("paymentGatewayForms", paymentGatewayForms);
                return modelAndView;
            } catch (Exception exception) {
                List<PaymentGatewayForm> paymentGatewayForms = this.paymentGatewayService.getPaymentGateways();
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("/payment-gateway");
                modelAndView.addObject("paymentGatewayForm", paymentGatewayForm);
                modelAndView.addObject("paymentGatewayForms", paymentGatewayForms);
                return modelAndView;
            }
        } else {
            List<PaymentGatewayForm> paymentGatewayForms = this.paymentGatewayService.getPaymentGateways();
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/payment-gateway");
            modelAndView.addObject("paymentGatewayForm", paymentGatewayForm);
            modelAndView.addObject("paymentGatewayForms", paymentGatewayForms);
            modelAndView.addObject("errorMessage", this.getError(bindingResult));
            return modelAndView;
        }
    }

    @GetMapping("/administration/payment-gateway/delete")
    public ModelAndView deletePaymentGateway(@RequestParam(name = "uuid") String paymentGatewayUUID, HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/administration");
        }

        if(null == paymentGatewayUUID || "".equals(paymentGatewayUUID)){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/payment-gateway");
            List<PaymentGatewayForm> paymentGatewayForms = this.paymentGatewayService.getPaymentGateways();
            modelAndView.addObject("userRoleForms", paymentGatewayForms);
            modelAndView.addObject("errorMessage", "Invalid uuid parameter...");

            return modelAndView;
        }

        try {
            this.paymentGatewayService.deletePaymentGateway(paymentGatewayUUID);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("redirect:/administration/payment-gateway?#payment-gateway-listing");
            return modelAndView;
        } catch (Exception e) {
            List<PaymentGatewayForm> paymentGatewayForms = this.paymentGatewayService.getPaymentGateways();
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/payment-gateway");
            modelAndView.addObject("userRoleForms", paymentGatewayForms);
            modelAndView.addObject("errorMessage", e.getMessage());

            return modelAndView;
        }
    }
}
