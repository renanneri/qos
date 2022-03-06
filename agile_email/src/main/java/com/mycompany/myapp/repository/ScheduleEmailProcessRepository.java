package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ScheduleEmailProcess;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ScheduleEmailProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ScheduleEmailProcessRepository extends JpaRepository<ScheduleEmailProcess, Long> {
    Optional<ScheduleEmailProcess> findByProcessInstanceId(Long processInstanceId);
}
