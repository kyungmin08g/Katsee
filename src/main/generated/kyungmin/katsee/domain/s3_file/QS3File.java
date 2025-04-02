package kyungmin.katsee.domain.s3_file;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QS3File is a Querydsl query type for S3File
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QS3File extends EntityPathBase<S3File> {

    private static final long serialVersionUID = 1353041596L;

    public static final QS3File s3File = new QS3File("s3File");

    public final StringPath fileName = createString("fileName");

    public final StringPath fileUrl = createString("fileUrl");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath originalName = createString("originalName");

    public QS3File(String variable) {
        super(S3File.class, forVariable(variable));
    }

    public QS3File(Path<? extends S3File> path) {
        super(path.getType(), path.getMetadata());
    }

    public QS3File(PathMetadata metadata) {
        super(S3File.class, metadata);
    }

}

