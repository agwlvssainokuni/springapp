package cherry.common.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QUser is a Querydsl query type for BUser
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QUser extends com.mysema.query.sql.RelationalPathBase<BUser> {

    private static final long serialVersionUID = 2002973767;

    public static final QUser user = new QUser("USER");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final StringPath firstName = createString("firstName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lastName = createString("lastName");

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final StringPath loginId = createString("loginId");

    public final StringPath password = createString("password");

    public final DateTimePath<org.joda.time.LocalDateTime> registeredAt = createDateTime("registeredAt", org.joda.time.LocalDateTime.class);

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BUser> userPkc = createPrimaryKey(id);

    public QUser(String variable) {
        super(BUser.class, forVariable(variable), "PUBLIC", "USER");
        addMetadata();
    }

    public QUser(String variable, String schema, String table) {
        super(BUser.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QUser(Path<? extends BUser> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "USER");
        addMetadata();
    }

    public QUser(PathMetadata<?> metadata) {
        super(BUser.class, metadata, "PUBLIC", "USER");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(8).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(10).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(firstName, ColumnMetadata.named("FIRST_NAME").withIndex(5).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(lastName, ColumnMetadata.named("LAST_NAME").withIndex(6).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(9).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(loginId, ColumnMetadata.named("LOGIN_ID").withIndex(2).ofType(Types.VARCHAR).withSize(300).notNull());
        addMetadata(password, ColumnMetadata.named("PASSWORD").withIndex(3).ofType(Types.VARCHAR).withSize(100).notNull());
        addMetadata(registeredAt, ColumnMetadata.named("REGISTERED_AT").withIndex(4).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(7).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

