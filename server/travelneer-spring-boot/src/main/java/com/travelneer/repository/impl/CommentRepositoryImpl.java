package com.travelneer.repository.impl;

import com.travelneer.jooq.tables.records.CommentRecord;
import com.travelneer.post.Comment;
import com.travelneer.post.Post;
import com.travelneer.repository.CommentRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

import static com.travelneer.jooq.Tables.COMMENT;
import static com.travelneer.jooq.Tables.POST;
import static com.travelneer.jooq.Tables.USER;
import static org.jooq.impl.DSL.count;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    private final DSLContext create;

    @Autowired
    public CommentRepositoryImpl(DSLContext create) {
        this.create = create;
    }

    @Override
    public List<Comment> getCommentsByParentPostId(int postId, int offset) {

        List<Comment> comments =
                create.select(COMMENT.ID, COMMENT.CONTENT, COMMENT.CREATED_AT, COMMENT.AUTHOR_ID, COMMENT.PARENT_POST_ID,
                        USER.NAME, USER.EMAIL)
                        .from(COMMENT)
                        .innerJoin(USER).on(COMMENT.AUTHOR_ID.eq(USER.ID))
                        .where(COMMENT.PARENT_POST_ID.eq(postId))
                        .orderBy(COMMENT.CREATED_AT.desc())
                        .offset(offset)
                        .limit(10)
                        .fetch().into(Comment.class);

        return comments;
    }

    @Override
    public Integer getCommentsCountByParentPostId(int postId) {
        return create.select(count()).from(COMMENT)
                .where(COMMENT.PARENT_POST_ID.eq(postId))
                .fetchOne(0, Integer.class);
    }


    @Override
    public void save(Comment entity) throws SQLException {
        CommentRecord record = create.newRecord(COMMENT);
        record.from(entity);
        record.store();
    }

    @Override
    public void delete(Comment entity) throws SQLException {
        create.deleteFrom(COMMENT).where(COMMENT.ID.eq(entity.getId()));
    }
}
