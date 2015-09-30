package cherry.foundation.db.gen.mapper;

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

import cherry.foundation.db.gen.dto.VerifyConstraints;
import cherry.foundation.db.gen.dto.VerifyConstraintsCriteria.Criteria;
import cherry.foundation.db.gen.dto.VerifyConstraintsCriteria.Criterion;
import cherry.foundation.db.gen.dto.VerifyConstraintsCriteria;
import java.util.List;
import java.util.Map;

public class VerifyConstraintsSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_CONSTRAINTS
     *
     * @mbggenerated
     */
    public String countByExample(VerifyConstraintsCriteria example) {
        BEGIN();
        SELECT("count(*)");
        FROM("VERIFY_CONSTRAINTS");
        applyWhere(example, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_CONSTRAINTS
     *
     * @mbggenerated
     */
    public String deleteByExample(VerifyConstraintsCriteria example) {
        BEGIN();
        DELETE_FROM("VERIFY_CONSTRAINTS");
        applyWhere(example, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_CONSTRAINTS
     *
     * @mbggenerated
     */
    public String insertSelective(VerifyConstraints record) {
        BEGIN();
        INSERT_INTO("VERIFY_CONSTRAINTS");
        
        if (record.getParentId() != null) {
            VALUES("PARENT_ID", "#{parentId,jdbcType=BIGINT}");
        }
        
        if (record.getName() != null) {
            VALUES("NAME", "#{name,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_CONSTRAINTS
     *
     * @mbggenerated
     */
    public String selectByExample(VerifyConstraintsCriteria example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("ID");
        } else {
            SELECT("ID");
        }
        SELECT("PARENT_ID");
        SELECT("NAME");
        FROM("VERIFY_CONSTRAINTS");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_CONSTRAINTS
     *
     * @mbggenerated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        VerifyConstraints record = (VerifyConstraints) parameter.get("record");
        VerifyConstraintsCriteria example = (VerifyConstraintsCriteria) parameter.get("example");
        
        BEGIN();
        UPDATE("VERIFY_CONSTRAINTS");
        
        if (record.getId() != null) {
            SET("ID = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getParentId() != null) {
            SET("PARENT_ID = #{record.parentId,jdbcType=BIGINT}");
        }
        
        if (record.getName() != null) {
            SET("NAME = #{record.name,jdbcType=VARCHAR}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_CONSTRAINTS
     *
     * @mbggenerated
     */
    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("VERIFY_CONSTRAINTS");
        
        SET("ID = #{record.id,jdbcType=BIGINT}");
        SET("PARENT_ID = #{record.parentId,jdbcType=BIGINT}");
        SET("NAME = #{record.name,jdbcType=VARCHAR}");
        
        VerifyConstraintsCriteria example = (VerifyConstraintsCriteria) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_CONSTRAINTS
     *
     * @mbggenerated
     */
    public String updateByPrimaryKeySelective(VerifyConstraints record) {
        BEGIN();
        UPDATE("VERIFY_CONSTRAINTS");
        
        if (record.getParentId() != null) {
            SET("PARENT_ID = #{parentId,jdbcType=BIGINT}");
        }
        
        if (record.getName() != null) {
            SET("NAME = #{name,jdbcType=VARCHAR}");
        }
        
        WHERE("ID = #{id,jdbcType=BIGINT}");
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_CONSTRAINTS
     *
     * @mbggenerated
     */
    protected void applyWhere(VerifyConstraintsCriteria example, boolean includeExamplePhrase) {
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