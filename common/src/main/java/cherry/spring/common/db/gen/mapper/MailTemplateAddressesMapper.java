package cherry.spring.common.db.gen.mapper;

import cherry.spring.common.db.gen.dto.MailTemplateAddresses;
import java.util.List;

public interface MailTemplateAddressesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MailTemplateAddresses record);

    MailTemplateAddresses selectByPrimaryKey(Integer id);

    List<MailTemplateAddresses> selectAll();

    int updateByPrimaryKey(MailTemplateAddresses record);
}