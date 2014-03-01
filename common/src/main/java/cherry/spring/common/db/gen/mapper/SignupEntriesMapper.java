package cherry.spring.common.db.gen.mapper;

import cherry.spring.common.db.gen.dto.SignupEntries;
import java.util.List;

public interface SignupEntriesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SignupEntries record);

    SignupEntries selectByPrimaryKey(Integer id);

    List<SignupEntries> selectAll();

    int updateByPrimaryKey(SignupEntries record);
}