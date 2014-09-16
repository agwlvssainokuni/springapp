package cherry.spring.common.lib.etl;

import java.io.IOException;
import java.util.Map;

public class RelabelConsumer extends DelegateConsumer {

	private final Map<String, String> labelMap;

	public RelabelConsumer(Consumer delegate, Map<String, String> labelMap) {
		super(delegate);
		this.labelMap = labelMap;
	}

	@Override
	protected Column[] prepareBegin(Column[] col) throws IOException {
		Column[] adjusted = new Column[col.length];
		for (int i = 0; i < col.length; i++) {
			adjusted[i] = new Column();
			adjusted[i].setType(col[i].getType());
			if (labelMap.containsKey(col[i].getLabel())) {
				adjusted[i].setLabel(labelMap.get(col[i].getLabel()));
			} else {
				adjusted[i].setLabel(col[i].getLabel());
			}
		}
		return adjusted;
	}

	@Override
	protected Object[] prepareConsume(Object[] record) throws IOException {
		return record;
	}

	@Override
	protected void prepareEnd() throws IOException {
		// NOTHING
	}

}
