package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Mail;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Mail entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {}
