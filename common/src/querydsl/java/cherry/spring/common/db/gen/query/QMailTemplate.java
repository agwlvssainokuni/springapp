package cherry.spring.common.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QMailTemplate is a Querydsl query type for BMailTemplate
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QMailTemplate extends com.mysema.query.sql.RelationalPathBase<BMailTemplate> {

    private static final long serialVersionUID = 56848374;

    public static final QMailTemplate mailTemplate = new QMailTemplate("MAIL_TEMPLATE");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath sender = createString("sender");

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BMailTemplate> mailTemplatePkc = createPrimaryKey(id);

    public QMailTemplate(String variable) {
        super(BMailTemplate.class, forVariable(variable), "PUBLIC", "MAIL_TEMPLATE");
        addMetadata();
    }

    public QMailTemplate(String variable, String schema, String table) {
        super(BMailTemplate.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QMailTemplate(Path<? extends BMailTemplate> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "MAIL_TEMPLATE");
        addMetadata();
    }

    public QMailTemplate(PathMetadata<?> metadata) {
        super(BMailTemplate.class, metadata, "PUBLIC", "MAIL_TEMPLATE");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(7).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(6).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(name, ColumnMetadata.named("NAME").withIndex(2).ofType(Types.VARCHAR).withSize(32).notNull());
        addMetadata(sender, ColumnMetadata.named("SENDER").withIndex(3).ofType(Types.VARCHAR).withSize(512).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(4).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

