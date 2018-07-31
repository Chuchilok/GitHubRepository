package com.webpublish.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.webpublish.common.utils.PaginationUtil;

public class PublishRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PaginationUtil pagination;

    public PublishRecordExample() {
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
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andVersionIdIsNull() {
            addCriterion("version_id is null");
            return (Criteria) this;
        }

        public Criteria andVersionIdIsNotNull() {
            addCriterion("version_id is not null");
            return (Criteria) this;
        }

        public Criteria andVersionIdEqualTo(Long value) {
            addCriterion("version_id =", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdNotEqualTo(Long value) {
            addCriterion("version_id <>", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdGreaterThan(Long value) {
            addCriterion("version_id >", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdGreaterThanOrEqualTo(Long value) {
            addCriterion("version_id >=", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdLessThan(Long value) {
            addCriterion("version_id <", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdLessThanOrEqualTo(Long value) {
            addCriterion("version_id <=", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdIn(List<Long> values) {
            addCriterion("version_id in", values, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdNotIn(List<Long> values) {
            addCriterion("version_id not in", values, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdBetween(Long value1, Long value2) {
            addCriterion("version_id between", value1, value2, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdNotBetween(Long value1, Long value2) {
            addCriterion("version_id not between", value1, value2, "versionId");
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

        public Criteria andPublishIpIsNull() {
            addCriterion("publish_ip is null");
            return (Criteria) this;
        }

        public Criteria andPublishIpIsNotNull() {
            addCriterion("publish_ip is not null");
            return (Criteria) this;
        }

        public Criteria andPublishIpEqualTo(String value) {
            addCriterion("publish_ip =", value, "publishIp");
            return (Criteria) this;
        }

        public Criteria andPublishIpNotEqualTo(String value) {
            addCriterion("publish_ip <>", value, "publishIp");
            return (Criteria) this;
        }

        public Criteria andPublishIpGreaterThan(String value) {
            addCriterion("publish_ip >", value, "publishIp");
            return (Criteria) this;
        }

        public Criteria andPublishIpGreaterThanOrEqualTo(String value) {
            addCriterion("publish_ip >=", value, "publishIp");
            return (Criteria) this;
        }

        public Criteria andPublishIpLessThan(String value) {
            addCriterion("publish_ip <", value, "publishIp");
            return (Criteria) this;
        }

        public Criteria andPublishIpLessThanOrEqualTo(String value) {
            addCriterion("publish_ip <=", value, "publishIp");
            return (Criteria) this;
        }

        public Criteria andPublishIpLike(String value) {
            addCriterion("publish_ip like", value, "publishIp");
            return (Criteria) this;
        }

        public Criteria andPublishIpNotLike(String value) {
            addCriterion("publish_ip not like", value, "publishIp");
            return (Criteria) this;
        }

        public Criteria andPublishIpIn(List<String> values) {
            addCriterion("publish_ip in", values, "publishIp");
            return (Criteria) this;
        }

        public Criteria andPublishIpNotIn(List<String> values) {
            addCriterion("publish_ip not in", values, "publishIp");
            return (Criteria) this;
        }

        public Criteria andPublishIpBetween(String value1, String value2) {
            addCriterion("publish_ip between", value1, value2, "publishIp");
            return (Criteria) this;
        }

        public Criteria andPublishIpNotBetween(String value1, String value2) {
            addCriterion("publish_ip not between", value1, value2, "publishIp");
            return (Criteria) this;
        }

        public Criteria andPublishPortIsNull() {
            addCriterion("publish_port is null");
            return (Criteria) this;
        }

        public Criteria andPublishPortIsNotNull() {
            addCriterion("publish_port is not null");
            return (Criteria) this;
        }

        public Criteria andPublishPortEqualTo(Integer value) {
            addCriterion("publish_port =", value, "publishPort");
            return (Criteria) this;
        }

        public Criteria andPublishPortNotEqualTo(Integer value) {
            addCriterion("publish_port <>", value, "publishPort");
            return (Criteria) this;
        }

        public Criteria andPublishPortGreaterThan(Integer value) {
            addCriterion("publish_port >", value, "publishPort");
            return (Criteria) this;
        }

        public Criteria andPublishPortGreaterThanOrEqualTo(Integer value) {
            addCriterion("publish_port >=", value, "publishPort");
            return (Criteria) this;
        }

        public Criteria andPublishPortLessThan(Integer value) {
            addCriterion("publish_port <", value, "publishPort");
            return (Criteria) this;
        }

        public Criteria andPublishPortLessThanOrEqualTo(Integer value) {
            addCriterion("publish_port <=", value, "publishPort");
            return (Criteria) this;
        }

        public Criteria andPublishPortIn(List<Integer> values) {
            addCriterion("publish_port in", values, "publishPort");
            return (Criteria) this;
        }

        public Criteria andPublishPortNotIn(List<Integer> values) {
            addCriterion("publish_port not in", values, "publishPort");
            return (Criteria) this;
        }

        public Criteria andPublishPortBetween(Integer value1, Integer value2) {
            addCriterion("publish_port between", value1, value2, "publishPort");
            return (Criteria) this;
        }

        public Criteria andPublishPortNotBetween(Integer value1, Integer value2) {
            addCriterion("publish_port not between", value1, value2, "publishPort");
            return (Criteria) this;
        }

        public Criteria andPublishtimesIsNull() {
            addCriterion("publishTimes is null");
            return (Criteria) this;
        }

        public Criteria andPublishtimesIsNotNull() {
            addCriterion("publishTimes is not null");
            return (Criteria) this;
        }

        public Criteria andPublishtimesEqualTo(Date value) {
            addCriterion("publishTimes =", value, "publishtimes");
            return (Criteria) this;
        }

        public Criteria andPublishtimesNotEqualTo(Date value) {
            addCriterion("publishTimes <>", value, "publishtimes");
            return (Criteria) this;
        }

        public Criteria andPublishtimesGreaterThan(Date value) {
            addCriterion("publishTimes >", value, "publishtimes");
            return (Criteria) this;
        }

        public Criteria andPublishtimesGreaterThanOrEqualTo(Date value) {
            addCriterion("publishTimes >=", value, "publishtimes");
            return (Criteria) this;
        }

        public Criteria andPublishtimesLessThan(Date value) {
            addCriterion("publishTimes <", value, "publishtimes");
            return (Criteria) this;
        }

        public Criteria andPublishtimesLessThanOrEqualTo(Date value) {
            addCriterion("publishTimes <=", value, "publishtimes");
            return (Criteria) this;
        }

        public Criteria andPublishtimesIn(List<Date> values) {
            addCriterion("publishTimes in", values, "publishtimes");
            return (Criteria) this;
        }

        public Criteria andPublishtimesNotIn(List<Date> values) {
            addCriterion("publishTimes not in", values, "publishtimes");
            return (Criteria) this;
        }

        public Criteria andPublishtimesBetween(Date value1, Date value2) {
            addCriterion("publishTimes between", value1, value2, "publishtimes");
            return (Criteria) this;
        }

        public Criteria andPublishtimesNotBetween(Date value1, Date value2) {
            addCriterion("publishTimes not between", value1, value2, "publishtimes");
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