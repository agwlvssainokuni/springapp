package cherry.spring.common.db.gen.dto;

import cherry.foundation.type.DeletedFlag;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDateTime;

public class AsyncProcessCriteria {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ASYNC_PROCESS
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ASYNC_PROCESS
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ASYNC_PROCESS
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS
     *
     * @mbggenerated
     */
    public AsyncProcessCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS
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
     * This method corresponds to the database table ASYNC_PROCESS
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
     * This method corresponds to the database table ASYNC_PROCESS
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCESS
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
     * This class corresponds to the database table ASYNC_PROCESS
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andLaunchedByIsNull() {
            addCriterion("LAUNCHED_BY is null");
            return (Criteria) this;
        }

        public Criteria andLaunchedByIsNotNull() {
            addCriterion("LAUNCHED_BY is not null");
            return (Criteria) this;
        }

        public Criteria andLaunchedByEqualTo(String value) {
            addCriterion("LAUNCHED_BY =", value, "launchedBy");
            return (Criteria) this;
        }

        public Criteria andLaunchedByNotEqualTo(String value) {
            addCriterion("LAUNCHED_BY <>", value, "launchedBy");
            return (Criteria) this;
        }

        public Criteria andLaunchedByGreaterThan(String value) {
            addCriterion("LAUNCHED_BY >", value, "launchedBy");
            return (Criteria) this;
        }

        public Criteria andLaunchedByGreaterThanOrEqualTo(String value) {
            addCriterion("LAUNCHED_BY >=", value, "launchedBy");
            return (Criteria) this;
        }

        public Criteria andLaunchedByLessThan(String value) {
            addCriterion("LAUNCHED_BY <", value, "launchedBy");
            return (Criteria) this;
        }

        public Criteria andLaunchedByLessThanOrEqualTo(String value) {
            addCriterion("LAUNCHED_BY <=", value, "launchedBy");
            return (Criteria) this;
        }

        public Criteria andLaunchedByLike(String value) {
            addCriterion("LAUNCHED_BY like", value, "launchedBy");
            return (Criteria) this;
        }

        public Criteria andLaunchedByNotLike(String value) {
            addCriterion("LAUNCHED_BY not like", value, "launchedBy");
            return (Criteria) this;
        }

        public Criteria andLaunchedByIn(List<String> values) {
            addCriterion("LAUNCHED_BY in", values, "launchedBy");
            return (Criteria) this;
        }

        public Criteria andLaunchedByNotIn(List<String> values) {
            addCriterion("LAUNCHED_BY not in", values, "launchedBy");
            return (Criteria) this;
        }

        public Criteria andLaunchedByBetween(String value1, String value2) {
            addCriterion("LAUNCHED_BY between", value1, value2, "launchedBy");
            return (Criteria) this;
        }

        public Criteria andLaunchedByNotBetween(String value1, String value2) {
            addCriterion("LAUNCHED_BY not between", value1, value2, "launchedBy");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("TYPE =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("TYPE <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("TYPE >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("TYPE <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("TYPE <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("TYPE like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("TYPE not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("TYPE in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("TYPE not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("TYPE not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andLaunchedAtIsNull() {
            addCriterion("LAUNCHED_AT is null");
            return (Criteria) this;
        }

        public Criteria andLaunchedAtIsNotNull() {
            addCriterion("LAUNCHED_AT is not null");
            return (Criteria) this;
        }

        public Criteria andLaunchedAtEqualTo(LocalDateTime value) {
            addCriterion("LAUNCHED_AT =", value, "launchedAt");
            return (Criteria) this;
        }

        public Criteria andLaunchedAtNotEqualTo(LocalDateTime value) {
            addCriterion("LAUNCHED_AT <>", value, "launchedAt");
            return (Criteria) this;
        }

        public Criteria andLaunchedAtGreaterThan(LocalDateTime value) {
            addCriterion("LAUNCHED_AT >", value, "launchedAt");
            return (Criteria) this;
        }

        public Criteria andLaunchedAtGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("LAUNCHED_AT >=", value, "launchedAt");
            return (Criteria) this;
        }

        public Criteria andLaunchedAtLessThan(LocalDateTime value) {
            addCriterion("LAUNCHED_AT <", value, "launchedAt");
            return (Criteria) this;
        }

        public Criteria andLaunchedAtLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("LAUNCHED_AT <=", value, "launchedAt");
            return (Criteria) this;
        }

        public Criteria andLaunchedAtIn(List<LocalDateTime> values) {
            addCriterion("LAUNCHED_AT in", values, "launchedAt");
            return (Criteria) this;
        }

        public Criteria andLaunchedAtNotIn(List<LocalDateTime> values) {
            addCriterion("LAUNCHED_AT not in", values, "launchedAt");
            return (Criteria) this;
        }

        public Criteria andLaunchedAtBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("LAUNCHED_AT between", value1, value2, "launchedAt");
            return (Criteria) this;
        }

        public Criteria andLaunchedAtNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("LAUNCHED_AT not between", value1, value2, "launchedAt");
            return (Criteria) this;
        }

        public Criteria andInvokedAtIsNull() {
            addCriterion("INVOKED_AT is null");
            return (Criteria) this;
        }

        public Criteria andInvokedAtIsNotNull() {
            addCriterion("INVOKED_AT is not null");
            return (Criteria) this;
        }

        public Criteria andInvokedAtEqualTo(LocalDateTime value) {
            addCriterion("INVOKED_AT =", value, "invokedAt");
            return (Criteria) this;
        }

        public Criteria andInvokedAtNotEqualTo(LocalDateTime value) {
            addCriterion("INVOKED_AT <>", value, "invokedAt");
            return (Criteria) this;
        }

        public Criteria andInvokedAtGreaterThan(LocalDateTime value) {
            addCriterion("INVOKED_AT >", value, "invokedAt");
            return (Criteria) this;
        }

        public Criteria andInvokedAtGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("INVOKED_AT >=", value, "invokedAt");
            return (Criteria) this;
        }

        public Criteria andInvokedAtLessThan(LocalDateTime value) {
            addCriterion("INVOKED_AT <", value, "invokedAt");
            return (Criteria) this;
        }

        public Criteria andInvokedAtLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("INVOKED_AT <=", value, "invokedAt");
            return (Criteria) this;
        }

