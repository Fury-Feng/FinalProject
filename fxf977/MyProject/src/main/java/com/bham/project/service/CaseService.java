package com.bham.project.service;

import com.bham.project.entity.School;

import java.util.HashMap;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/19
 */
public interface CaseService {

    /**
     * Get the information of the case by caseId
     * @param caseId    The id of the case
     * @return          the information of the case
     */
    public HashMap<String, Object> getCaseInstance(Integer caseId);

    /**
     * Get all cases.
     * @return          the information of all cases
     */
    public HashMap<String, Object> getAllCases();

    /**
     * Get the cases by the schoolName
     * @param schoolName    the name of the school
     * @return              the cases with the right school name
     */
    public HashMap<String, Object> getCasesBySchool(String schoolName);

    /**
     * Get the cases by the majorName
     * @param majorName     the name of the major
     * @return              the cases with the right major name
     */
    public HashMap<String, Object> getCasesByMajor(String majorName);

    /**
     * Get the cases by the schoolName and majorName
     * @param schoolName    the name of the school
     * @param majorName     the name of the major
     * @return              the cases with right school name and major name
     */
    public HashMap<String, Object> getCases(String schoolName, String majorName);

    public HashMap<String, Object> addCase(HashMap<String, Object> caseInfo);
}
