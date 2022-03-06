package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Mail;
import com.mycompany.myapp.repository.MailRepository;
import com.mycompany.myapp.service.dto.MailDTO;
import com.mycompany.myapp.service.mapper.MailMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Mail}.
 */
@Service
@Transactional
public class MailService {

    private final Logger log = LoggerFactory.getLogger(MailService.class);

    private final MailRepository mailRepository;

    private final MailMapper mailMapper;

    public MailService(MailRepository mailRepository, MailMapper mailMapper) {
        this.mailRepository = mailRepository;
        this.mailMapper = mailMapper;
    }

    /**
     * Save a mail.
     *
     * @param mailDTO the entity to save.
     * @return the persisted entity.
     */
    public MailDTO save(MailDTO mailDTO) {
        log.debug("Request to save Mail : {}", mailDTO);
        Mail mail = mailMapper.toEntity(mailDTO);
        mail = mailRepository.save(mail);
        return mailMapper.toDto(mail);
    }

    /**
     * Partially update a mail.
     *
     * @param mailDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<MailDTO> partialUpdate(MailDTO mailDTO) {
        log.debug("Request to partially update Mail : {}", mailDTO);

        return mailRepository
            .findById(mailDTO.getId())
            .map(
                existingMail -> {
                    mailMapper.partialUpdate(existingMail, mailDTO);
                    return existingMail;
                }
            )
            .map(mailRepository::save)
            .map(mailMapper::toDto);
    }

    /**
     * Get all the mail.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MailDTO> findAll() {
        log.debug("Request to get all Mail");
        return mailRepository.findAll().stream().map(mailMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one mail by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MailDTO> findOne(Long id) {
        log.debug("Request to get Mail : {}", id);
        return mailRepository.findById(id).map(mailMapper::toDto);
    }

    /**
     * Delete the mail by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Mail : {}", id);
        mailRepository.deleteById(id);
    }
}
