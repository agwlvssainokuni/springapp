package cherry.spring.common.db.gen.mapper;

import cherry.spring.common.db.gen.dto.MailTemplateAddresses;
import java.util.List;

public interface MailTemplateAddressesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESSES
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESSES
     *
     * @mbggenerated
     */
    int insert(MailTemplateAddresses record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESSES
     *
     * @mbggenerated
     */
    MailTemplateAddresses selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESSES
     *
     * @mbggenerated
     */
    List<MailTemplateAddresses> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_ADDRESSES
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(MailTemplateAddresses record);
}