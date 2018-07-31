package com.dogpro.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.PaginationUtil;

public class FriendCircleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PaginationUtil pagination;

    public FriendCircleExample() {
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
        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
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

        public Criteria andPublishtimeIsNull() {
            addCriterion("publishTime is null");
            return (Criteria) this;
        }

        public Criteria andPublishtimeIsNotNull() {
            addCriterion("publishTime is not null");
            return (Criteria) this;
        }

        public Criteria andPublishtimeEqualTo(Date value) {
            addCriterion("publishTime =", value, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeNotEqualTo(Date value) {
            addCriterion("publishTime <>", value, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeGreaterThan(Date value) {
            addCriterion("publishTime >", value, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("publishTime >=", value, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeLessThan(Date value) {
            addCriterion("publishTime <", value, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeLessThanOrEqualTo(Date value) {
            addCriterion("publishTime <=", value, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeIn(List<Date> values) {
            addCriterion("publishTime in", values, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeNotIn(List<Date> values) {
            addCriterion("publishTime not in", values, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeBetween(Date value1, Date value2) {
            addCriterion("publishTime between", value1, value2, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeNotBetween(Date value1, Date value2) {
            addCriterion("publishTime not between", value1, value2, "publishtime");
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

        public Criteria andPublishipIsNull() {
            addCriterion("publishIp is null");
            return (Criteria) this;
        }

        public Criteria andPublishipIsNotNull() {
            addCriterion("publishIp is not null");
            return (Criteria) this;
        }

        public Criteria andPublishipEqualTo(String value) {
            addCriterion("publishIp =", value, "publiship");
            return (Criteria) this;
        }

        public Criteria andPublishipNotEqualTo(String value) {
            addCriterion("publishIp <>", value, "publiship");
            return (Criteria) this;
        }

        public Criteria andPublishipGreaterThan(String value) {
            addCriterion("publishIp >", value, "publiship");
            return (Criteria) this;
        }

        public Criteria andPublishipGreaterThanOrEqualTo(String value) {
            addCriterion("publishIp >=", value, "publiship");
            return (Criteria) this;
        }

        public Criteria andPublishipLessThan(String value) {
            addCriterion("publishIp <", value, "publiship");
            return (Criteria) this;
        }

        public Criteria andPublishipLessThanOrEqualTo(String value) {
            addCriterion("publishIp <=", value, "publiship");
            return (Criteria) this;
        }

        public Criteria andPublishipLike(String value) {
            addCriterion("publishIp like", value, "publiship");
            return (Criteria) this;
        }

        public Criteria andPublishipNotLike(String value) {
            addCriterion("publishIp not like", value, "publiship");
            return (Criteria) this;
        }

        public Criteria andPublishipIn(List<String> values) {
            addCriterion("publishIp in", values, "publiship");
            return (Criteria) this;
        }

        public Criteria andPublishipNotIn(List<String> values) {
            addCriterion("publishIp not in", values, "publiship");
            return (Criteria) this;
        }

        public Criteria andPublishipBetween(String value1, String value2) {
            addCriterion("publishIp between", value1, value2, "publiship");
            return (Criteria) this;
        }

        public Criteria andPublishipNotBetween(String value1, String value2) {
            addCriterion("publishIp not between", value1, value2, "publiship");
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

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(String value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(String value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(String value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(String value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(String value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(String value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLike(String value) {
            addCriterion("sort like", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotLike(String value) {
            addCriterion("sort not like", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<String> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<String> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(String value1, String value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(String value1, String value2) {
            addCriterion("sort not between", value1, value2, "sort");
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

        public Criteria andProvincesIsNull() {
            addCriterion("provinces is null");
            return (Criteria) this;
        }

        public Criteria andProvincesIsNotNull() {
            addCriterion("provinces is not null");
            return (Criteria) this;
        }

        public Criteria andProvincesEqualTo(String value) {
            addCriterion("provinces =", value, "provinces");
            return (Criteria) this;
        }

        public Criteria andProvincesNotEqualTo(String value) {
            addCriterion("provinces <>", value, "provinces");
            return (Criteria) this;
        }

        public Criteria andProvincesGreaterThan(String value) {
            addCriterion("provinces >", value, "provinces");
            return (Criteria) this;
        }

        public Criteria andProvincesGreaterThanOrEqualTo(String value) {
            addCriterion("provinces >=", value, "provinces");
            return (Criteria) this;
        }

        public Criteria andProvincesLessThan(String value) {
            addCriterion("provinces <", value, "provinces");
            return (Criteria) this;
        }

        public Criteria andProvincesLessThanOrEqualTo(String value) {
            addCriterion("provinces <=", value, "provinces");
            return (Criteria) this;
        }

        public Criteria andProvincesLike(String value) {
            addCriterion("provinces like", value, "provinces");
            return (Criteria) this;
        }

        public Criteria andProvincesNotLike(String value) {
            addCriterion("provinces not like", value, "provinces");
            return (Criteria) this;
        }

        public Criteria andProvincesIn(List<String> values) {
            addCriterion("provinces in", values, "provinces");
            return (Criteria) this;
        }

        public Criteria andProvincesNotIn(List<String> values) {
            addCriterion("provinces not in", values, "provinces");
            return (Criteria) this;
        }

        public Criteria andProvincesBetween(String value1, String value2) {
            addCriterion("provinces between", value1, value2, "provinces");
            return (Criteria) this;
        }

        public Criteria andProvincesNotBetween(String value1, String value2) {
            addCriterion("provinces not between", value1, value2, "provinces");
            return (Criteria) this;
        }

        public Criteria andMunicipalitiesIsNull() {
            addCriterion("municipalities is null");
            return (Criteria) this;
        }

        public Criteria andMunicipalitiesIsNotNull() {
            addCriterion("municipalities is not null");
            return (Criteria) this;
        }

        public Criteria andMunicipalitiesEqualTo(String value) {
            addCriterion("municipalities =", value, "municipalities");
            return (Criteria) this;
        }

        public Criteria andMunicipalitiesNotEqualTo(String value) {
            addCriterion("municipalities <>", value, "municipalities");
            return (Criteria) this;
        }

        public Criteria andMunicipalitiesGreaterThan(String value) {
            addCriterion("municipalities >", value, "municipalities");
            return (Criteria) this;
        }

        public Criteria andMunicipalitiesGreaterThanOrEqualTo(String value) {
            addCriterion("municipalities >=", value, "municipalities");
            return (Criteria) this;
        }

        public Criteria andMunicipalitiesLessThan(String value) {
            addCriterion("municipalities <", value, "municipalities");
            return (Criteria) this;
        }

        public Criteria andMunicipalitiesLessThanOrEqualTo(String value) {
            addCriterion("municipalities <=", value, "municipalities");
            return (Criteria) this;
        }

        public Criteria andMunicipalitiesLike(String value) {
            addCriterion("municipalities like", value, "municipalities");
            return (Criteria) this;
        }

        public Criteria andMunicipalitiesNotLike(String value) {
            addCriterion("municipalities not like", value, "municipalities");
            return (Criteria) this;
        }

        public Criteria andMunicipalitiesIn(List<String> values) {
            addCriterion("municipalities in", values, "municipalities");
            return (Criteria) this;
        }

        public Criteria andMunicipalitiesNotIn(List<String> values) {
            addCriterion("municipalities not in", values, "municipalities");
            return (Criteria) this;
        }

        public Criteria andMunicipalitiesBetween(String value1, String value2) {
            addCriterion("municipalities between", value1, value2, "municipalities");
            return (Criteria) this;
        }

        public Criteria andMunicipalitiesNotBetween(String value1, String value2) {
            addCriterion("municipalities not between", value1, value2, "municipalities");
            return (Criteria) this;
        }

        public Criteria andDistrictsIsNull() {
            addCriterion("districts is null");
            return (Criteria) this;
        }

        public Criteria andDistrictsIsNotNull() {
            addCriterion("districts is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictsEqualTo(String value) {
            addCriterion("districts =", value, "districts");
            return (Criteria) this;
        }

        public Criteria andDistrictsNotEqualTo(String value) {
            addCriterion("districts <>", value, "districts");
            return (Criteria) this;
        }

        public Criteria andDistrictsGreaterThan(String value) {
            addCriterion("districts >", value, "districts");
            return (Criteria) this;
        }

        public Criteria andDistrictsGreaterThanOrEqualTo(String value) {
            addCriterion("districts >=", value, "districts");
            return (Criteria) this;
        }

        public Criteria andDistrictsLessThan(String value) {
            addCriterion("districts <", value, "districts");
            return (Criteria) this;
        }

        public Criteria andDistrictsLessThanOrEqualTo(String value) {
            addCriterion("districts <=", value, "districts");
            return (Criteria) this;
        }

        public Criteria andDistrictsLike(String value) {
            addCriterion("districts like", value, "districts");
            return (Criteria) this;
        }

        public Criteria andDistrictsNotLike(String value) {
            addCriterion("districts not like", value, "districts");
            return (Criteria) this;
        }

        public Criteria andDistrictsIn(List<String> values) {
            addCriterion("districts in", values, "districts");
            return (Criteria) this;
        }

        public Criteria andDistrictsNotIn(List<String> values) {
            addCriterion("districts not in", values, "districts");
            return (Criteria) this;
        }

        public Criteria andDistrictsBetween(String value1, String value2) {
            addCriterion("districts between", value1, value2, "districts");
            return (Criteria) this;
        }

        public Criteria andDistrictsNotBetween(String value1, String value2) {
            addCriterion("districts not between", value1, value2, "districts");
            return (Criteria) this;
        }

        public Criteria andTownstreetIsNull() {
            addCriterion("townStreet is null");
            return (Criteria) this;
        }

        public Criteria andTownstreetIsNotNull() {
            addCriterion("townStreet is not null");
            return (Criteria) this;
        }

        public Criteria andTownstreetEqualTo(String value) {
            addCriterion("townStreet =", value, "townstreet");
            return (Criteria) this;
        }

        public Criteria andTownstreetNotEqualTo(String value) {
            addCriterion("townStreet <>", value, "townstreet");
            return (Criteria) this;
        }

        public Criteria andTownstreetGreaterThan(String value) {
            addCriterion("townStreet >", value, "townstreet");
            return (Criteria) this;
        }

        public Criteria andTownstreetGreaterThanOrEqualTo(String value) {
            addCriterion("townStreet >=", value, "townstreet");
            return (Criteria) this;
        }

        public Criteria andTownstreetLessThan(String value) {
            addCriterion("townStreet <", value, "townstreet");
            return (Criteria) this;
        }

        public Criteria andTownstreetLessThanOrEqualTo(String value) {
            addCriterion("townStreet <=", value, "townstreet");
            return (Criteria) this;
        }

        public Criteria andTownstreetLike(String value) {
            addCriterion("townStreet like", value, "townstreet");
            return (Criteria) this;
        }

        public Criteria andTownstreetNotLike(String value) {
            addCriterion("townStreet not like", value, "townstreet");
            return (Criteria) this;
        }

        public Criteria andTownstreetIn(List<String> values) {
            addCriterion("townStreet in", values, "townstreet");
            return (Criteria) this;
        }

        public Criteria andTownstreetNotIn(List<String> values) {
            addCriterion("townStreet not in", values, "townstreet");
            return (Criteria) this;
        }

        public Criteria andTownstreetBetween(String value1, String value2) {
            addCriterion("townStreet between", value1, value2, "townstreet");
            return (Criteria) this;
        }

        public Criteria andTownstreetNotBetween(String value1, String value2) {
            addCriterion("townStreet not between", value1, value2, "townstreet");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(Float value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(Float value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(Float value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(Float value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(Float value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(Float value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<Float> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<Float> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(Float value1, Float value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(Float value1, Float value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(Float value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(Float value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(Float value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(Float value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(Float value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(Float value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<Float> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<Float> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(Float value1, Float value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(Float value1, Float value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
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

        public Criteria andFriendcircodeIsNull() {
            addCriterion("friendCirCode is null");
            return (Criteria) this;
        }

        public Criteria andFriendcircodeIsNotNull() {
            addCriterion("friendCirCode is not null");
            return (Criteria) this;
        }

        public Criteria andFriendcircodeEqualTo(Long value) {
            addCriterion("friendCirCode =", value, "friendcircode");
            return (Criteria) this;
        }

        public Criteria andFriendcircodeNotEqualTo(Long value) {
            addCriterion("friendCirCode <>", value, "friendcircode");
            return (Criteria) this;
        }

        public Criteria andFriendcircodeGreaterThan(Long value) {
            addCriterion("friendCirCode >", value, "friendcircode");
            return (Criteria) this;
        }

        public Criteria andFriendcircodeGreaterThanOrEqualTo(Long value) {
            addCriterion("friendCirCode >=", value, "friendcircode");
            return (Criteria) this;
        }

        public Criteria andFriendcircodeLessThan(Long value) {
            addCriterion("friendCirCode <", value, "friendcircode");
            return (Criteria) this;
        }

        public Criteria andFriendcircodeLessThanOrEqualTo(Long value) {
            addCriterion("friendCirCode <=", value, "friendcircode");
            return (Criteria) this;
        }

        public Criteria andFriendcircodeIn(List<Long> values) {
            addCriterion("friendCirCode in", values, "friendcircode");
            return (Criteria) this;
        }

        public Criteria andFriendcircodeNotIn(List<Long> values) {
            addCriterion("friendCirCode not in", values, "friendcircode");
            return (Criteria) this;
        }

        public Criteria andFriendcircodeBetween(Long value1, Long value2) {
            addCriterion("friendCirCode between", value1, value2, "friendcircode");
            return (Criteria) this;
        }

        public Criteria andFriendcircodeNotBetween(Long value1, Long value2) {
            addCriterion("friendCirCode not between", value1, value2, "friendcircode");
            return (Criteria) this;
        }

        public Criteria andUpdatecodeIsNull() {
            addCriterion("updateCode is null");
            return (Criteria) this;
        }

        public Criteria andUpdatecodeIsNotNull() {
            addCriterion("updateCode is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatecodeEqualTo(Long value) {
            addCriterion("updateCode =", value, "updatecode");
            return (Criteria) this;
        }

        public Criteria andUpdatecodeNotEqualTo(Long value) {
            addCriterion("updateCode <>", value, "updatecode");
            return (Criteria) this;
        }

        public Criteria andUpdatecodeGreaterThan(Long value) {
            addCriterion("updateCode >", value, "updatecode");
            return (Criteria) this;
        }

        public Criteria andUpdatecodeGreaterThanOrEqualTo(Long value) {
            addCriterion("updateCode >=", value, "updatecode");
            return (Criteria) this;
        }

        public Criteria andUpdatecodeLessThan(Long value) {
            addCriterion("updateCode <", value, "updatecode");
            return (Criteria) this;
        }

        public Criteria andUpdatecodeLessThanOrEqualTo(Long value) {
            addCriterion("updateCode <=", value, "updatecode");
            return (Criteria) this;
        }

        public Criteria andUpdatecodeIn(List<Long> values) {
            addCriterion("updateCode in", values, "updatecode");
            return (Criteria) this;
        }

        public Criteria andUpdatecodeNotIn(List<Long> values) {
            addCriterion("updateCode not in", values, "updatecode");
            return (Criteria) this;
        }

        public Criteria andUpdatecodeBetween(Long value1, Long value2) {
            addCriterion("updateCode between", value1, value2, "updatecode");
            return (Criteria) this;
        }

        public Criteria andUpdatecodeNotBetween(Long value1, Long value2) {
            addCriterion("updateCode not between", value1, value2, "updatecode");
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