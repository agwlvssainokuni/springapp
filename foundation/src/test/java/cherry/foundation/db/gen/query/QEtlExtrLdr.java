package cherry.foundation.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QEtlExtrLdr is a Querydsl query type for BEtlExtrLdr
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QEtlExtrLdr extends com.mysema.query.sql.RelationalPathBase<BEtlExtrLdr> {

    private static final long serialVersionUID = -1957280352;

    public static final QEtlExtrLdr etlExtrLdr = new QEtlExtrLdr("ETL_EXTR_LDR");

    public final StringPath address = createString("address");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final com.mysema.query.sql.PrimaryKey<BEtlExtrLdr> etlExtrLdrPkc = createPrimaryKey(id);

    public QEtlExtrLdr(String variable) {
        super(BEtlExtrLdr.class, forVariable(variable), "PUBLIC", "ETL_EXTR_LDR");
        addMetadata();
    }

    public QEtlExtrLdr(String variable, String schema, String table) {
        super(BEtlExtrLdr.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QEtlExtrLdr(Path<? extends BEtlExtrLdr> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "ETL_EXTR_LDR");
        addMetadata();
    }

    public QEtlExtrLdr(PathMetadata<?> metadata) {
        super(BEtlExtrLdr.class, metadata, "PUBLIC", "ETL_EXTR_LDR");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(address, ColumnMetadata.named("ADDRESS").withIndex(3).ofType(Types.VARCHAR).withSize(64).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(name, ColumnMetadata.named("NAME").withIndex(2).ofType(Types.VARCHAR).withSize(32).notNull());
    }

}

