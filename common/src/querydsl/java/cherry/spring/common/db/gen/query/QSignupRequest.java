package cherry.spring.common.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QSignupRequest is a Querydsl query type for SignupRequest
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSignupRequest extends com.mysema.query.sql.RelationalPathBase<SignupRequest> {

    private static final long serialVersionUID = -1934738054;

    public static final QSignupRequest signupRequest = new QSignupRequest("SIGNUP_REQUEST");

    public final DateTimePath<java.sql.Timestamp> appliedAt = createDateTime("appliedAt", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> createdAt = createDateTime("createdAt", java.sql.Timestamp.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final StringPath mailAddr = createString("mailAddr");

    public final StringPath token = createString("token");

    public final DateTimePath<java.sql.Timestamp> updatedAt = createDateTime("updatedAt", java.sql.Timestamp.class);

    public final com.mysema.query.sql.PrimaryKey<SignupRequest> signupRequestPkc = createPrimaryKey(id);

    public QSignupRequest(String variable) {
        super(SignupRequest.class, forVariable(variable), "PUBLIC", "SIGNUP_REQUEST");
        addMetadata();
    }

    public QSignupRequest(String variable, String schema, String table) {
        super(SignupRequest.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QSignupRequest(Path<? extends SignupRequest> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "SIGNUP_REQUEST");
        addMetadata();
    }

    public QSignupRequest(PathMetadata<?> metadata) {
        super(SignupRequest.class, metadata, "PUBLIC", "SIGNUP_REQUEST");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(appliedAt, ColumnMetadata.named("APPLIED_AT").withIndex(4).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(6).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(8).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(7).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(mailAddr, ColumnMetadata.named("MAIL_ADDR").withIndex(2).ofType(Types.VARCHAR).withSize(512).notNull());
        addMetadata(token, ColumnMetadata.named("TOKEN").withIndex(3).ofType(Types.CHAR).withSize(36).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

