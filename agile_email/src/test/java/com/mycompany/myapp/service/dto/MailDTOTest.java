package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MailDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MailDTO.class);
        MailDTO mailDTO1 = new MailDTO();
        mailDTO1.setId(1L);
        MailDTO mailDTO2 = new MailDTO();
        assertThat(mailDTO1).isNotEqualTo(mailDTO2);
        mailDTO2.setId(mailDTO1.getId());
        assertThat(mailDTO1).isEqualTo(mailDTO2);
        mailDTO2.setId(2L);
        assertThat(mailDTO1).isNotEqualTo(mailDTO2);
        mailDTO1.setId(null);
        assertThat(mailDTO1).isNotEqualTo(mailDTO2);
    }
}
