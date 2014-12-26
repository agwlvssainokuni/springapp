package cherry.querytutorial.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QPriorityMaster is a Querydsl query type for BPriorityMaster
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QPriorityMaster extends com.mysema.query.sql.RelationalPathBase<BPriorityMaster> {

    private static final long serialVersionUID = 646196113;

    public static final QPriorityMaster priorityMaster = new QPriorityMaster("PRIORITY_MASTER");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final NumberPath<Integer> priorityCd = createNumber("priorityCd", Integer.class);

    public final StringPath priorityLabel = createString("priorityLabel");

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BPriorityMaster> priorityMasterPkc = createPrimaryKey(id);

    public QPriorityMaster(String variable) {
        super(BPriorityMaster.class, forVariable(variable), "PUBLIC", "PRIORITY_MASTER");
        addMetadata();
    }

    public QPriorityMaster(String variable, String schema, String table) {
        super(BPriorityMaster.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QPriorityMaster(Path<? extends BPriorityMaster> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "PRIORITY_MASTER");
        addMetadata();
    }

    public QPriorityMaster(PathMetadata<?> metadata) {
        super(BPriorityMaster.class, metadata, "PUBLIC", "PRIORITY_MASTER");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(7).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(6).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(priorityCd, ColumnMetadata.named("PRIORITY_CD").withIndex(2).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(priorityLabel, ColumnMetadata.named("PRIORITY_LABEL").withIndex(3).ofType(Types.VARCHAR).withSize(10).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(4).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

