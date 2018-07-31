package com.dogpro.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.PaginationUtil;

public class FriendsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PaginationUtil pagination;

    public FriendsExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
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

        public Criteria andFriendUserIdIsNull() {
            addCriterion("friend_user_id is null");
            return (Criteria) this;
        }

        public Criteria andFriendUserIdIsNotNull() {
            addCriterion("friend_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andFriendUserIdEqualTo(Long value) {
            addCriterion("friend_user_id =", value, "friendUserId");
            return (Criteria) this;
        }

        public Criteria andFriendUserIdNotEqualTo(Long value) {
            addCriterion("friend_user_id <>", value, "friendUserId");
            return (Criteria) this;
        }

        public Criteria andFriendUserIdGreaterThan(Long value) {
            addCriterion("friend_user_id >", value, "friendUserId");
            return (Criteria) this;
        }

        public Criteria andFriendUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("friend_user_id >=", value, "friendUserId");
            return (Criteria) this;
        }

        public Criteria andFriendUserIdLessThan(Long value) {
            addCriterion("friend_user_id <", value, "friendUserId");
            return (Criteria) this;
        }

        public Criteria andFriendUserIdLessThanOrEqualTo(Long value) {
            addCriterion("friend_user_id <=", value, "friendUserId");
            return (Criteria) this;
        }

        public Criteria andFriendUserIdIn(List<Long> values) {
            addCriterion("friend_user_id in", values, "friendUserId");
            return (Criteria) this;
        }

        public Criteria andFriendUserIdNotIn(List<Long> values) {
            addCriterion("friend_user_id not in", values, "friendUserId");
            return (Criteria) this;
        }

        public Criteria andFriendUserIdBetween(Long value1, Long value2) {
            addCriterion("friend_user_id between", value1, value2, "friendUserId");
            return (Criteria) this;
        }

        public Criteria andFriendUserIdNotBetween(Long value1, Long value2) {
            addCriterion("friend_user_id not between", value1, value2, "friendUserId");
            return (Criteria) this;
        }

        public Criteria andIsRequestIsNull() {
            addCriterion("is_request is null");
            return (Criteria) this;
        }

        public Criteria andIsRequestIsNotNull() {
            addCriterion("is_request is not null");
            return (Criteria) this;
        }

        public Criteria andIsRequestEqualTo(Integer value) {
            addCriterion("is_request =", value, "isRequest");
            return (Criteria) this;
        }

        public Criteria andIsRequestNotEqualTo(Integer value) {
            addCriterion("is_request <>", value, "isRequest");
            return (Criteria) this;
        }

        public Criteria andIsRequestGreaterThan(Integer value) {
            addCriterion("is_request >", value, "isRequest");
            return (Criteria) this;
        }

        public Criteria andIsRequestGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_request >=", value, "isRequest");
            return (Criteria) this;
        }

        public Criteria andIsRequestLessThan(Integer value) {
            addCriterion("is_request <", value, "isRequest");
            return (Criteria) this;
        }

        public Criteria andIsRequestLessThanOrEqualTo(Integer value) {
            addCriterion("is_request <=", value, "isRequest");
            return (Criteria) this;
        }

        public Criteria andIsRequestIn(List<Integer> values) {
            addCriterion("is_request in", values, "isRequest");
            return (Criteria) this;
        }

        public Criteria andIsRequestNotIn(List<Integer> values) {
            addCriterion("is_request not in", values, "isRequest");
            return (Criteria) this;
        }

        public Criteria andIsRequestBetween(Integer value1, Integer value2) {
            addCriterion("is_request between", value1, value2, "isRequest");
            return (Criteria) this;
        }

        public Criteria andIsRequestNotBetween(Integer value1, Integer value2) {
            addCriterion("is_request not between", value1, value2, "isRequest");
            return (Criteria) this;
        }

        public Criteria andRequestTimeIsNull() {
            addCriterion("request_time is null");
            return (Criteria) this;
        }

        public Criteria andRequestTimeIsNotNull() {
            addCriterion("request_time is not null");
            return (Criteria) this;
        }

        public Criteria andRequestTimeEqualTo(Date value) {
            addCriterion("request_time =", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeNotEqualTo(Date value) {
            addCriterion("request_time <>", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeGreaterThan(Date value) {
            addCriterion("request_time >", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("request_time >=", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeLessThan(Date value) {
            addCriterion("request_time <", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeLessThanOrEqualTo(Date value) {
            addCriterion("request_time <=", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeIn(List<Date> values) {
            addCriterion("request_time in", values, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeNotIn(List<Date> values) {
            addCriterion("request_time not in", values, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeBetween(Date value1, Date value2) {
            addCriterion("request_time between", value1, value2, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeNotBetween(Date value1, Date value2) {
            addCriterion("request_time not between", value1, value2, "requestTime");
            return (Criteria) this;
        }

        public Criteria andFriendCommentIsNull() {
            addCriterion("friend_comment is null");
            return (Criteria) this;
        }

        public Criteria andFriendCommentIsNotNull() {
            addCriterion("friend_comment is not null");
            return (Criteria) this;
        }

        public Criteria andFriendCommentEqualTo(String value) {
            addCriterion("friend_comment =", value, "friendComment");
            return (Criteria) this;
        }

        public Criteria andFriendCommentNotEqualTo(String value) {
            addCriterion("friend_comment <>", value, "friendComment");
            return (Criteria) this;
        }

        public Criteria andFriendCommentGreaterThan(String value) {
            addCriterion("friend_comment >", value, "friendComment");
            return (Criteria) this;
        }

        public Criteria andFriendCommentGreaterThanOrEqualTo(String value) {
            addCriterion("friend_comment >=", value, "friendComment");
            return (Criteria) this;
        }

        public Criteria andFriendCommentLessThan(String value) {
            addCriterion("friend_comment <", value, "friendComment");
            return (Criteria) this;
        }

        public Criteria andFriendCommentLessThanOrEqualTo(String value) {
            addCriterion("friend_comment <=", value, "friendComment");
            return (Criteria) this;
        }

        public Criteria andFriendCommentLike(String value) {
            addCriterion("friend_comment like", value, "friendComment");
            return (Criteria) this;
        }

        public Criteria andFriendCommentNotLike(String value) {
            addCriterion("friend_comment not like", value, "friendComment");
            return (Criteria) this;
        }

        public Criteria andFriendCommentIn(List<String> values) {
            addCriterion("friend_comment in", values, "friendComment");
            return (Criteria) this;
        }

        public Criteria andFriendCommentNotIn(List<String> values) {
            addCriterion("friend_comment not in", values, "friendComment");
            return (Criteria) this;
        }

        public Criteria andFriendCommentBetween(String value1, String value2) {
            addCriterion("friend_comment between", value1, value2, "friendComment");
            return (Criteria) this;
        }

        public Criteria andFriendCommentNotBetween(String value1, String value2) {
            addCriterion("friend_comment not between", value1, value2, "friendComment");
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

        public Criteria andIsOpenfriendCirToFriendIsNull() {
            addCriterion("is_openFriend_cir_to_friend is null");
            return (Criteria) this;
        }

        public Criteria andIsOpenfriendCirToFriendIsNotNull() {
            addCriterion("is_openFriend_cir_to_friend is not null");
            return (Criteria) this;
        }

        public Criteria andIsOpenfriendCirToFriendEqualTo(Integer value) {
            addCriterion("is_openFriend_cir_to_friend =", value, "isOpenfriendCirToFriend");
            return (Criteria) this;
        }

        public Criteria andIsOpenfriendCirToFriendNotEqualTo(Integer value) {
            addCriterion("is_openFriend_cir_to_friend <>", value, "isOpenfriendCirToFriend");
            return (Criteria) this;
        }

        public Criteria andIsOpenfriendCirToFriendGreaterThan(Integer value) {
            addCriterion("is_openFriend_cir_to_friend >", value, "isOpenfriendCirToFriend");
            return (Criteria) this;
        }

        public Criteria andIsOpenfriendCirToFriendGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_openFriend_cir_to_friend >=", value, "isOpenfriendCirToFriend");
            return (Criteria) this;
        }

        public Criteria andIsOpenfriendCirToFriendLessThan(Integer value) {
            addCriterion("is_openFriend_cir_to_friend <", value, "isOpenfriendCirToFriend");
            return (Criteria) this;
        }

        public Criteria andIsOpenfriendCirToFriendLessThanOrEqualTo(Integer value) {
            addCriterion("is_openFriend_cir_to_friend <=", value, "isOpenfriendCirToFriend");
            return (Criteria) this;
        }

        public Criteria andIsOpenfriendCirToFriendIn(List<Integer> values) {
            addCriterion("is_openFriend_cir_to_friend in", values, "isOpenfriendCirToFriend");
            return (Criteria) this;
        }

        public Criteria andIsOpenfriendCirToFriendNotIn(List<Integer> values) {
            addCriterion("is_openFriend_cir_to_friend not in", values, "isOpenfriendCirToFriend");
            return (Criteria) this;
        }

        public Criteria andIsOpenfriendCirToFriendBetween(Integer value1, Integer value2) {
            addCriterion("is_openFriend_cir_to_friend between", value1, value2, "isOpenfriendCirToFriend");
            return (Criteria) this;
        }

        public Criteria andIsOpenfriendCirToFriendNotBetween(Integer value1, Integer value2) {
            addCriterion("is_openFriend_cir_to_friend not between", value1, value2, "isOpenfriendCirToFriend");
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

        public Criteria andFriendStateIsNull() {
            addCriterion("friend_state is null");
            return (Criteria) this;
        }

        public Criteria andFriendStateIsNotNull() {
            addCriterion("friend_state is not null");
            return (Criteria) this;
        }

        public Criteria andFriendStateEqualTo(Integer value) {
            addCriterion("friend_state =", value, "friendState");
            return (Criteria) this;
        }

        public Criteria andFriendStateNotEqualTo(Integer value) {
            addCriterion("friend_state <>", value, "friendState");
            return (Criteria) this;
        }

        public Criteria andFriendStateGreaterThan(Integer value) {
            addCriterion("friend_state >", value, "friendState");
            return (Criteria) this;
        }

        public Criteria andFriendStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("friend_state >=", value, "friendState");
            return (Criteria) this;
        }

        public Criteria andFriendStateLessThan(Integer value) {
            addCriterion("friend_state <", value, "friendState");
            return (Criteria) this;
        }

        public Criteria andFriendStateLessThanOrEqualTo(Integer value) {
            addCriterion("friend_state <=", value, "friendState");
            return (Criteria) this;
        }

        public Criteria andFriendStateIn(List<Integer> values) {
            addCriterion("friend_state in", values, "friendState");
            return (Criteria) this;
        }

        public Criteria andFriendStateNotIn(List<Integer> values) {
            addCriterion("friend_state not in", values, "friendState");
            return (Criteria) this;
        }

        public Criteria andFriendStateBetween(Integer value1, Integer value2) {
            addCriterion("friend_state between", value1, value2, "friendState");
            return (Criteria) this;
        }

        public Criteria andFriendStateNotBetween(Integer value1, Integer value2) {
            addCriterion("friend_state not between", value1, value2, "friendState");
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