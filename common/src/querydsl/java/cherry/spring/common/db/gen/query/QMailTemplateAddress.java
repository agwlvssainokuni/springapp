package cherry.spring.common.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QMailTemplateAddress is a Querydsl query type for QMailTemplateAddress
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QMailTemplateAddress extends com.mysema.query.sql.RelationalPathBase<QMailTemplateAddress> {

    private static final long serialVersionUID = 492737199;

    public static final QMailTemplateAddress mailTemplateAddress = new QMailTemplateAddress("mail_template_address");

    public QMailTemplateAddress(String variable) {
        super(QMailTemplateAddress.class, forVariable(variable), "public", "mail_template_address");
        addMetadata();
    }

    public QMailTemplateAddress(String variable, String schema, String table) {
        super(QMailTemplateAddress.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QMailTemplateAddress(Path<? extends QMailTemplateAddress> path) {
        super(path.getType(), path.getMetadata(), "public", "mail_template_address");
        addMetadata();
    }

    public QMailTemplateAddress(PathMetadata<?> metadata) {
        super(QMailTemplateAddress.class, metadata, "public", "mail_template_address");
        addMetadata();
    }

    public void addMetadata() {
    }

}

