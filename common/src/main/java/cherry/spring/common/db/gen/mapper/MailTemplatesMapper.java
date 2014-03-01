package cherry.spring.common.db.gen.mapper;

import cherry.spring.common.db.gen.dto.MailTemplates;
import java.util.List;

public interface MailTemplatesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MailTemplates record);

    MailTemplates selectByPrimaryKey(Integer id);

    List<MailTemplates> selectAll();

    int updateByPrimaryKey(MailTemplates record);
}