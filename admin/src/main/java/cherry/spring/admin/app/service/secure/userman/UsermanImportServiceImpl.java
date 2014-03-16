package cherry.spring.admin.app.service.secure.userman;

import java.io.IOException;
import java.io.Reader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cherry.spring.common.lib.db.CsvDataProvider;
import cherry.spring.common.lib.db.DataLoader;
import cherry.spring.common.lib.db.DataLoader.Result;

@Component
public class UsermanImportServiceImpl implements UsermanImportService {

	@Autowired
	@Qualifier("usersLoader")
	DataLoader usersLoader;

	@Transactional
	@Override
	public Result importUsers(Reader reader) {
		try {
			CsvDataProvider provider = new CsvDataProvider(reader, true);
			return usersLoader.load(provider);
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

}
