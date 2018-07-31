package com.dogpro.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.PaginationUtil;

public class WalkingDogGroupExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PaginationUtil pagination;

    public WalkingDogGroupExample() {
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

        public Criteria andGroupidIsNull() {
            addCriterion("groupId is null");
            return (Criteria) this;
        }

        public Criteria andGroupidIsNotNull() {
            addCriterion("groupId is not null");
            return (Criteria) this;
        }

        public Criteria andGroupidEqualTo(Long value) {
            addCriterion("groupId =", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidNotEqualTo(Long value) {
            addCriterion("groupId <>", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidGreaterThan(Long value) {
            addCriterion("groupId >", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidGreaterThanOrEqualTo(Long value) {
            addCriterion("groupId >=", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidLessThan(Long value) {
            addCriterion("groupId <", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidLessThanOrEqualTo(Long value) {
            addCriterion("groupId <=", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidIn(List<Long> values) {
            addCriterion("groupId in", values, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidNotIn(List<Long> values) {
            addCriterion("groupId not in", values, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidBetween(Long value1, Long value2) {
            addCriterion("groupId between", value1, value2, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidNotBetween(Long value1, Long value2) {
            addCriterion("groupId not between", value1, value2, "groupid");
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

        public Criteria andDoglocationidIsNull() {
            addCriterion("doglocationId is null");
            return (Criteria) this;
        }

        public Criteria andDoglocationidIsNotNull() {
            addCriterion("doglocationId is not null");
            return (Criteria) this;
        }

        public Criteria andDoglocationidEqualTo(Long value) {
            addCriterion("doglocationId =", value, "doglocationid");
            return (Criteria) this;
        }

        public Criteria andDoglocationidNotEqualTo(Long value) {
            addCriterion("doglocationId <>", value, "doglocationid");
            return (Criteria) this;
        }

        public Criteria andDoglocationidGreaterThan(Long value) {
            addCriterion("doglocationId >", value, "doglocationid");
            return (Criteria) this;
        }

        public Criteria andDoglocationidGreaterThanOrEqualTo(Long value) {
            addCriterion("doglocationId >=", value, "doglocationid");
            return (Criteria) this;
        }

        public Criteria andDoglocationidLessThan(Long value) {
            addCriterion("doglocationId <", value, "doglocationid");
            return (Criteria) this;
        }

        public Criteria andDoglocationidLessThanOrEqualTo(Long value) {
            addCriterion("doglocationId <=", value, "doglocationid");
            return (Criteria) this;
        }

        public Criteria andDoglocationidIn(List<Long> values) {
            addCriterion("doglocationId in", values, "doglocationid");
            return (Criteria) this;
        }

        public Criteria andDoglocationidNotIn(List<Long> values) {
            addCriterion("doglocationId not in", values, "doglocationid");
            return (Criteria) this;
        }

        public Criteria andDoglocationidBetween(Long value1, Long value2) {
            addCriterion("doglocationId between", value1, value2, "doglocationid");
            return (Criteria) this;
        }

        public Criteria andDoglocationidNotBetween(Long value1, Long value2) {
            addCriterion("doglocationId not between", value1, value2, "doglocationid");
            return (Criteria) this;
        }

        public Criteria andIsdisturbIsNull() {
            addCriterion("isDisturb is null");
            return (Criteria) this;
        }

        public Criteria andIsdisturbIsNotNull() {
            addCriterion("isDisturb is not null");
            return (Criteria) this;
        }

        public Criteria andIsdisturbEqualTo(Integer value) {
            addCriterion("isDisturb =", value, "isdisturb");
            return (Criteria) this;
        }

        public Criteria andIsdisturbNotEqualTo(Integer value) {
            addCriterion("isDisturb <>", value, "isdisturb");
            return (Criteria) this;
        }

        public Criteria andIsdisturbGreaterThan(Integer value) {
            addCriterion("isDisturb >", value, "isdisturb");
            return (Criteria) this;
        }

        public Criteria andIsdisturbGreaterThanOrEqualTo(Integer value) {
            addCriterion("isDisturb >=", value, "isdisturb");
            return (Criteria) this;
        }

        public Criteria andIsdisturbLessThan(Integer value) {
            addCriterion("isDisturb <", value, "isdisturb");
            return (Criteria) this;
        }

        public Criteria andIsdisturbLessThanOrEqualTo(Integer value) {
            addCriterion("isDisturb <=", value, "isdisturb");
            return (Criteria) this;
        }

        public Criteria andIsdisturbIn(List<Integer> values) {
            addCriterion("isDisturb in", values, "isdisturb");
            return (Criteria) this;
        }

        public Criteria andIsdisturbNotIn(List<Integer> values) {
            addCriterion("isDisturb not in", values, "isdisturb");
            return (Criteria) this;
        }

        public Criteria andIsdisturbBetween(Integer value1, Integer value2) {
            addCriterion("isDisturb between", value1, value2, "isdisturb");
            return (Criteria) this;
        }

        public Criteria andIsdisturbNotBetween(Integer value1, Integer value2) {
            addCriterion("isDisturb not between", value1, value2, "isdisturb");
            return (Criteria) this;
        }

        public Criteria andJointimesIsNull() {
            addCriterion("joinTimes is null");
            return (Criteria) this;
        }

        public Criteria andJointimesIsNotNull() {
            addCriterion("joinTimes is not null");
            return (Criteria) this;
        }

        public Criteria andJointimesEqualTo(Date value) {
            addCriterion("joinTimes =", value, "jointimes");
            return (Criteria) this;
        }

        public Criteria andJointimesNotEqualTo(Date value) {
            addCriterion("joinTimes <>", value, "jointimes");
            return (Criteria) this;
        }

        public Criteria andJointimesGreaterThan(Date value) {
            addCriterion("joinTimes >", value, "jointimes");
            return (Criteria) this;
        }

        public Criteria andJointimesGreaterThanOrEqualTo(Date value) {
            addCriterion("joinTimes >=", value, "jointimes");
            return (Criteria) this;
        }

        public Criteria andJointimesLessThan(Date value) {
            addCriterion("joinTimes <", value, "jointimes");
            return (Criteria) this;
        }

        public Criteria andJointimesLessThanOrEqualTo(Date value) {
            addCriterion("joinTimes <=", value, "jointimes");
            return (Criteria) this;
        }

        public Criteria andJointimesIn(List<Date> values) {
            addCriterion("joinTimes in", values, "jointimes");
            return (Criteria) this;
        }

        public Criteria andJointimesNotIn(List<Date> values) {
            addCriterion("joinTimes not in", values, "jointimes");
            return (Criteria) this;
        }

        public Criteria andJointimesBetween(Date value1, Date value2) {
            addCriterion("joinTimes between", value1, value2, "jointimes");
            return (Criteria) this;
        }

        public Criteria andJointimesNotBetween(Date value1, Date value2) {
            addCriterion("joinTimes not between", value1, value2, "jointimes");
            return (Criteria) this;
        }

        public Criteria andJoinlongitudeIsNull() {
            addCriterion("joinLongitude is null");
            return (Criteria) this;
        }

        public Criteria andJoinlongitudeIsNotNull() {
            addCriterion("joinLongitude is not null");
            return (Criteria) this;
        }

        public Criteria andJoinlongitudeEqualTo(BigDecimal value) {
            addCriterion("joinLongitude =", value, "joinlongitude");
            return (Criteria) this;
        }

        public Criteria andJoinlongitudeNotEqualTo(BigDecimal value) {
            addCriterion("joinLongitude <>", value, "joinlongitude");
            return (Criteria) this;
        }

        public Criteria andJoinlongitudeGreaterThan(BigDecimal value) {
            addCriterion("joinLongitude >", value, "joinlongitude");
            return (Criteria) this;
        }

        public Criteria andJoinlongitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("joinLongitude >=", value, "joinlongitude");
            return (Criteria) this;
        }

        public Criteria andJoinlongitudeLessThan(BigDecimal value) {
            addCriterion("joinLongitude <", value, "joinlongitude");
            return (Criteria) this;
        }

        public Criteria andJoinlongitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("joinLongitude <=", value, "joinlongitude");
            return (Criteria) this;
        }

        public Criteria andJoinlongitudeIn(List<BigDecimal> values) {
            addCriterion("joinLongitude in", values, "joinlongitude");
            return (Criteria) this;
        }

        public Criteria andJoinlongitudeNotIn(List<BigDecimal> values) {
            addCriterion("joinLongitude not in", values, "joinlongitude");
            return (Criteria) this;
        }

        public Criteria andJoinlongitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("joinLongitude between", value1, value2, "joinlongitude");
            return (Criteria) this;
        }

        public Criteria andJoinlongitudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("joinLongitude not between", value1, value2, "joinlongitude");
            return (Criteria) this;
        }

        public Criteria andJoinlatitudeIsNull() {
            addCriterion("joinLatitude is null");
            return (Criteria) this;
        }

        public Criteria andJoinlatitudeIsNotNull() {
            addCriterion("joinLatitude is not null");
            return (Criteria) this;
        }

        public Criteria andJoinlatitudeEqualTo(BigDecimal value) {
            addCriterion("joinLatitude =", value, "joinlatitude");
            return (Criteria) this;
        }

        public Criteria andJoinlatitudeNotEqualTo(BigDecimal value) {
            addCriterion("joinLatitude <>", value, "joinlatitude");
            return (Criteria) this;
        }

        public Criteria andJoinlatitudeGreaterThan(BigDecimal value) {
            addCriterion("joinLatitude >", value, "joinlatitude");
            return (Criteria) this;
        }

        public Criteria andJoinlatitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("joinLatitude >=", value, "joinlatitude");
            return (Criteria) this;
        }

        public Criteria andJoinlatitudeLessThan(BigDecimal value) {
            addCriterion("joinLatitude <", value, "joinlatitude");
            return (Criteria) this;
        }

        public Criteria andJoinlatitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("joinLatitude <=", value, "joinlatitude");
            return (Criteria) this;
        }

        public Criteria andJoinlatitudeIn(List<BigDecimal> values) {
            addCriterion("joinLatitude in", values, "joinlatitude");
            return (Criteria) this;
        }

        public Criteria andJoinlatitudeNotIn(List<BigDecimal> values) {
            addCriterion("joinLatitude not in", values, "joinlatitude");
            return (Criteria) this;
        }

        public Criteria andJoinlatitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("joinLatitude between", value1, value2, "joinlatitude");
            return (Criteria) this;
        }

        public Criteria andJoinlatitudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("joinLatitude not between", value1, value2, "joinlatitude");
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

        public Criteria andEndtimesIsNull() {
            addCriterion("endTimes is null");
            return (Criteria) this;
        }

        public Criteria andEndtimesIsNotNull() {
            addCriterion("endTimes is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimesEqualTo(Date value) {
            addCriterion("endTimes =", value, "endtimes");
            return (Criteria) this;
        }

        public Criteria andEndtimesNotEqualTo(Date value) {
            addCriterion("endTimes <>", value, "endtimes");
            return (Criteria) this;
        }

        public Criteria andEndtimesGreaterThan(Date value) {
            addCriterion("endTimes >", value, "endtimes");
            return (Criteria) this;
        }

        public Criteria andEndtimesGreaterThanOrEqualTo(Date value) {
            addCriterion("endTimes >=", value, "endtimes");
            return (Criteria) this;
        }

        public Criteria andEndtimesLessThan(Date value) {
            addCriterion("endTimes <", value, "endtimes");
            return (Criteria) this;
        }

        public Criteria andEndtimesLessThanOrEqualTo(Date value) {
            addCriterion("endTimes <=", value, "endtimes");
            return (Criteria) this;
        }

        public Criteria andEndtimesIn(List<Date> values) {
            addCriterion("endTimes in", values, "endtimes");
            return (Criteria) this;
        }

        public Criteria andEndtimesNotIn(List<Date> values) {
            addCriterion("endTimes not in", values, "endtimes");
            return (Criteria) this;
        }

        public Criteria andEndtimesBetween(Date value1, Date value2) {
            addCriterion("endTimes between", value1, value2, "endtimes");
            return (Criteria) this;
        }

        public Criteria andEndtimesNotBetween(Date value1, Date value2) {
            addCriterion("endTimes not between", value1, value2, "endtimes");
            return (Criteria) this;
        }

        public Criteria andEndlongitudeIsNull() {
            addCriterion("endLongitude is null");
            return (Criteria) this;
        }

        public Criteria andEndlongitudeIsNotNull() {
            addCriterion("endLongitude is not null");
            return (Criteria) this;
        }

        public Criteria andEndlongitudeEqualTo(BigDecimal value) {
            addCriterion("endLongitude =", value, "endlongitude");
            return (Criteria) this;
        }

        public Criteria andEndlongitudeNotEqualTo(BigDecimal value) {
            addCriterion("endLongitude <>", value, "endlongitude");
            return (Criteria) this;
        }

        public Criteria andEndlongitudeGreaterThan(BigDecimal value) {
            addCriterion("endLongitude >", value, "endlongitude");
            return (Criteria) this;
        }

        public Criteria andEndlongitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("endLongitude >=", value, "endlongitude");
            return (Criteria) this;
        }

        public Criteria andEndlongitudeLessThan(BigDecimal value) {
            addCriterion("endLongitude <", value, "endlongitude");
            return (Criteria) this;
        }

        public Criteria andEndlongitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("endLongitude <=", value, "endlongitude");
            return (Criteria) this;
        }

        public Criteria andEndlongitudeIn(List<BigDecimal> values) {
            addCriterion("endLongitude in", values, "endlongitude");
            return (Criteria) this;
        }

        public Criteria andEndlongitudeNotIn(List<BigDecimal> values) {
            addCriterion("endLongitude not in", values, "endlongitude");
            return (Criteria) this;
        }

        public Criteria andEndlongitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("endLongitude between", value1, value2, "endlongitude");
            return (Criteria) this;
        }

        public Criteria andEndlongitudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("endLongitude not between", value1, value2, "endlongitude");
            return (Criteria) this;
        }

        public Criteria andEndlatitudeIsNull() {
            addCriterion("endLatitude is null");
            return (Criteria) this;
        }

        public Criteria andEndlatitudeIsNotNull() {
            addCriterion("endLatitude is not null");
            return (Criteria) this;
        }

        public Criteria andEndlatitudeEqualTo(BigDecimal value) {
            addCriterion("endLatitude =", value, "endlatitude");
            return (Criteria) this;
        }

        public Criteria andEndlatitudeNotEqualTo(BigDecimal value) {
            addCriterion("endLatitude <>", value, "endlatitude");
            return (Criteria) this;
        }

        public Criteria andEndlatitudeGreaterThan(BigDecimal value) {
            addCriterion("endLatitude >", value, "endlatitude");
            return (Criteria) this;
        }

        public Criteria andEndlatitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("endLatitude >=", value, "endlatitude");
            return (Criteria) this;
        }

        public Criteria andEndlatitudeLessThan(BigDecimal value) {
            addCriterion("endLatitude <", value, "endlatitude");
            return (Criteria) this;
        }

        public Criteria andEndlatitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("endLatitude <=", value, "endlatitude");
            return (Criteria) this;
        }

        public Criteria andEndlatitudeIn(List<BigDecimal> values) {
            addCriterion("endLatitude in", values, "endlatitude");
            return (Criteria) this;
        }

        public Criteria andEndlatitudeNotIn(List<BigDecimal> values) {
            addCriterion("endLatitude not in", values, "endlatitude");
            return (Criteria) this;
        }

        public Criteria andEndlatitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("endLatitude between", value1, value2, "endlatitude");
            return (Criteria) this;
        }

        public Criteria andEndlatitudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("endLatitude not between", value1, value2, "endlatitude");
            return (Criteria) this;
        }

        public Criteria andOuttimesIsNull() {
            addCriterion("outTimes is null");
            return (Criteria) this;
        }

        public Criteria andOuttimesIsNotNull() {
            addCriterion("outTimes is not null");
            return (Criteria) this;
        }

        public Criteria andOuttimesEqualTo(Date value) {
            addCriterion("outTimes =", value, "outtimes");
            return (Criteria) this;
        }

        public Criteria andOuttimesNotEqualTo(Date value) {
            addCriterion("outTimes <>", value, "outtimes");
            return (Criteria) this;
        }

        public Criteria andOuttimesGreaterThan(Date value) {
            addCriterion("outTimes >", value, "outtimes");
            return (Criteria) this;
        }

        public Criteria andOuttimesGreaterThanOrEqualTo(Date value) {
            addCriterion("outTimes >=", value, "outtimes");
            return (Criteria) this;
        }

        public Criteria andOuttimesLessThan(Date value) {
            addCriterion("outTimes <", value, "outtimes");
            return (Criteria) this;
        }

        public Criteria andOuttimesLessThanOrEqualTo(Date value) {
            addCriterion("outTimes <=", value, "outtimes");
            return (Criteria) this;
        }

        public Criteria andOuttimesIn(List<Date> values) {
            addCriterion("outTimes in", values, "outtimes");
            return (Criteria) this;
        }

        public Criteria andOuttimesNotIn(List<Date> values) {
            addCriterion("outTimes not in", values, "outtimes");
            return (Criteria) this;
        }

        public Criteria andOuttimesBetween(Date value1, Date value2) {
            addCriterion("outTimes between", value1, value2, "outtimes");
            return (Criteria) this;
        }

        public Criteria andOuttimesNotBetween(Date value1, Date value2) {
            addCriterion("outTimes not between", value1, value2, "outtimes");
            return (Criteria) this;
        }

        public Criteria andOutlongitudeIsNull() {
            addCriterion("outLongitude is null");
            return (Criteria) this;
        }

        public Criteria andOutlongitudeIsNotNull() {
            addCriterion("outLongitude is not null");
            return (Criteria) this;
        }

        public Criteria andOutlongitudeEqualTo(BigDecimal value) {
            addCriterion("outLongitude =", value, "outlongitude");
            return (Criteria) this;
        }

        public Criteria andOutlongitudeNotEqualTo(BigDecimal value) {
            addCriterion("outLongitude <>", value, "outlongitude");
            return (Criteria) this;
        }

        public Criteria andOutlongitudeGreaterThan(BigDecimal value) {
            addCriterion("outLongitude >", value, "outlongitude");
            return (Criteria) this;
        }

        public Criteria andOutlongitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("outLongitude >=", value, "outlongitude");
            return (Criteria) this;
        }

        public Criteria andOutlongitudeLessThan(BigDecimal value) {
            addCriterion("outLongitude <", value, "outlongitude");
            return (Criteria) this;
        }

        public Criteria andOutlongitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("outLongitude <=", value, "outlongitude");
            return (Criteria) this;
        }

        public Criteria andOutlongitudeIn(List<BigDecimal> values) {
            addCriterion("outLongitude in", values, "outlongitude");
            return (Criteria) this;
        }

        public Criteria andOutlongitudeNotIn(List<BigDecimal> values) {
            addCriterion("outLongitude not in", values, "outlongitude");
            return (Criteria) this;
        }

        public Criteria andOutlongitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("outLongitude between", value1, value2, "outlongitude");
            return (Criteria) this;
        }

        public Criteria andOutlongitudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("outLongitude not between", value1, value2, "outlongitude");
            return (Criteria) this;
        }

        public Criteria andOutlatitudeIsNull() {
            addCriterion("outLatitude is null");
            return (Criteria) this;
        }

        public Criteria andOutlatitudeIsNotNull() {
            addCriterion("outLatitude is not null");
            return (Criteria) this;
        }

        public Criteria andOutlatitudeEqualTo(BigDecimal value) {
            addCriterion("outLatitude =", value, "outlatitude");
            return (Criteria) this;
        }

        public Criteria andOutlatitudeNotEqualTo(BigDecimal value) {
            addCriterion("outLatitude <>", value, "outlatitude");
            return (Criteria) this;
        }

        public Criteria andOutlatitudeGreaterThan(BigDecimal value) {
            addCriterion("outLatitude >", value, "outlatitude");
            return (Criteria) this;
        }

        public Criteria andOutlatitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("outLatitude >=", value, "outlatitude");
            return (Criteria) this;
        }

        public Criteria andOutlatitudeLessThan(BigDecimal value) {
            addCriterion("outLatitude <", value, "outlatitude");
            return (Criteria) this;
        }

        public Criteria andOutlatitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("outLatitude <=", value, "outlatitude");
            return (Criteria) this;
        }

        public Criteria andOutlatitudeIn(List<BigDecimal> values) {
            addCriterion("outLatitude in", values, "outlatitude");
            return (Criteria) this;
        }

        public Criteria andOutlatitudeNotIn(List<BigDecimal> values) {
            addCriterion("outLatitude not in", values, "outlatitude");
            return (Criteria) this;
        }

        public Criteria andOutlatitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("outLatitude between", value1, value2, "outlatitude");
            return (Criteria) this;
        }

        public Criteria andOutlatitudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("outLatitude not between", value1, value2, "outlatitude");
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