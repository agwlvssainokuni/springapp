package cherry.common.db.gen.mapper;

import cherry.common.db.gen.dto.DayoffMaster;
import cherry.common.db.gen.dto.DayoffMasterCriteria;
import cherry.common.db.gen.dto.DayoffMasterKey;
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

public interface DayoffMasterMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DAYOFF_MASTER
     *
     * @mbggenerated
     */
    @SelectProvider(type=DayoffMasterSqlProvider.class, method="countByExample")
    int countByExample(DayoffMasterCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DAYOFF_MASTER
     *
     * @mbggenerated
     */
    @DeleteProvider(type=DayoffMasterSqlProvider.class, method="deleteByExample")
    int deleteByExample(DayoffMasterCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DAYOFF_MASTER
     *
     * @mbggenerated
     */
    @Delete({
        "delete from DAYOFF_MASTER",
        "where NAME = #{name,jdbcType=VARCHAR}",
          "and DT = #{dt,jdbcType=DATE}"
    })
    int deleteByPrimaryKey(DayoffMasterKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DAYOFF_MASTER
     *
     * @mbggenerated
     */
    @Insert({
        "insert into DAYOFF_MASTER (NAME, DT, TYPE, ",
        "UPDATED_AT, CREATED_AT, ",
        "LOCK_VERSION, DELETED_FLG)",
        "values (#{name,jdbcType=VARCHAR}, #{dt,jdbcType=DATE}, #{type,jdbcType=VARCHAR}, ",
        "#{updatedAt,jdbcType=TIMESTAMP}, #{createdAt,jdbcType=TIMESTAMP}, ",
        "#{lockVersion,jdbcType=INTEGER}, #{deletedFlg,jdbcType=INTEGER})"
    })
    int insert(DayoffMaster record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DAYOFF_MASTER
     *
     * @mbggenerated
     */
    @InsertProvider(type=DayoffMasterSqlProvider.class, method="insertSelective")
    int insertSelective(DayoffMaster record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DAYOFF_MASTER
     *
     * @mbggenerated
     */
    @SelectProvider(type=DayoffMasterSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DT", property="dt", jdbcType=JdbcType.DATE, id=true),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    List<DayoffMaster> selectByExampleWithRowbounds(DayoffMasterCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DAYOFF_MASTER
     *
     * @mbggenerated
     */
    @SelectProvider(type=DayoffMasterSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DT", property="dt", jdbcType=JdbcType.DATE, id=true),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    List<DayoffMaster> selectByExample(DayoffMasterCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DAYOFF_MASTER
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "NAME, DT, TYPE, UPDATED_AT, CREATED_AT, LOCK_VERSION, DELETED_FLG",
        "from DAYOFF_MASTER",
        "where NAME = #{name,jdbcType=VARCHAR}",
          "and DT = #{dt,jdbcType=DATE}"
    })
    @Results({
        @Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DT", property="dt", jdbcType=JdbcType.DATE, id=true),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATED_AT", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATED_AT", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOCK_VERSION", property="lockVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETED_FLG", property="deletedFlg", jdbcType=JdbcType.INTEGER)
    })
    DayoffMaster selectByPrimaryKey(DayoffMasterKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DAYOFF_MASTER
     *
     * @mbggenerated
     */
    @UpdateProvider(type=DayoffMasterSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") DayoffMaster record, @Param("example") DayoffMasterCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DAYOFF_MASTER
     *
     * @mbggenerated
     */
    @UpdateProvider(type=DayoffMasterSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") DayoffMaster record, @Param("example") DayoffMasterCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DAYOFF_MASTER
     *
     * @mbggenerated
     */
    @UpdateProvider(type=DayoffMasterSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(DayoffMaster record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DAYOFF_MASTER
     *
     * @mbggenerated
     */
    @Update({
        "update DAYOFF_MASTER",
        "set TYPE = #{type,jdbcType=VARCHAR},",
          "UPDATED_AT = #{updatedAt,jdbcType=TIMESTAMP},",
          "CREATED_AT = #{createdAt,jdbcType=TIMESTAMP},",
          "LOCK_VERSION = #{lockVersion,jdbcType=INTEGER},",
          "DELETED_FLG = #{deletedFlg,jdbcType=INTEGER}",
        "where NAME = #{name,jdbcType=VARCHAR}",
          "and DT = #{dt,jdbcType=DATE}"
    })
    int updateByPrimaryKey(DayoffMaster record);
}