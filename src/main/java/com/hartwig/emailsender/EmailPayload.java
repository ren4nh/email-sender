package com.hartwig.emailsender;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public record EmailPayload(
        @Email(message = "Email is required") String to,
        @NotNull(message = "Subject is required") String subject,
        @NotNull(message = "Body is required") String body) {
}
