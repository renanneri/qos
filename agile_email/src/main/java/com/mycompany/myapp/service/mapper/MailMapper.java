package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.MailDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Mail} and its DTO {@link MailDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MailMapper extends EntityMapper<MailDTO, Mail> {}
