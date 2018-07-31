package com.dogpro.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.PaginationUtil;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PaginationUtil pagination;

    public UserExample() {
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

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(Integer value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(Integer value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(Integer value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(Integer value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(Integer value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(Integer value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<Integer> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<Integer> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(Integer value1, Integer value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(Integer value1, Integer value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andHeadpicIsNull() {
            addCriterion("headPic is null");
            return (Criteria) this;
        }

        public Criteria andHeadpicIsNotNull() {
            addCriterion("headPic is not null");
            return (Criteria) this;
        }

        public Criteria andHeadpicEqualTo(String value) {
            addCriterion("headPic =", value, "headpic");
            return (Criteria) this;
        }

        public Criteria andHeadpicNotEqualTo(String value) {
            addCriterion("headPic <>", value, "headpic");
            return (Criteria) this;
        }

        public Criteria andHeadpicGreaterThan(String value) {
            addCriterion("headPic >", value, "headpic");
            return (Criteria) this;
        }

        public Criteria andHeadpicGreaterThanOrEqualTo(String value) {
            addCriterion("headPic >=", value, "headpic");
            return (Criteria) this;
        }

        public Criteria andHeadpicLessThan(String value) {
            addCriterion("headPic <", value, "headpic");
            return (Criteria) this;
        }

        public Criteria andHeadpicLessThanOrEqualTo(String value) {
            addCriterion("headPic <=", value, "headpic");
            return (Criteria) this;
        }

        public Criteria andHeadpicLike(String value) {
            addCriterion("headPic like", value, "headpic");
            return (Criteria) this;
        }

        public Criteria andHeadpicNotLike(String value) {
            addCriterion("headPic not like", value, "headpic");
            return (Criteria) this;
        }

        public Criteria andHeadpicIn(List<String> values) {
            addCriterion("headPic in", values, "headpic");
            return (Criteria) this;
        }

        public Criteria andHeadpicNotIn(List<String> values) {
            addCriterion("headPic not in", values, "headpic");
            return (Criteria) this;
        }

        public Criteria andHeadpicBetween(String value1, String value2) {
            addCriterion("headPic between", value1, value2, "headpic");
            return (Criteria) this;
        }

        public Criteria andHeadpicNotBetween(String value1, String value2) {
            addCriterion("headPic not between", value1, value2, "headpic");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNull() {
            addCriterion("nickname is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("nickname is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("nickname =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("nickname <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("nickname >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("nickname >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("nickname <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("nickname <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("nickname like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("nickname not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("nickname in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("nickname not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("nickname between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("nickname not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andSignIsNull() {
            addCriterion("sign is null");
            return (Criteria) this;
        }

        public Criteria andSignIsNotNull() {
            addCriterion("sign is not null");
            return (Criteria) this;
        }

        public Criteria andSignEqualTo(String value) {
            addCriterion("sign =", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignNotEqualTo(String value) {
            addCriterion("sign <>", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignGreaterThan(String value) {
            addCriterion("sign >", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignGreaterThanOrEqualTo(String value) {
            addCriterion("sign >=", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignLessThan(String value) {
            addCriterion("sign <", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignLessThanOrEqualTo(String value) {
            addCriterion("sign <=", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignLike(String value) {
            addCriterion("sign like", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignNotLike(String value) {
            addCriterion("sign not like", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignIn(List<String> values) {
            addCriterion("sign in", values, "sign");
            return (Criteria) this;
        }

        public Criteria andSignNotIn(List<String> values) {
            addCriterion("sign not in", values, "sign");
            return (Criteria) this;
        }

        public Criteria andSignBetween(String value1, String value2) {
            addCriterion("sign between", value1, value2, "sign");
            return (Criteria) this;
        }

        public Criteria andSignNotBetween(String value1, String value2) {
            addCriterion("sign not between", value1, value2, "sign");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPswdIsNull() {
            addCriterion("pswd is null");
            return (Criteria) this;
        }

        public Criteria andPswdIsNotNull() {
            addCriterion("pswd is not null");
            return (Criteria) this;
        }

        public Criteria andPswdEqualTo(String value) {
            addCriterion("pswd =", value, "pswd");
            return (Criteria) this;
        }

        public Criteria andPswdNotEqualTo(String value) {
            addCriterion("pswd <>", value, "pswd");
            return (Criteria) this;
        }

        public Criteria andPswdGreaterThan(String value) {
            addCriterion("pswd >", value, "pswd");
            return (Criteria) this;
        }

        public Criteria andPswdGreaterThanOrEqualTo(String value) {
            addCriterion("pswd >=", value, "pswd");
            return (Criteria) this;
        }

        public Criteria andPswdLessThan(String value) {
            addCriterion("pswd <", value, "pswd");
            return (Criteria) this;
        }

        public Criteria andPswdLessThanOrEqualTo(String value) {
            addCriterion("pswd <=", value, "pswd");
            return (Criteria) this;
        }

        public Criteria andPswdLike(String value) {
            addCriterion("pswd like", value, "pswd");
            return (Criteria) this;
        }

        public Criteria andPswdNotLike(String value) {
            addCriterion("pswd not like", value, "pswd");
            return (Criteria) this;
        }

        public Criteria andPswdIn(List<String> values) {
            addCriterion("pswd in", values, "pswd");
            return (Criteria) this;
        }

        public Criteria andPswdNotIn(List<String> values) {
            addCriterion("pswd not in", values, "pswd");
            return (Criteria) this;
        }

        public Criteria andPswdBetween(String value1, String value2) {
            addCriterion("pswd between", value1, value2, "pswd");
            return (Criteria) this;
        }

        public Criteria andPswdNotBetween(String value1, String value2) {
            addCriterion("pswd not between", value1, value2, "pswd");
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

        public Criteria andOfflinetimesIsNull() {
            addCriterion("offlineTimes is null");
            return (Criteria) this;
        }

        public Criteria andOfflinetimesIsNotNull() {
            addCriterion("offlineTimes is not null");
            return (Criteria) this;
        }

        public Criteria andOfflinetimesEqualTo(Long value) {
            addCriterion("offlineTimes =", value, "offlinetimes");
            return (Criteria) this;
        }

        public Criteria andOfflinetimesNotEqualTo(Long value) {
            addCriterion("offlineTimes <>", value, "offlinetimes");
            return (Criteria) this;
        }

        public Criteria andOfflinetimesGreaterThan(Long value) {
            addCriterion("offlineTimes >", value, "offlinetimes");
            return (Criteria) this;
        }

        public Criteria andOfflinetimesGreaterThanOrEqualTo(Long value) {
            addCriterion("offlineTimes >=", value, "offlinetimes");
            return (Criteria) this;
        }

        public Criteria andOfflinetimesLessThan(Long value) {
            addCriterion("offlineTimes <", value, "offlinetimes");
            return (Criteria) this;
        }

        public Criteria andOfflinetimesLessThanOrEqualTo(Long value) {
            addCriterion("offlineTimes <=", value, "offlinetimes");
            return (Criteria) this;
        }

        public Criteria andOfflinetimesIn(List<Long> values) {
            addCriterion("offlineTimes in", values, "offlinetimes");
            return (Criteria) this;
        }

        public Criteria andOfflinetimesNotIn(List<Long> values) {
            addCriterion("offlineTimes not in", values, "offlinetimes");
            return (Criteria) this;
        }

        public Criteria andOfflinetimesBetween(Long value1, Long value2) {
            addCriterion("offlineTimes between", value1, value2, "offlinetimes");
            return (Criteria) this;
        }

        public Criteria andOfflinetimesNotBetween(Long value1, Long value2) {
            addCriterion("offlineTimes not between", value1, value2, "offlinetimes");
            return (Criteria) this;
        }

        public Criteria andIscompletedIsNull() {
            addCriterion("isCompleted is null");
            return (Criteria) this;
        }

        public Criteria andIscompletedIsNotNull() {
            addCriterion("isCompleted is not null");
            return (Criteria) this;
        }

        public Criteria andIscompletedEqualTo(Integer value) {
            addCriterion("isCompleted =", value, "iscompleted");
            return (Criteria) this;
        }

        public Criteria andIscompletedNotEqualTo(Integer value) {
            addCriterion("isCompleted <>", value, "iscompleted");
            return (Criteria) this;
        }

        public Criteria andIscompletedGreaterThan(Integer value) {
            addCriterion("isCompleted >", value, "iscompleted");
            return (Criteria) this;
        }

        public Criteria andIscompletedGreaterThanOrEqualTo(Integer value) {
            addCriterion("isCompleted >=", value, "iscompleted");
            return (Criteria) this;
        }

        public Criteria andIscompletedLessThan(Integer value) {
            addCriterion("isCompleted <", value, "iscompleted");
            return (Criteria) this;
        }

        public Criteria andIscompletedLessThanOrEqualTo(Integer value) {
            addCriterion("isCompleted <=", value, "iscompleted");
            return (Criteria) this;
        }

        public Criteria andIscompletedIn(List<Integer> values) {
            addCriterion("isCompleted in", values, "iscompleted");
            return (Criteria) this;
        }

        public Criteria andIscompletedNotIn(List<Integer> values) {
            addCriterion("isCompleted not in", values, "iscompleted");
            return (Criteria) this;
        }

        public Criteria andIscompletedBetween(Integer value1, Integer value2) {
            addCriterion("isCompleted between", value1, value2, "iscompleted");
            return (Criteria) this;
        }

        public Criteria andIscompletedNotBetween(Integer value1, Integer value2) {
            addCriterion("isCompleted not between", value1, value2, "iscompleted");
            return (Criteria) this;
        }

        public Criteria andUuidIsNull() {
            addCriterion("UUID is null");
            return (Criteria) this;
        }

        public Criteria andUuidIsNotNull() {
            addCriterion("UUID is not null");
            return (Criteria) this;
        }

        public Criteria andUuidEqualTo(String value) {
            addCriterion("UUID =", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualTo(String value) {
            addCriterion("UUID <>", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThan(String value) {
            addCriterion("UUID >", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualTo(String value) {
            addCriterion("UUID >=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThan(String value) {
            addCriterion("UUID <", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualTo(String value) {
            addCriterion("UUID <=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLike(String value) {
            addCriterion("UUID like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotLike(String value) {
            addCriterion("UUID not like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidIn(List<String> values) {
            addCriterion("UUID in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotIn(List<String> values) {
            addCriterion("UUID not in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidBetween(String value1, String value2) {
            addCriterion("UUID between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotBetween(String value1, String value2) {
            addCriterion("UUID not between", value1, value2, "uuid");
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