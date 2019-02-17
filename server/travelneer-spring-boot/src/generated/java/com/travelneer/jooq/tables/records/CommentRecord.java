/*
 * This file is generated by jOOQ.
 */
package com.travelneer.jooq.tables.records;


import com.travelneer.jooq.tables.Comment;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CommentRecord extends UpdatableRecordImpl<CommentRecord> implements Record5<Integer, String, Integer, Integer, Timestamp> {

    private static final long serialVersionUID = -1251037821;

    /**
     * Setter for <code>travelneer.comment.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>travelneer.comment.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>travelneer.comment.content</code>.
     */
    public void setContent(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>travelneer.comment.content</code>.
     */
    public String getContent() {
        return (String) get(1);
    }

    /**
     * Setter for <code>travelneer.comment.author_id</code>.
     */
    public void setAuthorId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>travelneer.comment.author_id</code>.
     */
    public Integer getAuthorId() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>travelneer.comment.parent_post_id</code>.
     */
    public void setParentPostId(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>travelneer.comment.parent_post_id</code>.
     */
    public Integer getParentPostId() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>travelneer.comment.created_at</code>.
     */
    public void setCreatedAt(Timestamp value) {
        set(4, value);
    }

    /**
     * Getter for <code>travelneer.comment.created_at</code>.
     */
    public Timestamp getCreatedAt() {
        return (Timestamp) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, String, Integer, Integer, Timestamp> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, String, Integer, Integer, Timestamp> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Comment.COMMENT.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Comment.COMMENT.CONTENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return Comment.COMMENT.AUTHOR_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return Comment.COMMENT.PARENT_POST_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field5() {
        return Comment.COMMENT.CREATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getContent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component3() {
        return getAuthorId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component4() {
        return getParentPostId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component5() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getContent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value3() {
        return getAuthorId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value4() {
        return getParentPostId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value5() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommentRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommentRecord value2(String value) {
        setContent(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommentRecord value3(Integer value) {
        setAuthorId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommentRecord value4(Integer value) {
        setParentPostId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommentRecord value5(Timestamp value) {
        setCreatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommentRecord values(Integer value1, String value2, Integer value3, Integer value4, Timestamp value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CommentRecord
     */
    public CommentRecord() {
        super(Comment.COMMENT);
    }

    /**
     * Create a detached, initialised CommentRecord
     */
    public CommentRecord(Integer id, String content, Integer authorId, Integer parentPostId, Timestamp createdAt) {
        super(Comment.COMMENT);

        set(0, id);
        set(1, content);
        set(2, authorId);
        set(3, parentPostId);
        set(4, createdAt);
    }
}
