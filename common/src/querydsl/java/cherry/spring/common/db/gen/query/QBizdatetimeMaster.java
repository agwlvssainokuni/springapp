package cherry.spring.common.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QBizdatetimeMaster is a Querydsl query type for QBizdatetimeMaster
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QBizdatetimeMaster extends com.mysema.query.sql.RelationalPathBase<QBizdatetimeMaster> {

    private static final long serialVersionUID = 1368434652;

    public static final QBizdatetimeMaster bizdatetimeMaster = new QBizdatetimeMaster("bizdatetime_master");

    public QBizdatetimeMaster(String variable) {
        super(QBizdatetimeMaster.class, forVariable(variable), "public", "bizdatetime_master");
        addMetadata();
    }

    public QBizdatetimeMaster(String variable, String schema, String table) {
        super(QBizdatetimeMaster.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QBizdatetimeMaster(Path<? extends QBizdatetimeMaster> path) {
        super(path.getType(), path.getMetadata(), "public", "bizdatetime_master");
        addMetadata();
    }

    public QBizdatetimeMaster(PathMetadata<?> metadata) {
        super(QBizdatetimeMaster.class, metadata, "public", "bizdatetime_master");
        addMetadata();
    }

    public void addMetadata() {
    }

}

