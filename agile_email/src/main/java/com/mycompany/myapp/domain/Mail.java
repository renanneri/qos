package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Mail.
 */
@Entity
@Table(name = "mail")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Mail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "recipient")
    private String recipient;

    @Column(name = "message")
    private String message;

    @Column(name = "jhi_group")
    private String group;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mail id(Long id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return this.subject;
    }

    public Mail subject(String subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRecipient() {
        return this.recipient;
    }

    public Mail recipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return this.message;
    }

    public Mail message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGroup() {
        return this.group;
    }

    public Mail group(String group) {
        this.group = group;
        return this;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Mail)) {
            return false;
        }
        return id != null && id.equals(((Mail) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Mail{" +
            "id=" + getId() +
            ", subject='" + getSubject() + "'" +
            ", recipient='" + getRecipient() + "'" +
            ", message='" + getMessage() + "'" +
            ", group='" + getGroup() + "'" +
            "}";
    }
}
