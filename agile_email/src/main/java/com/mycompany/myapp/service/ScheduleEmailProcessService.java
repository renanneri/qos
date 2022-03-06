package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.ScheduleEmailProcess;
import com.mycompany.myapp.repository.GroupRepository;
import com.mycompany.myapp.repository.ScheduleEmailProcessRepository;
import com.mycompany.myapp.service.dto.ScheduleEmailProcessDTO;
import com.mycompany.myapp.service.mapper.ScheduleEmailProcessMapper;
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
 * Service Implementation for managing {@link ScheduleEmailProcess}.
 */
@Service
@Transactional
public class ScheduleEmailProcessService {

    public static final String BPMN_PROCESS_DEFINITION_ID = "email-sender";

    private final Logger log = LoggerFactory.getLogger(ScheduleEmailProcessService.class);

    private final ProcessInstanceService processInstanceService;

    private final GroupRepository groupRepository;

    private final ScheduleEmailProcessRepository scheduleEmailProcessRepository;

    private final ScheduleEmailProcessMapper scheduleEmailProcessMapper;

    public ScheduleEmailProcessService(
        ProcessInstanceService processInstanceService,
        GroupRepository groupRepository,
        ScheduleEmailProcessRepository scheduleEmailProcessRepository,
        ScheduleEmailProcessMapper scheduleEmailProcessMapper
    ) {
        this.processInstanceService = processInstanceService;
        this.groupRepository = groupRepository;
        this.scheduleEmailProcessRepository = scheduleEmailProcessRepository;
        this.scheduleEmailProcessMapper = scheduleEmailProcessMapper;
    }

    /**
     * Save a scheduleEmailProcess.
     *
     * @param scheduleEmailProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public ScheduleEmailProcessDTO create(ScheduleEmailProcessDTO scheduleEmailProcessDTO) {
        log.debug("Request to save ScheduleEmailProcess : {}", scheduleEmailProcessDTO);

        ScheduleEmailProcess scheduleEmailProcess = scheduleEmailProcessMapper.toEntity(scheduleEmailProcessDTO);

        //Saving the domainEntity
        groupRepository.save(scheduleEmailProcess.getGroup());

        //Creating the process instance in the Camunda and setting it in the process entity
        ProcessInstance processInstance = processInstanceService.create(
            BPMN_PROCESS_DEFINITION_ID,
            "Group#" + scheduleEmailProcess.getGroup().getId(),
            scheduleEmailProcess
        );
        scheduleEmailProcess.setProcessInstance(processInstance);

        //Saving the process entity
        scheduleEmailProcess = scheduleEmailProcessRepository.save(scheduleEmailProcess);
        return scheduleEmailProcessMapper.toDto(scheduleEmailProcess);
    }

    /**
     * Get all the scheduleEmailProcesss.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ScheduleEmailProcessDTO> findAll() {
        log.debug("Request to get all ScheduleEmailProcesss");
        return scheduleEmailProcessRepository
            .findAll()
            .stream()
            .map(scheduleEmailProcessMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one scheduleEmailProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ScheduleEmailProcessDTO> findOne(Long id) {
        log.debug("Request to get ScheduleEmailProcess : {}", id);
        return scheduleEmailProcessRepository.findById(id).map(scheduleEmailProcessMapper::toDto);
    }

    /**
     * Get one scheduleEmailProcess by id.
     *
     * @param processInstanceId the id of the processInstance associated to the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ScheduleEmailProcessDTO> findByProcessInstanceId(Long processInstanceId) {
        log.debug("Request to get ScheduleEmailProcess by  processInstanceId: {}", processInstanceId);
        return scheduleEmailProcessRepository.findByProcessInstanceId(processInstanceId).map(scheduleEmailProcessMapper::toDto);
    }
}
