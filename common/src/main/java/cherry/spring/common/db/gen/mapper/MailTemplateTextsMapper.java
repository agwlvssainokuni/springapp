package cherry.spring.common.db.gen.mapper;

import cherry.spring.common.db.gen.dto.MailTemplateTexts;
import java.util.List;

public interface MailTemplateTextsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MailTemplateTexts record);

    MailTemplateTexts selectByPrimaryKey(Integer id);

    List<MailTemplateTexts> selectAll();

    int updateByPrimaryKey(MailTemplateTexts record);
}