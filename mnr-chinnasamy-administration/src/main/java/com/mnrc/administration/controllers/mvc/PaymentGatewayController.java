package com.mnrc.administration.controllers.mvc;

import com.mnrc.administration.enums.SessionAttribute;
import com.mnrc.administration.services.PaymentGatewayService;
import com.mnrc.administration.ui.forms.LoginForm;
import com.mnrc.administration.ui.forms.PaymentGatewayForm;
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
public class PaymentGatewayController extends BaseMVCController{

    @Autowired
    private PaymentGatewayService paymentGatewayService;

    @Override
    protected List<String> getMandatoryFields() {
        List<String> mandatoryFields = new ArrayList<>();
        mandatoryFields.add("name");
        mandatoryFields.add("merchantId");
        mandatoryFields.add("paymentGatewayKey");
        mandatoryFields.add("paymentGatewaySecret");
        mandatoryFields.add("applicationPaymentSuccessPage");
        mandatoryFields.add("applicationPaymentFailurePage");
        return mandatoryFields;
    }

    @GetMapping("/payment-gateway")
    public ModelAndView paymentGatewayLandingPage(HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        List<PaymentGatewayForm> paymentGatewayForms = this.paymentGatewayService.getPaymentGateways();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/payment-gateway");
        modelAndView.addObject("paymentGatewayForms", paymentGatewayForms);
        return modelAndView;
    }

    @PostMapping("/payment-gateway/add")
    public ModelAndView addPaymentGateway(@Valid PaymentGatewayForm paymentGatewayForm, BindingResult bindingResult, HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        if(!bindingResult.hasErrors()){
            LoginForm loginForm = (LoginForm) httpServletRequest.getSession().getAttribute(SessionAttribute.LOGGED_IN_USER.toString());
            String userFullName = String.format("%s %s", loginForm.getFirstName(), loginForm.getLastName()).trim();

            PaymentGatewayForm response = this.paymentGatewayService.addPaymentGateway(paymentGatewayForm.getName(), paymentGatewayForm.getMerchantId(), paymentGatewayForm.getPaymentGatewayKey(), paymentGatewayForm.getPaymentGatewaySecret(), paymentGatewayForm.getCallbackUrl(), userFullName);

            if(null != response){
                List<PaymentGatewayForm> paymentGatewayForms = this.paymentGatewayService.getPaymentGateways();
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("/payment-gateway");
                modelAndView.addObject("paymentGatewayForms", paymentGatewayForms);
                return modelAndView;
            } else {
                List<PaymentGatewayForm> paymentGatewayForms = this.paymentGatewayService.getPaymentGateways();
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("/payment-gateway");
                modelAndView.addObject("paymentGatewayForms", paymentGatewayForms);
                modelAndView.addObject("errorMessage", "Unable to save payment gateway...");
                return modelAndView;
            }
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/payment-gateway");
            modelAndView.addObject("paymentGatewayForm", paymentGatewayForm);
            modelAndView.addObject("errorMessage", this.getError(bindingResult));
            return modelAndView;
        }
    }

    @GetMapping("/payment-gateway/edit")
    public ModelAndView editPaymentGateway(@RequestParam(name = "uuid") String paymentGatewayUUID, HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
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

    @PostMapping("/payment-gateway/edit")
    public ModelAndView editRole(@Valid PaymentGatewayForm paymentGatewayForm, BindingResult bindingResult, HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        if(!bindingResult.hasErrors()){
            LoginForm login = (LoginForm) httpServletRequest.getSession().getAttribute(SessionAttribute.LOGGED_IN_USER.toString());
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
}
