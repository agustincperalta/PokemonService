package org.bankaya.pokemonservice.infrastructure.config.interceptors;

import java.util.Locale;
import javax.xml.namespace.QName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.interceptor.EndpointInterceptorAdapter;
import org.springframework.ws.soap.SoapBody;
import org.springframework.ws.soap.SoapEnvelope;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.SoapMessage;
@Slf4j
public class CustomSoapFaultInterceptor extends EndpointInterceptorAdapter {
  @Override
  public boolean handleFault(MessageContext messageContext, Object endpoint) {
    log.info("Intercepted a fault...");

    SoapBody soapBody = getSoapBody(messageContext);
    SoapFault soapFault = soapBody.getFault();

    log.error(soapFault.getFaultStringOrReason());

    SoapFaultDetail detail = soapFault.addFaultDetail();
    QName detailElementQName = new QName("http://bankaya.com/soap/pokemon/v1", "ErrorDetail");

    detail.addFaultDetailElement(detailElementQName).addText("Error occurred while invoking SOAP service : " + soapFault.getFaultStringOrReason());

    return true;
  }

  private SoapBody getSoapBody(MessageContext messageContext) {
    SoapMessage soapMessage = (SoapMessage) messageContext.getResponse();
    SoapEnvelope soapEnvelope = soapMessage.getEnvelope();
    return soapEnvelope.getBody();
  }
}