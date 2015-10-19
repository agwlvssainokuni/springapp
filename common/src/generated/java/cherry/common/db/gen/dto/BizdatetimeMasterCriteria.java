package cherry.common.db.gen.dto;

import cherry.foundation.type.DeletedFlag;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

public class BizdatetimeMasterCriteria {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table BIZDATETIME_MASTER
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table BIZDATETIME_MASTER
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table BIZDATETIME_MASTER
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BIZDATETIME_MASTER
     *
     * @mbggenerated
     */
    public BizdatetimeMasterCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BIZDATETIME_MASTER
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BIZDATETIME_MASTER
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BIZDATETIME_MASTER
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BIZDATETIME_MASTER
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BIZDATETIME_MASTER
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BIZDATETIME_MASTER
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BIZDATETIME_MASTER
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
     * This method corresponds to the database table BIZDATETIME_MASTER
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
     * This method corresponds to the database table BIZDATETIME_MASTER
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BIZDATETIME_MASTER
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
     * This class corresponds to the database table BIZDATETIME_MASTER
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

        public Criteria andBizdateIsNull() {
            addCriterion("BIZDATE is null");
            return (Criteria) this;
        }

        public Criteria andBizdateIsNotNull() {
            addCriterion("BIZDATE is not null");
            return (Criteria) this;
        }

        public Criteria andBizdateEqualTo(LocalDate value) {
            addCriterion("BIZDATE =", value, "bizdate");
            return (Criteria) this;
        }

        public Criteria andBizdateNotEqualTo(LocalDate value) {
            addCriterion("BIZDATE <>", value, "bizdate");
            return (Criteria) this;
        }

        public Criteria andBizdateGreaterThan(LocalDate value) {
            addCriterion("BIZDATE >", value, "bizdate");
            return (Criteria) this;
        }

        public Criteria andBizdateGreaterThanOrEqualTo(LocalDate value) {
            addCriterion("BIZDATE >=", value, "bizdate");
            return (Criteria) this;
        }

        public Criteria andBizdateLessThan(LocalDate value) {
            addCriterion("BIZDATE <", value, "bizdate");
            return (Criteria) this;
        }

        public Criteria andBizdateLessThanOrEqualTo(LocalDate value) {
            addCriterion("BIZDATE <=", value, "bizdate");
            return (Criteria) this;
        }

        public Criteria andBizdateIn(List<LocalDate> values) {
            addCriterion("BIZDATE in", values, "bizdate");
            return (Criteria) this;
        }

        public Criteria andBizdateNotIn(List<LocalDate> values) {
            addCriterion("BIZDATE not in", values, "bizdate");
            return (Criteria) this;
        }

        public Criteria andBizdateBetween(LocalDate value1, LocalDate value2) {
            addCriterion("BIZDATE between", value1, value2, "bizdate");
            return (Criteria) this;
        }

        public Criteria andBizdateNotBetween(LocalDate value1, LocalDate value2) {
            addCriterion("BIZDATE not between", value1, value2, "bizdate");
            return (Criteria) this;
        }

        public Criteria andOffsetDayIsNull() {
            addCriterion("OFFSET_DAY is null");
            return (Criteria) this;
        }

        public Criteria andOffsetDayIsNotNull() {
            addCriterion("OFFSET_DAY is not null");
            return (Criteria) this;
        }

        public Criteria andOffsetDayEqualTo(Integer value) {
            addCriterion("OFFSET_DAY =", value, "offsetDay");
            return (Criteria) this;
        }

        public Criteria andOffsetDayNotEqualTo(Integer value) {
            addCriterion("OFFSET_DAY <>", value, "offsetDay");
            return (Criteria) this;
        }

        public Criteria andOffsetDayGreaterThan(Integer value) {
            addCriterion("OFFSET_DAY >", value, "offsetDay");
            return (Criteria) this;
        }

