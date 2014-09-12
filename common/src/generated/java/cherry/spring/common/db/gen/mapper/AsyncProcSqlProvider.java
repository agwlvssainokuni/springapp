package cherry.spring.common.db.gen.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT_DISTINCT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import cherry.spring.common.db.gen.dto.AsyncProc;
import cherry.spring.common.db.gen.dto.AsyncProcCriteria.Criteria;
import cherry.spring.common.db.gen.dto.AsyncProcCriteria.Criterion;
import cherry.spring.common.db.gen.dto.AsyncProcCriteria;
import java.util.List;
import java.util.Map;

public class AsyncProcSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROC
     *
     * @mbggenerated
     */
    public String countByExample(AsyncProcCriteria example) {
        BEGIN();
        SELECT("count(*)");
        FROM("ASYNC_PROC");
        applyWhere(example, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROC
     *
     * @mbggenerated
     */
    public String deleteByExample(AsyncProcCriteria example) {
        BEGIN();
        DELETE_FROM("ASYNC_PROC");
        applyWhere(example, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROC
     *
     * @mbggenerated
     */
    public String insertSelective(AsyncProc record) {
        BEGIN();
        INSERT_INTO("ASYNC_PROC");
        
        if (record.getLauncherId() != null) {
            VALUES("LAUNCHER_ID", "#{launcherId,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            VALUES("NAME", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            VALUES("STATUS", "#{status,jdbcType=VARCHAR}");
        }
        
        if (record.getRegisteredAt() != null) {
            VALUES("REGISTERED_AT", "#{registeredAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getInvokedAt() != null) {
            VALUES("INVOKED_AT", "#{invokedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStartedAt() != null) {
            VALUES("STARTED_AT", "#{startedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFinishedAt() != null) {
            VALUES("FINISHED_AT", "#{finishedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getResult() != null) {
            VALUES("RESULT", "#{result,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdatedAt() != null) {
            VALUES("UPDATED_AT", "#{updatedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreatedAt() != null) {
            VALUES("CREATED_AT", "#{createdAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLockVersion() != null) {
            VALUES("LOCK_VERSION", "#{lockVersion,jdbcType=INTEGER}");
        }
        
        if (record.getDeletedFlg() != null) {
            VALUES("DELETED_FLG", "#{deletedFlg,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROC
     *
     * @mbggenerated
     */
    public String selectByExample(AsyncProcCriteria example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("ID");
        } else {
            SELECT("ID");
        }
        SELECT("LAUNCHER_ID");
        SELECT("NAME");
        SELECT("STATUS");
        SELECT("REGISTERED_AT");
        SELECT("INVOKED_AT");
        SELECT("STARTED_AT");
        SELECT("FINISHED_AT");
        SELECT("RESULT");
        SELECT("UPDATED_AT");
        SELECT("CREATED_AT");
        SELECT("LOCK_VERSION");
        SELECT("DELETED_FLG");
        FROM("ASYNC_PROC");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROC
     *
     * @mbggenerated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        AsyncProc record = (AsyncProc) parameter.get("record");
        AsyncProcCriteria example = (AsyncProcCriteria) parameter.get("example");
        
        BEGIN();
        UPDATE("ASYNC_PROC");
        
        if (record.getId() != null) {
            SET("ID = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getLauncherId() != null) {
            SET("LAUNCHER_ID = #{record.launcherId,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            SET("NAME = #{record.name,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            SET("STATUS = #{record.status,jdbcType=VARCHAR}");
        }
        
        if (record.getRegisteredAt() != null) {
            SET("REGISTERED_AT = #{record.registeredAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getInvokedAt() != null) {
            SET("INVOKED_AT = #{record.invokedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStartedAt() != null) {
            SET("STARTED_AT = #{record.startedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFinishedAt() != null) {
            SET("FINISHED_AT = #{record.finishedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getResult() != null) {
            SET("RESULT = #{record.result,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdatedAt() != null) {
            SET("UPDATED_AT = #{record.updatedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreatedAt() != null) {
            SET("CREATED_AT = #{record.createdAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLockVersion() != null) {
            SET("LOCK_VERSION = #{record.lockVersion,jdbcType=INTEGER}");
        }
        
        if (record.getDeletedFlg() != null) {
            SET("DELETED_FLG = #{record.deletedFlg,jdbcType=INTEGER}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROC
     *
     * @mbggenerated
     */
    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("ASYNC_PROC");
        
        SET("ID = #{record.id,jdbcType=INTEGER}");
        SET("LAUNCHER_ID = #{record.launcherId,jdbcType=VARCHAR}");
        SET("NAME = #{record.name,jdbcType=VARCHAR}");
        SET("STATUS = #{record.status,jdbcType=VARCHAR}");
        SET("REGISTERED_AT = #{record.registeredAt,jdbcType=TIMESTAMP}");
        SET("INVOKED_AT = #{record.invokedAt,jdbcType=TIMESTAMP}");
        SET("STARTED_AT = #{record.startedAt,jdbcType=TIMESTAMP}");
        SET("FINISHED_AT = #{record.finishedAt,jdbcType=TIMESTAMP}");
        SET("RESULT = #{record.result,jdbcType=VARCHAR}");
        SET("UPDATED_AT = #{record.updatedAt,jdbcType=TIMESTAMP}");
        SET("CREATED_AT = #{record.createdAt,jdbcType=TIMESTAMP}");
        SET("LOCK_VERSION = #{record.lockVersion,jdbcType=INTEGER}");
        SET("DELETED_FLG = #{record.deletedFlg,jdbcType=INTEGER}");
        
        AsyncProcCriteria example = (AsyncProcCriteria) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROC
     *
     * @mbggenerated
     */
    public String updateByPrimaryKeySelective(AsyncProc record) {
        BEGIN();
        UPDATE("ASYNC_PROC");
        
        if (record.getLauncherId() != null) {
            SET("LAUNCHER_ID = #{launcherId,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            SET("NAME = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            SET("STATUS = #{status,jdbcType=VARCHAR}");
        }
        
        if (record.getRegisteredAt() != null) {
            SET("REGISTERED_AT = #{registeredAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getInvokedAt() != null) {
            SET("INVOKED_AT = #{invokedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStartedAt() != null) {
            SET("STARTED_AT = #{startedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFinishedAt() != null) {
            SET("FINISHED_AT = #{finishedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getResult() != null) {
            SET("RESULT = #{result,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdatedAt() != null) {
            SET("UPDATED_AT = #{updatedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreatedAt() != null) {
            SET("CREATED_AT = #{createdAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLockVersion() != null) {
            SET("LOCK_VERSION = #{lockVersion,jdbcType=INTEGER}");
        }
        
        if (record.getDeletedFlg() != null) {
            SET("DELETED_FLG = #{deletedFlg,jdbcType=INTEGER}");
        }
        
        WHERE("ID = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROC
     *
     * @mbggenerated
     */
    protected void applyWhere(AsyncProcCriteria example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }
}