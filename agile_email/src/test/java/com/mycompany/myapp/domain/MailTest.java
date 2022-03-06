package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MailTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Mail.class);
        Mail mail1 = new Mail();
        mail1.setId(1L);
        Mail mail2 = new Mail();
        mail2.setId(mail1.getId());
        assertThat(mail1).isEqualTo(mail2);
        mail2.setId(2L);
        assertThat(mail1).isNotEqualTo(mail2);
        mail1.setId(null);
        assertThat(mail1).isNotEqualTo(mail2);
    }
}
