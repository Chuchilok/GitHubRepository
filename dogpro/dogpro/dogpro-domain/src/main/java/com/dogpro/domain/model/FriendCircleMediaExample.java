package com.dogpro.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.PaginationUtil;

public class FriendCircleMediaExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PaginationUtil pagination;

    public FriendCircleMediaExample() {
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

        public Criteria andMediaIdIsNull() {
            addCriterion("media_id is null");
            return (Criteria) this;
        }

        public Criteria andMediaIdIsNotNull() {
            addCriterion("media_id is not null");
            return (Criteria) this;
        }

        public Criteria andMediaIdEqualTo(Long value) {
            addCriterion("media_id =", value, "mediaId");
            return (Criteria) this;
        }

        public Criteria andMediaIdNotEqualTo(Long value) {
            addCriterion("media_id <>", value, "mediaId");
            return (Criteria) this;
        }

        public Criteria andMediaIdGreaterThan(Long value) {
            addCriterion("media_id >", value, "mediaId");
            return (Criteria) this;
        }

        public Criteria andMediaIdGreaterThanOrEqualTo(Long value) {
            addCriterion("media_id >=", value, "mediaId");
            return (Criteria) this;
        }

        public Criteria andMediaIdLessThan(Long value) {
            addCriterion("media_id <", value, "mediaId");
            return (Criteria) this;
        }

        public Criteria andMediaIdLessThanOrEqualTo(Long value) {
            addCriterion("media_id <=", value, "mediaId");
            return (Criteria) this;
        }

        public Criteria andMediaIdIn(List<Long> values) {
            addCriterion("media_id in", values, "mediaId");
            return (Criteria) this;
        }

        public Criteria andMediaIdNotIn(List<Long> values) {
            addCriterion("media_id not in", values, "mediaId");
            return (Criteria) this;
        }

        public Criteria andMediaIdBetween(Long value1, Long value2) {
            addCriterion("media_id between", value1, value2, "mediaId");
            return (Criteria) this;
        }

        public Criteria andMediaIdNotBetween(Long value1, Long value2) {
            addCriterion("media_id not between", value1, value2, "mediaId");
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

        public Criteria andMediaurlIsNull() {
            addCriterion("mediaUrl is null");
            return (Criteria) this;
        }

        public Criteria andMediaurlIsNotNull() {
            addCriterion("mediaUrl is not null");
            return (Criteria) this;
        }

        public Criteria andMediaurlEqualTo(String value) {
            addCriterion("mediaUrl =", value, "mediaurl");
            return (Criteria) this;
        }

        public Criteria andMediaurlNotEqualTo(String value) {
            addCriterion("mediaUrl <>", value, "mediaurl");
            return (Criteria) this;
        }

        public Criteria andMediaurlGreaterThan(String value) {
            addCriterion("mediaUrl >", value, "mediaurl");
            return (Criteria) this;
        }

        public Criteria andMediaurlGreaterThanOrEqualTo(String value) {
            addCriterion("mediaUrl >=", value, "mediaurl");
            return (Criteria) this;
        }

        public Criteria andMediaurlLessThan(String value) {
            addCriterion("mediaUrl <", value, "mediaurl");
            return (Criteria) this;
        }

        public Criteria andMediaurlLessThanOrEqualTo(String value) {
            addCriterion("mediaUrl <=", value, "mediaurl");
            return (Criteria) this;
        }

        public Criteria andMediaurlLike(String value) {
            addCriterion("mediaUrl like", value, "mediaurl");
            return (Criteria) this;
        }

        public Criteria andMediaurlNotLike(String value) {
            addCriterion("mediaUrl not like", value, "mediaurl");
            return (Criteria) this;
        }

        public Criteria andMediaurlIn(List<String> values) {
            addCriterion("mediaUrl in", values, "mediaurl");
            return (Criteria) this;
        }

        public Criteria andMediaurlNotIn(List<String> values) {
            addCriterion("mediaUrl not in", values, "mediaurl");
            return (Criteria) this;
        }

        public Criteria andMediaurlBetween(String value1, String value2) {
            addCriterion("mediaUrl between", value1, value2, "mediaurl");
            return (Criteria) this;
        }

        public Criteria andMediaurlNotBetween(String value1, String value2) {
            addCriterion("mediaUrl not between", value1, value2, "mediaurl");
            return (Criteria) this;
        }

        public Criteria andMediasuburlIsNull() {
            addCriterion("mediaSubUrl is null");
            return (Criteria) this;
        }

        public Criteria andMediasuburlIsNotNull() {
            addCriterion("mediaSubUrl is not null");
            return (Criteria) this;
        }

        public Criteria andMediasuburlEqualTo(String value) {
            addCriterion("mediaSubUrl =", value, "mediasuburl");
            return (Criteria) this;
        }

        public Criteria andMediasuburlNotEqualTo(String value) {
            addCriterion("mediaSubUrl <>", value, "mediasuburl");
            return (Criteria) this;
        }

        public Criteria andMediasuburlGreaterThan(String value) {
            addCriterion("mediaSubUrl >", value, "mediasuburl");
            return (Criteria) this;
        }

        public Criteria andMediasuburlGreaterThanOrEqualTo(String value) {
            addCriterion("mediaSubUrl >=", value, "mediasuburl");
            return (Criteria) this;
        }

        public Criteria andMediasuburlLessThan(String value) {
            addCriterion("mediaSubUrl <", value, "mediasuburl");
            return (Criteria) this;
        }

        public Criteria andMediasuburlLessThanOrEqualTo(String value) {
            addCriterion("mediaSubUrl <=", value, "mediasuburl");
            return (Criteria) this;
        }

        public Criteria andMediasuburlLike(String value) {
            addCriterion("mediaSubUrl like", value, "mediasuburl");
            return (Criteria) this;
        }

        public Criteria andMediasuburlNotLike(String value) {
            addCriterion("mediaSubUrl not like", value, "mediasuburl");
            return (Criteria) this;
        }

        public Criteria andMediasuburlIn(List<String> values) {
            addCriterion("mediaSubUrl in", values, "mediasuburl");
            return (Criteria) this;
        }

        public Criteria andMediasuburlNotIn(List<String> values) {
            addCriterion("mediaSubUrl not in", values, "mediasuburl");
            return (Criteria) this;
        }

        public Criteria andMediasuburlBetween(String value1, String value2) {
            addCriterion("mediaSubUrl between", value1, value2, "mediasuburl");
            return (Criteria) this;
        }

        public Criteria andMediasuburlNotBetween(String value1, String value2) {
            addCriterion("mediaSubUrl not between", value1, value2, "mediasuburl");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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