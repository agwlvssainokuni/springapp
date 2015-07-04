package cherry.sqlman.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QSqlStatement is a Querydsl query type for BSqlStatement
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlStatement extends com.mysema.query.sql.RelationalPathBase<BSqlStatement> {

    private static final long serialVersionUID = 1313077308;

    public static final QSqlStatement sqlStatement = new QSqlStatement("SQL_STATEMENT");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final StringPath databaseName = createString("databaseName");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final StringPath paramMap = createString("paramMap");

    public final StringPath query = createString("query");

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BSqlStatement> sqlStatementPkc = createPrimaryKey(id);

    public QSqlStatement(String variable) {
        super(BSqlStatement.class, forVariable(variable), "PUBLIC", "SQL_STATEMENT");
        addMetadata();
    }

    public QSqlStatement(String variable, String schema, String table) {
        super(BSqlStatement.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QSqlStatement(Path<? extends BSqlStatement> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "SQL_STATEMENT");
        addMetadata();
    }

    public QSqlStatement(PathMetadata<?> metadata) {
        super(BSqlStatement.class, metadata, "PUBLIC", "SQL_STATEMENT");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(6).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(databaseName, ColumnMetadata.named("DATABASE_NAME").withIndex(2).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(7).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(paramMap, ColumnMetadata.named("PARAM_MAP").withIndex(4).ofType(Types.VARCHAR).withSize(5000));
        addMetadata(query, ColumnMetadata.named("QUERY").withIndex(3).ofType(Types.VARCHAR).withSize(5000).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

