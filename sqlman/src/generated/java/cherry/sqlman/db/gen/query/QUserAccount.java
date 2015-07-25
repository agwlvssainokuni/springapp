package cherry.sqlman.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QUserAccount is a Querydsl query type for BUserAccount
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QUserAccount extends com.mysema.query.sql.RelationalPathBase<BUserAccount> {

    private static final long serialVersionUID = -497247353;

    public static final QUserAccount userAccount = new QUserAccount("USER_ACCOUNT");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final StringPath loginId = createString("loginId");

    public final StringPath mailAddr = createString("mailAddr");

    public final StringPath password = createString("password");

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BUserAccount> userAccountPkc = createPrimaryKey(id);

    public QUserAccount(String variable) {
        super(BUserAccount.class, forVariable(variable), "PUBLIC", "USER_ACCOUNT");
        addMetadata();
    }

    public QUserAccount(String variable, String schema, String table) {
        super(BUserAccount.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QUserAccount(Path<? extends BUserAccount> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "USER_ACCOUNT");
        addMetadata();
    }

    public QUserAccount(PathMetadata<?> metadata) {
        super(BUserAccount.class, metadata, "PUBLIC", "USER_ACCOUNT");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(6).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(7).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(loginId, ColumnMetadata.named("LOGIN_ID").withIndex(2).ofType(Types.VARCHAR).withSize(32).notNull());
        addMetadata(mailAddr, ColumnMetadata.named("MAIL_ADDR").withIndex(4).ofType(Types.VARCHAR).withSize(300).notNull());
        addMetadata(password, ColumnMetadata.named("PASSWORD").withIndex(3).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

