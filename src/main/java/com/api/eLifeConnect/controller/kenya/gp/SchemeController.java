package com.api.elifeconnect.controller.kenya.gp;

import java.io.IOException;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.elifeconnect.aop.ApiShortNames;
import com.api.elifeconnect.aop.LogApiCall;
import com.api.elifeconnect.common.response.ApiResponse;
import com.api.elifeconnect.common.response.ApiResponseBuilder;
import com.api.elifeconnect.dto.gp.scheme.AdminSchemesRequest;
import com.api.elifeconnect.dto.gp.scheme.AdminSchemesResponse;
import com.api.elifeconnect.dto.gp.scheme.PensionerSchemesRequest;
import com.api.elifeconnect.dto.gp.scheme.PensionerSchemesResponse;
import com.api.elifeconnect.dto.gp.scheme.SchemeDetailsRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeDetailsResponse;
import com.api.elifeconnect.dto.gp.scheme.SchemeFundBalanceRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileInformationRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileInformationResponse;
import com.api.elifeconnect.service.gp.SchemeService;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileResponse;
import com.api.elifeconnect.dto.gp.scheme.SchemeMemberDetailsRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeMemberDetailsResponse;
import com.api.elifeconnect.dto.gp.scheme.NationalIdSchemesRequest;
import com.api.elifeconnect.dto.gp.scheme.NationalIdSchemesResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/kenya/gp/scheme")
@Tag(name = "Group Pension - Scheme", description = "Endpoints for Kenya Group Pension scheme profile, balances, and member details")
public class SchemeController {

        private final SchemeService schemeService;
        private final ApiResponseBuilder responseBuilder;

        @Autowired
        public SchemeController(SchemeService schemeService) {
                this.schemeService = schemeService;
                this.responseBuilder = new ApiResponseBuilder();
        }

