package cherry.foundation.db.gen.mapper;

import cherry.foundation.db.gen.dto.VerifyFlag;
import cherry.foundation.db.gen.dto.VerifyFlagCriteria;
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

public interface VerifyFlagMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_FLAG
     *
     * @mbggenerated
     */
    @SelectProvider(type=VerifyFlagSqlProvider.class, method="countByExample")
    int countByExample(VerifyFlagCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_FLAG
     *
     * @mbggenerated
     */
    @DeleteProvider(type=VerifyFlagSqlProvider.class, method="deleteByExample")
    int deleteByExample(VerifyFlagCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_FLAG
     *
     * @mbggenerated
     */
    @Delete({
        "delete from VERIFY_FLAG",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_FLAG
     *
     * @mbggenerated
     */
    @Insert({
        "insert into VERIFY_FLAG (FLAG_CODE, DELETED_FLG)",
        "values (#{flagCode,jdbcType=INTEGER}, #{deletedFlg,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(VerifyFlag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_FLAG
     *
     * @mbggenerated
     */
    @InsertProvider(type=VerifyFlagSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(VerifyFlag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_FLAG
     *
     * @mbggenerated
     */
    @SelectProvider(type=VerifyFlagSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="FLAG_CODE", property="flagCode", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    List<VerifyFlag> selectByExampleWithRowbounds(VerifyFlagCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_FLAG
     *
     * @mbggenerated
     */
    @SelectProvider(type=VerifyFlagSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="FLAG_CODE", property="flagCode", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    List<VerifyFlag> selectByExample(VerifyFlagCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_FLAG
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "ID, FLAG_CODE, DELETED_FLG",
        "from VERIFY_FLAG",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="FLAG_CODE", property="flagCode", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    VerifyFlag selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_FLAG
     *
     * @mbggenerated
     */
    @UpdateProvider(type=VerifyFlagSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") VerifyFlag record, @Param("example") VerifyFlagCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_FLAG
     *
     * @mbggenerated
     */
    @UpdateProvider(type=VerifyFlagSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") VerifyFlag record, @Param("example") VerifyFlagCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_FLAG
     *
     * @mbggenerated
     */
    @UpdateProvider(type=VerifyFlagSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(VerifyFlag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VERIFY_FLAG
     *
     * @mbggenerated
     */
    @Update({
        "update VERIFY_FLAG",
        "set FLAG_CODE = #{flagCode,jdbcType=INTEGER},",
          "DELETED_FLG = #{deletedFlg,jdbcType=INTEGER}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(VerifyFlag record);
}