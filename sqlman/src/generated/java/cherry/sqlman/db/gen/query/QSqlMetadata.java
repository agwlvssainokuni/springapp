package cherry.sqlman.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QSqlMetadata is a Querydsl query type for BSqlMetadata
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlMetadata extends com.mysema.query.sql.RelationalPathBase<BSqlMetadata> {

    private static final long serialVersionUID = 1737825986;

    public static final QSqlMetadata sqlMetadata = new QSqlMetadata("SQL_METADATA");

    public final DateTimePath<org.joda.time.LocalDateTime> changedAt = createDateTime("changedAt", org.joda.time.LocalDateTime.class);

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final StringPath description = createString("description");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath ownedBy = createString("ownedBy");

    public final NumberPath<Integer> publishedFlg = createNumber("publishedFlg", Integer.class);

    public final DateTimePath<org.joda.time.LocalDateTime> registeredAt = createDateTime("registeredAt", org.joda.time.LocalDateTime.class);

    public final StringPath sqlType = createString("sqlType");

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BSqlMetadata> sqlMetadataPkc = createPrimaryKey(id);

    public QSqlMetadata(String variable) {
        super(BSqlMetadata.class, forVariable(variable), "PUBLIC", "SQL_METADATA");
        addMetadata();
    }

    public QSqlMetadata(String variable, String schema, String table) {
        super(BSqlMetadata.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QSqlMetadata(Path<? extends BSqlMetadata> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "SQL_METADATA");
        addMetadata();
    }

    public QSqlMetadata(PathMetadata<?> metadata) {
        super(BSqlMetadata.class, metadata, "PUBLIC", "SQL_METADATA");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(changedAt, ColumnMetadata.named("CHANGED_AT").withIndex(8).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(10).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(description, ColumnMetadata.named("DESCRIPTION").withIndex(4).ofType(Types.VARCHAR).withSize(500).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(11).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(name, ColumnMetadata.named("NAME").withIndex(3).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(ownedBy, ColumnMetadata.named("OWNED_BY").withIndex(5).ofType(Types.VARCHAR).withSize(32).notNull());
        addMetadata(publishedFlg, ColumnMetadata.named("PUBLISHED_FLG").withIndex(6).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(registeredAt, ColumnMetadata.named("REGISTERED_AT").withIndex(7).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(sqlType, ColumnMetadata.named("SQL_TYPE").withIndex(2).ofType(Types.VARCHAR).withSize(32).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(9).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

