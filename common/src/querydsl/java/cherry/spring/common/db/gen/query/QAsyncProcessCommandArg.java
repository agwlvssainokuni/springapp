package cherry.spring.common.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QAsyncProcessCommandArg is a Querydsl query type for BAsyncProcessCommandArg
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QAsyncProcessCommandArg extends com.mysema.query.sql.RelationalPathBase<BAsyncProcessCommandArg> {

    private static final long serialVersionUID = -888081885;

    public static final QAsyncProcessCommandArg asyncProcessCommandArg = new QAsyncProcessCommandArg("ASYNC_PROCESS_COMMAND_ARG");

    public final StringPath argument = createString("argument");

    public final NumberPath<Integer> asyncProcessId = createNumber("asyncProcessId", Integer.class);

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BAsyncProcessCommandArg> asyncProcessCommandArgPkc = createPrimaryKey(id);

    public QAsyncProcessCommandArg(String variable) {
        super(BAsyncProcessCommandArg.class, forVariable(variable), "PUBLIC", "ASYNC_PROCESS_COMMAND_ARG");
        addMetadata();
    }

    public QAsyncProcessCommandArg(String variable, String schema, String table) {
        super(BAsyncProcessCommandArg.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAsyncProcessCommandArg(Path<? extends BAsyncProcessCommandArg> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "ASYNC_PROCESS_COMMAND_ARG");
        addMetadata();
    }

    public QAsyncProcessCommandArg(PathMetadata<?> metadata) {
        super(BAsyncProcessCommandArg.class, metadata, "PUBLIC", "ASYNC_PROCESS_COMMAND_ARG");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(argument, ColumnMetadata.named("ARGUMENT").withIndex(3).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(asyncProcessId, ColumnMetadata.named("ASYNC_PROCESS_ID").withIndex(2).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(7).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(6).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(4).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

