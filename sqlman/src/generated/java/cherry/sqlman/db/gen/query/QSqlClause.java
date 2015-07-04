package cherry.sqlman.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QSqlClause is a Querydsl query type for BSqlClause
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlClause extends com.mysema.query.sql.RelationalPathBase<BSqlClause> {

    private static final long serialVersionUID = 807467458;

    public static final QSqlClause sqlClause = new QSqlClause("SQL_CLAUSE");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final StringPath databaseName = createString("databaseName");

    public final StringPath fromClause = createString("fromClause");

    public final StringPath groupByClause = createString("groupByClause");

    public final StringPath havingClause = createString("havingClause");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final StringPath orderByClause = createString("orderByClause");

    public final StringPath paramMap = createString("paramMap");

    public final StringPath selectClause = createString("selectClause");

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final StringPath whereClause = createString("whereClause");

    public final com.mysema.query.sql.PrimaryKey<BSqlClause> sqlClausePkc = createPrimaryKey(id);

    public QSqlClause(String variable) {
        super(BSqlClause.class, forVariable(variable), "PUBLIC", "SQL_CLAUSE");
        addMetadata();
    }

    public QSqlClause(String variable, String schema, String table) {
        super(BSqlClause.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QSqlClause(Path<? extends BSqlClause> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "SQL_CLAUSE");
        addMetadata();
    }

    public QSqlClause(PathMetadata<?> metadata) {
        super(BSqlClause.class, metadata, "PUBLIC", "SQL_CLAUSE");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(11).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(databaseName, ColumnMetadata.named("DATABASE_NAME").withIndex(2).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(fromClause, ColumnMetadata.named("FROM_CLAUSE").withIndex(4).ofType(Types.VARCHAR).withSize(500).notNull());
        addMetadata(groupByClause, ColumnMetadata.named("GROUP_BY_CLAUSE").withIndex(6).ofType(Types.VARCHAR).withSize(500));
        addMetadata(havingClause, ColumnMetadata.named("HAVING_CLAUSE").withIndex(7).ofType(Types.VARCHAR).withSize(500));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(12).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(orderByClause, ColumnMetadata.named("ORDER_BY_CLAUSE").withIndex(8).ofType(Types.VARCHAR).withSize(500));
        addMetadata(paramMap, ColumnMetadata.named("PARAM_MAP").withIndex(9).ofType(Types.VARCHAR).withSize(5000));
        addMetadata(selectClause, ColumnMetadata.named("SELECT_CLAUSE").withIndex(3).ofType(Types.VARCHAR).withSize(500).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(10).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(whereClause, ColumnMetadata.named("WHERE_CLAUSE").withIndex(5).ofType(Types.VARCHAR).withSize(500));
    }

}

