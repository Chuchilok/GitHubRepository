package com.dogpro.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.PaginationUtil;

public class SettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PaginationUtil pagination;

    public SettingExample() {
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

        public Criteria andNotifyIsNull() {
            addCriterion("notify is null");
            return (Criteria) this;
        }

        public Criteria andNotifyIsNotNull() {
            addCriterion("notify is not null");
            return (Criteria) this;
        }

        public Criteria andNotifyEqualTo(Integer value) {
            addCriterion("notify =", value, "notify");
            return (Criteria) this;
        }

        public Criteria andNotifyNotEqualTo(Integer value) {
            addCriterion("notify <>", value, "notify");
            return (Criteria) this;
        }

        public Criteria andNotifyGreaterThan(Integer value) {
            addCriterion("notify >", value, "notify");
            return (Criteria) this;
        }

        public Criteria andNotifyGreaterThanOrEqualTo(Integer value) {
            addCriterion("notify >=", value, "notify");
            return (Criteria) this;
        }

        public Criteria andNotifyLessThan(Integer value) {
            addCriterion("notify <", value, "notify");
            return (Criteria) this;
        }

        public Criteria andNotifyLessThanOrEqualTo(Integer value) {
            addCriterion("notify <=", value, "notify");
            return (Criteria) this;
        }

        public Criteria andNotifyIn(List<Integer> values) {
            addCriterion("notify in", values, "notify");
            return (Criteria) this;
        }

        public Criteria andNotifyNotIn(List<Integer> values) {
            addCriterion("notify not in", values, "notify");
            return (Criteria) this;
        }

        public Criteria andNotifyBetween(Integer value1, Integer value2) {
            addCriterion("notify between", value1, value2, "notify");
            return (Criteria) this;
        }

        public Criteria andNotifyNotBetween(Integer value1, Integer value2) {
            addCriterion("notify not between", value1, value2, "notify");
            return (Criteria) this;
        }

        public Criteria andSoundIsNull() {
            addCriterion("sound is null");
            return (Criteria) this;
        }

        public Criteria andSoundIsNotNull() {
            addCriterion("sound is not null");
            return (Criteria) this;
        }

        public Criteria andSoundEqualTo(Integer value) {
            addCriterion("sound =", value, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundNotEqualTo(Integer value) {
            addCriterion("sound <>", value, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundGreaterThan(Integer value) {
            addCriterion("sound >", value, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundGreaterThanOrEqualTo(Integer value) {
            addCriterion("sound >=", value, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundLessThan(Integer value) {
            addCriterion("sound <", value, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundLessThanOrEqualTo(Integer value) {
            addCriterion("sound <=", value, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundIn(List<Integer> values) {
            addCriterion("sound in", values, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundNotIn(List<Integer> values) {
            addCriterion("sound not in", values, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundBetween(Integer value1, Integer value2) {
            addCriterion("sound between", value1, value2, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundNotBetween(Integer value1, Integer value2) {
            addCriterion("sound not between", value1, value2, "sound");
            return (Criteria) this;
        }

        public Criteria andShakeIsNull() {
            addCriterion("shake is null");
            return (Criteria) this;
        }

        public Criteria andShakeIsNotNull() {
            addCriterion("shake is not null");
            return (Criteria) this;
        }

        public Criteria andShakeEqualTo(Integer value) {
            addCriterion("shake =", value, "shake");
            return (Criteria) this;
        }

        public Criteria andShakeNotEqualTo(Integer value) {
            addCriterion("shake <>", value, "shake");
            return (Criteria) this;
        }

        public Criteria andShakeGreaterThan(Integer value) {
            addCriterion("shake >", value, "shake");
            return (Criteria) this;
        }

        public Criteria andShakeGreaterThanOrEqualTo(Integer value) {
            addCriterion("shake >=", value, "shake");
            return (Criteria) this;
        }

        public Criteria andShakeLessThan(Integer value) {
            addCriterion("shake <", value, "shake");
            return (Criteria) this;
        }

        public Criteria andShakeLessThanOrEqualTo(Integer value) {
            addCriterion("shake <=", value, "shake");
            return (Criteria) this;
        }

        public Criteria andShakeIn(List<Integer> values) {
            addCriterion("shake in", values, "shake");
            return (Criteria) this;
        }

        public Criteria andShakeNotIn(List<Integer> values) {
            addCriterion("shake not in", values, "shake");
            return (Criteria) this;
        }

        public Criteria andShakeBetween(Integer value1, Integer value2) {
            addCriterion("shake between", value1, value2, "shake");
            return (Criteria) this;
        }

        public Criteria andShakeNotBetween(Integer value1, Integer value2) {
            addCriterion("shake not between", value1, value2, "shake");
            return (Criteria) this;
        }

        public Criteria andHandsetIsNull() {
            addCriterion("handset is null");
            return (Criteria) this;
        }

        public Criteria andHandsetIsNotNull() {
            addCriterion("handset is not null");
            return (Criteria) this;
        }

        public Criteria andHandsetEqualTo(Integer value) {
            addCriterion("handset =", value, "handset");
            return (Criteria) this;
        }

        public Criteria andHandsetNotEqualTo(Integer value) {
            addCriterion("handset <>", value, "handset");
            return (Criteria) this;
        }

        public Criteria andHandsetGreaterThan(Integer value) {
            addCriterion("handset >", value, "handset");
            return (Criteria) this;
        }

        public Criteria andHandsetGreaterThanOrEqualTo(Integer value) {
            addCriterion("handset >=", value, "handset");
            return (Criteria) this;
        }

        public Criteria andHandsetLessThan(Integer value) {
            addCriterion("handset <", value, "handset");
            return (Criteria) this;
        }

        public Criteria andHandsetLessThanOrEqualTo(Integer value) {
            addCriterion("handset <=", value, "handset");
            return (Criteria) this;
        }

        public Criteria andHandsetIn(List<Integer> values) {
            addCriterion("handset in", values, "handset");
            return (Criteria) this;
        }

        public Criteria andHandsetNotIn(List<Integer> values) {
            addCriterion("handset not in", values, "handset");
            return (Criteria) this;
        }

        public Criteria andHandsetBetween(Integer value1, Integer value2) {
            addCriterion("handset between", value1, value2, "handset");
            return (Criteria) this;
        }

        public Criteria andHandsetNotBetween(Integer value1, Integer value2) {
            addCriterion("handset not between", value1, value2, "handset");
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