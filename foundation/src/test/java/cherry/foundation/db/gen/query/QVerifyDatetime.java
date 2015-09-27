package cherry.foundation.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QVerifyDatetime is a Querydsl query type for BVerifyDatetime
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QVerifyDatetime extends com.mysema.query.sql.RelationalPathBase<BVerifyDatetime> {

    private static final long serialVersionUID = 1765812552;

    public static final QVerifyDatetime verifyDatetime = new QVerifyDatetime("VERIFY_DATETIME");

    public final DatePath<org.joda.time.LocalDate> dt = createDate("dt", org.joda.time.LocalDate.class);

    public final DateTimePath<org.joda.time.LocalDateTime> dtm = createDateTime("dtm", org.joda.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final TimePath<org.joda.time.LocalTime> tm = createTime("tm", org.joda.time.LocalTime.class);

    public final com.mysema.query.sql.PrimaryKey<BVerifyDatetime> verifyDatetimePkc = createPrimaryKey(id);

    public QVerifyDatetime(String variable) {
        super(BVerifyDatetime.class, forVariable(variable), "PUBLIC", "VERIFY_DATETIME");
        addMetadata();
    }

    public QVerifyDatetime(String variable, String schema, String table) {
        super(BVerifyDatetime.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QVerifyDatetime(Path<? extends BVerifyDatetime> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "VERIFY_DATETIME");
        addMetadata();
    }

    public QVerifyDatetime(PathMetadata<?> metadata) {
        super(BVerifyDatetime.class, metadata, "PUBLIC", "VERIFY_DATETIME");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(dt, ColumnMetadata.named("DT").withIndex(2).ofType(Types.DATE).withSize(8));
        addMetadata(dtm, ColumnMetadata.named("DTM").withIndex(4).ofType(Types.TIMESTAMP).withSize(23).withDigits(10));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(tm, ColumnMetadata.named("TM").withIndex(3).ofType(Types.TIME).withSize(6));
    }

}

