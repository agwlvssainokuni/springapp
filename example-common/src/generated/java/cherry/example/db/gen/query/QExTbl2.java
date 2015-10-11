package cherry.example.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QExTbl2 is a Querydsl query type for BExTbl2
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QExTbl2 extends com.mysema.query.sql.RelationalPathBase<BExTbl2> {

    private static final long serialVersionUID = 327057902;

    public static final QExTbl2 exTbl2 = new QExTbl2("EX_TBL_2");

    public final StringPath code2 = createString("code2");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Double> decimal1 = createNumber("decimal1", Double.class);

    public final NumberPath<Double> decimal3 = createNumber("decimal3", Double.class);

    public final DatePath<org.joda.time.LocalDate> dt = createDate("dt", org.joda.time.LocalDate.class);

    public final DateTimePath<org.joda.time.LocalDateTime> dtm = createDateTime("dtm", org.joda.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> int64 = createNumber("int64", Long.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final StringPath text10 = createString("text10");

    public final StringPath text100 = createString("text100");

    public final TimePath<org.joda.time.LocalTime> tm = createTime("tm", org.joda.time.LocalTime.class);

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BExTbl2> exTbl2Pkc = createPrimaryKey(id);

    public QExTbl2(String variable) {
        super(BExTbl2.class, forVariable(variable), "PUBLIC", "EX_TBL_2");
        addMetadata();
    }

    public QExTbl2(String variable, String schema, String table) {
        super(BExTbl2.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QExTbl2(Path<? extends BExTbl2> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "EX_TBL_2");
        addMetadata();
    }

    public QExTbl2(PathMetadata<?> metadata) {
        super(BExTbl2.class, metadata, "PUBLIC", "EX_TBL_2");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(code2, ColumnMetadata.named("CODE2").withIndex(11).ofType(Types.VARCHAR).withSize(2));
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(13).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(decimal1, ColumnMetadata.named("DECIMAL1").withIndex(6).ofType(Types.DECIMAL).withSize(11).withDigits(1));
        addMetadata(decimal3, ColumnMetadata.named("DECIMAL3").withIndex(7).ofType(Types.DECIMAL).withSize(13).withDigits(3));
        addMetadata(dt, ColumnMetadata.named("DT").withIndex(8).ofType(Types.DATE).withSize(8));
        addMetadata(dtm, ColumnMetadata.named("DTM").withIndex(10).ofType(Types.TIMESTAMP).withSize(23).withDigits(10));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(int64, ColumnMetadata.named("INT64").withIndex(5).ofType(Types.BIGINT).withSize(19));
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(14).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(parentId, ColumnMetadata.named("PARENT_ID").withIndex(2).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(text10, ColumnMetadata.named("TEXT10").withIndex(3).ofType(Types.VARCHAR).withSize(10));
        addMetadata(text100, ColumnMetadata.named("TEXT100").withIndex(4).ofType(Types.VARCHAR).withSize(100));
        addMetadata(tm, ColumnMetadata.named("TM").withIndex(9).ofType(Types.TIME).withSize(6));
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(12).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

