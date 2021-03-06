package cherry.common.db.gen.dto;

import cherry.foundation.type.DeletedFlag;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDateTime;

public class ZipcdMasterCriteria {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    public ZipcdMasterCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
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

        public Criteria andCityCdIsNull() {
            addCriterion("CITY_CD is null");
            return (Criteria) this;
        }

        public Criteria andCityCdIsNotNull() {
            addCriterion("CITY_CD is not null");
            return (Criteria) this;
        }

        public Criteria andCityCdEqualTo(Integer value) {
            addCriterion("CITY_CD =", value, "cityCd");
            return (Criteria) this;
        }

        public Criteria andCityCdNotEqualTo(Integer value) {
            addCriterion("CITY_CD <>", value, "cityCd");
            return (Criteria) this;
        }

        public Criteria andCityCdGreaterThan(Integer value) {
            addCriterion("CITY_CD >", value, "cityCd");
            return (Criteria) this;
        }

        public Criteria andCityCdGreaterThanOrEqualTo(Integer value) {
            addCriterion("CITY_CD >=", value, "cityCd");
            return (Criteria) this;
        }

        public Criteria andCityCdLessThan(Integer value) {
            addCriterion("CITY_CD <", value, "cityCd");
            return (Criteria) this;
        }

        public Criteria andCityCdLessThanOrEqualTo(Integer value) {
            addCriterion("CITY_CD <=", value, "cityCd");
            return (Criteria) this;
        }

        public Criteria andCityCdIn(List<Integer> values) {
            addCriterion("CITY_CD in", values, "cityCd");
            return (Criteria) this;
        }

        public Criteria andCityCdNotIn(List<Integer> values) {
            addCriterion("CITY_CD not in", values, "cityCd");
            return (Criteria) this;
        }

        public Criteria andCityCdBetween(Integer value1, Integer value2) {
            addCriterion("CITY_CD between", value1, value2, "cityCd");
            return (Criteria) this;
        }

        public Criteria andCityCdNotBetween(Integer value1, Integer value2) {
            addCriterion("CITY_CD not between", value1, value2, "cityCd");
            return (Criteria) this;
        }

        public Criteria andZipcdIsNull() {
            addCriterion("ZIPCD is null");
            return (Criteria) this;
        }

        public Criteria andZipcdIsNotNull() {
            addCriterion("ZIPCD is not null");
            return (Criteria) this;
        }

        public Criteria andZipcdEqualTo(String value) {
            addCriterion("ZIPCD =", value, "zipcd");
            return (Criteria) this;
        }

        public Criteria andZipcdNotEqualTo(String value) {
            addCriterion("ZIPCD <>", value, "zipcd");
            return (Criteria) this;
        }

        public Criteria andZipcdGreaterThan(String value) {
            addCriterion("ZIPCD >", value, "zipcd");
            return (Criteria) this;
        }

        public Criteria andZipcdGreaterThanOrEqualTo(String value) {
            addCriterion("ZIPCD >=", value, "zipcd");
            return (Criteria) this;
        }

        public Criteria andZipcdLessThan(String value) {
            addCriterion("ZIPCD <", value, "zipcd");
            return (Criteria) this;
        }

        public Criteria andZipcdLessThanOrEqualTo(String value) {
            addCriterion("ZIPCD <=", value, "zipcd");
            return (Criteria) this;
        }

        public Criteria andZipcdLike(String value) {
            addCriterion("ZIPCD like", value, "zipcd");
            return (Criteria) this;
        }

        public Criteria andZipcdNotLike(String value) {
            addCriterion("ZIPCD not like", value, "zipcd");
            return (Criteria) this;
        }

        public Criteria andZipcdIn(List<String> values) {
            addCriterion("ZIPCD in", values, "zipcd");
            return (Criteria) this;
        }

        public Criteria andZipcdNotIn(List<String> values) {
            addCriterion("ZIPCD not in", values, "zipcd");
            return (Criteria) this;
        }

