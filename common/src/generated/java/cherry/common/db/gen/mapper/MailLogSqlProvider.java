package cherry.common.db.gen.mapper;

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

import cherry.common.db.gen.dto.MailLog;
import cherry.common.db.gen.dto.MailLogCriteria.Criteria;
import cherry.common.db.gen.dto.MailLogCriteria.Criterion;
import cherry.common.db.gen.dto.MailLogCriteria;
import java.util.List;
import java.util.Map;

public class MailLogSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_LOG
     *
     * @mbggenerated
     */
    public String countByExample(MailLogCriteria example) {
        BEGIN();
        SELECT("count(*)");
        FROM("MAIL_LOG");
        applyWhere(example, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_LOG
     *
     * @mbggenerated
     */
    public String deleteByExample(MailLogCriteria example) {
        BEGIN();
        DELETE_FROM("MAIL_LOG");
        applyWhere(example, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_LOG
     *
     * @mbggenerated
     */
    public String insertSelective(MailLog record) {
        BEGIN();
        INSERT_INTO("MAIL_LOG");
        
        if (record.getLaunchedBy() != null) {
            VALUES("LAUNCHED_BY", "#{launchedBy,jdbcType=VARCHAR}");
        }
        
        if (record.getLaunchedAt() != null) {
            VALUES("LAUNCHED_AT", "#{launchedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getMailStatus() != null) {
            VALUES("MAIL_STATUS", "#{mailStatus,jdbcType=INTEGER}");
        }
        
        if (record.getMessageName() != null) {
            VALUES("MESSAGE_NAME", "#{messageName,jdbcType=VARCHAR}");
        }
        
        if (record.getScheduledAt() != null) {
            VALUES("SCHEDULED_AT", "#{scheduledAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getSentAt() != null) {
            VALUES("SENT_AT", "#{sentAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFromAddr() != null) {
            VALUES("FROM_ADDR", "#{fromAddr,jdbcType=VARCHAR}");
        }
        
        if (record.getReplyToAddr() != null) {
            VALUES("REPLY_TO_ADDR", "#{replyToAddr,jdbcType=VARCHAR}");
        }
        
        if (record.getSubject() != null) {
            VALUES("SUBJECT", "#{subject,jdbcType=VARCHAR}");
        }
        
        if (record.getBody() != null) {
            VALUES("BODY", "#{body,jdbcType=VARCHAR}");
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
     * This method corresponds to the database table MAIL_LOG
     *
     * @mbggenerated
     */
    public String selectByExample(MailLogCriteria example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("ID");
        } else {
            SELECT("ID");
        }
        SELECT("LAUNCHED_BY");
        SELECT("LAUNCHED_AT");
        SELECT("MAIL_STATUS");
        SELECT("MESSAGE_NAME");
        SELECT("SCHEDULED_AT");
        SELECT("SENT_AT");
        SELECT("FROM_ADDR");
        SELECT("REPLY_TO_ADDR");
        SELECT("SUBJECT");
        SELECT("BODY");
        SELECT("UPDATED_AT");
        SELECT("CREATED_AT");
        SELECT("LOCK_VERSION");
        SELECT("DELETED_FLG");
        FROM("MAIL_LOG");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_LOG
     *
     * @mbggenerated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        MailLog record = (MailLog) parameter.get("record");
        MailLogCriteria example = (MailLogCriteria) parameter.get("example");
        
        BEGIN();
        UPDATE("MAIL_LOG");
        
        if (record.getId() != null) {
            SET("ID = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getLaunchedBy() != null) {
            SET("LAUNCHED_BY = #{record.launchedBy,jdbcType=VARCHAR}");
        }
        
        if (record.getLaunchedAt() != null) {
            SET("LAUNCHED_AT = #{record.launchedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getMailStatus() != null) {
            SET("MAIL_STATUS = #{record.mailStatus,jdbcType=INTEGER}");
        }
        
        if (record.getMessageName() != null) {
            SET("MESSAGE_NAME = #{record.messageName,jdbcType=VARCHAR}");
        }
        
        if (record.getScheduledAt() != null) {
            SET("SCHEDULED_AT = #{record.scheduledAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getSentAt() != null) {
            SET("SENT_AT = #{record.sentAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFromAddr() != null) {
            SET("FROM_ADDR = #{record.fromAddr,jdbcType=VARCHAR}");
        }
        
        if (record.getReplyToAddr() != null) {
            SET("REPLY_TO_ADDR = #{record.replyToAddr,jdbcType=VARCHAR}");
        }
        
        if (record.getSubject() != null) {
            SET("SUBJECT = #{record.subject,jdbcType=VARCHAR}");
        }
        
        if (record.getBody() != null) {
            SET("BODY = #{record.body,jdbcType=VARCHAR}");
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
     * This method corresponds to the database table MAIL_LOG
     *
     * @mbggenerated
     */
    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("MAIL_LOG");
        
        SET("ID = #{record.id,jdbcType=BIGINT}");
        SET("LAUNCHED_BY = #{record.launchedBy,jdbcType=VARCHAR}");
        SET("LAUNCHED_AT = #{record.launchedAt,jdbcType=TIMESTAMP}");
        SET("MAIL_STATUS = #{record.mailStatus,jdbcType=INTEGER}");
        SET("MESSAGE_NAME = #{record.messageName,jdbcType=VARCHAR}");
        SET("SCHEDULED_AT = #{record.scheduledAt,jdbcType=TIMESTAMP}");
        SET("SENT_AT = #{record.sentAt,jdbcType=TIMESTAMP}");
        SET("FROM_ADDR = #{record.fromAddr,jdbcType=VARCHAR}");
        SET("REPLY_TO_ADDR = #{record.replyToAddr,jdbcType=VARCHAR}");
        SET("SUBJECT = #{record.subject,jdbcType=VARCHAR}");
        SET("BODY = #{record.body,jdbcType=VARCHAR}");
        SET("UPDATED_AT = #{record.updatedAt,jdbcType=TIMESTAMP}");
        SET("CREATED_AT = #{record.createdAt,jdbcType=TIMESTAMP}");
        SET("LOCK_VERSION = #{record.lockVersion,jdbcType=INTEGER}");
        SET("DELETED_FLG = #{record.deletedFlg,jdbcType=INTEGER}");
        
        MailLogCriteria example = (MailLogCriteria) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_LOG
     *
     * @mbggenerated
     */
    public String updateByPrimaryKeySelective(MailLog record) {
        BEGIN();
        UPDATE("MAIL_LOG");
        
        if (record.getLaunchedBy() != null) {
            SET("LAUNCHED_BY = #{launchedBy,jdbcType=VARCHAR}");
        }
        
        if (record.getLaunchedAt() != null) {
            SET("LAUNCHED_AT = #{launchedAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getMailStatus() != null) {
            SET("MAIL_STATUS = #{mailStatus,jdbcType=INTEGER}");
        }
        
        if (record.getMessageName() != null) {
            SET("MESSAGE_NAME = #{messageName,jdbcType=VARCHAR}");
        }
        
        if (record.getScheduledAt() != null) {
            SET("SCHEDULED_AT = #{scheduledAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getSentAt() != null) {
            SET("SENT_AT = #{sentAt,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFromAddr() != null) {
            SET("FROM_ADDR = #{fromAddr,jdbcType=VARCHAR}");
        }
        
        if (record.getReplyToAddr() != null) {
            SET("REPLY_TO_ADDR = #{replyToAddr,jdbcType=VARCHAR}");
        }
        
        if (record.getSubject() != null) {
            SET("SUBJECT = #{subject,jdbcType=VARCHAR}");
        }
        
        if (record.getBody() != null) {
            SET("BODY = #{body,jdbcType=VARCHAR}");
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
        
        WHERE("ID = #{id,jdbcType=BIGINT}");
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_LOG
     *
     * @mbggenerated
     */
    protected void applyWhere(MailLogCriteria example, boolean includeExamplePhrase) {
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