        public Criteria andInvokedAtIn(List<LocalDateTime> values) {
            addCriterion("INVOKED_AT in", values, "invokedAt");
            return (Criteria) this;
        }

        public Criteria andInvokedAtNotIn(List<LocalDateTime> values) {
            addCriterion("INVOKED_AT not in", values, "invokedAt");
            return (Criteria) this;
        }

        public Criteria andInvokedAtBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("INVOKED_AT between", value1, value2, "invokedAt");
            return (Criteria) this;
        }

        public Criteria andInvokedAtNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("INVOKED_AT not between", value1, value2, "invokedAt");
            return (Criteria) this;
        }

        public Criteria andStartedAtIsNull() {
            addCriterion("STARTED_AT is null");
            return (Criteria) this;
        }

        public Criteria andStartedAtIsNotNull() {
            addCriterion("STARTED_AT is not null");
            return (Criteria) this;
        }

        public Criteria andStartedAtEqualTo(LocalDateTime value) {
            addCriterion("STARTED_AT =", value, "startedAt");
            return (Criteria) this;
        }

        public Criteria andStartedAtNotEqualTo(LocalDateTime value) {
            addCriterion("STARTED_AT <>", value, "startedAt");
            return (Criteria) this;
        }

        public Criteria andStartedAtGreaterThan(LocalDateTime value) {
            addCriterion("STARTED_AT >", value, "startedAt");
            return (Criteria) this;
        }

        public Criteria andStartedAtGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("STARTED_AT >=", value, "startedAt");
            return (Criteria) this;
        }

        public Criteria andStartedAtLessThan(LocalDateTime value) {
            addCriterion("STARTED_AT <", value, "startedAt");
            return (Criteria) this;
        }

        public Criteria andStartedAtLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("STARTED_AT <=", value, "startedAt");
            return (Criteria) this;
        }

        public Criteria andStartedAtIn(List<LocalDateTime> values) {
            addCriterion("STARTED_AT in", values, "startedAt");
            return (Criteria) this;
        }

        public Criteria andStartedAtNotIn(List<LocalDateTime> values) {
            addCriterion("STARTED_AT not in", values, "startedAt");
            return (Criteria) this;
        }

        public Criteria andStartedAtBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("STARTED_AT between", value1, value2, "startedAt");
            return (Criteria) this;
        }

        public Criteria andStartedAtNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("STARTED_AT not between", value1, value2, "startedAt");
            return (Criteria) this;
        }

        public Criteria andFinishedAtIsNull() {
            addCriterion("FINISHED_AT is null");
            return (Criteria) this;
        }

        public Criteria andFinishedAtIsNotNull() {
            addCriterion("FINISHED_AT is not null");
            return (Criteria) this;
        }

        public Criteria andFinishedAtEqualTo(LocalDateTime value) {
            addCriterion("FINISHED_AT =", value, "finishedAt");
            return (Criteria) this;
        }

        public Criteria andFinishedAtNotEqualTo(LocalDateTime value) {
            addCriterion("FINISHED_AT <>", value, "finishedAt");
            return (Criteria) this;
        }

        public Criteria andFinishedAtGreaterThan(LocalDateTime value) {
            addCriterion("FINISHED_AT >", value, "finishedAt");
            return (Criteria) this;
        }

        public Criteria andFinishedAtGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("FINISHED_AT >=", value, "finishedAt");
            return (Criteria) this;
        }

        public Criteria andFinishedAtLessThan(LocalDateTime value) {
            addCriterion("FINISHED_AT <", value, "finishedAt");
            return (Criteria) this;
        }

        public Criteria andFinishedAtLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("FINISHED_AT <=", value, "finishedAt");
            return (Criteria) this;
        }

        public Criteria andFinishedAtIn(List<LocalDateTime> values) {
            addCriterion("FINISHED_AT in", values, "finishedAt");
            return (Criteria) this;
        }

        public Criteria andFinishedAtNotIn(List<LocalDateTime> values) {
            addCriterion("FINISHED_AT not in", values, "finishedAt");
            return (Criteria) this;
        }

        public Criteria andFinishedAtBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("FINISHED_AT between", value1, value2, "finishedAt");
            return (Criteria) this;
        }

        public Criteria andFinishedAtNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("FINISHED_AT not between", value1, value2, "finishedAt");
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
     * This class corresponds to the database table ASYNC_PROCESS
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
     * This class corresponds to the database table ASYNC_PROCESS
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