package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ChooseGroupProcessService;
import com.mycompany.myapp.service.dto.ChooseGroupProcessDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ChooseGroupProcess}.
 */
@RestController
@RequestMapping("/api")
public class ChooseGroupProcessResource {

    private final Logger log = LoggerFactory.getLogger(ChooseGroupProcessResource.class);

    private static final String ENTITY_NAME = "chooseGroupProcess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChooseGroupProcessService chooseGroupProcessService;

    public ChooseGroupProcessResource(ChooseGroupProcessService chooseGroupProcessService) {
        this.chooseGroupProcessService = chooseGroupProcessService;
    }

    /**
     * {@code POST  /choose-group-processes} : Create a new chooseGroupProcess.
     *
     * @param chooseGroupProcessDTO the chooseGroupProcessDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chooseGroupProcessDTO, or with status {@code 400 (Bad Request)} if the chooseGroupProcess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/choose-group-processes")
    public ResponseEntity<ChooseGroupProcessDTO> create(@RequestBody ChooseGroupProcessDTO chooseGroupProcessDTO)
        throws URISyntaxException {
        log.debug("REST request to save ChooseGroupProcess : {}", chooseGroupProcessDTO);
        if (chooseGroupProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new chooseGroupProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChooseGroupProcessDTO result = chooseGroupProcessService.create(chooseGroupProcessDTO);
        return ResponseEntity
            .created(new URI("/api/choose-group-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /choose-group-processes} : get all the chooseGroupProcesss.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chooseGroupProcesss in body.
     */
    @GetMapping("/choose-group-processes")
    public List<ChooseGroupProcessDTO> getAllChooseGroupProcesss() {
        log.debug("REST request to get all ChooseGroupProcesss");
        return chooseGroupProcessService.findAll();
    }

    /**
     * {@code GET  /choose-group-processes/:id} : get the "id" chooseGroupProcess.
     *
     * @param processInstanceId the id of the chooseGroupProcessDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chooseGroupProcessDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/choose-group-processes/{processInstanceId}")
    public ResponseEntity<ChooseGroupProcessDTO> getByProcessInstanceId(@PathVariable Long processInstanceId) {
        log.debug("REST request to get ChooseGroupProcess by processInstanceId : {}", processInstanceId);
        Optional<ChooseGroupProcessDTO> chooseGroupProcessDTO = chooseGroupProcessService.findByProcessInstanceId(processInstanceId);
        return ResponseUtil.wrapOrNotFound(chooseGroupProcessDTO);
    }
}
