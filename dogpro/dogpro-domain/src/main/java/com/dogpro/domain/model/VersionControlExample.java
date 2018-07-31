package com.dogpro.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.PaginationUtil;

public class VersionControlExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PaginationUtil pagination;

    public VersionControlExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andVersionnameIsNull() {
            addCriterion("VersionName is null");
            return (Criteria) this;
        }

        public Criteria andVersionnameIsNotNull() {
            addCriterion("VersionName is not null");
            return (Criteria) this;
        }

        public Criteria andVersionnameEqualTo(String value) {
            addCriterion("VersionName =", value, "versionname");
            return (Criteria) this;
        }

        public Criteria andVersionnameNotEqualTo(String value) {
            addCriterion("VersionName <>", value, "versionname");
            return (Criteria) this;
        }

        public Criteria andVersionnameGreaterThan(String value) {
            addCriterion("VersionName >", value, "versionname");
            return (Criteria) this;
        }

        public Criteria andVersionnameGreaterThanOrEqualTo(String value) {
            addCriterion("VersionName >=", value, "versionname");
            return (Criteria) this;
        }

        public Criteria andVersionnameLessThan(String value) {
            addCriterion("VersionName <", value, "versionname");
            return (Criteria) this;
        }

        public Criteria andVersionnameLessThanOrEqualTo(String value) {
            addCriterion("VersionName <=", value, "versionname");
            return (Criteria) this;
        }

        public Criteria andVersionnameLike(String value) {
            addCriterion("VersionName like", value, "versionname");
            return (Criteria) this;
        }

        public Criteria andVersionnameNotLike(String value) {
            addCriterion("VersionName not like", value, "versionname");
            return (Criteria) this;
        }

        public Criteria andVersionnameIn(List<String> values) {
            addCriterion("VersionName in", values, "versionname");
            return (Criteria) this;
        }

        public Criteria andVersionnameNotIn(List<String> values) {
            addCriterion("VersionName not in", values, "versionname");
            return (Criteria) this;
        }

        public Criteria andVersionnameBetween(String value1, String value2) {
            addCriterion("VersionName between", value1, value2, "versionname");
            return (Criteria) this;
        }

        public Criteria andVersionnameNotBetween(String value1, String value2) {
            addCriterion("VersionName not between", value1, value2, "versionname");
            return (Criteria) this;
        }

        public Criteria andDevicetypeIsNull() {
            addCriterion("DeviceType is null");
            return (Criteria) this;
        }

        public Criteria andDevicetypeIsNotNull() {
            addCriterion("DeviceType is not null");
            return (Criteria) this;
        }

        public Criteria andDevicetypeEqualTo(Integer value) {
            addCriterion("DeviceType =", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeNotEqualTo(Integer value) {
            addCriterion("DeviceType <>", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeGreaterThan(Integer value) {
            addCriterion("DeviceType >", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("DeviceType >=", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeLessThan(Integer value) {
            addCriterion("DeviceType <", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeLessThanOrEqualTo(Integer value) {
            addCriterion("DeviceType <=", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeIn(List<Integer> values) {
            addCriterion("DeviceType in", values, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeNotIn(List<Integer> values) {
            addCriterion("DeviceType not in", values, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeBetween(Integer value1, Integer value2) {
            addCriterion("DeviceType between", value1, value2, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeNotBetween(Integer value1, Integer value2) {
            addCriterion("DeviceType not between", value1, value2, "devicetype");
            return (Criteria) this;
        }

        public Criteria andVersionnumberIsNull() {
            addCriterion("VersionNumber is null");
            return (Criteria) this;
        }

        public Criteria andVersionnumberIsNotNull() {
            addCriterion("VersionNumber is not null");
            return (Criteria) this;
        }

        public Criteria andVersionnumberEqualTo(Integer value) {
            addCriterion("VersionNumber =", value, "versionnumber");
            return (Criteria) this;
        }

        public Criteria andVersionnumberNotEqualTo(Integer value) {
            addCriterion("VersionNumber <>", value, "versionnumber");
            return (Criteria) this;
        }

        public Criteria andVersionnumberGreaterThan(Integer value) {
            addCriterion("VersionNumber >", value, "versionnumber");
            return (Criteria) this;
        }

        public Criteria andVersionnumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("VersionNumber >=", value, "versionnumber");
            return (Criteria) this;
        }

        public Criteria andVersionnumberLessThan(Integer value) {
            addCriterion("VersionNumber <", value, "versionnumber");
            return (Criteria) this;
        }

        public Criteria andVersionnumberLessThanOrEqualTo(Integer value) {
            addCriterion("VersionNumber <=", value, "versionnumber");
            return (Criteria) this;
        }

        public Criteria andVersionnumberIn(List<Integer> values) {
            addCriterion("VersionNumber in", values, "versionnumber");
            return (Criteria) this;
        }

        public Criteria andVersionnumberNotIn(List<Integer> values) {
            addCriterion("VersionNumber not in", values, "versionnumber");
            return (Criteria) this;
        }

        public Criteria andVersionnumberBetween(Integer value1, Integer value2) {
            addCriterion("VersionNumber between", value1, value2, "versionnumber");
            return (Criteria) this;
        }

        public Criteria andVersionnumberNotBetween(Integer value1, Integer value2) {
            addCriterion("VersionNumber not between", value1, value2, "versionnumber");
            return (Criteria) this;
        }

        public Criteria andDownloadurlIsNull() {
            addCriterion("DownloadURL is null");
            return (Criteria) this;
        }

        public Criteria andDownloadurlIsNotNull() {
            addCriterion("DownloadURL is not null");
            return (Criteria) this;
        }

        public Criteria andDownloadurlEqualTo(String value) {
            addCriterion("DownloadURL =", value, "downloadurl");
            return (Criteria) this;
        }

        public Criteria andDownloadurlNotEqualTo(String value) {
            addCriterion("DownloadURL <>", value, "downloadurl");
            return (Criteria) this;
        }

        public Criteria andDownloadurlGreaterThan(String value) {
            addCriterion("DownloadURL >", value, "downloadurl");
            return (Criteria) this;
        }

        public Criteria andDownloadurlGreaterThanOrEqualTo(String value) {
            addCriterion("DownloadURL >=", value, "downloadurl");
            return (Criteria) this;
        }

        public Criteria andDownloadurlLessThan(String value) {
            addCriterion("DownloadURL <", value, "downloadurl");
            return (Criteria) this;
        }

        public Criteria andDownloadurlLessThanOrEqualTo(String value) {
            addCriterion("DownloadURL <=", value, "downloadurl");
            return (Criteria) this;
        }

        public Criteria andDownloadurlLike(String value) {
            addCriterion("DownloadURL like", value, "downloadurl");
            return (Criteria) this;
        }

        public Criteria andDownloadurlNotLike(String value) {
            addCriterion("DownloadURL not like", value, "downloadurl");
            return (Criteria) this;
        }

        public Criteria andDownloadurlIn(List<String> values) {
            addCriterion("DownloadURL in", values, "downloadurl");
            return (Criteria) this;
        }

        public Criteria andDownloadurlNotIn(List<String> values) {
            addCriterion("DownloadURL not in", values, "downloadurl");
            return (Criteria) this;
        }

        public Criteria andDownloadurlBetween(String value1, String value2) {
            addCriterion("DownloadURL between", value1, value2, "downloadurl");
            return (Criteria) this;
        }

        public Criteria andDownloadurlNotBetween(String value1, String value2) {
            addCriterion("DownloadURL not between", value1, value2, "downloadurl");
            return (Criteria) this;
        }

        public Criteria andReleasetimeIsNull() {
            addCriterion("ReleaseTime is null");
            return (Criteria) this;
        }

        public Criteria andReleasetimeIsNotNull() {
            addCriterion("ReleaseTime is not null");
            return (Criteria) this;
        }

        public Criteria andReleasetimeEqualTo(Date value) {
            addCriterion("ReleaseTime =", value, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeNotEqualTo(Date value) {
            addCriterion("ReleaseTime <>", value, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeGreaterThan(Date value) {
            addCriterion("ReleaseTime >", value, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ReleaseTime >=", value, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeLessThan(Date value) {
            addCriterion("ReleaseTime <", value, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeLessThanOrEqualTo(Date value) {
            addCriterion("ReleaseTime <=", value, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeIn(List<Date> values) {
            addCriterion("ReleaseTime in", values, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeNotIn(List<Date> values) {
            addCriterion("ReleaseTime not in", values, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeBetween(Date value1, Date value2) {
            addCriterion("ReleaseTime between", value1, value2, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeNotBetween(Date value1, Date value2) {
            addCriterion("ReleaseTime not between", value1, value2, "releasetime");
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

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
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