        public Criteria andZipcdBetween(String value1, String value2) {
            addCriterion("ZIPCD between", value1, value2, "zipcd");
            return (Criteria) this;
        }

        public Criteria andZipcdNotBetween(String value1, String value2) {
            addCriterion("ZIPCD not between", value1, value2, "zipcd");
            return (Criteria) this;
        }

        public Criteria andPrefIsNull() {
            addCriterion("PREF is null");
            return (Criteria) this;
        }

        public Criteria andPrefIsNotNull() {
            addCriterion("PREF is not null");
            return (Criteria) this;
        }

        public Criteria andPrefEqualTo(String value) {
            addCriterion("PREF =", value, "pref");
            return (Criteria) this;
        }

        public Criteria andPrefNotEqualTo(String value) {
            addCriterion("PREF <>", value, "pref");
            return (Criteria) this;
        }

        public Criteria andPrefGreaterThan(String value) {
            addCriterion("PREF >", value, "pref");
            return (Criteria) this;
        }

        public Criteria andPrefGreaterThanOrEqualTo(String value) {
            addCriterion("PREF >=", value, "pref");
            return (Criteria) this;
        }

        public Criteria andPrefLessThan(String value) {
            addCriterion("PREF <", value, "pref");
            return (Criteria) this;
        }

        public Criteria andPrefLessThanOrEqualTo(String value) {
            addCriterion("PREF <=", value, "pref");
            return (Criteria) this;
        }

        public Criteria andPrefLike(String value) {
            addCriterion("PREF like", value, "pref");
            return (Criteria) this;
        }

        public Criteria andPrefNotLike(String value) {
            addCriterion("PREF not like", value, "pref");
            return (Criteria) this;
        }

        public Criteria andPrefIn(List<String> values) {
            addCriterion("PREF in", values, "pref");
            return (Criteria) this;
        }

        public Criteria andPrefNotIn(List<String> values) {
            addCriterion("PREF not in", values, "pref");
            return (Criteria) this;
        }

        public Criteria andPrefBetween(String value1, String value2) {
            addCriterion("PREF between", value1, value2, "pref");
            return (Criteria) this;
        }

