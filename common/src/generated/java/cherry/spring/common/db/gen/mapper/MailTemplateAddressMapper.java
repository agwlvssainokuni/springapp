package cherry.spring.common.db.gen.mapper;

import cherry.spring.common.db.gen.dto.MailTemplateAddress;
import cherry.spring.common.db.gen.dto.MailTemplateAddressCriteria;
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

public interface MailTemplateAddressMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @SelectProvider(type=MailTemplateAddressSqlProvider.class, method="countByExample")
    int countByExample(MailTemplateAddressCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @DeleteProvider(type=MailTemplateAddressSqlProvider.class, method="deleteByExample")
    int deleteByExample(MailTemplateAddressCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @Delete({
        "delete from MAIL_TEMPLATE_ADDRESS",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @Insert({
        "insert into MAIL_TEMPLATE_ADDRESS (MAIL_TEMPLATE_ID, MAIL_ADDR, ",
        "RCPT_TYPE, UPDATED_AT, ",
        "CREATED_AT, LOCK_VERSION, ",
        "DELETED_FLG)",
        "values (#{mailTemplateId,jdbcType=INTEGER}, #{mailAddr,jdbcType=VARCHAR}, ",
        "#{rcptType,jdbcType=VARCHAR}, #{updatedAt,jdbcType=TIMESTAMP}, ",
        "#{createdAt,jdbcType=TIMESTAMP}, #{lockVersion,jdbcType=INTEGER}, ",
        "#{deletedFlg,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(MailTemplateAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @InsertProvider(type=MailTemplateAddressSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(MailTemplateAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @SelectProvider(type=MailTemplateAddressSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="MAIL_TEMPLATE_ID", property="mailTemplateId", jdbcType=JdbcType.INTEGER),
        @Result(column="MAIL_ADDR", property="mailAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="RCPT_TYPE", property="rcptType", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    List<MailTemplateAddress> selectByExampleWithRowbounds(MailTemplateAddressCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @SelectProvider(type=MailTemplateAddressSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="MAIL_TEMPLATE_ID", property="mailTemplateId", jdbcType=JdbcType.INTEGER),
        @Result(column="MAIL_ADDR", property="mailAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="RCPT_TYPE", property="rcptType", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    List<MailTemplateAddress> selectByExample(MailTemplateAddressCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "ID, MAIL_TEMPLATE_ID, MAIL_ADDR, RCPT_TYPE, UPDATED_AT, CREATED_AT, LOCK_VERSION, ",
        "DELETED_FLG",
        "from MAIL_TEMPLATE_ADDRESS",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="MAIL_TEMPLATE_ID", property="mailTemplateId", jdbcType=JdbcType.INTEGER),
        @Result(column="MAIL_ADDR", property="mailAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="RCPT_TYPE", property="rcptType", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    MailTemplateAddress selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @UpdateProvider(type=MailTemplateAddressSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MailTemplateAddress record, @Param("example") MailTemplateAddressCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @UpdateProvider(type=MailTemplateAddressSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") MailTemplateAddress record, @Param("example") MailTemplateAddressCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @UpdateProvider(type=MailTemplateAddressSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MailTemplateAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESS
     *
     * @mbggenerated
     */
    @Update({
        "update MAIL_TEMPLATE_ADDRESS",
        "set MAIL_TEMPLATE_ID = #{mailTemplateId,jdbcType=INTEGER},",
          "MAIL_ADDR = #{mailAddr,jdbcType=VARCHAR},",
          "RCPT_TYPE = #{rcptType,jdbcType=VARCHAR},",
          "UPDATED_AT = #{updatedAt,jdbcType=TIMESTAMP},",
          "CREATED_AT = #{createdAt,jdbcType=TIMESTAMP},",
          "LOCK_VERSION = #{lockVersion,jdbcType=INTEGER},",
          "DELETED_FLG = #{deletedFlg,jdbcType=INTEGER}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(MailTemplateAddress record);
}