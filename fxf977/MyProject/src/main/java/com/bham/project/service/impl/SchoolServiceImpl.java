package com.bham.project.service.impl;

import com.bham.project.dao.mapper.*;
import com.bham.project.entity.*;
import com.bham.project.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/25
 */
@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolRankMapper schoolRankMapper;

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private StanCaseMapper stanCaseMapper;

    @Autowired
    private ApplicantMapper applicantMapper;

    @Autowired
    private SchoolsAlumnusMapper schoolsAlumnusMapper;

    @Autowired
    private AlumnusMapper alumnusMapper;

    /**
     * Get the rank list by the rank name.
     *
     * @param rankName the name of the list
     * @param page     the page of the list
     * @param size     the size of one page
     * @return the list of the school
     */
    @Override
    public HashMap<String, Object> getSchoolRankTable(String rankName, int page, int size) {
        if (rankName.compareTo("QSRank") == 0) {
            List<String> schoolsIdList = schoolRankMapper.getSchoolIdByQS();
            List<HashMap<String, Object>> schoolsList = new ArrayList<>();

            for (String schoolId : schoolsIdList) {
                HashMap<String, Object> schoolMap = new HashMap<>();
                School school = schoolMapper.selectBySchoolId(schoolId);
                SchoolRank schoolRank = schoolRankMapper.selectByPrimaryKey(schoolId);

                schoolMap.put("school_id", school.getSchoolId());
                schoolMap.put("school_name", school.getSchoolName());
                schoolMap.put("qs_rank", schoolRank.getQsRank());
                schoolMap.put("times_rank", schoolRank.getTimesRank());
                schoolMap.put("country", school.getLocation());
                schoolMap.put("url", school.getUrl());

                schoolsList.add(schoolMap);
            }

            // choose the school by the page and size
            List newList = schoolsList.subList((page - 1) * size, page * size);

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("status", 200);
            resultMap.put("msg", "success");
            resultMap.put("data", newList);
            return resultMap;
        }

        if (rankName.compareTo("TimesRank") == 0) {
            List<String> schoolsIdList = schoolRankMapper.getSchoolIdByTimes();

            List<HashMap<String, Object>> schoolsList = new ArrayList<>();

            for (String schoolId : schoolsIdList) {
                HashMap<String, Object> schoolMap = new HashMap<>();
                School school = schoolMapper.selectBySchoolId(schoolId);
                SchoolRank schoolRank = schoolRankMapper.selectByPrimaryKey(schoolId);

                schoolMap.put("school_id", school.getSchoolId());
                schoolMap.put("school_name", school.getSchoolName());
                schoolMap.put("qs_rank", schoolRank.getQsRank());
                schoolMap.put("times_rank", schoolRank.getTimesRank());
                schoolMap.put("country", school.getLocation());
                schoolMap.put("url", school.getUrl());

                schoolsList.add(schoolMap);
            }

            // choose the school by the page and size
            List newList = schoolsList.subList((page - 1) * size, page * size);

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("status", 200);
            resultMap.put("msg", "success");
            resultMap.put("data", newList);
            return resultMap;

        }

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", 404);
        resultMap.put("msg", "Failed to implement the interface");
        return resultMap;
    }

    /**
     * Get the school details by the school name
     *
     * @param schoolName the name of the school
     * @param infoModule the module of the school information
     * @return the detail information of the school
     */
    @Override
    public HashMap<String, Object> getSchoolDetails(String schoolName, String infoModule) {
        if (infoModule == null) {
            HashMap<String, Object> data = new HashMap<>();

            School school = new School();
            school.setSchoolName(schoolName);
            School schoolExample = schoolMapper.selectOne(school);
            SchoolRank schoolRank = schoolRankMapper.selectByPrimaryKey(schoolExample.getSchoolId());
            Major major = majorMapper.selectOneMajorBySchoolId(schoolExample.getSchoolId());

            // get the school_info data
            HashMap<String, Object> school_info = new HashMap<>();
            school_info.put("school_id", schoolExample.getSchoolId());
            school_info.put("school_name", schoolExample.getSchoolName());
            school_info.put("location", schoolExample.getLocation());
            school_info.put("school_title", schoolExample.getSchoolTitle());
            school_info.put("qs_rank", schoolRank.getQsRank());
            school_info.put("times_rank", schoolRank.getTimesRank());

            // get the apply_info data
            HashMap<String, Object> apply_info = new HashMap<>();
            apply_info.put("open_date", schoolExample.getOpenDate());
            apply_info.put("days", schoolExample.getDays());
            apply_info.put("required_ielts", schoolExample.getRequiredIelts());
            apply_info.put("accept_rate", schoolExample.getRejectRate());
            apply_info.put("tuition_fee", schoolExample.getTuitionFee());
            apply_info.put("study_hours", major.getStudyHours());

            // get the apply_cases data
            List<HashMap<String, Object>> apply_cases = new ArrayList<>();
            List<StanCase> casesList = stanCaseMapper.selectBySchoolId(schoolExample.getSchoolId());
            for (StanCase stanCase : casesList) {
                HashMap<String, Object> applyCase = new HashMap<>();
                Applicant applicant = applicantMapper.selectByAid(stanCase.getAid());
                Major majorCase = majorMapper.selectByPrimaryKey(stanCase.getMajorId());
                applyCase.put("case_id", stanCase.getCaseId());
                applyCase.put("applicant_id", stanCase.getAid());
                applyCase.put("ug_school", applicant.getUgSchool());
                applyCase.put("ug_gpa", applicant.getUgGpa());
                applyCase.put("major_name", majorCase.getMajorName());
                applyCase.put("degree", stanCase.getDegree());
                applyCase.put("major_rank", majorCase.getMajorRank());
                applyCase.put("offer_date", stanCase.getResultDate());
                apply_cases.add(applyCase);
            }

            // get the famous_alumni data
            List<HashMap<String, Object>> famous_alumni = new ArrayList<>();
            List<SchoolsAlumnus> schoolsAlumnusList = schoolsAlumnusMapper.selectBySchoolId(schoolExample.getSchoolId());
            for (SchoolsAlumnus schoolsAlumnus : schoolsAlumnusList) {
                HashMap<String, Object> alumniMap = new HashMap<>();
                Alumnus alumnus = alumnusMapper.selectByPrimaryKey(schoolsAlumnus.getAlumnusId());
                alumniMap.put("name", alumnus.getAlumnusName());
                alumniMap.put("title", alumnus.getIntroduction());
            }

            data.put("school_info", school_info);
            data.put("apply_info", apply_info);
            data.put("apply_cases", apply_cases);
            data.put("famous_alumni", famous_alumni);

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("status", 200);
            resultMap.put("msg", "success");
            resultMap.put("data", data);
            return resultMap;
        }

        if (infoModule.compareTo("schoolInfo") == 0) {

            HashMap<String, Object> school_info = new HashMap<>();
            School school = new School();
            school.setSchoolName(schoolName);
            School schoolExample = schoolMapper.selectOne(school);
            SchoolRank schoolRank = schoolRankMapper.selectByPrimaryKey(schoolExample.getSchoolId());
            school_info.put("school_id", schoolExample.getSchoolId());
            school_info.put("school_name", schoolExample.getSchoolName());
            school_info.put("location", schoolExample.getLocation());
            school_info.put("school_title", schoolExample.getSchoolTitle());
            school_info.put("qs_rank", schoolRank.getQsRank());
            school_info.put("times_rank", schoolRank.getTimesRank());

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("status", 200);
            resultMap.put("msg", "success");
            resultMap.put("data", school_info);
            return resultMap;
        }

        if (infoModule.compareTo("applyInfo") == 0) {
            School school = new School();
            school.setSchoolName(schoolName);
            School schoolExample = schoolMapper.selectOne(school);
            Major major = majorMapper.selectOneMajorBySchoolId(schoolExample.getSchoolId());

            HashMap<String, Object> apply_info = new HashMap<>();
            apply_info.put("open_date", schoolExample.getOpenDate());
            apply_info.put("days", schoolExample.getDays());
            apply_info.put("required_ielts", schoolExample.getRequiredIelts());
            apply_info.put("accept_rate", schoolExample.getRejectRate());
            apply_info.put("tuition_fee", schoolExample.getTuitionFee());
            apply_info.put("study_hours", major.getStudyHours());

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("status", 200);
            resultMap.put("msg", "success");
            resultMap.put("data", apply_info);
            return resultMap;
        }

        if (infoModule.compareTo("applyCases") == 0) {
            List<HashMap<String, Object>> apply_cases = new ArrayList<>();
            School school = new School();
            school.setSchoolName(schoolName);
            School schoolExample = schoolMapper.selectOne(school);
            List<StanCase> casesList = stanCaseMapper.selectBySchoolId(schoolExample.getSchoolId());
            for (StanCase stanCase : casesList) {
                HashMap<String, Object> applyCase = new HashMap<>();
                Applicant applicant = applicantMapper.selectByAid(stanCase.getAid());
                Major majorCase = majorMapper.selectByPrimaryKey(stanCase.getMajorId());
                applyCase.put("case_id", stanCase.getCaseId());
                applyCase.put("applicant_id", stanCase.getAid());
                applyCase.put("ug_school", applicant.getUgSchool());
                applyCase.put("ug_gpa", applicant.getUgGpa());
                applyCase.put("major_name", majorCase.getMajorName());
                applyCase.put("degree", stanCase.getDegree());
                applyCase.put("major_rank", majorCase.getMajorRank());
                applyCase.put("offer_date", stanCase.getResultDate());
                apply_cases.add(applyCase);
            }

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("status", 200);
            resultMap.put("msg", "success");
            resultMap.put("data", apply_cases);
            return resultMap;
        }

        if (infoModule.compareTo("famousAlumni") == 0) {
            List<HashMap<String, Object>> famous_alumni = new ArrayList<>();
            School school = new School();
            school.setSchoolName(schoolName);
            School schoolExample = schoolMapper.selectOne(school);
            List<SchoolsAlumnus> schoolsAlumnusList = schoolsAlumnusMapper.selectBySchoolId(schoolExample.getSchoolId());
            for (SchoolsAlumnus schoolsAlumnus : schoolsAlumnusList) {
                HashMap<String, Object> alumniMap = new HashMap<>();
                Alumnus alumnus = alumnusMapper.selectByPrimaryKey(schoolsAlumnus.getAlumnusId());
                alumniMap.put("name", alumnus.getAlumnusName());
                alumniMap.put("title", alumnus.getIntroduction());
            }

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("status", 200);
            resultMap.put("msg", "success");
            resultMap.put("data", famous_alumni);
            return resultMap;
        }

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", 404);
        resultMap.put("msg", "Failed to implement the interface");
        return resultMap;
    }

    /**
     * Get the major information by the school name and the major name
     *
     * @param schoolName the name of the school
     * @param majorName  the name of the major
     * @return the information of the major with the right school name and major name
     */
    @Override
    public HashMap<String, Object> getSchoolMajor(String schoolName, String majorName) {
        if (majorName == null) {
            List<HashMap<String, Object>> data = new ArrayList<>();
            School school = new School();
            school.setSchoolName(schoolName);
            School schoolExample = schoolMapper.selectOne(school);
            List<Major> majorsList = majorMapper.selectBySchoolId(schoolExample.getSchoolId());

            for (Major major : majorsList) {
                HashMap<String, Object> majorInfo = new HashMap<>();
                majorInfo.put("major_name", major.getMajorName());
                majorInfo.put("course_set", major.getCourseSet());
                majorInfo.put("study_hours", major.getStudyHours());
                majorInfo.put("language_condition", major.getLanguageCon());
                majorInfo.put("major_rank", major.getMajorRank());
                data.add(majorInfo);
            }

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("status", 200);
            resultMap.put("msg", "success");
            resultMap.put("data", data);

            return resultMap;
        } else {
            HashMap<String, Object> data = new HashMap<>();
            // get major_info data
            HashMap<String, Object> major_info = new HashMap<>();
            School test = new School();
            test.setSchoolName(schoolName);
            School school = schoolMapper.selectOne(test);
            Major major = majorMapper.selectBySchoolIdAndMajorName(school.getSchoolId(), majorName);
            major_info.put("major_name", major.getMajorName());
            major_info.put("country", school.getLocation());
            major_info.put("major_rank", major.getMajorRank());
            major_info.put("course_set", major.getCourseSet());

            // get apply_info data
            HashMap<String, Object> apply_info = new HashMap<>();
            apply_info.put("open_date", school.getOpenDate());
            apply_info.put("days", school.getDays());
            apply_info.put("required_ielts", major.getLanguageCon());
            apply_info.put("acceptance_rate", school.getRejectRate());
            apply_info.put("tuition_fee", school.getTuitionFee());


            // get apply_cases info
            List<HashMap<String, Object>> apply_cases = new ArrayList<>();
            List<StanCase> casesList = stanCaseMapper.selectByMajorId(major.getMajorId());
            for (StanCase singleCase : casesList) {
                HashMap<String, Object> applyCase = new HashMap<>();
                Applicant applicant = applicantMapper.selectByAid(singleCase.getAid());
                Major majorExample = majorMapper.selectByPrimaryKey(singleCase.getMajorId());
                applyCase.put("applicant", singleCase.getAid());
                applyCase.put("ug_level", applicant.getUgSchool());
                applyCase.put("ug_gpa", applicant.getUgGpa());
                applyCase.put("major_name", majorExample.getMajorName());
                applyCase.put("degree", singleCase.getDegree());
                applyCase.put("major_rank", majorExample.getMajorRank());
                applyCase.put("offer_date", singleCase.getResultDate());
                apply_cases.add(applyCase);
            }


            data.put("major_info", major_info);
            data.put("apply_info", apply_info);
            data.put("apply_cases", apply_cases);

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("status", 200);
            resultMap.put("msg", "success");
            resultMap.put("data", data);

            return resultMap;
        }
    }

    /**
     * Get the application information by the school name
     *
     * @param schoolName the name of the school
     * @return the information of the school application info with the right school name
     */
    @Override
    public HashMap<String, Object> getSchoolApplications(String schoolName) {
        School school = new School();
        school.setSchoolName(schoolName);
        School schoolInfo = schoolMapper.selectOne(school);

        HashMap<String, Object> applyInfo = new HashMap<>();
        applyInfo.put("open_date", schoolInfo.getOpenDate());
        applyInfo.put("days", schoolInfo.getDays());
        applyInfo.put("required_ielts", schoolInfo.getRequiredIelts());
        applyInfo.put("acceptance_rate", schoolInfo.getRejectRate());
        applyInfo.put("tution_fee", schoolInfo.getTuitionFee());

        if (applyInfo.isEmpty()) {
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("status", 500);
            resultMap.put("msg", "failed to implement the interface");
            resultMap.put("data", applyInfo);
        }

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", 200);
        resultMap.put("msg", "success");
        resultMap.put("data", applyInfo);

        return resultMap;
    }

    /**
     * Get the information of school by the school name
     *
     * @param schoolName the name of the school
     * @return the school information with the right school name
     */
    @Override
    public HashMap<String, Object> getSchoolInfo(String schoolName) {
        HashMap<String, Object> school_info = new HashMap<>();
        School school = new School();
        school.setSchoolName(schoolName);
        School schoolExample = schoolMapper.selectOne(school);
        SchoolRank schoolRank = schoolRankMapper.selectByPrimaryKey(schoolExample.getSchoolId());
        school_info.put("school_id", schoolExample.getSchoolId());
        school_info.put("school_name", schoolExample.getSchoolName());
        school_info.put("location", schoolExample.getLocation());
        school_info.put("school_title", schoolExample.getSchoolTitle());
        school_info.put("qs_rank", schoolRank.getQsRank());
        school_info.put("times_rank", schoolRank.getTimesRank());

        if (school_info.isEmpty()) {
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("status", 500);
            resultMap.put("msg", "failed to implement the interface");
            resultMap.put("data", school_info);
        }

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", 200);
        resultMap.put("msg", "success");
        resultMap.put("data", school_info);
        return resultMap;
    }

    /**
     * Get the alumnus information by the school name
     *
     * @param schoolName the name of the school
     * @return the alumnus information with the right school name
     */
    @Override
    public HashMap<String, Object> getAlumnus(String schoolName) {
        List<HashMap<String, Object>> famous_alumni = new ArrayList<>();
        School school = new School();
        school.setSchoolName(schoolName);
        School schoolExample = schoolMapper.selectOne(school);
        List<SchoolsAlumnus> schoolsAlumnusList = schoolsAlumnusMapper.selectBySchoolId(schoolExample.getSchoolId());
        for (SchoolsAlumnus schoolsAlumnus : schoolsAlumnusList) {
            HashMap<String, Object> alumniMap = new HashMap<>();
            Alumnus alumnus = alumnusMapper.selectByPrimaryKey(schoolsAlumnus.getAlumnusId());
            alumniMap.put("name", alumnus.getAlumnusName());
            alumniMap.put("title", alumnus.getIntroduction());
        }

        if (famous_alumni.isEmpty()) {
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("status", 500);
            resultMap.put("msg", "failed to implement the interface");
            resultMap.put("data", famous_alumni);
        }

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", 200);
        resultMap.put("msg", "success");
        resultMap.put("data", famous_alumni);
        return resultMap;
    }
}
