package cherry.common.db.gen.mapper;

import cherry.common.db.gen.dto.ZipcdMaster;
import cherry.common.db.gen.dto.ZipcdMasterCriteria;
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

public interface ZipcdMasterMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    @SelectProvider(type=ZipcdMasterSqlProvider.class, method="countByExample")
    int countByExample(ZipcdMasterCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    @DeleteProvider(type=ZipcdMasterSqlProvider.class, method="deleteByExample")
    int deleteByExample(ZipcdMasterCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    @Delete({
        "delete from ZIPCD_MASTER",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    @Insert({
        "insert into ZIPCD_MASTER (CITY_CD, ZIPCD, ",
        "PREF, CITY, ADDR, ",
        "PREF_KANA, CITY_KANA, ",
        "ADDR_KANA, UPDATED_AT, ",
        "CREATED_AT, LOCK_VERSION, ",
        "DELETED_FLG)",
        "values (#{cityCd,jdbcType=INTEGER}, #{zipcd,jdbcType=VARCHAR}, ",
        "#{pref,jdbcType=VARCHAR}, #{city,jdbcType=VARCHAR}, #{addr,jdbcType=VARCHAR}, ",
        "#{prefKana,jdbcType=VARCHAR}, #{cityKana,jdbcType=VARCHAR}, ",
        "#{addrKana,jdbcType=VARCHAR}, #{updatedAt,jdbcType=TIMESTAMP}, ",
        "#{createdAt,jdbcType=TIMESTAMP}, #{lockVersion,jdbcType=INTEGER}, ",
        "#{deletedFlg,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(ZipcdMaster record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    @InsertProvider(type=ZipcdMasterSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(ZipcdMaster record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    @SelectProvider(type=ZipcdMasterSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="CITY_CD", property="cityCd", jdbcType=JdbcType.INTEGER),
        @Result(column="ZIPCD", property="zipcd", jdbcType=JdbcType.VARCHAR),
        @Result(column="PREF", property="pref", jdbcType=JdbcType.VARCHAR),
        @Result(column="CITY", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="ADDR", property="addr", jdbcType=JdbcType.VARCHAR),
        @Result(column="PREF_KANA", property="prefKana", jdbcType=JdbcType.VARCHAR),
        @Result(column="CITY_KANA", property="cityKana", jdbcType=JdbcType.VARCHAR),
        @Result(column="ADDR_KANA", property="addrKana", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    List<ZipcdMaster> selectByExampleWithRowbounds(ZipcdMasterCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    @SelectProvider(type=ZipcdMasterSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="CITY_CD", property="cityCd", jdbcType=JdbcType.INTEGER),
        @Result(column="ZIPCD", property="zipcd", jdbcType=JdbcType.VARCHAR),
        @Result(column="PREF", property="pref", jdbcType=JdbcType.VARCHAR),
        @Result(column="CITY", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="ADDR", property="addr", jdbcType=JdbcType.VARCHAR),
        @Result(column="PREF_KANA", property="prefKana", jdbcType=JdbcType.VARCHAR),
        @Result(column="CITY_KANA", property="cityKana", jdbcType=JdbcType.VARCHAR),
        @Result(column="ADDR_KANA", property="addrKana", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    List<ZipcdMaster> selectByExample(ZipcdMasterCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "ID, CITY_CD, ZIPCD, PREF, CITY, ADDR, PREF_KANA, CITY_KANA, ADDR_KANA, UPDATED_AT, ",
        "CREATED_AT, LOCK_VERSION, DELETED_FLG",
        "from ZIPCD_MASTER",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="CITY_CD", property="cityCd", jdbcType=JdbcType.INTEGER),
        @Result(column="ZIPCD", property="zipcd", jdbcType=JdbcType.VARCHAR),
        @Result(column="PREF", property="pref", jdbcType=JdbcType.VARCHAR),
        @Result(column="CITY", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="ADDR", property="addr", jdbcType=JdbcType.VARCHAR),
        @Result(column="PREF_KANA", property="prefKana", jdbcType=JdbcType.VARCHAR),
        @Result(column="CITY_KANA", property="cityKana", jdbcType=JdbcType.VARCHAR),
        @Result(column="ADDR_KANA", property="addrKana", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    ZipcdMaster selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    @UpdateProvider(type=ZipcdMasterSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ZipcdMaster record, @Param("example") ZipcdMasterCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    @UpdateProvider(type=ZipcdMasterSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ZipcdMaster record, @Param("example") ZipcdMasterCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    @UpdateProvider(type=ZipcdMasterSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ZipcdMaster record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZIPCD_MASTER
     *
     * @mbggenerated
     */
    @Update({
        "update ZIPCD_MASTER",
        "set CITY_CD = #{cityCd,jdbcType=INTEGER},",
          "ZIPCD = #{zipcd,jdbcType=VARCHAR},",
          "PREF = #{pref,jdbcType=VARCHAR},",
          "CITY = #{city,jdbcType=VARCHAR},",
          "ADDR = #{addr,jdbcType=VARCHAR},",
          "PREF_KANA = #{prefKana,jdbcType=VARCHAR},",
          "CITY_KANA = #{cityKana,jdbcType=VARCHAR},",
          "ADDR_KANA = #{addrKana,jdbcType=VARCHAR},",
          "UPDATED_AT = #{updatedAt,jdbcType=TIMESTAMP},",
          "CREATED_AT = #{createdAt,jdbcType=TIMESTAMP},",
          "LOCK_VERSION = #{lockVersion,jdbcType=INTEGER},",
          "DELETED_FLG = #{deletedFlg,jdbcType=INTEGER}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ZipcdMaster record);
}