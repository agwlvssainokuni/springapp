package cherry.sqlman.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QPasswordRequest is a Querydsl query type for BPasswordRequest
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QPasswordRequest extends com.mysema.query.sql.RelationalPathBase<BPasswordRequest> {

    private static final long serialVersionUID = 507220121;

    public static final QPasswordRequest passwordRequest = new QPasswordRequest("PASSWORD_REQUEST");

    public final DateTimePath<org.joda.time.LocalDateTime> appliedAt = createDateTime("appliedAt", org.joda.time.LocalDateTime.class);

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> doneFlg = createNumber("doneFlg", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final StringPath mailAddr = createString("mailAddr");

    public final StringPath token = createString("token");

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BPasswordRequest> passwordRequestPkc = createPrimaryKey(id);

    public QPasswordRequest(String variable) {
        super(BPasswordRequest.class, forVariable(variable), "PUBLIC", "PASSWORD_REQUEST");
        addMetadata();
    }

    public QPasswordRequest(String variable, String schema, String table) {
        super(BPasswordRequest.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QPasswordRequest(Path<? extends BPasswordRequest> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "PASSWORD_REQUEST");
        addMetadata();
    }

    public QPasswordRequest(PathMetadata<?> metadata) {
        super(BPasswordRequest.class, metadata, "PUBLIC", "PASSWORD_REQUEST");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(appliedAt, ColumnMetadata.named("APPLIED_AT").withIndex(4).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(7).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(doneFlg, ColumnMetadata.named("DONE_FLG").withIndex(5).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(8).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(mailAddr, ColumnMetadata.named("MAIL_ADDR").withIndex(2).ofType(Types.VARCHAR).withSize(300).notNull());
        addMetadata(token, ColumnMetadata.named("TOKEN").withIndex(3).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(6).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