        public Criteria andPrefNotBetween(String value1, String value2) {
            addCriterion("PREF not between", value1, value2, "pref");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("CITY is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("CITY is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("CITY =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("CITY <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("CITY >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("CITY >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("CITY <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("CITY <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("CITY like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("CITY not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("CITY in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("CITY not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("CITY between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("CITY not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andAddrIsNull() {
            addCriterion("ADDR is null");
            return (Criteria) this;
        }

        public Criteria andAddrIsNotNull() {
            addCriterion("ADDR is not null");
            return (Criteria) this;
        }

        public Criteria andAddrEqualTo(String value) {
            addCriterion("ADDR =", value, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrNotEqualTo(String value) {
            addCriterion("ADDR <>", value, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrGreaterThan(String value) {
            addCriterion("ADDR >", value, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrGreaterThanOrEqualTo(String value) {
            addCriterion("ADDR >=", value, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrLessThan(String value) {
            addCriterion("ADDR <", value, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrLessThanOrEqualTo(String value) {
            addCriterion("ADDR <=", value, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrLike(String value) {
            addCriterion("ADDR like", value, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrNotLike(String value) {
            addCriterion("ADDR not like", value, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrIn(List<String> values) {
            addCriterion("ADDR in", values, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrNotIn(List<String> values) {
            addCriterion("ADDR not in", values, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrBetween(String value1, String value2) {
            addCriterion("ADDR between", value1, value2, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrNotBetween(String value1, String value2) {
            addCriterion("ADDR not between", value1, value2, "addr");
            return (Criteria) this;
        }

        public Criteria andPrefKanaIsNull() {
            addCriterion("PREF_KANA is null");
            return (Criteria) this;
        }

        public Criteria andPrefKanaIsNotNull() {
            addCriterion("PREF_KANA is not null");
            return (Criteria) this;
        }

        public Criteria andPrefKanaEqualTo(String value) {
            addCriterion("PREF_KANA =", value, "prefKana");
            return (Criteria) this;
        }

        public Criteria andPrefKanaNotEqualTo(String value) {
            addCriterion("PREF_KANA <>", value, "prefKana");
            return (Criteria) this;
        }

        public Criteria andPrefKanaGreaterThan(String value) {
            addCriterion("PREF_KANA >", value, "prefKana");
            return (Criteria) this;
        }

        public Criteria andPrefKanaGreaterThanOrEqualTo(String value) {
            addCriterion("PREF_KANA >=", value, "prefKana");
            return (Criteria) this;
        }

        public Criteria andPrefKanaLessThan(String value) {
            addCriterion("PREF_KANA <", value, "prefKana");
            return (Criteria) this;
        }

        public Criteria andPrefKanaLessThanOrEqualTo(String value) {
            addCriterion("PREF_KANA <=", value, "prefKana");
            return (Criteria) this;
        }

        public Criteria andPrefKanaLike(String value) {
            addCriterion("PREF_KANA like", value, "prefKana");
            return (Criteria) this;
        }

        public Criteria andPrefKanaNotLike(String value) {
            addCriterion("PREF_KANA not like", value, "prefKana");
            return (Criteria) this;
        }

        public Criteria andPrefKanaIn(List<String> values) {
            addCriterion("PREF_KANA in", values, "prefKana");
            return (Criteria) this;
        }

        public Criteria andPrefKanaNotIn(List<String> values) {
            addCriterion("PREF_KANA not in", values, "prefKana");
            return (Criteria) this;
        }

        public Criteria andPrefKanaBetween(String value1, String value2) {
            addCriterion("PREF_KANA between", value1, value2, "prefKana");
            return (Criteria) this;
        }

        public Criteria andPrefKanaNotBetween(String value1, String value2) {
            addCriterion("PREF_KANA not between", value1, value2, "prefKana");
            return (Criteria) this;
        }

        public Criteria andCityKanaIsNull() {
            addCriterion("CITY_KANA is null");
            return (Criteria) this;
        }

        public Criteria andCityKanaIsNotNull() {
            addCriterion("CITY_KANA is not null");
            return (Criteria) this;
        }

        public Criteria andCityKanaEqualTo(String value) {
            addCriterion("CITY_KANA =", value, "cityKana");
            return (Criteria) this;
        }

        public Criteria andCityKanaNotEqualTo(String value) {
            addCriterion("CITY_KANA <>", value, "cityKana");
            return (Criteria) this;
        }

        public Criteria andCityKanaGreaterThan(String value) {
            addCriterion("CITY_KANA >", value, "cityKana");
            return (Criteria) this;
        }

        public Criteria andCityKanaGreaterThanOrEqualTo(String value) {
            addCriterion("CITY_KANA >=", value, "cityKana");
            return (Criteria) this;
        }

        public Criteria andCityKanaLessThan(String value) {
            addCriterion("CITY_KANA <", value, "cityKana");
            return (Criteria) this;
        }

        public Criteria andCityKanaLessThanOrEqualTo(String value) {
            addCriterion("CITY_KANA <=", value, "cityKana");
            return (Criteria) this;
        }

        public Criteria andCityKanaLike(String value) {
            addCriterion("CITY_KANA like", value, "cityKana");
            return (Criteria) this;
        }

        public Criteria andCityKanaNotLike(String value) {
            addCriterion("CITY_KANA not like", value, "cityKana");
            return (Criteria) this;
        }

        public Criteria andCityKanaIn(List<String> values) {
            addCriterion("CITY_KANA in", values, "cityKana");
            return (Criteria) this;
        }

        public Criteria andCityKanaNotIn(List<String> values) {
            addCriterion("CITY_KANA not in", values, "cityKana");
            return (Criteria) this;
        }

        public Criteria andCityKanaBetween(String value1, String value2) {
            addCriterion("CITY_KANA between", value1, value2, "cityKana");
            return (Criteria) this;
        }

        public Criteria andCityKanaNotBetween(String value1, String value2) {
            addCriterion("CITY_KANA not between", value1, value2, "cityKana");
            return (Criteria) this;
        }

        public Criteria andAddrKanaIsNull() {
            addCriterion("ADDR_KANA is null");
            return (Criteria) this;
        }

        public Criteria andAddrKanaIsNotNull() {
            addCriterion("ADDR_KANA is not null");
            return (Criteria) this;
        }

        public Criteria andAddrKanaEqualTo(String value) {
            addCriterion("ADDR_KANA =", value, "addrKana");
            return (Criteria) this;
        }

        public Criteria andAddrKanaNotEqualTo(String value) {
            addCriterion("ADDR_KANA <>", value, "addrKana");
            return (Criteria) this;
        }

        public Criteria andAddrKanaGreaterThan(String value) {
            addCriterion("ADDR_KANA >", value, "addrKana");
            return (Criteria) this;
        }

        public Criteria andAddrKanaGreaterThanOrEqualTo(String value) {
            addCriterion("ADDR_KANA >=", value, "addrKana");
            return (Criteria) this;
        }

        public Criteria andAddrKanaLessThan(String value) {
            addCriterion("ADDR_KANA <", value, "addrKana");
            return (Criteria) this;
        }

        public Criteria andAddrKanaLessThanOrEqualTo(String value) {
            addCriterion("ADDR_KANA <=", value, "addrKana");
            return (Criteria) this;
        }

        public Criteria andAddrKanaLike(String value) {
            addCriterion("ADDR_KANA like", value, "addrKana");
            return (Criteria) this;
        }

        public Criteria andAddrKanaNotLike(String value) {
            addCriterion("ADDR_KANA not like", value, "addrKana");
            return (Criteria) this;
        }

        public Criteria andAddrKanaIn(List<String> values) {
            addCriterion("ADDR_KANA in", values, "addrKana");
            return (Criteria) this;
        }

        public Criteria andAddrKanaNotIn(List<String> values) {
            addCriterion("ADDR_KANA not in", values, "addrKana");
            return (Criteria) this;
        }

        public Criteria andAddrKanaBetween(String value1, String value2) {
            addCriterion("ADDR_KANA between", value1, value2, "addrKana");
            return (Criteria) this;
        }

        public Criteria andAddrKanaNotBetween(String value1, String value2) {
            addCriterion("ADDR_KANA not between", value1, value2, "addrKana");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNull() {
            addCriterion("UPDATED_AT is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNotNull() {
            addCriterion("UPDATED_AT is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtEqualTo(LocalDateTime value) {
            addCriterion("UPDATED_AT =", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(LocalDateTime value) {
            addCriterion("UPDATED_AT <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(LocalDateTime value) {
            addCriterion("UPDATED_AT >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("UPDATED_AT >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(LocalDateTime value) {
            addCriterion("UPDATED_AT <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("UPDATED_AT <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIn(List<LocalDateTime> values) {
            addCriterion("UPDATED_AT in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotIn(List<LocalDateTime> values) {
            addCriterion("UPDATED_AT not in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("UPDATED_AT between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("UPDATED_AT not between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("CREATED_AT is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("CREATED_AT is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(LocalDateTime value) {
            addCriterion("CREATED_AT =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(LocalDateTime value) {
            addCriterion("CREATED_AT <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(LocalDateTime value) {
            addCriterion("CREATED_AT >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("CREATED_AT >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(LocalDateTime value) {
            addCriterion("CREATED_AT <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("CREATED_AT <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<LocalDateTime> values) {
            addCriterion("CREATED_AT in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<LocalDateTime> values) {
            addCriterion("CREATED_AT not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("CREATED_AT between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("CREATED_AT not between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andLockVersionIsNull() {
            addCriterion("LOCK_VERSION is null");
            return (Criteria) this;
        }

        public Criteria andLockVersionIsNotNull() {
            addCriterion("LOCK_VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andLockVersionEqualTo(Integer value) {
            addCriterion("LOCK_VERSION =", value, "lockVersion");
            return (Criteria) this;
        }

        public Criteria andLockVersionNotEqualTo(Integer value) {
            addCriterion("LOCK_VERSION <>", value, "lockVersion");
            return (Criteria) this;
        }

        public Criteria andLockVersionGreaterThan(Integer value) {
            addCriterion("LOCK_VERSION >", value, "lockVersion");
            return (Criteria) this;
        }

        public Criteria andLockVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("LOCK_VERSION >=", value, "lockVersion");
            return (Criteria) this;
        }

        public Criteria andLockVersionLessThan(Integer value) {
            addCriterion("LOCK_VERSION <", value, "lockVersion");
            return (Criteria) this;
        }

        public Criteria andLockVersionLessThanOrEqualTo(Integer value) {
            addCriterion("LOCK_VERSION <=", value, "lockVersion");
            return (Criteria) this;
        }

        public Criteria andLockVersionIn(List<Integer> values) {
            addCriterion("LOCK_VERSION in", values, "lockVersion");
            return (Criteria) this;
        }

        public Criteria andLockVersionNotIn(List<Integer> values) {
            addCriterion("LOCK_VERSION not in", values, "lockVersion");
            return (Criteria) this;
        }

        public Criteria andLockVersionBetween(Integer value1, Integer value2) {
            addCriterion("LOCK_VERSION between", value1, value2, "lockVersion");
            return (Criteria) this;
        }

        public Criteria andLockVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("LOCK_VERSION not between", value1, value2, "lockVersion");
            return (Criteria) this;
        }

        public Criteria andDeletedFlgIsNull() {
            addCriterion("DELETED_FLG is null");
            return (Criteria) this;
        }

        public Criteria andDeletedFlgIsNotNull() {
            addCriterion("DELETED_FLG is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedFlgEqualTo(DeletedFlag value) {
            addCriterion("DELETED_FLG =", value, "deletedFlg");
            return (Criteria) this;
        }

        public Criteria andDeletedFlgNotEqualTo(DeletedFlag value) {
            addCriterion("DELETED_FLG <>", value, "deletedFlg");
            return (Criteria) this;
        }

        public Criteria andDeletedFlgGreaterThan(DeletedFlag value) {
            addCriterion("DELETED_FLG >", value, "deletedFlg");
            return (Criteria) this;
        }

        public Criteria andDeletedFlgGreaterThanOrEqualTo(DeletedFlag value) {
            addCriterion("DELETED_FLG >=", value, "deletedFlg");
            return (Criteria) this;
        }

        public Criteria andDeletedFlgLessThan(DeletedFlag value) {
            addCriterion("DELETED_FLG <", value, "deletedFlg");
            return (Criteria) this;
        }

        public Criteria andDeletedFlgLessThanOrEqualTo(DeletedFlag value) {
            addCriterion("DELETED_FLG <=", value, "deletedFlg");
            return (Criteria) this;
        }

        public Criteria andDeletedFlgIn(List<DeletedFlag> values) {
            addCriterion("DELETED_FLG in", values, "deletedFlg");
            return (Criteria) this;
        }

        public Criteria andDeletedFlgNotIn(List<DeletedFlag> values) {
            addCriterion("DELETED_FLG not in", values, "deletedFlg");
            return (Criteria) this;
        }

        public Criteria andDeletedFlgBetween(DeletedFlag value1, DeletedFlag value2) {
            addCriterion("DELETED_FLG between", value1, value2, "deletedFlg");
            return (Criteria) this;
        }

        public Criteria andDeletedFlgNotBetween(DeletedFlag value1, DeletedFlag value2) {
            addCriterion("DELETED_FLG not between", value1, value2, "deletedFlg");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
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