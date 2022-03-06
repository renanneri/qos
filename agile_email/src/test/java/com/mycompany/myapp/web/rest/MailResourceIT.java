package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Mail;
import com.mycompany.myapp.repository.MailRepository;
import com.mycompany.myapp.service.dto.MailDTO;
import com.mycompany.myapp.service.mapper.MailMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link MailResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MailResourceIT {

    private static final String DEFAULT_SUBJECT = "AAAAAAAAAA";
    private static final String UPDATED_SUBJECT = "BBBBBBBBBB";

    private static final String DEFAULT_RECIPIENT = "AAAAAAAAAA";
    private static final String UPDATED_RECIPIENT = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_GROUP = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/mail";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MailRepository mailRepository;

    @Autowired
    private MailMapper mailMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMailMockMvc;

    private Mail mail;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Mail createEntity(EntityManager em) {
        Mail mail = new Mail().subject(DEFAULT_SUBJECT).recipient(DEFAULT_RECIPIENT).message(DEFAULT_MESSAGE).group(DEFAULT_GROUP);
        return mail;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Mail createUpdatedEntity(EntityManager em) {
        Mail mail = new Mail().subject(UPDATED_SUBJECT).recipient(UPDATED_RECIPIENT).message(UPDATED_MESSAGE).group(UPDATED_GROUP);
        return mail;
    }

    @BeforeEach
    public void initTest() {
        mail = createEntity(em);
    }

    @Test
    @Transactional
    void getAllMail() throws Exception {
        // Initialize the database
        mailRepository.saveAndFlush(mail);

        // Get all the mailList
        restMailMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mail.getId().intValue())))
            .andExpect(jsonPath("$.[*].subject").value(hasItem(DEFAULT_SUBJECT)))
            .andExpect(jsonPath("$.[*].recipient").value(hasItem(DEFAULT_RECIPIENT)))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE)))
            .andExpect(jsonPath("$.[*].group").value(hasItem(DEFAULT_GROUP)));
    }

    @Test
    @Transactional
    void getMail() throws Exception {
        // Initialize the database
        mailRepository.saveAndFlush(mail);

        // Get the mail
        restMailMockMvc
            .perform(get(ENTITY_API_URL_ID, mail.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mail.getId().intValue()))
            .andExpect(jsonPath("$.subject").value(DEFAULT_SUBJECT))
            .andExpect(jsonPath("$.recipient").value(DEFAULT_RECIPIENT))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE))
            .andExpect(jsonPath("$.group").value(DEFAULT_GROUP));
    }

    @Test
    @Transactional
    void getNonExistingMail() throws Exception {
        // Get the mail
        restMailMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
