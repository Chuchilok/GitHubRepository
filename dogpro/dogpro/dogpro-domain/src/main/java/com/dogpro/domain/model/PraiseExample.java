package com.dogpro.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.PaginationUtil;

public class PraiseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PaginationUtil pagination;

    public PraiseExample() {
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

        public Criteria andPraiseIdIsNull() {
            addCriterion("praise_id is null");
            return (Criteria) this;
        }

        public Criteria andPraiseIdIsNotNull() {
            addCriterion("praise_id is not null");
            return (Criteria) this;
        }

        public Criteria andPraiseIdEqualTo(Long value) {
            addCriterion("praise_id =", value, "praiseId");
            return (Criteria) this;
        }

        public Criteria andPraiseIdNotEqualTo(Long value) {
            addCriterion("praise_id <>", value, "praiseId");
            return (Criteria) this;
        }

        public Criteria andPraiseIdGreaterThan(Long value) {
            addCriterion("praise_id >", value, "praiseId");
            return (Criteria) this;
        }

        public Criteria andPraiseIdGreaterThanOrEqualTo(Long value) {
            addCriterion("praise_id >=", value, "praiseId");
            return (Criteria) this;
        }

        public Criteria andPraiseIdLessThan(Long value) {
            addCriterion("praise_id <", value, "praiseId");
            return (Criteria) this;
        }

        public Criteria andPraiseIdLessThanOrEqualTo(Long value) {
            addCriterion("praise_id <=", value, "praiseId");
            return (Criteria) this;
        }

        public Criteria andPraiseIdIn(List<Long> values) {
            addCriterion("praise_id in", values, "praiseId");
            return (Criteria) this;
        }

        public Criteria andPraiseIdNotIn(List<Long> values) {
            addCriterion("praise_id not in", values, "praiseId");
            return (Criteria) this;
        }

        public Criteria andPraiseIdBetween(Long value1, Long value2) {
            addCriterion("praise_id between", value1, value2, "praiseId");
            return (Criteria) this;
        }

        public Criteria andPraiseIdNotBetween(Long value1, Long value2) {
            addCriterion("praise_id not between", value1, value2, "praiseId");
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

        public Criteria andPraisetimeIsNull() {
            addCriterion("praiseTime is null");
            return (Criteria) this;
        }

        public Criteria andPraisetimeIsNotNull() {
            addCriterion("praiseTime is not null");
            return (Criteria) this;
        }

        public Criteria andPraisetimeEqualTo(Date value) {
            addCriterion("praiseTime =", value, "praisetime");
            return (Criteria) this;
        }

        public Criteria andPraisetimeNotEqualTo(Date value) {
            addCriterion("praiseTime <>", value, "praisetime");
            return (Criteria) this;
        }

        public Criteria andPraisetimeGreaterThan(Date value) {
            addCriterion("praiseTime >", value, "praisetime");
            return (Criteria) this;
        }

        public Criteria andPraisetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("praiseTime >=", value, "praisetime");
            return (Criteria) this;
        }

        public Criteria andPraisetimeLessThan(Date value) {
            addCriterion("praiseTime <", value, "praisetime");
            return (Criteria) this;
        }

        public Criteria andPraisetimeLessThanOrEqualTo(Date value) {
            addCriterion("praiseTime <=", value, "praisetime");
            return (Criteria) this;
        }

        public Criteria andPraisetimeIn(List<Date> values) {
            addCriterion("praiseTime in", values, "praisetime");
            return (Criteria) this;
        }

        public Criteria andPraisetimeNotIn(List<Date> values) {
            addCriterion("praiseTime not in", values, "praisetime");
            return (Criteria) this;
        }

        public Criteria andPraisetimeBetween(Date value1, Date value2) {
            addCriterion("praiseTime between", value1, value2, "praisetime");
            return (Criteria) this;
        }

        public Criteria andPraisetimeNotBetween(Date value1, Date value2) {
            addCriterion("praiseTime not between", value1, value2, "praisetime");
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

        public Criteria andPraisestarIsNull() {
            addCriterion("praiseStar is null");
            return (Criteria) this;
        }

        public Criteria andPraisestarIsNotNull() {
            addCriterion("praiseStar is not null");
            return (Criteria) this;
        }

        public Criteria andPraisestarEqualTo(String value) {
            addCriterion("praiseStar =", value, "praisestar");
            return (Criteria) this;
        }

        public Criteria andPraisestarNotEqualTo(String value) {
            addCriterion("praiseStar <>", value, "praisestar");
            return (Criteria) this;
        }

        public Criteria andPraisestarGreaterThan(String value) {
            addCriterion("praiseStar >", value, "praisestar");
            return (Criteria) this;
        }

        public Criteria andPraisestarGreaterThanOrEqualTo(String value) {
            addCriterion("praiseStar >=", value, "praisestar");
            return (Criteria) this;
        }

        public Criteria andPraisestarLessThan(String value) {
            addCriterion("praiseStar <", value, "praisestar");
            return (Criteria) this;
        }

        public Criteria andPraisestarLessThanOrEqualTo(String value) {
            addCriterion("praiseStar <=", value, "praisestar");
            return (Criteria) this;
        }

        public Criteria andPraisestarLike(String value) {
            addCriterion("praiseStar like", value, "praisestar");
            return (Criteria) this;
        }

        public Criteria andPraisestarNotLike(String value) {
            addCriterion("praiseStar not like", value, "praisestar");
            return (Criteria) this;
        }

        public Criteria andPraisestarIn(List<String> values) {
            addCriterion("praiseStar in", values, "praisestar");
            return (Criteria) this;
        }

        public Criteria andPraisestarNotIn(List<String> values) {
            addCriterion("praiseStar not in", values, "praisestar");
            return (Criteria) this;
        }

        public Criteria andPraisestarBetween(String value1, String value2) {
            addCriterion("praiseStar between", value1, value2, "praisestar");
            return (Criteria) this;
        }

        public Criteria andPraisestarNotBetween(String value1, String value2) {
            addCriterion("praiseStar not between", value1, value2, "praisestar");
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