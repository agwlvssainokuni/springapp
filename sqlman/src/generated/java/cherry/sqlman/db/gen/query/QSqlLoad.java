package cherry.sqlman.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QSqlLoad is a Querydsl query type for BSqlLoad
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlLoad extends com.mysema.query.sql.RelationalPathBase<BSqlLoad> {

    private static final long serialVersionUID = 1833511417;

    public static final QSqlLoad sqlLoad = new QSqlLoad("SQL_LOAD");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final StringPath databaseName = createString("databaseName");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final StringPath query = createString("query");

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BSqlLoad> sqlLoadPkc = createPrimaryKey(id);

    public QSqlLoad(String variable) {
        super(BSqlLoad.class, forVariable(variable), "PUBLIC", "SQL_LOAD");
        addMetadata();
    }

    public QSqlLoad(String variable, String schema, String table) {
        super(BSqlLoad.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QSqlLoad(Path<? extends BSqlLoad> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "SQL_LOAD");
        addMetadata();
    }

    public QSqlLoad(PathMetadata<?> metadata) {
        super(BSqlLoad.class, metadata, "PUBLIC", "SQL_LOAD");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(databaseName, ColumnMetadata.named("DATABASE_NAME").withIndex(2).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(6).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(query, ColumnMetadata.named("QUERY").withIndex(3).ofType(Types.VARCHAR).withSize(5000).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(4).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

