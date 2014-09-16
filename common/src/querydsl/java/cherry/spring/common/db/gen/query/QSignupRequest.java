package cherry.spring.common.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QSignupRequest is a Querydsl query type for QSignupRequest
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSignupRequest extends com.mysema.query.sql.RelationalPathBase<QSignupRequest> {

    private static final long serialVersionUID = 624112291;

    public static final QSignupRequest signupRequest = new QSignupRequest("signup_request");

    public QSignupRequest(String variable) {
        super(QSignupRequest.class, forVariable(variable), "public", "signup_request");
        addMetadata();
    }

    public QSignupRequest(String variable, String schema, String table) {
        super(QSignupRequest.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QSignupRequest(Path<? extends QSignupRequest> path) {
        super(path.getType(), path.getMetadata(), "public", "signup_request");
        addMetadata();
    }

    public QSignupRequest(PathMetadata<?> metadata) {
        super(QSignupRequest.class, metadata, "public", "signup_request");
        addMetadata();
    }

    public void addMetadata() {
    }

}

