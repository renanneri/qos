package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ScheduleEmailProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ScheduleEmailProcess} and its DTO {@link ScheduleEmailProcessDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class, GroupMapper.class })
public interface ScheduleEmailProcessMapper extends EntityMapper<ScheduleEmailProcessDTO, ScheduleEmailProcess> {
    @Mapping(target = "processInstance", source = "processInstance")
    @Mapping(target = "group", source = "group")
    ScheduleEmailProcessDTO toDto(ScheduleEmailProcess s);
}
