package cherry.spring.common.db.gen.mapper;

import cherry.spring.common.db.gen.dto.SignupRequest;
import java.util.List;

public interface SignupRequestMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SIGNUP_REQUEST
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SIGNUP_REQUEST
     *
     * @mbggenerated
     */
    int insert(SignupRequest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SIGNUP_REQUEST
     *
     * @mbggenerated
     */
    SignupRequest selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SIGNUP_REQUEST
     *
     * @mbggenerated
     */
    List<SignupRequest> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SIGNUP_REQUEST
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SignupRequest record);
}