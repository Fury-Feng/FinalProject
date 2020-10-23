package com.bham.project.service.impl;

import com.bham.project.dao.mapper.*;
import com.bham.project.entity.*;
import com.bham.project.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/23
 */
@Service
public class CaseServiceImpl implements CaseService {

    @Autowired
    private StanCaseMapper stanCaseMapper;

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private SchoolRankMapper schoolRankMapper;

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private ApplicantMapper applicantMapper;

    @Autowired
    private IeltsMapper ieltsMapper;

    @Autowired
    private UserApplicantMapper userApplicantMapper;
    /**
     * Get the information of the case by caseId
     *
     * @param caseId The id of the case
     * @return the information of the case
     */
    @Override
    public HashMap<String, Object> getCaseInstance(Integer caseId) {
        HashMap<String, Object> data = new HashMap<>();
        StanCase caseInstance = stanCaseMapper.selectByCaseId(caseId);

        School school = schoolMapper.selectBySchoolId(caseInstance.getSchoolId());
        SchoolRank schoolRank = schoolRankMapper.selectByPrimaryKey(school.getSchoolId());
        Major major = majorMapper.selectByPrimaryKey(caseInstance.getMajorId());
        Applicant applicant = applicantMapper.selectByAid(caseInstance.getAid());
        Ielts ielts = ieltsMapper.selectByAid(caseInstance.getAid());

        String aid = caseInstance.getAid();
        String schoolName = school.getSchoolName();
        String majorName = major.getMajorName();
        String degree = caseInstance.getDegree();
        int qsRank = schoolRank.getQsRank();
        int timesRank = schoolRank.getTimesRank();
        int majorRank = major.getMajorRank();
        String offerDate = caseInstance.getResultDate();
        String ugSchool = applicant.getUgSchool();
        float ugGpa = applicant.getUgGpa();
        int result = caseInstance.getResult();
        String applyResult;
        if (result == 0) {
            applyResult = "offer";
        } else {
            applyResult = "reject";
        }
        float ieltsScore = ielts.getOverall();
        String note = applicant.getPersonalIntro();
        int termNo = caseInstance.getTerm();
        String term;
        if (termNo == 0) {
            term = "Autumn";
        } else {
            term = "Spring";
        }

        data.put("case_id", caseId);
        data.put("aid", aid);
        data.put("school_name", schoolName);
        data.put("major_name", majorName);
        data.put("degree", degree);
        data.put("qs_rank", qsRank);
        data.put("times_rank", timesRank);
        data.put("major_rank", majorRank);
        data.put("offer_date", offerDate);
        data.put("ug_school", ugSchool);
        data.put("ug_gpa", ugGpa);
        data.put("apply_result", applyResult);
        data.put("ielts", ieltsScore);
        data.put("note", note);
        data.put("term", term);

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", 200);
        resultMap.put("msg", "success");
        resultMap.put("data", data);

        return resultMap;
    }

