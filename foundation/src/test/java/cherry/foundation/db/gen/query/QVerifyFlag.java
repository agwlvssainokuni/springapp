package cherry.foundation.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QVerifyFlag is a Querydsl query type for BVerifyFlag
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QVerifyFlag extends com.mysema.query.sql.RelationalPathBase<BVerifyFlag> {

    private static final long serialVersionUID = -715695175;

    public static final QVerifyFlag verifyFlag = new QVerifyFlag("VERIFY_FLAG");

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final NumberPath<Integer> flagCode = createNumber("flagCode", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.mysema.query.sql.PrimaryKey<BVerifyFlag> verifyFlagPkc = createPrimaryKey(id);

    public QVerifyFlag(String variable) {
        super(BVerifyFlag.class, forVariable(variable), "PUBLIC", "VERIFY_FLAG");
        addMetadata();
    }

    public QVerifyFlag(String variable, String schema, String table) {
        super(BVerifyFlag.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QVerifyFlag(Path<? extends BVerifyFlag> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "VERIFY_FLAG");
        addMetadata();
    }

    public QVerifyFlag(PathMetadata<?> metadata) {
        super(BVerifyFlag.class, metadata, "PUBLIC", "VERIFY_FLAG");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(3).ofType(Types.INTEGER).withSize(10));
        addMetadata(flagCode, ColumnMetadata.named("FLAG_CODE").withIndex(2).ofType(Types.INTEGER).withSize(10));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
    }

}

