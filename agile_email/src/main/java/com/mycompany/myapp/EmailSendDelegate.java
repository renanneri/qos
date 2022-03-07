package com.mycompany.myapp;

import com.mycompany.myapp.service.MailService;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Locale;

@Component
public class EmailSendDelegate implements JavaDelegate {

    @Autowired
    MailService mailService;

    @Autowired
    SpringTemplateEngine templateEngine;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {      
        String to = "xxx@gmail.com";
        String subject = "[AgileKip] Testando envio de email " ;
        Context context = new Context(Locale.getDefault());
        String content = templateEngine.process("mailSendTemplate/mailSendTemplate", context);
        mailService.sendEmail(to, subject, content, false, true);
    }
}