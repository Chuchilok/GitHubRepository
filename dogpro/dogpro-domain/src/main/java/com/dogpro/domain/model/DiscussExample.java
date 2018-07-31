package com.dogpro.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.PaginationUtil;

public class DiscussExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PaginationUtil pagination;

    public DiscussExample() {
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

        public Criteria andDiscussIdIsNull() {
            addCriterion("discuss_id is null");
            return (Criteria) this;
        }

        public Criteria andDiscussIdIsNotNull() {
            addCriterion("discuss_id is not null");
            return (Criteria) this;
        }

        public Criteria andDiscussIdEqualTo(Long value) {
            addCriterion("discuss_id =", value, "discussId");
            return (Criteria) this;
        }

        public Criteria andDiscussIdNotEqualTo(Long value) {
            addCriterion("discuss_id <>", value, "discussId");
            return (Criteria) this;
        }

        public Criteria andDiscussIdGreaterThan(Long value) {
            addCriterion("discuss_id >", value, "discussId");
            return (Criteria) this;
        }

        public Criteria andDiscussIdGreaterThanOrEqualTo(Long value) {
            addCriterion("discuss_id >=", value, "discussId");
            return (Criteria) this;
        }

        public Criteria andDiscussIdLessThan(Long value) {
            addCriterion("discuss_id <", value, "discussId");
            return (Criteria) this;
        }

        public Criteria andDiscussIdLessThanOrEqualTo(Long value) {
            addCriterion("discuss_id <=", value, "discussId");
            return (Criteria) this;
        }

        public Criteria andDiscussIdIn(List<Long> values) {
            addCriterion("discuss_id in", values, "discussId");
            return (Criteria) this;
        }

        public Criteria andDiscussIdNotIn(List<Long> values) {
            addCriterion("discuss_id not in", values, "discussId");
            return (Criteria) this;
        }

        public Criteria andDiscussIdBetween(Long value1, Long value2) {
            addCriterion("discuss_id between", value1, value2, "discussId");
            return (Criteria) this;
        }

        public Criteria andDiscussIdNotBetween(Long value1, Long value2) {
            addCriterion("discuss_id not between", value1, value2, "discussId");
            return (Criteria) this;
        }

        public Criteria andPidIsNull() {
            addCriterion("PId is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("PId is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(Long value) {
            addCriterion("PId =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Long value) {
            addCriterion("PId <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Long value) {
            addCriterion("PId >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Long value) {
            addCriterion("PId >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Long value) {
            addCriterion("PId <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Long value) {
            addCriterion("PId <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Long> values) {
            addCriterion("PId in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Long> values) {
            addCriterion("PId not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Long value1, Long value2) {
            addCriterion("PId between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Long value1, Long value2) {
            addCriterion("PId not between", value1, value2, "pid");
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

        public Criteria andDiscusstimeIsNull() {
            addCriterion("discussTime is null");
            return (Criteria) this;
        }

        public Criteria andDiscusstimeIsNotNull() {
            addCriterion("discussTime is not null");
            return (Criteria) this;
        }

        public Criteria andDiscusstimeEqualTo(Date value) {
            addCriterion("discussTime =", value, "discusstime");
            return (Criteria) this;
        }

        public Criteria andDiscusstimeNotEqualTo(Date value) {
            addCriterion("discussTime <>", value, "discusstime");
            return (Criteria) this;
        }

        public Criteria andDiscusstimeGreaterThan(Date value) {
            addCriterion("discussTime >", value, "discusstime");
            return (Criteria) this;
        }

        public Criteria andDiscusstimeGreaterThanOrEqualTo(Date value) {
            addCriterion("discussTime >=", value, "discusstime");
            return (Criteria) this;
        }

        public Criteria andDiscusstimeLessThan(Date value) {
            addCriterion("discussTime <", value, "discusstime");
            return (Criteria) this;
        }

        public Criteria andDiscusstimeLessThanOrEqualTo(Date value) {
            addCriterion("discussTime <=", value, "discusstime");
            return (Criteria) this;
        }

        public Criteria andDiscusstimeIn(List<Date> values) {
            addCriterion("discussTime in", values, "discusstime");
            return (Criteria) this;
        }

        public Criteria andDiscusstimeNotIn(List<Date> values) {
            addCriterion("discussTime not in", values, "discusstime");
            return (Criteria) this;
        }

        public Criteria andDiscusstimeBetween(Date value1, Date value2) {
            addCriterion("discussTime between", value1, value2, "discusstime");
            return (Criteria) this;
        }

        public Criteria andDiscusstimeNotBetween(Date value1, Date value2) {
            addCriterion("discussTime not between", value1, value2, "discusstime");
            return (Criteria) this;
        }

        public Criteria andDiscussipIsNull() {
            addCriterion("discussIp is null");
            return (Criteria) this;
        }

        public Criteria andDiscussipIsNotNull() {
            addCriterion("discussIp is not null");
            return (Criteria) this;
        }

        public Criteria andDiscussipEqualTo(String value) {
            addCriterion("discussIp =", value, "discussip");
            return (Criteria) this;
        }

        public Criteria andDiscussipNotEqualTo(String value) {
            addCriterion("discussIp <>", value, "discussip");
            return (Criteria) this;
        }

        public Criteria andDiscussipGreaterThan(String value) {
            addCriterion("discussIp >", value, "discussip");
            return (Criteria) this;
        }

        public Criteria andDiscussipGreaterThanOrEqualTo(String value) {
            addCriterion("discussIp >=", value, "discussip");
            return (Criteria) this;
        }

        public Criteria andDiscussipLessThan(String value) {
            addCriterion("discussIp <", value, "discussip");
            return (Criteria) this;
        }

        public Criteria andDiscussipLessThanOrEqualTo(String value) {
            addCriterion("discussIp <=", value, "discussip");
            return (Criteria) this;
        }

        public Criteria andDiscussipLike(String value) {
            addCriterion("discussIp like", value, "discussip");
            return (Criteria) this;
        }

        public Criteria andDiscussipNotLike(String value) {
            addCriterion("discussIp not like", value, "discussip");
            return (Criteria) this;
        }

        public Criteria andDiscussipIn(List<String> values) {
            addCriterion("discussIp in", values, "discussip");
            return (Criteria) this;
        }

        public Criteria andDiscussipNotIn(List<String> values) {
            addCriterion("discussIp not in", values, "discussip");
            return (Criteria) this;
        }

        public Criteria andDiscussipBetween(String value1, String value2) {
            addCriterion("discussIp between", value1, value2, "discussip");
            return (Criteria) this;
        }

        public Criteria andDiscussipNotBetween(String value1, String value2) {
            addCriterion("discussIp not between", value1, value2, "discussip");
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

        public Criteria andDiscusscontentIsNull() {
            addCriterion("discussContent is null");
            return (Criteria) this;
        }

        public Criteria andDiscusscontentIsNotNull() {
            addCriterion("discussContent is not null");
            return (Criteria) this;
        }

        public Criteria andDiscusscontentEqualTo(String value) {
            addCriterion("discussContent =", value, "discusscontent");
            return (Criteria) this;
        }

        public Criteria andDiscusscontentNotEqualTo(String value) {
            addCriterion("discussContent <>", value, "discusscontent");
            return (Criteria) this;
        }

        public Criteria andDiscusscontentGreaterThan(String value) {
            addCriterion("discussContent >", value, "discusscontent");
            return (Criteria) this;
        }

        public Criteria andDiscusscontentGreaterThanOrEqualTo(String value) {
            addCriterion("discussContent >=", value, "discusscontent");
            return (Criteria) this;
        }

        public Criteria andDiscusscontentLessThan(String value) {
            addCriterion("discussContent <", value, "discusscontent");
            return (Criteria) this;
        }

        public Criteria andDiscusscontentLessThanOrEqualTo(String value) {
            addCriterion("discussContent <=", value, "discusscontent");
            return (Criteria) this;
        }

        public Criteria andDiscusscontentLike(String value) {
            addCriterion("discussContent like", value, "discusscontent");
            return (Criteria) this;
        }

        public Criteria andDiscusscontentNotLike(String value) {
            addCriterion("discussContent not like", value, "discusscontent");
            return (Criteria) this;
        }

        public Criteria andDiscusscontentIn(List<String> values) {
            addCriterion("discussContent in", values, "discusscontent");
            return (Criteria) this;
        }

        public Criteria andDiscusscontentNotIn(List<String> values) {
            addCriterion("discussContent not in", values, "discusscontent");
            return (Criteria) this;
        }

        public Criteria andDiscusscontentBetween(String value1, String value2) {
            addCriterion("discussContent between", value1, value2, "discusscontent");
            return (Criteria) this;
        }

        public Criteria andDiscusscontentNotBetween(String value1, String value2) {
            addCriterion("discussContent not between", value1, value2, "discusscontent");
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