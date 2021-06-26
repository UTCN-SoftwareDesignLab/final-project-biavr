package com.recruitment;

public class UrlMapping {
    public static final String API_PATH = "/api";
    public static final String ITEMS = API_PATH + "/items";
    public static final String ENTITY = "/{id}";
    public static final String FILTERED = "/filtered";
    public static final String EXPORT_REPORT = "/export/{type}";

    public static final String COMPANIES = API_PATH + "/companies";
    public static final String JOBS = COMPANIES + "/jobs";
    public static final String APPLICATIONS = JOBS + "/applications";

    public static final String JOB_ADDITION = "/topic/socket/companies/jobs";

    public static final String REVIEWS = "/reviews";

    public static final String AUTH = API_PATH + "/auth";
    public static final String SIGN_IN = "/sign-in";
    public static final String SIGN_UP = "/sign-up";

    public static final String USERS = API_PATH + "/users";

}
