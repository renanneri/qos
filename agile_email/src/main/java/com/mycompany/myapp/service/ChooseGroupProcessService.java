package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.ChooseGroupProcess;
import com.mycompany.myapp.repository.ChooseGroupProcessRepository;
import com.mycompany.myapp.repository.GroupRepository;
import com.mycompany.myapp.service.dto.ChooseGroupProcessDTO;
import com.mycompany.myapp.service.mapper.ChooseGroupProcessMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.akip.domain.ProcessInstance;
import org.akip.service.ProcessInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ChooseGroupProcess}.
 */
@Service
@Transactional
public class ChooseGroupProcessService {

    public static final String BPMN_PROCESS_DEFINITION_ID = "email-sender";

    private final Logger log = LoggerFactory.getLogger(ChooseGroupProcessService.class);

    private final ProcessInstanceService processInstanceService;

    private final GroupRepository groupRepository;

    private final ChooseGroupProcessRepository chooseGroupProcessRepository;

    private final ChooseGroupProcessMapper chooseGroupProcessMapper;

    public ChooseGroupProcessService(
        ProcessInstanceService processInstanceService,
        GroupRepository groupRepository,
        ChooseGroupProcessRepository chooseGroupProcessRepository,
        ChooseGroupProcessMapper chooseGroupProcessMapper
    ) {
        this.processInstanceService = processInstanceService;
        this.groupRepository = groupRepository;
        this.chooseGroupProcessRepository = chooseGroupProcessRepository;
        this.chooseGroupProcessMapper = chooseGroupProcessMapper;
    }

    /**
     * Save a chooseGroupProcess.
     *
     * @param chooseGroupProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public ChooseGroupProcessDTO create(ChooseGroupProcessDTO chooseGroupProcessDTO) {
        log.debug("Request to save ChooseGroupProcess : {}", chooseGroupProcessDTO);

        ChooseGroupProcess chooseGroupProcess = chooseGroupProcessMapper.toEntity(chooseGroupProcessDTO);

        //Saving the domainEntity
        groupRepository.save(chooseGroupProcess.getGroup());

        //Creating the process instance in the Camunda and setting it in the process entity
        ProcessInstance processInstance = processInstanceService.create(
            BPMN_PROCESS_DEFINITION_ID,
            "Group#" + chooseGroupProcess.getGroup().getId(),
            chooseGroupProcess
        );
        chooseGroupProcess.setProcessInstance(processInstance);

        //Saving the process entity
        chooseGroupProcess = chooseGroupProcessRepository.save(chooseGroupProcess);
        return chooseGroupProcessMapper.toDto(chooseGroupProcess);
    }

    /**
     * Get all the chooseGroupProcesss.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ChooseGroupProcessDTO> findAll() {
        log.debug("Request to get all ChooseGroupProcesss");
        return chooseGroupProcessRepository
            .findAll()
            .stream()
            .map(chooseGroupProcessMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one chooseGroupProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ChooseGroupProcessDTO> findOne(Long id) {
        log.debug("Request to get ChooseGroupProcess : {}", id);
        return chooseGroupProcessRepository.findById(id).map(chooseGroupProcessMapper::toDto);
    }

    /**
     * Get one chooseGroupProcess by id.
     *
     * @param processInstanceId the id of the processInstance associated to the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ChooseGroupProcessDTO> findByProcessInstanceId(Long processInstanceId) {
        log.debug("Request to get ChooseGroupProcess by  processInstanceId: {}", processInstanceId);
        return chooseGroupProcessRepository.findByProcessInstanceId(processInstanceId).map(chooseGroupProcessMapper::toDto);
    }
}
