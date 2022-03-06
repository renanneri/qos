package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Mail} entity.
 */
public class MailDTO implements Serializable {

    private Long id;

    private String subject;

    private String recipient;

    private String message;

    private String group;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MailDTO)) {
            return false;
        }

        MailDTO mailDTO = (MailDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, mailDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MailDTO{" +
            "id=" + getId() +
            ", subject='" + getSubject() + "'" +
            ", recipient='" + getRecipient() + "'" +
            ", message='" + getMessage() + "'" +
            ", group='" + getGroup() + "'" +
            "}";
    }
}
