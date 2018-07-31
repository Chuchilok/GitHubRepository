package com.dogpro.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.PaginationUtil;

public class MessageMediaExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PaginationUtil pagination;

    public MessageMediaExample() {
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

        public Criteria andMsgmediaidIsNull() {
            addCriterion("msgMediaId is null");
            return (Criteria) this;
        }

        public Criteria andMsgmediaidIsNotNull() {
            addCriterion("msgMediaId is not null");
            return (Criteria) this;
        }

        public Criteria andMsgmediaidEqualTo(Long value) {
            addCriterion("msgMediaId =", value, "msgmediaid");
            return (Criteria) this;
        }

        public Criteria andMsgmediaidNotEqualTo(Long value) {
            addCriterion("msgMediaId <>", value, "msgmediaid");
            return (Criteria) this;
        }

        public Criteria andMsgmediaidGreaterThan(Long value) {
            addCriterion("msgMediaId >", value, "msgmediaid");
            return (Criteria) this;
        }

        public Criteria andMsgmediaidGreaterThanOrEqualTo(Long value) {
            addCriterion("msgMediaId >=", value, "msgmediaid");
            return (Criteria) this;
        }

        public Criteria andMsgmediaidLessThan(Long value) {
            addCriterion("msgMediaId <", value, "msgmediaid");
            return (Criteria) this;
        }

        public Criteria andMsgmediaidLessThanOrEqualTo(Long value) {
            addCriterion("msgMediaId <=", value, "msgmediaid");
            return (Criteria) this;
        }

        public Criteria andMsgmediaidIn(List<Long> values) {
            addCriterion("msgMediaId in", values, "msgmediaid");
            return (Criteria) this;
        }

        public Criteria andMsgmediaidNotIn(List<Long> values) {
            addCriterion("msgMediaId not in", values, "msgmediaid");
            return (Criteria) this;
        }

        public Criteria andMsgmediaidBetween(Long value1, Long value2) {
            addCriterion("msgMediaId between", value1, value2, "msgmediaid");
            return (Criteria) this;
        }

        public Criteria andMsgmediaidNotBetween(Long value1, Long value2) {
            addCriterion("msgMediaId not between", value1, value2, "msgmediaid");
            return (Criteria) this;
        }

        public Criteria andUploadtimesIsNull() {
            addCriterion("uploadTimes is null");
            return (Criteria) this;
        }

        public Criteria andUploadtimesIsNotNull() {
            addCriterion("uploadTimes is not null");
            return (Criteria) this;
        }

        public Criteria andUploadtimesEqualTo(Date value) {
            addCriterion("uploadTimes =", value, "uploadtimes");
            return (Criteria) this;
        }

        public Criteria andUploadtimesNotEqualTo(Date value) {
            addCriterion("uploadTimes <>", value, "uploadtimes");
            return (Criteria) this;
        }

        public Criteria andUploadtimesGreaterThan(Date value) {
            addCriterion("uploadTimes >", value, "uploadtimes");
            return (Criteria) this;
        }

        public Criteria andUploadtimesGreaterThanOrEqualTo(Date value) {
            addCriterion("uploadTimes >=", value, "uploadtimes");
            return (Criteria) this;
        }

        public Criteria andUploadtimesLessThan(Date value) {
            addCriterion("uploadTimes <", value, "uploadtimes");
            return (Criteria) this;
        }

        public Criteria andUploadtimesLessThanOrEqualTo(Date value) {
            addCriterion("uploadTimes <=", value, "uploadtimes");
            return (Criteria) this;
        }

        public Criteria andUploadtimesIn(List<Date> values) {
            addCriterion("uploadTimes in", values, "uploadtimes");
            return (Criteria) this;
        }

        public Criteria andUploadtimesNotIn(List<Date> values) {
            addCriterion("uploadTimes not in", values, "uploadtimes");
            return (Criteria) this;
        }

        public Criteria andUploadtimesBetween(Date value1, Date value2) {
            addCriterion("uploadTimes between", value1, value2, "uploadtimes");
            return (Criteria) this;
        }

        public Criteria andUploadtimesNotBetween(Date value1, Date value2) {
            addCriterion("uploadTimes not between", value1, value2, "uploadtimes");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Long value) {
            addCriterion("userId =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Long value) {
            addCriterion("userId <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Long value) {
            addCriterion("userId >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Long value) {
            addCriterion("userId >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Long value) {
            addCriterion("userId <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Long value) {
            addCriterion("userId <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Long> values) {
            addCriterion("userId in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Long> values) {
            addCriterion("userId not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Long value1, Long value2) {
            addCriterion("userId between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Long value1, Long value2) {
            addCriterion("userId not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andMediatypeIsNull() {
            addCriterion("mediaType is null");
            return (Criteria) this;
        }

        public Criteria andMediatypeIsNotNull() {
            addCriterion("mediaType is not null");
            return (Criteria) this;
        }

        public Criteria andMediatypeEqualTo(Integer value) {
            addCriterion("mediaType =", value, "mediatype");
            return (Criteria) this;
        }

        public Criteria andMediatypeNotEqualTo(Integer value) {
            addCriterion("mediaType <>", value, "mediatype");
            return (Criteria) this;
        }

        public Criteria andMediatypeGreaterThan(Integer value) {
            addCriterion("mediaType >", value, "mediatype");
            return (Criteria) this;
        }

        public Criteria andMediatypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("mediaType >=", value, "mediatype");
            return (Criteria) this;
        }

        public Criteria andMediatypeLessThan(Integer value) {
            addCriterion("mediaType <", value, "mediatype");
            return (Criteria) this;
        }

        public Criteria andMediatypeLessThanOrEqualTo(Integer value) {
            addCriterion("mediaType <=", value, "mediatype");
            return (Criteria) this;
        }

        public Criteria andMediatypeIn(List<Integer> values) {
            addCriterion("mediaType in", values, "mediatype");
            return (Criteria) this;
        }

        public Criteria andMediatypeNotIn(List<Integer> values) {
            addCriterion("mediaType not in", values, "mediatype");
            return (Criteria) this;
        }

        public Criteria andMediatypeBetween(Integer value1, Integer value2) {
            addCriterion("mediaType between", value1, value2, "mediatype");
            return (Criteria) this;
        }

        public Criteria andMediatypeNotBetween(Integer value1, Integer value2) {
            addCriterion("mediaType not between", value1, value2, "mediatype");
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

        public Criteria andMessageidIsNull() {
            addCriterion("messageId is null");
            return (Criteria) this;
        }

        public Criteria andMessageidIsNotNull() {
            addCriterion("messageId is not null");
            return (Criteria) this;
        }

        public Criteria andMessageidEqualTo(Long value) {
            addCriterion("messageId =", value, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidNotEqualTo(Long value) {
            addCriterion("messageId <>", value, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidGreaterThan(Long value) {
            addCriterion("messageId >", value, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidGreaterThanOrEqualTo(Long value) {
            addCriterion("messageId >=", value, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidLessThan(Long value) {
            addCriterion("messageId <", value, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidLessThanOrEqualTo(Long value) {
            addCriterion("messageId <=", value, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidIn(List<Long> values) {
            addCriterion("messageId in", values, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidNotIn(List<Long> values) {
            addCriterion("messageId not in", values, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidBetween(Long value1, Long value2) {
            addCriterion("messageId between", value1, value2, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidNotBetween(Long value1, Long value2) {
            addCriterion("messageId not between", value1, value2, "messageid");
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

        public Criteria andResourcecodeIsNull() {
            addCriterion("resourceCode is null");
            return (Criteria) this;
        }

        public Criteria andResourcecodeIsNotNull() {
            addCriterion("resourceCode is not null");
            return (Criteria) this;
        }

        public Criteria andResourcecodeEqualTo(Long value) {
            addCriterion("resourceCode =", value, "resourcecode");
            return (Criteria) this;
        }

        public Criteria andResourcecodeNotEqualTo(Long value) {
            addCriterion("resourceCode <>", value, "resourcecode");
            return (Criteria) this;
        }

        public Criteria andResourcecodeGreaterThan(Long value) {
            addCriterion("resourceCode >", value, "resourcecode");
            return (Criteria) this;
        }

        public Criteria andResourcecodeGreaterThanOrEqualTo(Long value) {
            addCriterion("resourceCode >=", value, "resourcecode");
            return (Criteria) this;
        }

        public Criteria andResourcecodeLessThan(Long value) {
            addCriterion("resourceCode <", value, "resourcecode");
            return (Criteria) this;
        }

        public Criteria andResourcecodeLessThanOrEqualTo(Long value) {
            addCriterion("resourceCode <=", value, "resourcecode");
            return (Criteria) this;
        }

        public Criteria andResourcecodeIn(List<Long> values) {
            addCriterion("resourceCode in", values, "resourcecode");
            return (Criteria) this;
        }

        public Criteria andResourcecodeNotIn(List<Long> values) {
            addCriterion("resourceCode not in", values, "resourcecode");
            return (Criteria) this;
        }

        public Criteria andResourcecodeBetween(Long value1, Long value2) {
            addCriterion("resourceCode between", value1, value2, "resourcecode");
            return (Criteria) this;
        }

        public Criteria andResourcecodeNotBetween(Long value1, Long value2) {
            addCriterion("resourceCode not between", value1, value2, "resourcecode");
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