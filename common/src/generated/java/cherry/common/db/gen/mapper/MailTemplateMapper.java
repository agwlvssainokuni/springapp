package cherry.common.db.gen.mapper;

import cherry.common.db.gen.dto.MailTemplate;
import cherry.common.db.gen.dto.MailTemplateCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

public interface MailTemplateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE
     *
     * @mbggenerated
     */
    @SelectProvider(type=MailTemplateSqlProvider.class, method="countByExample")
    int countByExample(MailTemplateCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE
     *
     * @mbggenerated
     */
    @DeleteProvider(type=MailTemplateSqlProvider.class, method="deleteByExample")
    int deleteByExample(MailTemplateCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE
     *
     * @mbggenerated
     */
    @Delete({
        "delete from MAIL_TEMPLATE",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE
     *
     * @mbggenerated
     */
    @Insert({
        "insert into MAIL_TEMPLATE (TEMPLATE_NAME, FROM_ADDR, ",
        "REPLY_TO_ADDR, SUBJECT, ",
        "BODY, UPDATED_AT, ",
        "CREATED_AT, LOCK_VERSION, ",
        "DELETED_FLG)",
        "values (#{templateName,jdbcType=VARCHAR}, #{fromAddr,jdbcType=VARCHAR}, ",
        "#{replyToAddr,jdbcType=VARCHAR}, #{subject,jdbcType=VARCHAR}, ",
        "#{body,jdbcType=VARCHAR}, #{updatedAt,jdbcType=TIMESTAMP}, ",
        "#{createdAt,jdbcType=TIMESTAMP}, #{lockVersion,jdbcType=INTEGER}, ",
        "#{deletedFlg,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(MailTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE
     *
     * @mbggenerated
     */
    @InsertProvider(type=MailTemplateSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(MailTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE
     *
     * @mbggenerated
     */
    @SelectProvider(type=MailTemplateSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="TEMPLATE_NAME", property="templateName", jdbcType=JdbcType.VARCHAR),
        @Result(column="FROM_ADDR", property="fromAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="REPLY_TO_ADDR", property="replyToAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="SUBJECT", property="subject", jdbcType=JdbcType.VARCHAR),
        @Result(column="BODY", property="body", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    List<MailTemplate> selectByExampleWithRowbounds(MailTemplateCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE
     *
     * @mbggenerated
     */
    @SelectProvider(type=MailTemplateSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="TEMPLATE_NAME", property="templateName", jdbcType=JdbcType.VARCHAR),
        @Result(column="FROM_ADDR", property="fromAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="REPLY_TO_ADDR", property="replyToAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="SUBJECT", property="subject", jdbcType=JdbcType.VARCHAR),
        @Result(column="BODY", property="body", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    List<MailTemplate> selectByExample(MailTemplateCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "ID, TEMPLATE_NAME, FROM_ADDR, REPLY_TO_ADDR, SUBJECT, BODY, UPDATED_AT, CREATED_AT, ",
        "LOCK_VERSION, DELETED_FLG",
        "from MAIL_TEMPLATE",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="TEMPLATE_NAME", property="templateName", jdbcType=JdbcType.VARCHAR),
        @Result(column="FROM_ADDR", property="fromAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="REPLY_TO_ADDR", property="replyToAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="SUBJECT", property="subject", jdbcType=JdbcType.VARCHAR),
        @Result(column="BODY", property="body", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    MailTemplate selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE
     *
     * @mbggenerated
     */
    @UpdateProvider(type=MailTemplateSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MailTemplate record, @Param("example") MailTemplateCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE
     *
     * @mbggenerated
     */
    @UpdateProvider(type=MailTemplateSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") MailTemplate record, @Param("example") MailTemplateCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE
     *
     * @mbggenerated
     */
    @UpdateProvider(type=MailTemplateSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MailTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE
     *
     * @mbggenerated
     */
    @Update({
        "update MAIL_TEMPLATE",
        "set TEMPLATE_NAME = #{templateName,jdbcType=VARCHAR},",
          "FROM_ADDR = #{fromAddr,jdbcType=VARCHAR},",
          "REPLY_TO_ADDR = #{replyToAddr,jdbcType=VARCHAR},",
          "SUBJECT = #{subject,jdbcType=VARCHAR},",
          "BODY = #{body,jdbcType=VARCHAR},",
          "UPDATED_AT = #{updatedAt,jdbcType=TIMESTAMP},",
          "CREATED_AT = #{createdAt,jdbcType=TIMESTAMP},",
          "LOCK_VERSION = #{lockVersion,jdbcType=INTEGER},",
          "DELETED_FLG = #{deletedFlg,jdbcType=INTEGER}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(MailTemplate record);
}