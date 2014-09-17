package cherry.spring.common.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QMailTemplateAddress is a Querydsl query type for MailTemplateAddress
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QMailTemplateAddress extends com.mysema.query.sql.RelationalPathBase<MailTemplateAddress> {

    private static final long serialVersionUID = 675668038;

    public static final QMailTemplateAddress mailTemplateAddress = new QMailTemplateAddress("MAIL_TEMPLATE_ADDRESS");

    public final DateTimePath<java.sql.Timestamp> createdAt = createDateTime("createdAt", java.sql.Timestamp.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final StringPath mailAddr = createString("mailAddr");

    public final NumberPath<Integer> mailTemplateId = createNumber("mailTemplateId", Integer.class);

    public final StringPath rcptType = createString("rcptType");

    public final DateTimePath<java.sql.Timestamp> updatedAt = createDateTime("updatedAt", java.sql.Timestamp.class);

    public final com.mysema.query.sql.PrimaryKey<MailTemplateAddress> mailTemplateAddressPkc = createPrimaryKey(id);

    public QMailTemplateAddress(String variable) {
        super(MailTemplateAddress.class, forVariable(variable), "PUBLIC", "MAIL_TEMPLATE_ADDRESS");
        addMetadata();
    }

    public QMailTemplateAddress(String variable, String schema, String table) {
        super(MailTemplateAddress.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QMailTemplateAddress(Path<? extends MailTemplateAddress> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "MAIL_TEMPLATE_ADDRESS");
        addMetadata();
    }

    public QMailTemplateAddress(PathMetadata<?> metadata) {
        super(MailTemplateAddress.class, metadata, "PUBLIC", "MAIL_TEMPLATE_ADDRESS");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(6).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(8).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(7).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(mailAddr, ColumnMetadata.named("MAIL_ADDR").withIndex(3).ofType(Types.VARCHAR).withSize(512).notNull());
        addMetadata(mailTemplateId, ColumnMetadata.named("MAIL_TEMPLATE_ID").withIndex(2).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(rcptType, ColumnMetadata.named("RCPT_TYPE").withIndex(4).ofType(Types.VARCHAR).withSize(3).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