    /**
     * Get all cases.
     *
     * @return the information of all cases
     */
    @Override
    public HashMap<String, Object> getAllCases() {
        List<StanCase> casesList = stanCaseMapper.selectAll();

        List<HashMap<String, Object>> data = new ArrayList<>();
        Iterator<StanCase> iterator = casesList.iterator();
        while (iterator.hasNext()) {
            StanCase singleCase = iterator.next();
            School school = schoolMapper.selectBySchoolId(singleCase.getSchoolId());
            SchoolRank schoolRank = schoolRankMapper.selectByPrimaryKey(school.getSchoolId());
            Applicant applicant = applicantMapper.selectByAid(singleCase.getAid());
            Major major = majorMapper.selectByPrimaryKey(singleCase.getMajorId());
            Ielts ielts = ieltsMapper.selectByAid(singleCase.getAid());


            HashMap<String, Object> singleCaseInfo = new HashMap<>();
            singleCaseInfo.put("case_id", singleCase.getCaseId());
            singleCaseInfo.put("aid", singleCase.getAid());
            singleCaseInfo.put("school_name", school.getSchoolName());
            singleCaseInfo.put("major_name", major.getMajorName());
            singleCaseInfo.put("degree", singleCase.getDegree());
            singleCaseInfo.put("qs_rank", schoolRank.getQsRank());
            singleCaseInfo.put("times_rank", schoolRank.getTimesRank());
            singleCaseInfo.put("offer_date", singleCase.getResultDate());
            singleCaseInfo.put("ug_school", applicant.getUgSchool());
            singleCaseInfo.put("ug_gpa", applicant.getUgGpa());
            if (singleCase.getResult() == 0) {
                singleCaseInfo.put("apply_result", "offer");
            } else if (singleCase.getResult() == 1) {
                singleCaseInfo.put("apply_result", "reject");
            }
            singleCaseInfo.put("ielts", ielts.getOverall());
            singleCaseInfo.put("note", applicant.getPersonalIntro());
            if (singleCase.getTerm() == 0) {
                singleCaseInfo.put("term", "Autumn");
            } else if (singleCase.getTerm() == 1) {
                singleCaseInfo.put("term", "Spring");
            }

            data.add(singleCaseInfo);
        }

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", 200);
        resultMap.put("msg", "success");
        resultMap.put("data", data);

        return resultMap;
    }

    /**
     * Get the cases by the schoolName
     *
     * @param schoolName the name of the school
     * @return the cases with the right school name
     */
    @Override
    public HashMap<String, Object> getCasesBySchool(String schoolName) {
        List<HashMap<String, Object>> data = new ArrayList<>();
        School school = new School();
        school.setSchoolName(schoolName);
        School schoolInfo = schoolMapper.selectOne(school);
        List<StanCase> casesList = stanCaseMapper.selectBySchoolId(schoolInfo.getSchoolId());

        // if the schoolInfo is null,it means that the school name is not right
        if (schoolInfo == null) {
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("status", 500);
            resultMap.put("msg", "The school is not exist");
            resultMap.put("data", data);
            return resultMap;
        }

        Iterator<StanCase> iterator = casesList.iterator();
        while (iterator.hasNext()) {
            StanCase singleCase = iterator.next();
            School schoolCase = schoolMapper.selectBySchoolId(singleCase.getSchoolId());
            SchoolRank schoolRank = schoolRankMapper.selectByPrimaryKey(schoolCase.getSchoolId());
            Applicant applicant = applicantMapper.selectByAid(singleCase.getAid());
            Major major = majorMapper.selectByPrimaryKey(singleCase.getMajorId());
            Ielts ielts = ieltsMapper.selectByAid(singleCase.getAid());


            HashMap<String, Object> singleCaseInfo = new HashMap<>();
            singleCaseInfo.put("case_id", singleCase.getCaseId());
            singleCaseInfo.put("aid", singleCase.getAid());
            singleCaseInfo.put("school_name", schoolCase.getSchoolName());
            singleCaseInfo.put("major_name", major.getMajorName());
            singleCaseInfo.put("degree", singleCase.getDegree());
            singleCaseInfo.put("qs_rank", schoolRank.getQsRank());
            singleCaseInfo.put("times_rank", schoolRank.getQsRank());
            singleCaseInfo.put("offer_date", singleCase.getResultDate());
            singleCaseInfo.put("ug_school", applicant.getUgSchool());
            singleCaseInfo.put("ug_gpa", applicant.getUgGpa());
            if (singleCase.getResult() == 0) {
                singleCaseInfo.put("apply_result", "offer");
            } else if (singleCase.getResult() == 1) {
                singleCaseInfo.put("apply_result", "reject");
            }
            singleCaseInfo.put("ielts", ielts.getOverall());
            singleCaseInfo.put("note", applicant.getPersonalIntro());
            if (singleCase.getTerm() == 0) {
                singleCaseInfo.put("term", "Autumn");
            } else if (singleCase.getTerm() == 1) {
                singleCaseInfo.put("term", "Spring");
            }

            data.add(singleCaseInfo);
        }

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", 200);
        resultMap.put("msg", "success");
        resultMap.put("data", data);

        return resultMap;
    }

