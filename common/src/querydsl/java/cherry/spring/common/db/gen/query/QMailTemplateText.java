package cherry.spring.common.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QMailTemplateText is a Querydsl query type for BMailTemplateText
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QMailTemplateText extends com.mysema.query.sql.RelationalPathBase<BMailTemplateText> {

    private static final long serialVersionUID = -1010418109;

    public static final QMailTemplateText mailTemplateText = new QMailTemplateText("MAIL_TEMPLATE_TEXT");

    public final StringPath body = createString("body");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath locale = createString("locale");

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final NumberPath<Integer> mailTemplateId = createNumber("mailTemplateId", Integer.class);

    public final StringPath subject = createString("subject");

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BMailTemplateText> mailTemplateTextPkc = createPrimaryKey(id);

    public QMailTemplateText(String variable) {
        super(BMailTemplateText.class, forVariable(variable), "PUBLIC", "MAIL_TEMPLATE_TEXT");
        addMetadata();
    }

    public QMailTemplateText(String variable, String schema, String table) {
        super(BMailTemplateText.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QMailTemplateText(Path<? extends BMailTemplateText> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "MAIL_TEMPLATE_TEXT");
        addMetadata();
    }

    public QMailTemplateText(PathMetadata<?> metadata) {
        super(BMailTemplateText.class, metadata, "PUBLIC", "MAIL_TEMPLATE_TEXT");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(body, ColumnMetadata.named("BODY").withIndex(5).ofType(Types.VARCHAR).withSize(4096).notNull());
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(7).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(9).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(locale, ColumnMetadata.named("LOCALE").withIndex(3).ofType(Types.VARCHAR).withSize(5).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(8).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(mailTemplateId, ColumnMetadata.named("MAIL_TEMPLATE_ID").withIndex(2).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(subject, ColumnMetadata.named("SUBJECT").withIndex(4).ofType(Types.VARCHAR).withSize(1024).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(6).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

