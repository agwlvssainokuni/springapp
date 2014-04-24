package cherry.spring.common.db.gen.mapper;

import cherry.spring.common.db.gen.dto.AsyncProcs;
import java.util.List;

public interface AsyncProcsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCS
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCS
     *
     * @mbggenerated
     */
    int insert(AsyncProcs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCS
     *
     * @mbggenerated
     */
    AsyncProcs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCS
     *
     * @mbggenerated
     */
    List<AsyncProcs> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASYNC_PROCS
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AsyncProcs record);
}