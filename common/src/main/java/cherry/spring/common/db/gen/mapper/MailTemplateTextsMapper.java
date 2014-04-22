package cherry.spring.common.db.gen.mapper;

import cherry.spring.common.db.gen.dto.MailTemplateTexts;
import java.util.List;

public interface MailTemplateTextsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_TEXTS
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_TEXTS
     *
     * @mbggenerated
     */
    int insert(MailTemplateTexts record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_TEXTS
     *
     * @mbggenerated
     */
    MailTemplateTexts selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_TEXTS
     *
     * @mbggenerated
     */
    List<MailTemplateTexts> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MAIL_TEMPLATE_TEXTS
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(MailTemplateTexts record);
}