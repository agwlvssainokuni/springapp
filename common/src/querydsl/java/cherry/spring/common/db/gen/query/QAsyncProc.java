package cherry.spring.common.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QAsyncProc is a Querydsl query type for QAsyncProc
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QAsyncProc extends com.mysema.query.sql.RelationalPathBase<QAsyncProc> {

    private static final long serialVersionUID = 1169262142;

    public static final QAsyncProc asyncProc = new QAsyncProc("async_proc");

    public QAsyncProc(String variable) {
        super(QAsyncProc.class, forVariable(variable), "public", "async_proc");
        addMetadata();
    }

    public QAsyncProc(String variable, String schema, String table) {
        super(QAsyncProc.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAsyncProc(Path<? extends QAsyncProc> path) {
        super(path.getType(), path.getMetadata(), "public", "async_proc");
        addMetadata();
    }

    public QAsyncProc(PathMetadata<?> metadata) {
        super(QAsyncProc.class, metadata, "public", "async_proc");
        addMetadata();
    }

    public void addMetadata() {
    }

}