    /**
     * Get the cases by the majorName
     *
     * @param majorName the name of the major
     * @return the cases with the right major name
     */
    @Override
    public HashMap<String, Object> getCasesByMajor(String majorName) {
        List<HashMap<String, Object>> data = new ArrayList<>();

        List<String> majorIdList = majorMapper.selectMajorIdByMajorName(majorName);
        List<StanCase> casesList = stanCaseMapper.selectByMajorsId(majorIdList);
        Iterator<StanCase> iterator = casesList.iterator();

        while (iterator.hasNext()) {
            StanCase singleCase = iterator.next();
            School schoolCase = schoolMapper.selectBySchoolId(singleCase.getSchoolId());
            SchoolRank schoolRank = schoolRankMapper.selectByPrimaryKey(schoolCase.getSchoolId());
            Applicant applicant = applicantMapper.selectByAid(singleCase.getAid());
            Major major = majorMapper.selectByPrimaryKey(singleCase.getMajorId());
            Ielts ielts = ieltsMapper.selectByAid(singleCase.getAid());


            HashMap<String, Object> singleCaseInfo = new HashMap<>();
            singleCaseInfo.put("case_id", singleCase.getCaseId());
            singleCaseInfo.put("aid", singleCase.getAid());
            singleCaseInfo.put("school_name", schoolCase.getSchoolName());
            singleCaseInfo.put("major_name", major.getMajorName());
            singleCaseInfo.put("degree", singleCase.getDegree());
            singleCaseInfo.put("qs_rank", schoolRank.getQsRank());
            singleCaseInfo.put("times_rank", schoolRank.getQsRank());
            singleCaseInfo.put("offer_date", singleCase.getResultDate());
            singleCaseInfo.put("ug_school", applicant.getUgSchool());
            singleCaseInfo.put("ug_gpa", applicant.getUgGpa());
            if (singleCase.getResult() == 0) {
                singleCaseInfo.put("apply_result", "offer");
            } else if (singleCase.getResult() == 1) {
                singleCaseInfo.put("apply_result", "reject");
            }
            singleCaseInfo.put("ielts", ielts.getOverall());
            singleCaseInfo.put("note", applicant.getPersonalIntro());
            if (singleCase.getTerm() == 0) {
                singleCaseInfo.put("term", "Autumn");
            } else if (singleCase.getTerm() == 1) {
                singleCaseInfo.put("term", "Spring");
            }

            data.add(singleCaseInfo);
        }

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", 200);
        resultMap.put("msg", "success");
        resultMap.put("data", data);

        return resultMap;
    }

