package com.api.elifeconnect.aop;

/**
 * Central registry of all API short names used in
 * {@link LogApiCall#shortName()}.
 *
 * <p>
 * Convention: max 8 characters, all uppercase, no spaces.
 * Format: [DOMAIN][ACTION] — e.g. AGNT (agent) + AUTH (authentication) =
 * AGNTAUTH
 *
 * <p>
 * Every constant here must match exactly one
 * {@code @LogApiCall(shortName = ...)} in a controller.
 * This class is the single source of truth — add new codes here first.
 *
 * <pre>
 * ┌─────────────┬─────────────────────────────────────────┬──────────────────────────────────────────────────────┐
 * │ Short Name  │ Full API Name                           │ Controller / Endpoint                                │
 * ├─────────────┼─────────────────────────────────────────┼──────────────────────────────────────────────────────┤
 * │ AGNTAUTH    │ Agent Authentication API                │ AgentController  POST /kenya/agent/authentication    │
 * │ COMSMT      │ Commission Statement API                │ AgentController  POST /kenya/agent/commission/...    │
 * │ CUSTAUTH    │ Customer Authentication API             │ CustomerController POST /kenya/customer/authentication│
 * │ EMPAUTH     │ Employee Authentication API             │ EmployeeController POST /kenya/employee/authentication│
 * │ REVQOT      │ Revival Quotation API                   │ PolicyControllerKenya POST /kenya/policy/revival/... │
 * │ PLNDET      │ Plan Details API                        │ PlanController   POST /kenya/plan/details            │
 * │ LNQOT       │ Loan Quotation API                      │ LoanController   POST /kenya/loan/quotation/download │
 * │ LNRPL       │ Loan Repayment Letter API               │ LoanController   POST /kenya/loan/repayment/...      │
 * │ PRMSMT      │ Premium Statement API                   │ PremiumController POST /kenya/premium/statement      │
 * │ PRMSMY      │ Premium Summary API                     │ PremiumController POST /kenya/premium/summary        │
 * │ PRPENQ      │ Proposal Premium Enquiry API            │ ProposalController POST /kenya/proposal/premium/...  │
 * │ PRBENQ      │ Proposal Submission Enquiry API         │ ProposalController POST /kenya/proposal/submission/..│
 * │ PRBSUB      │ Proposal Submission API                 │ ProposalController POST /kenya/proposal/submit       │
 * │ ULIPFPS     │ Ulip Fund Position Single API           │ UlipController   POST /kenya/ulip/fund/position/...  │
 * │ GPMBSMT     │ GP Member Statement API                 │ GpMemberController POST /kenya/gp/member/statement   │
 * ├─────────────┼─────────────────────────────────────────┼──────────────────────────────────────────────────────┤
 * │ RNWENQ      │ Renewal Premium Enquiry API             │ PremiumController POST /lanka/premium/renewal/enquiry│
 * │ RNWADJ      │ Renewal Premium Adjustment API          │ PremiumController POST /lanka/premium/renewal/...    │
 * │ PLCENQ      │ Customer/Agent Policy Enquiry API       │ PolicyController  POST /lanka/policy/enquiry/...     │
 * │ PRPENQ      │ Proposal Premium Enquiry API            │ ProposalController POST /lanka/proposal/premium/...  │
 * │ PRBENQ      │ Proposal Submission Enquiry API         │ ProposalController POST /lanka/proposal/submission/..│
 * │ PRBSUB      │ Proposal Submission API                 │ ProposalController POST /lanka/proposal/submit       │
 * │ PRPDEP      │ Proposal Deposit Create API             │ ProposalController POST /lanka/proposal/deposit/...  │
 * └─────────────┴─────────────────────────────────────────┴──────────────────────────────────────────────────────┘
 * </pre>
 */
public final class ApiShortNames {

    private ApiShortNames() {
        /* utility class — no instances */ }

    // ─── Kenya ────────────────────────────────────────────────────────────────

    /** Agent Authentication */
    public static final String AGENT_AUTH = "AGNTAUTH";

    /** Agent Commission Statement */
    public static final String AGENT_COMMISSION_STMT = "COMSMT";

    /** Customer Authentication */
    public static final String CUSTOMER_AUTH = "CUSTAUTH";

    /** Employee Authentication */
    public static final String EMPLOYEE_AUTH = "EMPAUTH";

    /** Policy Revival Quotation */
    public static final String POLICY_REVIVAL_QUOT = "REVQOT";

    /** Plan Details */
    public static final String PLAN_DETAILS = "PLNDET";

    /** Loan Quotation */
    public static final String LOAN_QUOTATION = "LNQOT";

    /** Loan Repayment Letter */
    public static final String LOAN_REPAYMENT_LETTER = "LNRPL";

    /** Premium Statement */
    public static final String PREMIUM_STATEMENT = "PRMSMT";

    /** Premium Summary */
    public static final String PREMIUM_SUMMARY = "PRMSMY";

    /** Proposal Premium Enquiry */
    public static final String PROPOSAL_PREMIUM_ENQ = "PRPENQ";

    /** Proposal Submission Enquiry */
    public static final String PROPOSAL_SUBMISSION_ENQ = "PRBENQ";

    /** Proposal Submission */
    public static final String PROPOSAL_SUBMISSION = "PRBSUB";

    /** ULIP Fund Position Single */
    public static final String ULIP_FUND_POSITION = "ULIPFPS";

    /** GP Member Statement */
    public static final String MEMBER_STATEMENT = "GPMBSMT";

    /** GP Member ContributionRecord Card */    
    public static final String MEMBER_RECORD_CARD = "MemberRecordCard";
    // ─── Lanka ────────────────────────────────────────────────────────────────

    /** Renewal Premium Enquiry */
    public static final String RENEWAL_PREMIUM_ENQ = "RNWENQ";

    /** Renewal Premium Adjustment */
    public static final String RENEWAL_PREMIUM_ADJ = "RNWADJ";

    /** Customer / Agent Policy Enquiry */
    public static final String POLICY_ENQUIRY = "PLCENQ";

    /** Proposal Deposit Create */
    public static final String PROPOSAL_DEPOSIT_CREATE = "PRPDEP";
    
}
