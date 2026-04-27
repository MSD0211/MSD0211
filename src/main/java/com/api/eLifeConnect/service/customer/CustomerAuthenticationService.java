package com.api.elifeconnect.service.customer;

import com.api.elifeconnect.dto.customer.CustomerAuthenticationRequest;
import com.api.elifeconnect.dto.customer.CustomerAuthenticationResponse;

public interface CustomerAuthenticationService {

    CustomerAuthenticationResponse customerAuthentication(CustomerAuthenticationRequest req);

}
