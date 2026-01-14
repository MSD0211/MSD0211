package com.api.elifeconnect.dto.proposal.submit;

import java.util.List;
import java.util.Map;

public record HealthStatements(
        MerDetails mer,
        String height,
        String weight,
        String smoker_status,
        List<FamilyHistory> family_history,
        HusbandDetails husband_details,
        List<HusbandInsurance> husband_insurances,
        Map<String, String> pf_health_questions,
        String date_of_last_delivery,
        String date_of_last_mensuration,
        Map<String, String> pf_male_health_questions,
        Map<String, String> pf_female_health_questions,
        String is_willing_to_answer_health_questions
) {}
