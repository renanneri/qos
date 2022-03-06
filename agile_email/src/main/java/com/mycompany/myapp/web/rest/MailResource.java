package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.MailRepository;
import com.mycompany.myapp.service.MailService;
import com.mycompany.myapp.service.dto.MailDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Mail}.
 */
@RestController
@RequestMapping("/api")
public class MailResource {

    private final Logger log = LoggerFactory.getLogger(MailResource.class);

    private final MailService mailService;

    private final MailRepository mailRepository;

    public MailResource(MailService mailService, MailRepository mailRepository) {
        this.mailService = mailService;
        this.mailRepository = mailRepository;
    }

    /**
     * {@code GET  /mail} : get all the mail.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mail in body.
     */
    @GetMapping("/mail")
    public List<MailDTO> getAllMail() {
        log.debug("REST request to get all Mail");
        return mailService.findAll();
    }

    /**
     * {@code GET  /mail/:id} : get the "id" mail.
     *
     * @param id the id of the mailDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mailDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mail/{id}")
    public ResponseEntity<MailDTO> getMail(@PathVariable Long id) {
        log.debug("REST request to get Mail : {}", id);
        Optional<MailDTO> mailDTO = mailService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mailDTO);
    }
}
