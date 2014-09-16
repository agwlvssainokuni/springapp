package cherry.spring.common.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QMailTemplateText is a Querydsl query type for QMailTemplateText
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QMailTemplateText extends com.mysema.query.sql.RelationalPathBase<QMailTemplateText> {

    private static final long serialVersionUID = 2109358162;

    public static final QMailTemplateText mailTemplateText = new QMailTemplateText("mail_template_text");

    public QMailTemplateText(String variable) {
        super(QMailTemplateText.class, forVariable(variable), "public", "mail_template_text");
        addMetadata();
    }

    public QMailTemplateText(String variable, String schema, String table) {
        super(QMailTemplateText.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QMailTemplateText(Path<? extends QMailTemplateText> path) {
        super(path.getType(), path.getMetadata(), "public", "mail_template_text");
        addMetadata();
    }

    public QMailTemplateText(PathMetadata<?> metadata) {
        super(QMailTemplateText.class, metadata, "public", "mail_template_text");
        addMetadata();
    }

    public void addMetadata() {
    }

}