    /**
     * Get the cases by the schoolName and majorName
     *
     * @param schoolName the name of the school
     * @param majorName  the name of the major
     * @return the cases with right school name and major name
     */
    @Override
    public HashMap<String, Object> getCases(String schoolName, String majorName) {
        List<HashMap<String, Object>> data = new ArrayList<>();

        School school = new School();
        school.setSchoolName(schoolName);
        School schoolInfo = schoolMapper.selectOne(school);
        if (schoolInfo == null) {
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("status", 500);
            resultMap.put("msg", "The school is not exist");
            resultMap.put("data", data);
            return resultMap;
        }

        Major major = majorMapper.selectBySchoolIdAndMajorName(schoolInfo.getSchoolId(), majorName);
        List<StanCase> casesList = stanCaseMapper.selectByMajorId(major.getMajorId());
        Iterator<StanCase> iterator = casesList.iterator();

        while (iterator.hasNext()) {
            StanCase singleCase = iterator.next();
            School schoolCase = schoolMapper.selectBySchoolId(singleCase.getSchoolId());
            SchoolRank schoolRank = schoolRankMapper.selectByPrimaryKey(schoolCase.getSchoolId());
            Applicant applicant = applicantMapper.selectByAid(singleCase.getAid());
            Major majorCase = majorMapper.selectByPrimaryKey(singleCase.getMajorId());
            Ielts ielts = ieltsMapper.selectByAid(singleCase.getAid());


            HashMap<String, Object> singleCaseInfo = new HashMap<>();
            singleCaseInfo.put("case_id", singleCase.getCaseId());
            singleCaseInfo.put("aid", singleCase.getAid());
            singleCaseInfo.put("school_name", schoolCase.getSchoolName());
            singleCaseInfo.put("major_name", majorCase.getMajorName());
            singleCaseInfo.put("degree", singleCase.getDegree());
            singleCaseInfo.put("qs_rank", schoolRank.getQsRank());
            singleCaseInfo.put("times_rank", schoolRank.getQsRank());
            singleCaseInfo.put("offer_date", singleCase.getResultDate());
            singleCaseInfo.put("ug_school", applicant.getUgSchool());
            singleCaseInfo.put("ug_gpa", applicant.getUgGpa());
            if (singleCase.getResult() == 0) {
                singleCaseInfo.put("apply_result", "offer");
            } else if (singleCase.getResult() == 1) {
                singleCaseInfo.put("apply_result", "reject");
            }
            singleCaseInfo.put("ielts", ielts.getOverall());
            singleCaseInfo.put("note", applicant.getPersonalIntro());
            if (singleCase.getTerm() == 0) {
                singleCaseInfo.put("term", "Autumn");
            } else if (singleCase.getTerm() == 1) {
                singleCaseInfo.put("term", "Spring");
            }

            data.add(singleCaseInfo);
        }
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", 200);
        resultMap.put("msg", "success");
        resultMap.put("data", data);

        return resultMap;
    }

    @Override
    public HashMap<String, Object> addCase(HashMap<String, Object> caseInfo) {
        int userId = Integer.parseInt(caseInfo.get("user_id").toString());
        String schoolName = caseInfo.get("school_name").toString();
        String majorName = caseInfo.get("major_name").toString();
        String degree = caseInfo.get("degree").toString();
        int result;
        if (caseInfo.get("result").toString().equals("offer")) {
            result = 0;
        } else {
            result = 1;
        }
        int term;
        if (caseInfo.get("term").toString().equals("Autumn")) {
            term = 0;
        } else {
            term = 1;
        }
        String resultDate = caseInfo.get("result_date").toString();
        Float ielts = Float.parseFloat(caseInfo.get("ielts").toString());
        String schoolId = schoolMapper.selectSchoolIdByName(schoolName);
        String majorId = majorMapper.selectBySchoolIdAndMajorName(schoolId, majorName).getMajorId();
        String aid = userApplicantMapper.selectByUserId(userId).getAid();
        StanCase stanCase = new StanCase();
        stanCase.setSchoolId(schoolId);
        stanCase.setDegree(degree);
        stanCase.setMajorId(majorId);
        stanCase.setResult(result);
        stanCase.setTerm(term);
        stanCase.setResultDate(resultDate);
        stanCase.setAid(aid);

        Ielts ielts1 = new Ielts();
        ielts1.setAid(aid);
        ielts1.setOverall(ielts);
        if (stanCaseMapper.insert(stanCase) == 1) {
            if (ieltsMapper.insert(ielts1) == 1) {
                HashMap<String, Object> resultMap = new HashMap<>();
                resultMap.put("status", 200);
                resultMap.put("msg", "success");
                return resultMap;
            } else {
                ieltsMapper.updateByPrimaryKey(ielts1);
                HashMap<String, Object> resultMap = new HashMap<>();
                resultMap.put("status", 200);
                resultMap.put("msg", "success");
                return resultMap;
            }
        } else {
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("status", 404);
            resultMap.put("msg", "Error");
            return resultMap;
        }
    }
}