        public Criteria andOffsetDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("OFFSET_DAY >=", value, "offsetDay");
            return (Criteria) this;
        }

        public Criteria andOffsetDayLessThan(Integer value) {
            addCriterion("OFFSET_DAY <", value, "offsetDay");
            return (Criteria) this;
        }

        public Criteria andOffsetDayLessThanOrEqualTo(Integer value) {
            addCriterion("OFFSET_DAY <=", value, "offsetDay");
            return (Criteria) this;
        }

        public Criteria andOffsetDayIn(List<Integer> values) {
            addCriterion("OFFSET_DAY in", values, "offsetDay");
            return (Criteria) this;
        }

        public Criteria andOffsetDayNotIn(List<Integer> values) {
            addCriterion("OFFSET_DAY not in", values, "offsetDay");
            return (Criteria) this;
        }

        public Criteria andOffsetDayBetween(Integer value1, Integer value2) {
            addCriterion("OFFSET_DAY between", value1, value2, "offsetDay");
            return (Criteria) this;
        }

        public Criteria andOffsetDayNotBetween(Integer value1, Integer value2) {
            addCriterion("OFFSET_DAY not between", value1, value2, "offsetDay");
            return (Criteria) this;
        }

        public Criteria andOffsetHourIsNull() {
            addCriterion("OFFSET_HOUR is null");
            return (Criteria) this;
        }

        public Criteria andOffsetHourIsNotNull() {
            addCriterion("OFFSET_HOUR is not null");
            return (Criteria) this;
        }

        public Criteria andOffsetHourEqualTo(Integer value) {
            addCriterion("OFFSET_HOUR =", value, "offsetHour");
            return (Criteria) this;
        }

        public Criteria andOffsetHourNotEqualTo(Integer value) {
            addCriterion("OFFSET_HOUR <>", value, "offsetHour");
            return (Criteria) this;
        }

        public Criteria andOffsetHourGreaterThan(Integer value) {
            addCriterion("OFFSET_HOUR >", value, "offsetHour");
            return (Criteria) this;
        }

        public Criteria andOffsetHourGreaterThanOrEqualTo(Integer value) {
            addCriterion("OFFSET_HOUR >=", value, "offsetHour");
            return (Criteria) this;
        }

        public Criteria andOffsetHourLessThan(Integer value) {
            addCriterion("OFFSET_HOUR <", value, "offsetHour");
            return (Criteria) this;
        }

        public Criteria andOffsetHourLessThanOrEqualTo(Integer value) {
            addCriterion("OFFSET_HOUR <=", value, "offsetHour");
            return (Criteria) this;
        }

        public Criteria andOffsetHourIn(List<Integer> values) {
            addCriterion("OFFSET_HOUR in", values, "offsetHour");
            return (Criteria) this;
        }

        public Criteria andOffsetHourNotIn(List<Integer> values) {
            addCriterion("OFFSET_HOUR not in", values, "offsetHour");
            return (Criteria) this;
        }

        public Criteria andOffsetHourBetween(Integer value1, Integer value2) {
            addCriterion("OFFSET_HOUR between", value1, value2, "offsetHour");
            return (Criteria) this;
        }

        public Criteria andOffsetHourNotBetween(Integer value1, Integer value2) {
            addCriterion("OFFSET_HOUR not between", value1, value2, "offsetHour");
            return (Criteria) this;
        }

        public Criteria andOffsetMinuteIsNull() {
            addCriterion("OFFSET_MINUTE is null");
            return (Criteria) this;
        }

        public Criteria andOffsetMinuteIsNotNull() {
            addCriterion("OFFSET_MINUTE is not null");
            return (Criteria) this;
        }

        public Criteria andOffsetMinuteEqualTo(Integer value) {
            addCriterion("OFFSET_MINUTE =", value, "offsetMinute");
            return (Criteria) this;
        }

        public Criteria andOffsetMinuteNotEqualTo(Integer value) {
            addCriterion("OFFSET_MINUTE <>", value, "offsetMinute");
            return (Criteria) this;
        }

        public Criteria andOffsetMinuteGreaterThan(Integer value) {
            addCriterion("OFFSET_MINUTE >", value, "offsetMinute");
            return (Criteria) this;
        }

        public Criteria andOffsetMinuteGreaterThanOrEqualTo(Integer value) {
            addCriterion("OFFSET_MINUTE >=", value, "offsetMinute");
            return (Criteria) this;
        }

        public Criteria andOffsetMinuteLessThan(Integer value) {
            addCriterion("OFFSET_MINUTE <", value, "offsetMinute");
            return (Criteria) this;
        }

        public Criteria andOffsetMinuteLessThanOrEqualTo(Integer value) {
            addCriterion("OFFSET_MINUTE <=", value, "offsetMinute");
            return (Criteria) this;
        }

        public Criteria andOffsetMinuteIn(List<Integer> values) {
            addCriterion("OFFSET_MINUTE in", values, "offsetMinute");
            return (Criteria) this;
        }

        public Criteria andOffsetMinuteNotIn(List<Integer> values) {
            addCriterion("OFFSET_MINUTE not in", values, "offsetMinute");
            return (Criteria) this;
        }

        public Criteria andOffsetMinuteBetween(Integer value1, Integer value2) {
            addCriterion("OFFSET_MINUTE between", value1, value2, "offsetMinute");
            return (Criteria) this;
        }

        public Criteria andOffsetMinuteNotBetween(Integer value1, Integer value2) {
            addCriterion("OFFSET_MINUTE not between", value1, value2, "offsetMinute");
            return (Criteria) this;
        }

        public Criteria andOffsetSecondIsNull() {
            addCriterion("OFFSET_SECOND is null");
            return (Criteria) this;
        }

        public Criteria andOffsetSecondIsNotNull() {
            addCriterion("OFFSET_SECOND is not null");
            return (Criteria) this;
        }

        public Criteria andOffsetSecondEqualTo(Integer value) {
            addCriterion("OFFSET_SECOND =", value, "offsetSecond");
            return (Criteria) this;
        }

        public Criteria andOffsetSecondNotEqualTo(Integer value) {
            addCriterion("OFFSET_SECOND <>", value, "offsetSecond");
            return (Criteria) this;
        }

        public Criteria andOffsetSecondGreaterThan(Integer value) {
            addCriterion("OFFSET_SECOND >", value, "offsetSecond");
            return (Criteria) this;
        }

        public Criteria andOffsetSecondGreaterThanOrEqualTo(Integer value) {
            addCriterion("OFFSET_SECOND >=", value, "offsetSecond");
            return (Criteria) this;
        }

        public Criteria andOffsetSecondLessThan(Integer value) {
            addCriterion("OFFSET_SECOND <", value, "offsetSecond");
            return (Criteria) this;
        }

        public Criteria andOffsetSecondLessThanOrEqualTo(Integer value) {
            addCriterion("OFFSET_SECOND <=", value, "offsetSecond");
            return (Criteria) this;
        }

        public Criteria andOffsetSecondIn(List<Integer> values) {
            addCriterion("OFFSET_SECOND in", values, "offsetSecond");
            return (Criteria) this;
        }

        public Criteria andOffsetSecondNotIn(List<Integer> values) {
            addCriterion("OFFSET_SECOND not in", values, "offsetSecond");
            return (Criteria) this;
        }

        public Criteria andOffsetSecondBetween(Integer value1, Integer value2) {
            addCriterion("OFFSET_SECOND between", value1, value2, "offsetSecond");
            return (Criteria) this;
        }

        public Criteria andOffsetSecondNotBetween(Integer value1, Integer value2) {
            addCriterion("OFFSET_SECOND not between", value1, value2, "offsetSecond");
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
     * This class corresponds to the database table BIZDATETIME_MASTER
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
     * This class corresponds to the database table BIZDATETIME_MASTER
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