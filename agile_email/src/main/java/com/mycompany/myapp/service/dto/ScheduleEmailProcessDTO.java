package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.akip.service.dto.ProcessInstanceDTO;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ScheduleEmailProcess} entity.
 */
public class ScheduleEmailProcessDTO implements Serializable {

    private Long id;

    private ProcessInstanceDTO processInstance;

    private GroupDTO group;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProcessInstanceDTO getProcessInstance() {
        return processInstance;
    }

    public void setProcessInstance(ProcessInstanceDTO processInstance) {
        this.processInstance = processInstance;
    }

    public GroupDTO getGroup() {
        return group;
    }

    public void setGroup(GroupDTO group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ScheduleEmailProcessDTO)) {
            return false;
        }

        ScheduleEmailProcessDTO scheduleEmailProcessDTO = (ScheduleEmailProcessDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, scheduleEmailProcessDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ScheduleEmailProcessDTO{" +
            "id=" + getId() +
            ", processInstance=" + getProcessInstance() +
            ", group=" + getGroup() +
            "}";
    }
}
