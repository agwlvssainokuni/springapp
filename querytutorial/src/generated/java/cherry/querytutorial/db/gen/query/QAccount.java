package cherry.querytutorial.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QAccount is a Querydsl query type for BAccount
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QAccount extends com.mysema.query.sql.RelationalPathBase<BAccount> {

    private static final long serialVersionUID = -1569540574;

    public static final QAccount account = new QAccount("ACCOUNT");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final StringPath loginId = createString("loginId");

    public final StringPath name = createString("name");

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BAccount> accountPkc = createPrimaryKey(id);

    public QAccount(String variable) {
        super(BAccount.class, forVariable(variable), "PUBLIC", "ACCOUNT");
        addMetadata();
    }

    public QAccount(String variable, String schema, String table) {
        super(BAccount.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAccount(Path<? extends BAccount> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "ACCOUNT");
        addMetadata();
    }

    public QAccount(PathMetadata<?> metadata) {
        super(BAccount.class, metadata, "PUBLIC", "ACCOUNT");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(7).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(6).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(loginId, ColumnMetadata.named("LOGIN_ID").withIndex(2).ofType(Types.VARCHAR).withSize(100).notNull());
        addMetadata(name, ColumnMetadata.named("NAME").withIndex(3).ofType(Types.VARCHAR).withSize(100).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(4).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

