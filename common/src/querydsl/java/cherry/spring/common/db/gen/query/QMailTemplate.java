package cherry.spring.common.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QMailTemplate is a Querydsl query type for QMailTemplate
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QMailTemplate extends com.mysema.query.sql.RelationalPathBase<QMailTemplate> {

    private static final long serialVersionUID = -49229435;

    public static final QMailTemplate mailTemplate = new QMailTemplate("mail_template");

    public QMailTemplate(String variable) {
        super(QMailTemplate.class, forVariable(variable), "public", "mail_template");
        addMetadata();
    }

    public QMailTemplate(String variable, String schema, String table) {
        super(QMailTemplate.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QMailTemplate(Path<? extends QMailTemplate> path) {
        super(path.getType(), path.getMetadata(), "public", "mail_template");
        addMetadata();
    }

    public QMailTemplate(PathMetadata<?> metadata) {
        super(QMailTemplate.class, metadata, "public", "mail_template");
        addMetadata();
    }

    public void addMetadata() {
    }

}

