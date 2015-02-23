package cherry.spring.common.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QHolidayMaster is a Querydsl query type for BHolidayMaster
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QHolidayMaster extends com.mysema.query.sql.RelationalPathBase<BHolidayMaster> {

    private static final long serialVersionUID = -247148331;

    public static final QHolidayMaster holidayMaster = new QHolidayMaster("HOLIDAY_MASTER");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final DatePath<org.joda.time.LocalDate> dt = createDate("dt", org.joda.time.LocalDate.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final StringPath type = createString("type");

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BHolidayMaster> holidayMasterPkc = createPrimaryKey(dt);

    public QHolidayMaster(String variable) {
        super(BHolidayMaster.class, forVariable(variable), "PUBLIC", "HOLIDAY_MASTER");
        addMetadata();
    }

    public QHolidayMaster(String variable, String schema, String table) {
        super(BHolidayMaster.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QHolidayMaster(Path<? extends BHolidayMaster> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "HOLIDAY_MASTER");
        addMetadata();
    }

    public QHolidayMaster(PathMetadata<?> metadata) {
        super(BHolidayMaster.class, metadata, "PUBLIC", "HOLIDAY_MASTER");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(4).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(6).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(dt, ColumnMetadata.named("DT").withIndex(1).ofType(Types.DATE).withSize(8).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(5).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(type, ColumnMetadata.named("TYPE").withIndex(2).ofType(Types.VARCHAR).withSize(2).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(3).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

