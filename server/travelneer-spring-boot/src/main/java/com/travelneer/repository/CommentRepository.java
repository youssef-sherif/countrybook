package com.travelneer.repository;

import com.travelneer.post.Post;

import java.sql.SQLException;
import java.util.List;

public interface CommentRepository extends IRepository<Post> {

    List<Post> getCommentsByParentPostId(int postId, int offset) throws SQLException;

    Integer getCommentsCountByParentPostId(int postId);
}
