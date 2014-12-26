package cherry.querytutorial.db.gen.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QNote is a Querydsl query type for BNote
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QNote extends com.mysema.query.sql.RelationalPathBase<BNote> {

    private static final long serialVersionUID = -1404301283;

    public static final QNote note = new QNote("NOTE");

    public final DateTimePath<org.joda.time.LocalDateTime> createdAt = createDateTime("createdAt", org.joda.time.LocalDateTime.class);

    public final NumberPath<Integer> deletedFlg = createNumber("deletedFlg", Integer.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final DateTimePath<org.joda.time.LocalDateTime> postedAt = createDateTime("postedAt", org.joda.time.LocalDateTime.class);

    public final StringPath postedBy = createString("postedBy");

    public final NumberPath<Long> todoId = createNumber("todoId", Long.class);

    public final DateTimePath<org.joda.time.LocalDateTime> updatedAt = createDateTime("updatedAt", org.joda.time.LocalDateTime.class);

    public final com.mysema.query.sql.PrimaryKey<BNote> notePkc = createPrimaryKey(id);

    public QNote(String variable) {
        super(BNote.class, forVariable(variable), "PUBLIC", "NOTE");
        addMetadata();
    }

    public QNote(String variable, String schema, String table) {
        super(BNote.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QNote(Path<? extends BNote> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "NOTE");
        addMetadata();
    }

    public QNote(PathMetadata<?> metadata) {
        super(BNote.class, metadata, "PUBLIC", "NOTE");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(7).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(deletedFlg, ColumnMetadata.named("DELETED_FLG").withIndex(9).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(description, ColumnMetadata.named("DESCRIPTION").withIndex(5).ofType(Types.VARCHAR).withSize(1000).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(8).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(postedAt, ColumnMetadata.named("POSTED_AT").withIndex(4).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(postedBy, ColumnMetadata.named("POSTED_BY").withIndex(3).ofType(Types.VARCHAR).withSize(100).notNull());
        addMetadata(todoId, ColumnMetadata.named("TODO_ID").withIndex(2).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(6).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

