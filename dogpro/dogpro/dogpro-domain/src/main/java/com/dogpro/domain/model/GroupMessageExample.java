package com.dogpro.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.PaginationUtil;

public class GroupMessageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PaginationUtil pagination;

    public GroupMessageExample() {
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

        public Criteria andSenduseridIsNull() {
            addCriterion("sendUserId is null");
            return (Criteria) this;
        }

        public Criteria andSenduseridIsNotNull() {
            addCriterion("sendUserId is not null");
            return (Criteria) this;
        }

        public Criteria andSenduseridEqualTo(Long value) {
            addCriterion("sendUserId =", value, "senduserid");
            return (Criteria) this;
        }

        public Criteria andSenduseridNotEqualTo(Long value) {
            addCriterion("sendUserId <>", value, "senduserid");
            return (Criteria) this;
        }

        public Criteria andSenduseridGreaterThan(Long value) {
            addCriterion("sendUserId >", value, "senduserid");
            return (Criteria) this;
        }

        public Criteria andSenduseridGreaterThanOrEqualTo(Long value) {
            addCriterion("sendUserId >=", value, "senduserid");
            return (Criteria) this;
        }

        public Criteria andSenduseridLessThan(Long value) {
            addCriterion("sendUserId <", value, "senduserid");
            return (Criteria) this;
        }

        public Criteria andSenduseridLessThanOrEqualTo(Long value) {
            addCriterion("sendUserId <=", value, "senduserid");
            return (Criteria) this;
        }

        public Criteria andSenduseridIn(List<Long> values) {
            addCriterion("sendUserId in", values, "senduserid");
            return (Criteria) this;
        }

        public Criteria andSenduseridNotIn(List<Long> values) {
            addCriterion("sendUserId not in", values, "senduserid");
            return (Criteria) this;
        }

        public Criteria andSenduseridBetween(Long value1, Long value2) {
            addCriterion("sendUserId between", value1, value2, "senduserid");
            return (Criteria) this;
        }

        public Criteria andSenduseridNotBetween(Long value1, Long value2) {
            addCriterion("sendUserId not between", value1, value2, "senduserid");
            return (Criteria) this;
        }

        public Criteria andAcceptuseridIsNull() {
            addCriterion("acceptUserId is null");
            return (Criteria) this;
        }

        public Criteria andAcceptuseridIsNotNull() {
            addCriterion("acceptUserId is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptuseridEqualTo(Long value) {
            addCriterion("acceptUserId =", value, "acceptuserid");
            return (Criteria) this;
        }

        public Criteria andAcceptuseridNotEqualTo(Long value) {
            addCriterion("acceptUserId <>", value, "acceptuserid");
            return (Criteria) this;
        }

        public Criteria andAcceptuseridGreaterThan(Long value) {
            addCriterion("acceptUserId >", value, "acceptuserid");
            return (Criteria) this;
        }

        public Criteria andAcceptuseridGreaterThanOrEqualTo(Long value) {
            addCriterion("acceptUserId >=", value, "acceptuserid");
            return (Criteria) this;
        }

        public Criteria andAcceptuseridLessThan(Long value) {
            addCriterion("acceptUserId <", value, "acceptuserid");
            return (Criteria) this;
        }

        public Criteria andAcceptuseridLessThanOrEqualTo(Long value) {
            addCriterion("acceptUserId <=", value, "acceptuserid");
            return (Criteria) this;
        }

        public Criteria andAcceptuseridIn(List<Long> values) {
            addCriterion("acceptUserId in", values, "acceptuserid");
            return (Criteria) this;
        }

        public Criteria andAcceptuseridNotIn(List<Long> values) {
            addCriterion("acceptUserId not in", values, "acceptuserid");
            return (Criteria) this;
        }

        public Criteria andAcceptuseridBetween(Long value1, Long value2) {
            addCriterion("acceptUserId between", value1, value2, "acceptuserid");
            return (Criteria) this;
        }

        public Criteria andAcceptuseridNotBetween(Long value1, Long value2) {
            addCriterion("acceptUserId not between", value1, value2, "acceptuserid");
            return (Criteria) this;
        }

        public Criteria andLocationidIsNull() {
            addCriterion("locationId is null");
            return (Criteria) this;
        }

        public Criteria andLocationidIsNotNull() {
            addCriterion("locationId is not null");
            return (Criteria) this;
        }

        public Criteria andLocationidEqualTo(Long value) {
            addCriterion("locationId =", value, "locationid");
            return (Criteria) this;
        }

        public Criteria andLocationidNotEqualTo(Long value) {
            addCriterion("locationId <>", value, "locationid");
            return (Criteria) this;
        }

        public Criteria andLocationidGreaterThan(Long value) {
            addCriterion("locationId >", value, "locationid");
            return (Criteria) this;
        }

        public Criteria andLocationidGreaterThanOrEqualTo(Long value) {
            addCriterion("locationId >=", value, "locationid");
            return (Criteria) this;
        }

        public Criteria andLocationidLessThan(Long value) {
            addCriterion("locationId <", value, "locationid");
            return (Criteria) this;
        }

        public Criteria andLocationidLessThanOrEqualTo(Long value) {
            addCriterion("locationId <=", value, "locationid");
            return (Criteria) this;
        }

        public Criteria andLocationidIn(List<Long> values) {
            addCriterion("locationId in", values, "locationid");
            return (Criteria) this;
        }

        public Criteria andLocationidNotIn(List<Long> values) {
            addCriterion("locationId not in", values, "locationid");
            return (Criteria) this;
        }

        public Criteria andLocationidBetween(Long value1, Long value2) {
            addCriterion("locationId between", value1, value2, "locationid");
            return (Criteria) this;
        }

        public Criteria andLocationidNotBetween(Long value1, Long value2) {
            addCriterion("locationId not between", value1, value2, "locationid");
            return (Criteria) this;
        }

        public Criteria andAccepttimesIsNull() {
            addCriterion("acceptTimes is null");
            return (Criteria) this;
        }

        public Criteria andAccepttimesIsNotNull() {
            addCriterion("acceptTimes is not null");
            return (Criteria) this;
        }

        public Criteria andAccepttimesEqualTo(Date value) {
            addCriterion("acceptTimes =", value, "accepttimes");
            return (Criteria) this;
        }

        public Criteria andAccepttimesNotEqualTo(Date value) {
            addCriterion("acceptTimes <>", value, "accepttimes");
            return (Criteria) this;
        }

        public Criteria andAccepttimesGreaterThan(Date value) {
            addCriterion("acceptTimes >", value, "accepttimes");
            return (Criteria) this;
        }

        public Criteria andAccepttimesGreaterThanOrEqualTo(Date value) {
            addCriterion("acceptTimes >=", value, "accepttimes");
            return (Criteria) this;
        }

        public Criteria andAccepttimesLessThan(Date value) {
            addCriterion("acceptTimes <", value, "accepttimes");
            return (Criteria) this;
        }

        public Criteria andAccepttimesLessThanOrEqualTo(Date value) {
            addCriterion("acceptTimes <=", value, "accepttimes");
            return (Criteria) this;
        }

        public Criteria andAccepttimesIn(List<Date> values) {
            addCriterion("acceptTimes in", values, "accepttimes");
            return (Criteria) this;
        }

        public Criteria andAccepttimesNotIn(List<Date> values) {
            addCriterion("acceptTimes not in", values, "accepttimes");
            return (Criteria) this;
        }

        public Criteria andAccepttimesBetween(Date value1, Date value2) {
            addCriterion("acceptTimes between", value1, value2, "accepttimes");
            return (Criteria) this;
        }

        public Criteria andAccepttimesNotBetween(Date value1, Date value2) {
            addCriterion("acceptTimes not between", value1, value2, "accepttimes");
            return (Criteria) this;
        }

        public Criteria andAcceptlongitudeIsNull() {
            addCriterion("acceptLongitude is null");
            return (Criteria) this;
        }

        public Criteria andAcceptlongitudeIsNotNull() {
            addCriterion("acceptLongitude is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptlongitudeEqualTo(Float value) {
            addCriterion("acceptLongitude =", value, "acceptlongitude");
            return (Criteria) this;
        }

        public Criteria andAcceptlongitudeNotEqualTo(Float value) {
            addCriterion("acceptLongitude <>", value, "acceptlongitude");
            return (Criteria) this;
        }

        public Criteria andAcceptlongitudeGreaterThan(Float value) {
            addCriterion("acceptLongitude >", value, "acceptlongitude");
            return (Criteria) this;
        }

        public Criteria andAcceptlongitudeGreaterThanOrEqualTo(Float value) {
            addCriterion("acceptLongitude >=", value, "acceptlongitude");
            return (Criteria) this;
        }

        public Criteria andAcceptlongitudeLessThan(Float value) {
            addCriterion("acceptLongitude <", value, "acceptlongitude");
            return (Criteria) this;
        }

        public Criteria andAcceptlongitudeLessThanOrEqualTo(Float value) {
            addCriterion("acceptLongitude <=", value, "acceptlongitude");
            return (Criteria) this;
        }

        public Criteria andAcceptlongitudeIn(List<Float> values) {
            addCriterion("acceptLongitude in", values, "acceptlongitude");
            return (Criteria) this;
        }

        public Criteria andAcceptlongitudeNotIn(List<Float> values) {
            addCriterion("acceptLongitude not in", values, "acceptlongitude");
            return (Criteria) this;
        }

        public Criteria andAcceptlongitudeBetween(Float value1, Float value2) {
            addCriterion("acceptLongitude between", value1, value2, "acceptlongitude");
            return (Criteria) this;
        }

        public Criteria andAcceptlongitudeNotBetween(Float value1, Float value2) {
            addCriterion("acceptLongitude not between", value1, value2, "acceptlongitude");
            return (Criteria) this;
        }

        public Criteria andAcceptlatitudeIsNull() {
            addCriterion("acceptLatitude is null");
            return (Criteria) this;
        }

        public Criteria andAcceptlatitudeIsNotNull() {
            addCriterion("acceptLatitude is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptlatitudeEqualTo(Float value) {
            addCriterion("acceptLatitude =", value, "acceptlatitude");
            return (Criteria) this;
        }

        public Criteria andAcceptlatitudeNotEqualTo(Float value) {
            addCriterion("acceptLatitude <>", value, "acceptlatitude");
            return (Criteria) this;
        }

        public Criteria andAcceptlatitudeGreaterThan(Float value) {
            addCriterion("acceptLatitude >", value, "acceptlatitude");
            return (Criteria) this;
        }

        public Criteria andAcceptlatitudeGreaterThanOrEqualTo(Float value) {
            addCriterion("acceptLatitude >=", value, "acceptlatitude");
            return (Criteria) this;
        }

        public Criteria andAcceptlatitudeLessThan(Float value) {
            addCriterion("acceptLatitude <", value, "acceptlatitude");
            return (Criteria) this;
        }

        public Criteria andAcceptlatitudeLessThanOrEqualTo(Float value) {
            addCriterion("acceptLatitude <=", value, "acceptlatitude");
            return (Criteria) this;
        }

        public Criteria andAcceptlatitudeIn(List<Float> values) {
            addCriterion("acceptLatitude in", values, "acceptlatitude");
            return (Criteria) this;
        }

        public Criteria andAcceptlatitudeNotIn(List<Float> values) {
            addCriterion("acceptLatitude not in", values, "acceptlatitude");
            return (Criteria) this;
        }

        public Criteria andAcceptlatitudeBetween(Float value1, Float value2) {
            addCriterion("acceptLatitude between", value1, value2, "acceptlatitude");
            return (Criteria) this;
        }

        public Criteria andAcceptlatitudeNotBetween(Float value1, Float value2) {
            addCriterion("acceptLatitude not between", value1, value2, "acceptlatitude");
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andKeyssIsNull() {
            addCriterion("keyss is null");
            return (Criteria) this;
        }

        public Criteria andKeyssIsNotNull() {
            addCriterion("keyss is not null");
            return (Criteria) this;
        }

        public Criteria andKeyssEqualTo(Long value) {
            addCriterion("keyss =", value, "keyss");
            return (Criteria) this;
        }

        public Criteria andKeyssNotEqualTo(Long value) {
            addCriterion("keyss <>", value, "keyss");
            return (Criteria) this;
        }

        public Criteria andKeyssGreaterThan(Long value) {
            addCriterion("keyss >", value, "keyss");
            return (Criteria) this;
        }

        public Criteria andKeyssGreaterThanOrEqualTo(Long value) {
            addCriterion("keyss >=", value, "keyss");
            return (Criteria) this;
        }

        public Criteria andKeyssLessThan(Long value) {
            addCriterion("keyss <", value, "keyss");
            return (Criteria) this;
        }

        public Criteria andKeyssLessThanOrEqualTo(Long value) {
            addCriterion("keyss <=", value, "keyss");
            return (Criteria) this;
        }

        public Criteria andKeyssIn(List<Long> values) {
            addCriterion("keyss in", values, "keyss");
            return (Criteria) this;
        }

        public Criteria andKeyssNotIn(List<Long> values) {
            addCriterion("keyss not in", values, "keyss");
            return (Criteria) this;
        }

        public Criteria andKeyssBetween(Long value1, Long value2) {
            addCriterion("keyss between", value1, value2, "keyss");
            return (Criteria) this;
        }

        public Criteria andKeyssNotBetween(Long value1, Long value2) {
            addCriterion("keyss not between", value1, value2, "keyss");
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