package com.travelneer.repository;

import com.travelneer.post.Comment;
import com.travelneer.post.Post;

import java.sql.SQLException;
import java.util.List;

public interface CommentRepository extends IRepository<Comment> {

    List<Comment> getCommentsByParentPostId(int postId, int offset) throws SQLException;

    List<Comment> getCommentsByParentCommentId(int postId, int commentId, int offset) throws SQLException;

    Integer getCommentsCountByParentPostId(int postId) throws SQLException;

    Comment getOneById(int postId, int commentId) throws SQLException;
}