        @PostMapping(value = "/fund/balance/download", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
        @PreAuthorize("hasAuthority('kenya.api.read')")
        @LogApiCall(value = "SchemeFundBalanceAPI", shortName = ApiShortNames.SCHEME_FUND_BALANCE)
        @Operation(summary = "Download Scheme Fund Balance PDF", description = "Generates and downloads the fund balance PDF for the specified scheme.")
        @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Scheme fund balance PDF generated successfully"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
        })
        public ResponseEntity<Resource> downloadSchemeFundBalance(
                        @Valid @RequestBody SchemeFundBalanceRequest request) throws IOException {

                MDC.put("apiName", "SchemeFundBalanceAPI");
                byte[] pdfBytes = schemeService.generateSchemeFundBalance(request);

                ByteArrayResource pdfResource = new ByteArrayResource(pdfBytes);

                return ResponseEntity.ok()
                                .contentType(MediaType.APPLICATION_PDF)
                                .header(
                                                HttpHeaders.CONTENT_DISPOSITION,
                                                "attachment; filename=\"scheme-fund-balance-"
                                                                + request.schemeNumber() + "-"
                                                                + ".pdf\"")
                                .contentLength(pdfBytes.length)
                                .body(pdfResource);
        }

        @PostMapping("/profile/information")
        @PreAuthorize("hasAuthority('kenya.api.read')")
        @LogApiCall(value = "Scheme Profile Information API", shortName = ApiShortNames.SCHEME_PROFILE_INFORMATION)
        @Operation(summary = "Get Scheme Profile Information", description = "Retrieves scheme profile information based on scheme code.")
        @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Scheme profile information retrieved successfully"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
        })
        public ResponseEntity<ApiResponse<SchemeProfileInformationResponse>> getSchemeProfileInformation(
                        @Valid @RequestBody SchemeProfileInformationRequest schemeProfileInformationRequest,
                        HttpServletRequest request) {

                MDC.put("apiName", "Scheme Profile Information API");
                SchemeProfileInformationResponse response = schemeService
                                .getSchemeProfileInformation(schemeProfileInformationRequest);
                ApiResponse<SchemeProfileInformationResponse> body = responseBuilder.success(request,
                                "Scheme Profile Information generated",
                                response);

                return ResponseEntity.ok(body);
        }

        @PostMapping("/profile")
        @PreAuthorize("hasAuthority('kenya.api.read')")
        @LogApiCall(value = "Scheme Profile API", shortName = ApiShortNames.SCHEME_PROFILE)
        @Operation(summary = "Get Scheme Profile", description = "Retrieves scheme profile details.")
        @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Scheme profile retrieved successfully"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
        })
        public ResponseEntity<ApiResponse<SchemeProfileResponse>> getSchemeProfile(
                        @Valid @RequestBody SchemeProfileRequest request,
                        HttpServletRequest httpRequest) {

                MDC.put("apiName", "Scheme Profile API");
                SchemeProfileResponse data = schemeService.getSchemeProfile(request);
                ApiResponse<SchemeProfileResponse> body = responseBuilder.success(httpRequest,
                                "Scheme Profile retrieved successfully", data);
                return ResponseEntity.ok(body);
        }

        @PostMapping("/member/details")
        @PreAuthorize("hasAuthority('kenya.api.read')")
        @LogApiCall(value = "Scheme Member Details API", shortName = ApiShortNames.SCHEME_MEMBER_DETAILS)
        @Operation(summary = "Get Scheme Member Details", description = "Retrieves member details for a scheme.")
        @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Scheme member details retrieved successfully"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
        })
        public ResponseEntity<ApiResponse<SchemeMemberDetailsResponse>> getSchemeMemberDetails(
                        @Valid @RequestBody SchemeMemberDetailsRequest request,
                        HttpServletRequest httpRequest) {

                MDC.put("apiName", "Scheme Member Details API");
                SchemeMemberDetailsResponse data = schemeService.getSchemeMemberDetails(request);
                ApiResponse<SchemeMemberDetailsResponse> body = responseBuilder.success(httpRequest,
                                "Scheme Member Details retrieved successfully", data);
                return ResponseEntity.ok(body);
        }

        @PostMapping("/details")
        @PreAuthorize("hasAuthority('kenya.api.read')")
        @LogApiCall(value = "Scheme Details API", shortName = ApiShortNames.SCHEME_DETAILS)
        @Operation(summary = "Get Scheme Details", description = "Retrieves general scheme details.")
        @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Scheme details retrieved successfully"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
        })
        public ResponseEntity<ApiResponse<SchemeDetailsResponse>> getSchemeDetails(
                        @Valid @RequestBody SchemeDetailsRequest request,
                        HttpServletRequest httpRequest) {

                MDC.put("apiName", "Scheme Details API");
                SchemeDetailsResponse data = schemeService.getSchemeDetails(request);
                ApiResponse<SchemeDetailsResponse> body = responseBuilder.success(httpRequest,
                                "Scheme Details retrieved successfully", data);
                return ResponseEntity.ok(body);
        }

        @PostMapping("/pensioner/email")
        @PreAuthorize("hasAuthority('kenya.api.read')")
        @LogApiCall(value = "Pensioner Schemes API", shortName = ApiShortNames.SCHEME_PENSIONER)
        @Operation(summary = "Get Pensioner Schemes by Email", description = "Retrieves pensioner schemes associated with an email address.")
        @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Pensioner schemes retrieved successfully"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
        })
        public ResponseEntity<ApiResponse<PensionerSchemesResponse>> getPensionerSchemes(
                        @Valid @RequestBody PensionerSchemesRequest request,
                        HttpServletRequest httpRequest) {

                MDC.put("apiName", "Scheme Details API");
                PensionerSchemesResponse data = schemeService.getPensionerSchemes(request);
                ApiResponse<PensionerSchemesResponse> body = responseBuilder.success(httpRequest,
                                "Scheme Details retrieved successfully", data);
                return ResponseEntity.ok(body);
        }

        @PostMapping("/admin")
        @PreAuthorize("hasAuthority('kenya.api.read')")
        @LogApiCall(value = "Pensioner Schemes API", shortName = ApiShortNames.SCHEME_ADMIN)
        @Operation(summary = "Get Admin Schemes", description = "Retrieves administered schemes for an administrator.")
        @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Admin schemes retrieved successfully"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
        })
        public ResponseEntity<ApiResponse<AdminSchemesResponse>> getAdminSchemes(
                        @Valid @RequestBody AdminSchemesRequest request,
                        HttpServletRequest httpRequest) {

                MDC.put("apiName", "Scheme Details API");
                AdminSchemesResponse data = schemeService.getAdminSchemes(request);
                ApiResponse<AdminSchemesResponse> body = responseBuilder.success(httpRequest,
                                "Scheme Details retrieved successfully", data);
                return ResponseEntity.ok(body);
        }

        @PostMapping("/pensioner/nationalid")
        @PreAuthorize("hasAuthority('kenya.api.read')")
        @LogApiCall(value = "Scheme Pensioner National Id API", shortName = ApiShortNames.SCHEME_PENSIONER)
        @Operation(summary = "Get Pensioner Schemes by National ID", description = "Retrieves pensioner schemes matching national ID.")
        @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Pensioner schemes retrieved successfully"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
        })
        public ResponseEntity<ApiResponse<NationalIdSchemesResponse>> getPensionerSchemesByNationalId(
                        @Valid @RequestBody NationalIdSchemesRequest request,
                        HttpServletRequest httpRequest) {

                MDC.put("apiName", "Scheme Pensioner National Id API");
                NationalIdSchemesResponse data = schemeService.getPensionerSchemesByNationalId(request);

                ApiResponse<NationalIdSchemesResponse> body = responseBuilder.success(
                                httpRequest,
                                "Scheme Pensioner details retrieved successfully",
                                data);

                return ResponseEntity.ok(body);
        }
}