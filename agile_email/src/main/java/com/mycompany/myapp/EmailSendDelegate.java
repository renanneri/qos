package com.mycompany.myapp;

import com.mycompany.myapp.service.MailService;
import com.mycompany.myapp.service.PersonListService;
import com.mycompany.myapp.service.ContactService;
import com.mycompany.myapp.service.dto.ContactDTO;
import com.mycompany.myapp.service.dto.EmailEventDTO;
import com.mycompany.myapp.service.dto.EmailEventProcessDTO;
import com.mycompany.myapp.service.dto.PersonListDTO;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.text.StringSubstitutor;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class EmailSendDelegate implements JavaDelegate {

    @Autowired
    MailService mailService;

    @Autowired
    PersonListService personListService;

    @Autowired
    ContactService contactService;


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception { 
   
        EmailEventProcessDTO emailEventProcess = (EmailEventProcessDTO) delegateExecution.getVariable("processInstance");
        EmailEventDTO emailEvent = emailEventProcess.getEmailEvent();
        PersonListDTO personList = emailEvent.getPersonList();

        Optional<PersonListDTO> personListOptional = personListService.findOne(personList.getId());
        personList = personListOptional.get();
        Set<ContactDTO> contacts = personList.getContacts();

        String subject = emailEvent.getSubject();
        String templateMessage = emailEvent.getMessage();

        final var objectMapper = new ObjectMapper();

        for(ContactDTO contact: contacts){
            Optional<ContactDTO> contactOptional = contactService.findOne(contact.getId());
            contact = contactOptional.get();
            String to = contact.getEmail();

            Map < String, Object> mapObj = objectMapper.convertValue(contact, Map.class);

            StringSubstitutor sub = new StringSubstitutor(mapObj);
            String content = sub.replace(templateMessage);

            mailService.sendEmail(to, subject, content, false, false);
        }

    }
}