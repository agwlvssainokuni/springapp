package cherry.spring.common.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QUser is a Querydsl query type for QUser
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QUser extends com.mysema.query.sql.RelationalPathBase<QUser> {

    private static final long serialVersionUID = -2078760449;

    public static final QUser user = new QUser("user");

    public QUser(String variable) {
        super(QUser.class, forVariable(variable), "public", "user");
        addMetadata();
    }

    public QUser(String variable, String schema, String table) {
        super(QUser.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QUser(Path<? extends QUser> path) {
        super(path.getType(), path.getMetadata(), "public", "user");
        addMetadata();
    }

    public QUser(PathMetadata<?> metadata) {
        super(QUser.class, metadata, "public", "user");
        addMetadata();
    }

    public void addMetadata() {
    }

}

