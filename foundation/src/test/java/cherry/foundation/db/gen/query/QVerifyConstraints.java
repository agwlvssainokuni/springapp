package cherry.foundation.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QVerifyConstraints is a Querydsl query type for BVerifyConstraints
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QVerifyConstraints extends com.mysema.query.sql.RelationalPathBase<BVerifyConstraints> {

    private static final long serialVersionUID = 739199305;

    public static final QVerifyConstraints verifyConstraints = new QVerifyConstraints("VERIFY_CONSTRAINTS");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final com.mysema.query.sql.PrimaryKey<BVerifyConstraints> verifyConstraintsPkc = createPrimaryKey(id);

    public final com.mysema.query.sql.ForeignKey<BVerifyConstraints> verifyConstraintsFk1 = createForeignKey(parentId, "ID");

    public final com.mysema.query.sql.ForeignKey<BVerifyConstraints> _verifyConstraintsFk1 = createInvForeignKey(id, "PARENT_ID");

    public QVerifyConstraints(String variable) {
        super(BVerifyConstraints.class, forVariable(variable), "PUBLIC", "VERIFY_CONSTRAINTS");
        addMetadata();
    }

    public QVerifyConstraints(String variable, String schema, String table) {
        super(BVerifyConstraints.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QVerifyConstraints(Path<? extends BVerifyConstraints> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "VERIFY_CONSTRAINTS");
        addMetadata();
    }

    public QVerifyConstraints(PathMetadata<?> metadata) {
        super(BVerifyConstraints.class, metadata, "PUBLIC", "VERIFY_CONSTRAINTS");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(name, ColumnMetadata.named("NAME").withIndex(3).ofType(Types.VARCHAR).withSize(32));
        addMetadata(parentId, ColumnMetadata.named("PARENT_ID").withIndex(2).ofType(Types.BIGINT).withSize(19));
    }

}

