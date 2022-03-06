package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ChooseGroupProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ChooseGroupProcess} and its DTO {@link ChooseGroupProcessDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class, GroupMapper.class })
public interface ChooseGroupProcessMapper extends EntityMapper<ChooseGroupProcessDTO, ChooseGroupProcess> {
    @Mapping(target = "processInstance", source = "processInstance")
    @Mapping(target = "group", source = "group")
    ChooseGroupProcessDTO toDto(ChooseGroupProcess s);
}
