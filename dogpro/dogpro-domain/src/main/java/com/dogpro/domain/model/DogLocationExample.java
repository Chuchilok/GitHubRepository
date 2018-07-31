package com.dogpro.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.PaginationUtil;

public class DogLocationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected PaginationUtil pagination;

    public DogLocationExample() {
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

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(Long value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Long value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Long value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Long value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Long value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Long value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Long> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Long> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Long value1, Long value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Long value1, Long value2) {
            addCriterion("pid not between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andAreanameIsNull() {
            addCriterion("areaName is null");
            return (Criteria) this;
        }

        public Criteria andAreanameIsNotNull() {
            addCriterion("areaName is not null");
            return (Criteria) this;
        }

        public Criteria andAreanameEqualTo(String value) {
            addCriterion("areaName =", value, "areaname");
            return (Criteria) this;
        }

        public Criteria andAreanameNotEqualTo(String value) {
            addCriterion("areaName <>", value, "areaname");
            return (Criteria) this;
        }

        public Criteria andAreanameGreaterThan(String value) {
            addCriterion("areaName >", value, "areaname");
            return (Criteria) this;
        }

        public Criteria andAreanameGreaterThanOrEqualTo(String value) {
            addCriterion("areaName >=", value, "areaname");
            return (Criteria) this;
        }

        public Criteria andAreanameLessThan(String value) {
            addCriterion("areaName <", value, "areaname");
            return (Criteria) this;
        }

        public Criteria andAreanameLessThanOrEqualTo(String value) {
            addCriterion("areaName <=", value, "areaname");
            return (Criteria) this;
        }

        public Criteria andAreanameLike(String value) {
            addCriterion("areaName like", value, "areaname");
            return (Criteria) this;
        }

        public Criteria andAreanameNotLike(String value) {
            addCriterion("areaName not like", value, "areaname");
            return (Criteria) this;
        }

        public Criteria andAreanameIn(List<String> values) {
            addCriterion("areaName in", values, "areaname");
            return (Criteria) this;
        }

        public Criteria andAreanameNotIn(List<String> values) {
            addCriterion("areaName not in", values, "areaname");
            return (Criteria) this;
        }

        public Criteria andAreanameBetween(String value1, String value2) {
            addCriterion("areaName between", value1, value2, "areaname");
            return (Criteria) this;
        }

        public Criteria andAreanameNotBetween(String value1, String value2) {
            addCriterion("areaName not between", value1, value2, "areaname");
            return (Criteria) this;
        }

        public Criteria andAddressaliasIsNull() {
            addCriterion("addressAlias is null");
            return (Criteria) this;
        }

        public Criteria andAddressaliasIsNotNull() {
            addCriterion("addressAlias is not null");
            return (Criteria) this;
        }

        public Criteria andAddressaliasEqualTo(String value) {
            addCriterion("addressAlias =", value, "addressalias");
            return (Criteria) this;
        }

        public Criteria andAddressaliasNotEqualTo(String value) {
            addCriterion("addressAlias <>", value, "addressalias");
            return (Criteria) this;
        }

        public Criteria andAddressaliasGreaterThan(String value) {
            addCriterion("addressAlias >", value, "addressalias");
            return (Criteria) this;
        }

        public Criteria andAddressaliasGreaterThanOrEqualTo(String value) {
            addCriterion("addressAlias >=", value, "addressalias");
            return (Criteria) this;
        }

        public Criteria andAddressaliasLessThan(String value) {
            addCriterion("addressAlias <", value, "addressalias");
            return (Criteria) this;
        }

        public Criteria andAddressaliasLessThanOrEqualTo(String value) {
            addCriterion("addressAlias <=", value, "addressalias");
            return (Criteria) this;
        }

        public Criteria andAddressaliasLike(String value) {
            addCriterion("addressAlias like", value, "addressalias");
            return (Criteria) this;
        }

        public Criteria andAddressaliasNotLike(String value) {
            addCriterion("addressAlias not like", value, "addressalias");
            return (Criteria) this;
        }

        public Criteria andAddressaliasIn(List<String> values) {
            addCriterion("addressAlias in", values, "addressalias");
            return (Criteria) this;
        }

        public Criteria andAddressaliasNotIn(List<String> values) {
            addCriterion("addressAlias not in", values, "addressalias");
            return (Criteria) this;
        }

        public Criteria andAddressaliasBetween(String value1, String value2) {
            addCriterion("addressAlias between", value1, value2, "addressalias");
            return (Criteria) this;
        }

        public Criteria andAddressaliasNotBetween(String value1, String value2) {
            addCriterion("addressAlias not between", value1, value2, "addressalias");
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

        public Criteria andLongitudeEqualTo(BigDecimal value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(BigDecimal value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(BigDecimal value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(BigDecimal value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<BigDecimal> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<BigDecimal> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(BigDecimal value1, BigDecimal value2) {
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

        public Criteria andLatitudeEqualTo(BigDecimal value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(BigDecimal value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(BigDecimal value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(BigDecimal value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<BigDecimal> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<BigDecimal> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andPerimeterIsNull() {
            addCriterion("perimeter is null");
            return (Criteria) this;
        }

        public Criteria andPerimeterIsNotNull() {
            addCriterion("perimeter is not null");
            return (Criteria) this;
        }

        public Criteria andPerimeterEqualTo(Float value) {
            addCriterion("perimeter =", value, "perimeter");
            return (Criteria) this;
        }

        public Criteria andPerimeterNotEqualTo(Float value) {
            addCriterion("perimeter <>", value, "perimeter");
            return (Criteria) this;
        }

        public Criteria andPerimeterGreaterThan(Float value) {
            addCriterion("perimeter >", value, "perimeter");
            return (Criteria) this;
        }

        public Criteria andPerimeterGreaterThanOrEqualTo(Float value) {
            addCriterion("perimeter >=", value, "perimeter");
            return (Criteria) this;
        }

        public Criteria andPerimeterLessThan(Float value) {
            addCriterion("perimeter <", value, "perimeter");
            return (Criteria) this;
        }

        public Criteria andPerimeterLessThanOrEqualTo(Float value) {
            addCriterion("perimeter <=", value, "perimeter");
            return (Criteria) this;
        }

        public Criteria andPerimeterIn(List<Float> values) {
            addCriterion("perimeter in", values, "perimeter");
            return (Criteria) this;
        }

        public Criteria andPerimeterNotIn(List<Float> values) {
            addCriterion("perimeter not in", values, "perimeter");
            return (Criteria) this;
        }

        public Criteria andPerimeterBetween(Float value1, Float value2) {
            addCriterion("perimeter between", value1, value2, "perimeter");
            return (Criteria) this;
        }

        public Criteria andPerimeterNotBetween(Float value1, Float value2) {
            addCriterion("perimeter not between", value1, value2, "perimeter");
            return (Criteria) this;
        }

        public Criteria andCreatoruseridIsNull() {
            addCriterion("creatorUserId is null");
            return (Criteria) this;
        }

        public Criteria andCreatoruseridIsNotNull() {
            addCriterion("creatorUserId is not null");
            return (Criteria) this;
        }

        public Criteria andCreatoruseridEqualTo(Long value) {
            addCriterion("creatorUserId =", value, "creatoruserid");
            return (Criteria) this;
        }

        public Criteria andCreatoruseridNotEqualTo(Long value) {
            addCriterion("creatorUserId <>", value, "creatoruserid");
            return (Criteria) this;
        }

        public Criteria andCreatoruseridGreaterThan(Long value) {
            addCriterion("creatorUserId >", value, "creatoruserid");
            return (Criteria) this;
        }

        public Criteria andCreatoruseridGreaterThanOrEqualTo(Long value) {
            addCriterion("creatorUserId >=", value, "creatoruserid");
            return (Criteria) this;
        }

        public Criteria andCreatoruseridLessThan(Long value) {
            addCriterion("creatorUserId <", value, "creatoruserid");
            return (Criteria) this;
        }

        public Criteria andCreatoruseridLessThanOrEqualTo(Long value) {
            addCriterion("creatorUserId <=", value, "creatoruserid");
            return (Criteria) this;
        }

        public Criteria andCreatoruseridIn(List<Long> values) {
            addCriterion("creatorUserId in", values, "creatoruserid");
            return (Criteria) this;
        }

        public Criteria andCreatoruseridNotIn(List<Long> values) {
            addCriterion("creatorUserId not in", values, "creatoruserid");
            return (Criteria) this;
        }

        public Criteria andCreatoruseridBetween(Long value1, Long value2) {
            addCriterion("creatorUserId between", value1, value2, "creatoruserid");
            return (Criteria) this;
        }

        public Criteria andCreatoruseridNotBetween(Long value1, Long value2) {
            addCriterion("creatorUserId not between", value1, value2, "creatoruserid");
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

        public Criteria andOrdersIsNull() {
            addCriterion("orders is null");
            return (Criteria) this;
        }

        public Criteria andOrdersIsNotNull() {
            addCriterion("orders is not null");
            return (Criteria) this;
        }

        public Criteria andOrdersEqualTo(Integer value) {
            addCriterion("orders =", value, "orders");
            return (Criteria) this;
        }

        public Criteria andOrdersNotEqualTo(Integer value) {
            addCriterion("orders <>", value, "orders");
            return (Criteria) this;
        }

        public Criteria andOrdersGreaterThan(Integer value) {
            addCriterion("orders >", value, "orders");
            return (Criteria) this;
        }

        public Criteria andOrdersGreaterThanOrEqualTo(Integer value) {
            addCriterion("orders >=", value, "orders");
            return (Criteria) this;
        }

        public Criteria andOrdersLessThan(Integer value) {
            addCriterion("orders <", value, "orders");
            return (Criteria) this;
        }

        public Criteria andOrdersLessThanOrEqualTo(Integer value) {
            addCriterion("orders <=", value, "orders");
            return (Criteria) this;
        }

        public Criteria andOrdersIn(List<Integer> values) {
            addCriterion("orders in", values, "orders");
            return (Criteria) this;
        }

        public Criteria andOrdersNotIn(List<Integer> values) {
            addCriterion("orders not in", values, "orders");
            return (Criteria) this;
        }

        public Criteria andOrdersBetween(Integer value1, Integer value2) {
            addCriterion("orders between", value1, value2, "orders");
            return (Criteria) this;
        }

        public Criteria andOrdersNotBetween(Integer value1, Integer value2) {
            addCriterion("orders not between", value1, value2, "orders");
            return (Criteria) this;
        }

        public Criteria andHotIsNull() {
            addCriterion("hot is null");
            return (Criteria) this;
        }

        public Criteria andHotIsNotNull() {
            addCriterion("hot is not null");
            return (Criteria) this;
        }

        public Criteria andHotEqualTo(Integer value) {
            addCriterion("hot =", value, "hot");
            return (Criteria) this;
        }

        public Criteria andHotNotEqualTo(Integer value) {
            addCriterion("hot <>", value, "hot");
            return (Criteria) this;
        }

        public Criteria andHotGreaterThan(Integer value) {
            addCriterion("hot >", value, "hot");
            return (Criteria) this;
        }

        public Criteria andHotGreaterThanOrEqualTo(Integer value) {
            addCriterion("hot >=", value, "hot");
            return (Criteria) this;
        }

        public Criteria andHotLessThan(Integer value) {
            addCriterion("hot <", value, "hot");
            return (Criteria) this;
        }

        public Criteria andHotLessThanOrEqualTo(Integer value) {
            addCriterion("hot <=", value, "hot");
            return (Criteria) this;
        }

        public Criteria andHotIn(List<Integer> values) {
            addCriterion("hot in", values, "hot");
            return (Criteria) this;
        }

        public Criteria andHotNotIn(List<Integer> values) {
            addCriterion("hot not in", values, "hot");
            return (Criteria) this;
        }

        public Criteria andHotBetween(Integer value1, Integer value2) {
            addCriterion("hot between", value1, value2, "hot");
            return (Criteria) this;
        }

        public Criteria andHotNotBetween(Integer value1, Integer value2) {
            addCriterion("hot not between", value1, value2, "hot");
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

        public Criteria andKeyssIsNull() {
            addCriterion("keyss is null");
            return (Criteria) this;
        }

        public Criteria andKeyssIsNotNull() {
            addCriterion("keyss is not null");
            return (Criteria) this;
        }

        public Criteria andKeyssEqualTo(String value) {
            addCriterion("keyss =", value, "keyss");
            return (Criteria) this;
        }

        public Criteria andKeyssNotEqualTo(String value) {
            addCriterion("keyss <>", value, "keyss");
            return (Criteria) this;
        }

        public Criteria andKeyssGreaterThan(String value) {
            addCriterion("keyss >", value, "keyss");
            return (Criteria) this;
        }

        public Criteria andKeyssGreaterThanOrEqualTo(String value) {
            addCriterion("keyss >=", value, "keyss");
            return (Criteria) this;
        }

        public Criteria andKeyssLessThan(String value) {
            addCriterion("keyss <", value, "keyss");
            return (Criteria) this;
        }

        public Criteria andKeyssLessThanOrEqualTo(String value) {
            addCriterion("keyss <=", value, "keyss");
            return (Criteria) this;
        }

        public Criteria andKeyssLike(String value) {
            addCriterion("keyss like", value, "keyss");
            return (Criteria) this;
        }

        public Criteria andKeyssNotLike(String value) {
            addCriterion("keyss not like", value, "keyss");
            return (Criteria) this;
        }

        public Criteria andKeyssIn(List<String> values) {
            addCriterion("keyss in", values, "keyss");
            return (Criteria) this;
        }

        public Criteria andKeyssNotIn(List<String> values) {
            addCriterion("keyss not in", values, "keyss");
            return (Criteria) this;
        }

        public Criteria andKeyssBetween(String value1, String value2) {
            addCriterion("keyss between", value1, value2, "keyss");
            return (Criteria) this;
        }

        public Criteria andKeyssNotBetween(String value1, String value2) {
            addCriterion("keyss not between", value1, value2, "keyss");
            return (Criteria) this;
        }

        public Criteria andLocationpicIsNull() {
            addCriterion("locationPic is null");
            return (Criteria) this;
        }

        public Criteria andLocationpicIsNotNull() {
            addCriterion("locationPic is not null");
            return (Criteria) this;
        }

        public Criteria andLocationpicEqualTo(String value) {
            addCriterion("locationPic =", value, "locationpic");
            return (Criteria) this;
        }

        public Criteria andLocationpicNotEqualTo(String value) {
            addCriterion("locationPic <>", value, "locationpic");
            return (Criteria) this;
        }

        public Criteria andLocationpicGreaterThan(String value) {
            addCriterion("locationPic >", value, "locationpic");
            return (Criteria) this;
        }

        public Criteria andLocationpicGreaterThanOrEqualTo(String value) {
            addCriterion("locationPic >=", value, "locationpic");
            return (Criteria) this;
        }

        public Criteria andLocationpicLessThan(String value) {
            addCriterion("locationPic <", value, "locationpic");
            return (Criteria) this;
        }

        public Criteria andLocationpicLessThanOrEqualTo(String value) {
            addCriterion("locationPic <=", value, "locationpic");
            return (Criteria) this;
        }

        public Criteria andLocationpicLike(String value) {
            addCriterion("locationPic like", value, "locationpic");
            return (Criteria) this;
        }

        public Criteria andLocationpicNotLike(String value) {
            addCriterion("locationPic not like", value, "locationpic");
            return (Criteria) this;
        }

        public Criteria andLocationpicIn(List<String> values) {
            addCriterion("locationPic in", values, "locationpic");
            return (Criteria) this;
        }

        public Criteria andLocationpicNotIn(List<String> values) {
            addCriterion("locationPic not in", values, "locationpic");
            return (Criteria) this;
        }

        public Criteria andLocationpicBetween(String value1, String value2) {
            addCriterion("locationPic between", value1, value2, "locationpic");
            return (Criteria) this;
        }

        public Criteria andLocationpicNotBetween(String value1, String value2) {
            addCriterion("locationPic not between", value1, value2, "locationpic");
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