package com.api.elifeconnect.dto.proposal.submit;

public record AppointeeDetails(
        String age,
        String dob,
        String address,
        String relation,
        String last_name,
        String first_name,
        String national_id,
        String nationality,
        String passport_number,
        String local_phone_number,
        String passport_expiry_date,
        String national_id_expiry_date
) {}
