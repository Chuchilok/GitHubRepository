package com.dogpro.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.PaginationUtil;

public class ComplaintExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PaginationUtil pagination;

    public ComplaintExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setPagination(PaginationUtil pagination) {
        this.pagination=pagination;
    }

    public PaginationUtil getPagination() {
        return pagination;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andComplaintIdIsNull() {
            addCriterion("complaint_id is null");
            return (Criteria) this;
        }

        public Criteria andComplaintIdIsNotNull() {
            addCriterion("complaint_id is not null");
            return (Criteria) this;
        }

        public Criteria andComplaintIdEqualTo(Long value) {
            addCriterion("complaint_id =", value, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdNotEqualTo(Long value) {
            addCriterion("complaint_id <>", value, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdGreaterThan(Long value) {
            addCriterion("complaint_id >", value, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdGreaterThanOrEqualTo(Long value) {
            addCriterion("complaint_id >=", value, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdLessThan(Long value) {
            addCriterion("complaint_id <", value, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdLessThanOrEqualTo(Long value) {
            addCriterion("complaint_id <=", value, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdIn(List<Long> values) {
            addCriterion("complaint_id in", values, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdNotIn(List<Long> values) {
            addCriterion("complaint_id not in", values, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdBetween(Long value1, Long value2) {
            addCriterion("complaint_id between", value1, value2, "complaintId");
            return (Criteria) this;
        }

        public Criteria andComplaintIdNotBetween(Long value1, Long value2) {
            addCriterion("complaint_id not between", value1, value2, "complaintId");
            return (Criteria) this;
        }

        public Criteria andFriendcirIdIsNull() {
            addCriterion("friendCir_id is null");
            return (Criteria) this;
        }

        public Criteria andFriendcirIdIsNotNull() {
            addCriterion("friendCir_id is not null");
            return (Criteria) this;
        }

        public Criteria andFriendcirIdEqualTo(Long value) {
            addCriterion("friendCir_id =", value, "friendcirId");
            return (Criteria) this;
        }

        public Criteria andFriendcirIdNotEqualTo(Long value) {
            addCriterion("friendCir_id <>", value, "friendcirId");
            return (Criteria) this;
        }

        public Criteria andFriendcirIdGreaterThan(Long value) {
            addCriterion("friendCir_id >", value, "friendcirId");
            return (Criteria) this;
        }

        public Criteria andFriendcirIdGreaterThanOrEqualTo(Long value) {
            addCriterion("friendCir_id >=", value, "friendcirId");
            return (Criteria) this;
        }

        public Criteria andFriendcirIdLessThan(Long value) {
            addCriterion("friendCir_id <", value, "friendcirId");
            return (Criteria) this;
        }

        public Criteria andFriendcirIdLessThanOrEqualTo(Long value) {
            addCriterion("friendCir_id <=", value, "friendcirId");
            return (Criteria) this;
        }

        public Criteria andFriendcirIdIn(List<Long> values) {
            addCriterion("friendCir_id in", values, "friendcirId");
            return (Criteria) this;
        }

        public Criteria andFriendcirIdNotIn(List<Long> values) {
            addCriterion("friendCir_id not in", values, "friendcirId");
            return (Criteria) this;
        }

        public Criteria andFriendcirIdBetween(Long value1, Long value2) {
            addCriterion("friendCir_id between", value1, value2, "friendcirId");
            return (Criteria) this;
        }

        public Criteria andFriendcirIdNotBetween(Long value1, Long value2) {
            addCriterion("friendCir_id not between", value1, value2, "friendcirId");
            return (Criteria) this;
        }

        public Criteria andComplainttimeIsNull() {
            addCriterion("complaintTime is null");
            return (Criteria) this;
        }

        public Criteria andComplainttimeIsNotNull() {
            addCriterion("complaintTime is not null");
            return (Criteria) this;
        }

        public Criteria andComplainttimeEqualTo(Date value) {
            addCriterion("complaintTime =", value, "complainttime");
            return (Criteria) this;
        }

        public Criteria andComplainttimeNotEqualTo(Date value) {
            addCriterion("complaintTime <>", value, "complainttime");
            return (Criteria) this;
        }

        public Criteria andComplainttimeGreaterThan(Date value) {
            addCriterion("complaintTime >", value, "complainttime");
            return (Criteria) this;
        }

        public Criteria andComplainttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("complaintTime >=", value, "complainttime");
            return (Criteria) this;
        }

        public Criteria andComplainttimeLessThan(Date value) {
            addCriterion("complaintTime <", value, "complainttime");
            return (Criteria) this;
        }

        public Criteria andComplainttimeLessThanOrEqualTo(Date value) {
            addCriterion("complaintTime <=", value, "complainttime");
            return (Criteria) this;
        }

        public Criteria andComplainttimeIn(List<Date> values) {
            addCriterion("complaintTime in", values, "complainttime");
            return (Criteria) this;
        }

        public Criteria andComplainttimeNotIn(List<Date> values) {
            addCriterion("complaintTime not in", values, "complainttime");
            return (Criteria) this;
        }

        public Criteria andComplainttimeBetween(Date value1, Date value2) {
            addCriterion("complaintTime between", value1, value2, "complainttime");
            return (Criteria) this;
        }

        public Criteria andComplainttimeNotBetween(Date value1, Date value2) {
            addCriterion("complaintTime not between", value1, value2, "complainttime");
            return (Criteria) this;
        }

        public Criteria andComplaintcontentIsNull() {
            addCriterion("complaintContent is null");
            return (Criteria) this;
        }

        public Criteria andComplaintcontentIsNotNull() {
            addCriterion("complaintContent is not null");
            return (Criteria) this;
        }

        public Criteria andComplaintcontentEqualTo(String value) {
            addCriterion("complaintContent =", value, "complaintcontent");
            return (Criteria) this;
        }

        public Criteria andComplaintcontentNotEqualTo(String value) {
            addCriterion("complaintContent <>", value, "complaintcontent");
            return (Criteria) this;
        }

        public Criteria andComplaintcontentGreaterThan(String value) {
            addCriterion("complaintContent >", value, "complaintcontent");
            return (Criteria) this;
        }

        public Criteria andComplaintcontentGreaterThanOrEqualTo(String value) {
            addCriterion("complaintContent >=", value, "complaintcontent");
            return (Criteria) this;
        }

        public Criteria andComplaintcontentLessThan(String value) {
            addCriterion("complaintContent <", value, "complaintcontent");
            return (Criteria) this;
        }

        public Criteria andComplaintcontentLessThanOrEqualTo(String value) {
            addCriterion("complaintContent <=", value, "complaintcontent");
            return (Criteria) this;
        }

        public Criteria andComplaintcontentLike(String value) {
            addCriterion("complaintContent like", value, "complaintcontent");
            return (Criteria) this;
        }

        public Criteria andComplaintcontentNotLike(String value) {
            addCriterion("complaintContent not like", value, "complaintcontent");
            return (Criteria) this;
        }

        public Criteria andComplaintcontentIn(List<String> values) {
            addCriterion("complaintContent in", values, "complaintcontent");
            return (Criteria) this;
        }

        public Criteria andComplaintcontentNotIn(List<String> values) {
            addCriterion("complaintContent not in", values, "complaintcontent");
            return (Criteria) this;
        }

        public Criteria andComplaintcontentBetween(String value1, String value2) {
            addCriterion("complaintContent between", value1, value2, "complaintcontent");
            return (Criteria) this;
        }

        public Criteria andComplaintcontentNotBetween(String value1, String value2) {
            addCriterion("complaintContent not between", value1, value2, "complaintcontent");
            return (Criteria) this;
        }

        public Criteria andComplainttypeIsNull() {
            addCriterion("complaintType is null");
            return (Criteria) this;
        }

        public Criteria andComplainttypeIsNotNull() {
            addCriterion("complaintType is not null");
            return (Criteria) this;
        }

        public Criteria andComplainttypeEqualTo(String value) {
            addCriterion("complaintType =", value, "complainttype");
            return (Criteria) this;
        }

        public Criteria andComplainttypeNotEqualTo(String value) {
            addCriterion("complaintType <>", value, "complainttype");
            return (Criteria) this;
        }

        public Criteria andComplainttypeGreaterThan(String value) {
            addCriterion("complaintType >", value, "complainttype");
            return (Criteria) this;
        }

        public Criteria andComplainttypeGreaterThanOrEqualTo(String value) {
            addCriterion("complaintType >=", value, "complainttype");
            return (Criteria) this;
        }

        public Criteria andComplainttypeLessThan(String value) {
            addCriterion("complaintType <", value, "complainttype");
            return (Criteria) this;
        }

        public Criteria andComplainttypeLessThanOrEqualTo(String value) {
            addCriterion("complaintType <=", value, "complainttype");
            return (Criteria) this;
        }

        public Criteria andComplainttypeLike(String value) {
            addCriterion("complaintType like", value, "complainttype");
            return (Criteria) this;
        }

        public Criteria andComplainttypeNotLike(String value) {
            addCriterion("complaintType not like", value, "complainttype");
            return (Criteria) this;
        }

        public Criteria andComplainttypeIn(List<String> values) {
            addCriterion("complaintType in", values, "complainttype");
            return (Criteria) this;
        }

        public Criteria andComplainttypeNotIn(List<String> values) {
            addCriterion("complaintType not in", values, "complainttype");
            return (Criteria) this;
        }

        public Criteria andComplainttypeBetween(String value1, String value2) {
            addCriterion("complaintType between", value1, value2, "complainttype");
            return (Criteria) this;
        }

        public Criteria andComplainttypeNotBetween(String value1, String value2) {
            addCriterion("complaintType not between", value1, value2, "complainttype");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andComplaintipIsNull() {
            addCriterion("complaintIp is null");
            return (Criteria) this;
        }

        public Criteria andComplaintipIsNotNull() {
            addCriterion("complaintIp is not null");
            return (Criteria) this;
        }

        public Criteria andComplaintipEqualTo(String value) {
            addCriterion("complaintIp =", value, "complaintip");
            return (Criteria) this;
        }

        public Criteria andComplaintipNotEqualTo(String value) {
            addCriterion("complaintIp <>", value, "complaintip");
            return (Criteria) this;
        }

        public Criteria andComplaintipGreaterThan(String value) {
            addCriterion("complaintIp >", value, "complaintip");
            return (Criteria) this;
        }

        public Criteria andComplaintipGreaterThanOrEqualTo(String value) {
            addCriterion("complaintIp >=", value, "complaintip");
            return (Criteria) this;
        }

        public Criteria andComplaintipLessThan(String value) {
            addCriterion("complaintIp <", value, "complaintip");
            return (Criteria) this;
        }

        public Criteria andComplaintipLessThanOrEqualTo(String value) {
            addCriterion("complaintIp <=", value, "complaintip");
            return (Criteria) this;
        }

        public Criteria andComplaintipLike(String value) {
            addCriterion("complaintIp like", value, "complaintip");
            return (Criteria) this;
        }

        public Criteria andComplaintipNotLike(String value) {
            addCriterion("complaintIp not like", value, "complaintip");
            return (Criteria) this;
        }

        public Criteria andComplaintipIn(List<String> values) {
            addCriterion("complaintIp in", values, "complaintip");
            return (Criteria) this;
        }

        public Criteria andComplaintipNotIn(List<String> values) {
            addCriterion("complaintIp not in", values, "complaintip");
            return (Criteria) this;
        }

        public Criteria andComplaintipBetween(String value1, String value2) {
            addCriterion("complaintIp between", value1, value2, "complaintip");
            return (Criteria) this;
        }

        public Criteria andComplaintipNotBetween(String value1, String value2) {
            addCriterion("complaintIp not between", value1, value2, "complaintip");
            return (Criteria) this;
        }

        public Criteria andCheckIsNull() {
            addCriterion("check is null");
            return (Criteria) this;
        }

        public Criteria andCheckIsNotNull() {
            addCriterion("check is not null");
            return (Criteria) this;
        }

        public Criteria andCheckEqualTo(String value) {
            addCriterion("check =", value, "check");
            return (Criteria) this;
        }

        public Criteria andCheckNotEqualTo(String value) {
            addCriterion("check <>", value, "check");
            return (Criteria) this;
        }

        public Criteria andCheckGreaterThan(String value) {
            addCriterion("check >", value, "check");
            return (Criteria) this;
        }

        public Criteria andCheckGreaterThanOrEqualTo(String value) {
            addCriterion("check >=", value, "check");
            return (Criteria) this;
        }

        public Criteria andCheckLessThan(String value) {
            addCriterion("check <", value, "check");
            return (Criteria) this;
        }

        public Criteria andCheckLessThanOrEqualTo(String value) {
            addCriterion("check <=", value, "check");
            return (Criteria) this;
        }

        public Criteria andCheckLike(String value) {
            addCriterion("check like", value, "check");
            return (Criteria) this;
        }

        public Criteria andCheckNotLike(String value) {
            addCriterion("check not like", value, "check");
            return (Criteria) this;
        }

        public Criteria andCheckIn(List<String> values) {
            addCriterion("check in", values, "check");
            return (Criteria) this;
        }

        public Criteria andCheckNotIn(List<String> values) {
            addCriterion("check not in", values, "check");
            return (Criteria) this;
        }

        public Criteria andCheckBetween(String value1, String value2) {
            addCriterion("check between", value1, value2, "check");
            return (Criteria) this;
        }

        public Criteria andCheckNotBetween(String value1, String value2) {
            addCriterion("check not between", value1, value2, "check");
            return (Criteria) this;
        }

        public Criteria andChecktimeIsNull() {
            addCriterion("checkTime is null");
            return (Criteria) this;
        }

        public Criteria andChecktimeIsNotNull() {
            addCriterion("checkTime is not null");
            return (Criteria) this;
        }

        public Criteria andChecktimeEqualTo(Date value) {
            addCriterion("checkTime =", value, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeNotEqualTo(Date value) {
            addCriterion("checkTime <>", value, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeGreaterThan(Date value) {
            addCriterion("checkTime >", value, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeGreaterThanOrEqualTo(Date value) {
            addCriterion("checkTime >=", value, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeLessThan(Date value) {
            addCriterion("checkTime <", value, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeLessThanOrEqualTo(Date value) {
            addCriterion("checkTime <=", value, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeIn(List<Date> values) {
            addCriterion("checkTime in", values, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeNotIn(List<Date> values) {
            addCriterion("checkTime not in", values, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeBetween(Date value1, Date value2) {
            addCriterion("checkTime between", value1, value2, "checktime");
            return (Criteria) this;
        }

        public Criteria andChecktimeNotBetween(Date value1, Date value2) {
            addCriterion("checkTime not between", value1, value2, "checktime");
            return (Criteria) this;
        }

        public Criteria andCheckuseridIsNull() {
            addCriterion("checkUserId is null");
            return (Criteria) this;
        }

        public Criteria andCheckuseridIsNotNull() {
            addCriterion("checkUserId is not null");
            return (Criteria) this;
        }

        public Criteria andCheckuseridEqualTo(Long value) {
            addCriterion("checkUserId =", value, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridNotEqualTo(Long value) {
            addCriterion("checkUserId <>", value, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridGreaterThan(Long value) {
            addCriterion("checkUserId >", value, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridGreaterThanOrEqualTo(Long value) {
            addCriterion("checkUserId >=", value, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridLessThan(Long value) {
            addCriterion("checkUserId <", value, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridLessThanOrEqualTo(Long value) {
            addCriterion("checkUserId <=", value, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridIn(List<Long> values) {
            addCriterion("checkUserId in", values, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridNotIn(List<Long> values) {
            addCriterion("checkUserId not in", values, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridBetween(Long value1, Long value2) {
            addCriterion("checkUserId between", value1, value2, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andCheckuseridNotBetween(Long value1, Long value2) {
            addCriterion("checkUserId not between", value1, value2, "checkuserid");
            return (Criteria) this;
        }

        public Criteria andHandleprocessIsNull() {
            addCriterion("handleProcess is null");
            return (Criteria) this;
        }

        public Criteria andHandleprocessIsNotNull() {
            addCriterion("handleProcess is not null");
            return (Criteria) this;
        }

        public Criteria andHandleprocessEqualTo(String value) {
            addCriterion("handleProcess =", value, "handleprocess");
            return (Criteria) this;
        }

        public Criteria andHandleprocessNotEqualTo(String value) {
            addCriterion("handleProcess <>", value, "handleprocess");
            return (Criteria) this;
        }

        public Criteria andHandleprocessGreaterThan(String value) {
            addCriterion("handleProcess >", value, "handleprocess");
            return (Criteria) this;
        }

        public Criteria andHandleprocessGreaterThanOrEqualTo(String value) {
            addCriterion("handleProcess >=", value, "handleprocess");
            return (Criteria) this;
        }

        public Criteria andHandleprocessLessThan(String value) {
            addCriterion("handleProcess <", value, "handleprocess");
            return (Criteria) this;
        }

        public Criteria andHandleprocessLessThanOrEqualTo(String value) {
            addCriterion("handleProcess <=", value, "handleprocess");
            return (Criteria) this;
        }

        public Criteria andHandleprocessLike(String value) {
            addCriterion("handleProcess like", value, "handleprocess");
            return (Criteria) this;
        }

        public Criteria andHandleprocessNotLike(String value) {
            addCriterion("handleProcess not like", value, "handleprocess");
            return (Criteria) this;
        }

        public Criteria andHandleprocessIn(List<String> values) {
            addCriterion("handleProcess in", values, "handleprocess");
            return (Criteria) this;
        }

        public Criteria andHandleprocessNotIn(List<String> values) {
            addCriterion("handleProcess not in", values, "handleprocess");
            return (Criteria) this;
        }

        public Criteria andHandleprocessBetween(String value1, String value2) {
            addCriterion("handleProcess between", value1, value2, "handleprocess");
            return (Criteria) this;
        }

        public Criteria andHandleprocessNotBetween(String value1, String value2) {
            addCriterion("handleProcess not between", value1, value2, "handleprocess");
            return (Criteria) this;
        }

        public Criteria andHandleresultIsNull() {
            addCriterion("handleResult is null");
            return (Criteria) this;
        }

        public Criteria andHandleresultIsNotNull() {
            addCriterion("handleResult is not null");
            return (Criteria) this;
        }

        public Criteria andHandleresultEqualTo(String value) {
            addCriterion("handleResult =", value, "handleresult");
            return (Criteria) this;
        }

        public Criteria andHandleresultNotEqualTo(String value) {
            addCriterion("handleResult <>", value, "handleresult");
            return (Criteria) this;
        }

        public Criteria andHandleresultGreaterThan(String value) {
            addCriterion("handleResult >", value, "handleresult");
            return (Criteria) this;
        }

        public Criteria andHandleresultGreaterThanOrEqualTo(String value) {
            addCriterion("handleResult >=", value, "handleresult");
            return (Criteria) this;
        }

        public Criteria andHandleresultLessThan(String value) {
            addCriterion("handleResult <", value, "handleresult");
            return (Criteria) this;
        }

        public Criteria andHandleresultLessThanOrEqualTo(String value) {
            addCriterion("handleResult <=", value, "handleresult");
            return (Criteria) this;
        }

        public Criteria andHandleresultLike(String value) {
            addCriterion("handleResult like", value, "handleresult");
            return (Criteria) this;
        }

        public Criteria andHandleresultNotLike(String value) {
            addCriterion("handleResult not like", value, "handleresult");
            return (Criteria) this;
        }

        public Criteria andHandleresultIn(List<String> values) {
            addCriterion("handleResult in", values, "handleresult");
            return (Criteria) this;
        }

        public Criteria andHandleresultNotIn(List<String> values) {
            addCriterion("handleResult not in", values, "handleresult");
            return (Criteria) this;
        }

        public Criteria andHandleresultBetween(String value1, String value2) {
            addCriterion("handleResult between", value1, value2, "handleresult");
            return (Criteria) this;
        }

        public Criteria andHandleresultNotBetween(String value1, String value2) {
            addCriterion("handleResult not between", value1, value2, "handleresult");
            return (Criteria) this;
        }

        public Criteria andAddtimesIsNull() {
            addCriterion("addTimes is null");
            return (Criteria) this;
        }

        public Criteria andAddtimesIsNotNull() {
            addCriterion("addTimes is not null");
            return (Criteria) this;
        }

        public Criteria andAddtimesEqualTo(Date value) {
            addCriterion("addTimes =", value, "addtimes");
            return (Criteria) this;
        }

        public Criteria andAddtimesNotEqualTo(Date value) {
            addCriterion("addTimes <>", value, "addtimes");
            return (Criteria) this;
        }

        public Criteria andAddtimesGreaterThan(Date value) {
            addCriterion("addTimes >", value, "addtimes");
            return (Criteria) this;
        }

        public Criteria andAddtimesGreaterThanOrEqualTo(Date value) {
            addCriterion("addTimes >=", value, "addtimes");
            return (Criteria) this;
        }

        public Criteria andAddtimesLessThan(Date value) {
            addCriterion("addTimes <", value, "addtimes");
            return (Criteria) this;
        }

        public Criteria andAddtimesLessThanOrEqualTo(Date value) {
            addCriterion("addTimes <=", value, "addtimes");
            return (Criteria) this;
        }

        public Criteria andAddtimesIn(List<Date> values) {
            addCriterion("addTimes in", values, "addtimes");
            return (Criteria) this;
        }

        public Criteria andAddtimesNotIn(List<Date> values) {
            addCriterion("addTimes not in", values, "addtimes");
            return (Criteria) this;
        }

        public Criteria andAddtimesBetween(Date value1, Date value2) {
            addCriterion("addTimes between", value1, value2, "addtimes");
            return (Criteria) this;
        }

        public Criteria andAddtimesNotBetween(Date value1, Date value2) {
            addCriterion("addTimes not between", value1, value2, "addtimes");
            return (Criteria) this;
        }

        public Criteria andUpdatetimesIsNull() {
            addCriterion("updateTimes is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimesIsNotNull() {
            addCriterion("updateTimes is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimesEqualTo(Date value) {
            addCriterion("updateTimes =", value, "updatetimes");
            return (Criteria) this;
        }

        public Criteria andUpdatetimesNotEqualTo(Date value) {
            addCriterion("updateTimes <>", value, "updatetimes");
            return (Criteria) this;
        }

        public Criteria andUpdatetimesGreaterThan(Date value) {
            addCriterion("updateTimes >", value, "updatetimes");
            return (Criteria) this;
        }

        public Criteria andUpdatetimesGreaterThanOrEqualTo(Date value) {
            addCriterion("updateTimes >=", value, "updatetimes");
            return (Criteria) this;
        }

        public Criteria andUpdatetimesLessThan(Date value) {
            addCriterion("updateTimes <", value, "updatetimes");
            return (Criteria) this;
        }

        public Criteria andUpdatetimesLessThanOrEqualTo(Date value) {
            addCriterion("updateTimes <=", value, "updatetimes");
            return (Criteria) this;
        }

        public Criteria andUpdatetimesIn(List<Date> values) {
            addCriterion("updateTimes in", values, "updatetimes");
            return (Criteria) this;
        }

        public Criteria andUpdatetimesNotIn(List<Date> values) {
            addCriterion("updateTimes not in", values, "updatetimes");
            return (Criteria) this;
        }

        public Criteria andUpdatetimesBetween(Date value1, Date value2) {
            addCriterion("updateTimes between", value1, value2, "updatetimes");
            return (Criteria) this;
        }

        public Criteria andUpdatetimesNotBetween(Date value1, Date value2) {
            addCriterion("updateTimes not between", value1, value2, "updatetimes");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}