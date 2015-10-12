package cherry.example.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QExTbl1 is a Querydsl query type for BExTbl1
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QExTbl1 extends com.mysema.query.sql.RelationalPathBase<BExTbl1> {

    private static final long serialVersionUID = 327057901;

    public static final QExTbl1 exTbl1 = new QExTbl1("EX_TBL_1");

    public final StringPath code2 = createString("code2");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<java.math.BigDecimal> decimal1 = createNumber("decimal1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> decimal3 = createNumber("decimal3", java.math.BigDecimal.class);

    public final DatePath<org.joda.time.LocalDate> dt = createDate("dt", org.joda.time.LocalDate.class);

    public final DateTimePath<org.joda.time.LocalDateTime> dtm = createDateTime("dtm", org.joda.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> int64 = createNumber("int64", Long.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final StringPath text10 = createString("text10");

    public final StringPath text100 = createString("text100");

    public final TimePath<org.joda.time.LocalTime> tm = createTime("tm", org.joda.time.LocalTime.class);

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BExTbl1> exTbl1Pkc = createPrimaryKey(id);

    public QExTbl1(String variable) {
        super(BExTbl1.class, forVariable(variable), "PUBLIC", "EX_TBL_1");
        addMetadata();
    }

    public QExTbl1(String variable, String schema, String table) {
        super(BExTbl1.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QExTbl1(Path<? extends BExTbl1> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "EX_TBL_1");
        addMetadata();
    }

    public QExTbl1(PathMetadata<?> metadata) {
        super(BExTbl1.class, metadata, "PUBLIC", "EX_TBL_1");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(code2, ColumnMetadata.named("CODE2").withIndex(10).ofType(Types.VARCHAR).withSize(2));
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(12).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(decimal1, ColumnMetadata.named("DECIMAL1").withIndex(5).ofType(Types.DECIMAL).withSize(11).withDigits(1));
        addMetadata(decimal3, ColumnMetadata.named("DECIMAL3").withIndex(6).ofType(Types.DECIMAL).withSize(13).withDigits(3));
        addMetadata(dt, ColumnMetadata.named("DT").withIndex(7).ofType(Types.DATE).withSize(8));
        addMetadata(dtm, ColumnMetadata.named("DTM").withIndex(9).ofType(Types.TIMESTAMP).withSize(23).withDigits(10));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(int64, ColumnMetadata.named("INT64").withIndex(4).ofType(Types.BIGINT).withSize(19));
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(13).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(text10, ColumnMetadata.named("TEXT10").withIndex(2).ofType(Types.VARCHAR).withSize(10));
        addMetadata(text100, ColumnMetadata.named("TEXT100").withIndex(3).ofType(Types.VARCHAR).withSize(100));
        addMetadata(tm, ColumnMetadata.named("TM").withIndex(8).ofType(Types.TIME).withSize(6));
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(11).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

