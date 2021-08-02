package com.mnrc.administration.controllers.rest.ajax;


import com.mnrc.administration.services.PaymentGatewayService;
import com.mnrc.administration.ui.forms.PaymentGatewayForm;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/ajax/payment-gateway")
public class PaymentGatewayEndisableAjaxController extends AbstractAjaxController {

    Logger logger = LoggerFactory.getLogger(AdministrationAppAjaxController.class);

    @Autowired
    private PaymentGatewayService paymentGatewayService;

    @GetMapping("/enable/{paymentGatewayId}")
    public ResponseEntity<?> endisablePaymentGateway(
            @PathVariable(name = "paymentGatewayId") String paymentGatewayId,
            HttpServletRequest httpServletRequest){

        if(this.isNotUserLoggedIn(httpServletRequest)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("response", "BAD-REQUEST");
            jsonObject.put("status", "failure");
            this.logger.error(jsonObject.toString());
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(jsonObject.toString());
        }

        try {
            String response = this.paymentGatewayService.enablePaymentGateway(paymentGatewayId);
            List<PaymentGatewayForm> paymentGatewayForms = this.paymentGatewayService.getPaymentGateways();
            for(PaymentGatewayForm paymentGatewayForm : paymentGatewayForms){
                if(!paymentGatewayId.equals(paymentGatewayForm.getPaymentGatewayUUID())){
                    this.paymentGatewayService.disablePaymentGateway(paymentGatewayForm.getPaymentGatewayUUID());
                }
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("response", response);
            jsonObject.put("status", "success");
            this.logger.info(jsonObject.toString());
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jsonObject.toString());
        } catch(Exception exception){
            this.logger.error(exception.getMessage(), exception);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("response", exception.getMessage());
            jsonObject.put("status", "failure");
            this.logger.error(jsonObject.toString());
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(jsonObject);
        }
    }
}