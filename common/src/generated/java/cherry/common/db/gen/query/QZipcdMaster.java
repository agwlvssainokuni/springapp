package cherry.common.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QZipcdMaster is a Querydsl query type for BZipcdMaster
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QZipcdMaster extends com.mysema.query.sql.RelationalPathBase<BZipcdMaster> {

    private static final long serialVersionUID = 500133960;

    public static final QZipcdMaster zipcdMaster = new QZipcdMaster("ZIPCD_MASTER");

    public final StringPath addr = createString("addr");

    public final StringPath addrKana = createString("addrKana");

    public final StringPath city = createString("city");

    public final NumberPath<Integer> cityCd = createNumber("cityCd", Integer.class);

    public final StringPath cityKana = createString("cityKana");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final StringPath pref = createString("pref");

    public final StringPath prefKana = createString("prefKana");

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final StringPath zipcd = createString("zipcd");

    public final com.mysema.query.sql.PrimaryKey<BZipcdMaster> zipcdMasterPkc = createPrimaryKey(id);

    public QZipcdMaster(String variable) {
        super(BZipcdMaster.class, forVariable(variable), "PUBLIC", "ZIPCD_MASTER");
        addMetadata();
    }

    public QZipcdMaster(String variable, String schema, String table) {
        super(BZipcdMaster.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QZipcdMaster(Path<? extends BZipcdMaster> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "ZIPCD_MASTER");
        addMetadata();
    }

    public QZipcdMaster(PathMetadata<?> metadata) {
        super(BZipcdMaster.class, metadata, "PUBLIC", "ZIPCD_MASTER");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(addr, ColumnMetadata.named("ADDR").withIndex(6).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(addrKana, ColumnMetadata.named("ADDR_KANA").withIndex(9).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(city, ColumnMetadata.named("CITY").withIndex(5).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(cityCd, ColumnMetadata.named("CITY_CD").withIndex(2).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(cityKana, ColumnMetadata.named("CITY_KANA").withIndex(8).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(11).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(13).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(12).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(pref, ColumnMetadata.named("PREF").withIndex(4).ofType(Types.VARCHAR).withSize(30).notNull());
        addMetadata(prefKana, ColumnMetadata.named("PREF_KANA").withIndex(7).ofType(Types.VARCHAR).withSize(30).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(10).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(zipcd, ColumnMetadata.named("ZIPCD").withIndex(3).ofType(Types.VARCHAR).withSize(10).notNull());
    }

}

