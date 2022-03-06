package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ScheduleEmailProcessService;
import com.mycompany.myapp.service.dto.ScheduleEmailProcessDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.ScheduleEmailProcess}.
 */
@RestController
@RequestMapping("/api")
public class ScheduleEmailProcessResource {

    private final Logger log = LoggerFactory.getLogger(ScheduleEmailProcessResource.class);

    private static final String ENTITY_NAME = "scheduleEmailProcess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ScheduleEmailProcessService scheduleEmailProcessService;

    public ScheduleEmailProcessResource(ScheduleEmailProcessService scheduleEmailProcessService) {
        this.scheduleEmailProcessService = scheduleEmailProcessService;
    }

    /**
     * {@code POST  /schedule-email-processes} : Create a new scheduleEmailProcess.
     *
     * @param scheduleEmailProcessDTO the scheduleEmailProcessDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new scheduleEmailProcessDTO, or with status {@code 400 (Bad Request)} if the scheduleEmailProcess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/schedule-email-processes")
    public ResponseEntity<ScheduleEmailProcessDTO> create(@RequestBody ScheduleEmailProcessDTO scheduleEmailProcessDTO)
        throws URISyntaxException {
        log.debug("REST request to save ScheduleEmailProcess : {}", scheduleEmailProcessDTO);
        if (scheduleEmailProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new scheduleEmailProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ScheduleEmailProcessDTO result = scheduleEmailProcessService.create(scheduleEmailProcessDTO);
        return ResponseEntity
            .created(new URI("/api/schedule-email-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /schedule-email-processes} : get all the scheduleEmailProcesss.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of scheduleEmailProcesss in body.
     */
    @GetMapping("/schedule-email-processes")
    public List<ScheduleEmailProcessDTO> getAllScheduleEmailProcesss() {
        log.debug("REST request to get all ScheduleEmailProcesss");
        return scheduleEmailProcessService.findAll();
    }

    /**
     * {@code GET  /schedule-email-processes/:id} : get the "id" scheduleEmailProcess.
     *
     * @param processInstanceId the id of the scheduleEmailProcessDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the scheduleEmailProcessDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/schedule-email-processes/{processInstanceId}")
    public ResponseEntity<ScheduleEmailProcessDTO> getByProcessInstanceId(@PathVariable Long processInstanceId) {
        log.debug("REST request to get ScheduleEmailProcess by processInstanceId : {}", processInstanceId);
        Optional<ScheduleEmailProcessDTO> scheduleEmailProcessDTO = scheduleEmailProcessService.findByProcessInstanceId(processInstanceId);
        return ResponseUtil.wrapOrNotFound(scheduleEmailProcessDTO);
    }
}
