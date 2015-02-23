package cherry.spring.common.db.gen.mapper;

import cherry.spring.common.db.gen.dto.Digit;
import cherry.spring.common.db.gen.dto.DigitCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

public interface DigitMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DIGIT
     *
     * @mbggenerated
     */
    @SelectProvider(type=DigitSqlProvider.class, method="countByExample")
    int countByExample(DigitCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DIGIT
     *
     * @mbggenerated
     */
    @DeleteProvider(type=DigitSqlProvider.class, method="deleteByExample")
    int deleteByExample(DigitCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DIGIT
     *
     * @mbggenerated
     */
    @Delete({
        "delete from DIGIT",
        "where D = #{d,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer d);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DIGIT
     *
     * @mbggenerated
     */
    @Insert({
        "insert into DIGIT (D, UPDATED_AT, ",
        "CREATED_AT, LOCK_VERSION, ",
        "DELETED_FLG)",
        "values (#{d,jdbcType=INTEGER}, #{updatedAt,jdbcType=TIMESTAMP}, ",
        "#{createdAt,jdbcType=TIMESTAMP}, #{lockVersion,jdbcType=INTEGER}, ",
        "#{deletedFlg,jdbcType=INTEGER})"
    })
    int insert(Digit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DIGIT
     *
     * @mbggenerated
     */
    @InsertProvider(type=DigitSqlProvider.class, method="insertSelective")
    int insertSelective(Digit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DIGIT
     *
     * @mbggenerated
     */
    @SelectProvider(type=DigitSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="D", property="d", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    List<Digit> selectByExampleWithRowbounds(DigitCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DIGIT
     *
     * @mbggenerated
     */
    @SelectProvider(type=DigitSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="D", property="d", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    List<Digit> selectByExample(DigitCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DIGIT
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "D, UPDATED_AT, CREATED_AT, LOCK_VERSION, DELETED_FLG",
        "from DIGIT",
        "where D = #{d,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="D", property="d", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    Digit selectByPrimaryKey(Integer d);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DIGIT
     *
     * @mbggenerated
     */
    @UpdateProvider(type=DigitSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Digit record, @Param("example") DigitCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DIGIT
     *
     * @mbggenerated
     */
    @UpdateProvider(type=DigitSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Digit record, @Param("example") DigitCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DIGIT
     *
     * @mbggenerated
     */
    @UpdateProvider(type=DigitSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Digit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DIGIT
     *
     * @mbggenerated
     */
    @Update({
        "update DIGIT",
        "set UPDATED_AT = #{updatedAt,jdbcType=TIMESTAMP},",
          "CREATED_AT = #{createdAt,jdbcType=TIMESTAMP},",
          "LOCK_VERSION = #{lockVersion,jdbcType=INTEGER},",
          "DELETED_FLG = #{deletedFlg,jdbcType=INTEGER}",
        "where D = #{d,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Digit record);
}