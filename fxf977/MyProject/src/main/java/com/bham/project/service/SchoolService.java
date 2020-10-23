package com.bham.project.service;

import java.util.HashMap;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/19
 */
public interface SchoolService {
    /**
     * Get the rank list by the rank name.
     * @param rankName      the name of the list
     * @param page          the page of the list
     * @param size          the size of one page
     * @return              the list of the school
     */
    public HashMap<String, Object> getSchoolRankTable(String rankName, int page, int size);

    /**
     * Get the school details by the school name
     * @param schoolName    the name of the school
     * @param infoModule    the module of the school information
     * @return              the detail information of the school
     */
    public HashMap<String, Object> getSchoolDetails(String schoolName, String infoModule);

    /**
     * Get the major information by the school name and the major name
     * @param schoolName    the name of the school
     * @param majorName     the name of the major
     * @return              the information of the major with the right school name and major name
     */
    public HashMap<String, Object> getSchoolMajor(String schoolName, String majorName);

    /**
     * Get the application information by the school name
     * @param schoolName    the name of the school
     * @return              the information of the school application info with the right school name
     */
    public HashMap<String, Object> getSchoolApplications(String schoolName);

    /**
     * Get the information of school by the school name
     * @param schoolName    the name of the school
     * @return              the school information with the right school name
     */
    public HashMap<String, Object> getSchoolInfo(String schoolName);

    /**
     * Get the alumnus information by the school name
     * @param schoolName    the name of the school
     * @return              the alumnus information with the right school name
     */
    public HashMap<String, Object> getAlumnus(String schoolName);
}
