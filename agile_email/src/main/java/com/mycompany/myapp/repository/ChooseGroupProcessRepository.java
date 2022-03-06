package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ChooseGroupProcess;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ChooseGroupProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChooseGroupProcessRepository extends JpaRepository<ChooseGroupProcess, Long> {
    Optional<ChooseGroupProcess> findByProcessInstanceId(Long processInstanceId);
}
