package cherry.spring.common.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QAsyncProcess is a Querydsl query type for BAsyncProcess
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QAsyncProcess extends com.mysema.query.sql.RelationalPathBase<BAsyncProcess> {

    private static final long serialVersionUID = 1409880312;

    public static final QAsyncProcess asyncProcess = new QAsyncProcess("ASYNC_PROCESS");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final DateTimePath<org.joda.time.LocalDateTime> finishedAt = createDateTime("finishedAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<org.joda.time.LocalDateTime> invokedAt = createDateTime("invokedAt", org.joda.time.LocalDateTime.class);

    public final DateTimePath<org.joda.time.LocalDateTime> launchedAt = createDateTime("launchedAt", org.joda.time.LocalDateTime.class);

    public final StringPath launchedBy = createString("launchedBy");

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final DateTimePath<org.joda.time.LocalDateTime> startedAt = createDateTime("startedAt", org.joda.time.LocalDateTime.class);

    public final StringPath status = createString("status");

    public final StringPath type = createString("type");

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BAsyncProcess> asyncProcessPkc = createPrimaryKey(id);

    public QAsyncProcess(String variable) {
        super(BAsyncProcess.class, forVariable(variable), "PUBLIC", "ASYNC_PROCESS");
        addMetadata();
    }

    public QAsyncProcess(String variable, String schema, String table) {
        super(BAsyncProcess.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAsyncProcess(Path<? extends BAsyncProcess> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "ASYNC_PROCESS");
        addMetadata();
    }

    public QAsyncProcess(PathMetadata<?> metadata) {
        super(BAsyncProcess.class, metadata, "PUBLIC", "ASYNC_PROCESS");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(10).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(12).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(finishedAt, ColumnMetadata.named("FINISHED_AT").withIndex(8).ofType(Types.TIMESTAMP).withSize(23).withDigits(10));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(invokedAt, ColumnMetadata.named("INVOKED_AT").withIndex(6).ofType(Types.TIMESTAMP).withSize(23).withDigits(10));
        addMetadata(launchedAt, ColumnMetadata.named("LAUNCHED_AT").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(launchedBy, ColumnMetadata.named("LAUNCHED_BY").withIndex(2).ofType(Types.VARCHAR).withSize(32).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(11).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(startedAt, ColumnMetadata.named("STARTED_AT").withIndex(7).ofType(Types.TIMESTAMP).withSize(23).withDigits(10));
        addMetadata(status, ColumnMetadata.named("STATUS").withIndex(4).ofType(Types.VARCHAR).withSize(3).notNull());
        addMetadata(type, ColumnMetadata.named("TYPE").withIndex(3).ofType(Types.VARCHAR).withSize(3).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(9).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

