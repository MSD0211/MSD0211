package com.api.elifeconnect.controller.common;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller to serve the custom modern Tailwind Swagger UI.
 */
@Controller
@Hidden
public class DocsController {

    @GetMapping({"/", "/docs", "/docs/", "/swagger-custom", "/api-docs-ui"})
    public String customSwaggerUi() {
        return "forward:/docs/index.html";
    }
}
