package cherry.spring.common.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QBizdatetimeMaster is a Querydsl query type for BizdatetimeMaster
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QBizdatetimeMaster extends com.mysema.query.sql.RelationalPathBase<BizdatetimeMaster> {

    private static final long serialVersionUID = 474771251;

    public static final QBizdatetimeMaster bizdatetimeMaster = new QBizdatetimeMaster("BIZDATETIME_MASTER");

    public final DatePath<java.sql.Date> bizdate = createDate("bizdate", java.sql.Date.class);

    public final DateTimePath<java.sql.Timestamp> createdAt = createDateTime("createdAt", java.sql.Timestamp.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final NumberPath<Integer> offsetDay = createNumber("offsetDay", Integer.class);

    public final NumberPath<Integer> offsetHour = createNumber("offsetHour", Integer.class);

    public final NumberPath<Integer> offsetMinute = createNumber("offsetMinute", Integer.class);

    public final NumberPath<Integer> offsetSecond = createNumber("offsetSecond", Integer.class);

    public final DateTimePath<java.sql.Timestamp> updatedAt = createDateTime("updatedAt", java.sql.Timestamp.class);

    public final com.mysema.query.sql.PrimaryKey<BizdatetimeMaster> bizdatetimeMasterPkc = createPrimaryKey(id);

    public QBizdatetimeMaster(String variable) {
        super(BizdatetimeMaster.class, forVariable(variable), "PUBLIC", "BIZDATETIME_MASTER");
        addMetadata();
    }

    public QBizdatetimeMaster(String variable, String schema, String table) {
        super(BizdatetimeMaster.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QBizdatetimeMaster(Path<? extends BizdatetimeMaster> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "BIZDATETIME_MASTER");
        addMetadata();
    }

    public QBizdatetimeMaster(PathMetadata<?> metadata) {
        super(BizdatetimeMaster.class, metadata, "PUBLIC", "BIZDATETIME_MASTER");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(bizdate, ColumnMetadata.named("BIZDATE").withIndex(2).ofType(Types.DATE).withSize(8).notNull());
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(8).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(10).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(9).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(offsetDay, ColumnMetadata.named("OFFSET_DAY").withIndex(3).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(offsetHour, ColumnMetadata.named("OFFSET_HOUR").withIndex(4).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(offsetMinute, ColumnMetadata.named("OFFSET_MINUTE").withIndex(5).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(offsetSecond, ColumnMetadata.named("OFFSET_SECOND").withIndex(6).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(7).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

