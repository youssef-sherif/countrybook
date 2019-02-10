package com.travelneer.post;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Post {

    private Integer id;
    private String content;
    private Integer authorId;
    private Short countryId;
    private Timestamp createdAt;

    private String name;
    private String email;

    private Map<Long, String> timeDiff;


    public Post() {}

    public Post(Post value) {
        this.id = value.id;
        this.countryId = value.countryId;
        this.createdAt = value.createdAt;
        this.authorId = value.authorId;
        this.content = value.content;
    }

    public Post(
            Integer   id,
            Integer   authorId,
            Short     countryId,
            String content,
            Timestamp createdAt

    ) {
        this.id = id;
        this.countryId = countryId;
        this.authorId = authorId;
        this.content = content;
        this.createdAt = createdAt;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Short getCountryId() {
        return this.countryId;
    }

    public void setCountryId(Short countryId) {
        this.countryId = countryId;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String userName) {
        this.name = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<Long, String> getTimeDiff() {
        return timeDiff;
    }

    public void calculateTimeDifference() {
        long timeDifference = System.currentTimeMillis() - this.getCreatedAt().getTime();

        long inSeconds = timeDifference / 1000;
        long inMinutes = inSeconds / 60;
        long inHours = inMinutes / 60;
        long inDays = inHours / 24;

        timeDiff = new HashMap<>();

        if(inSeconds <= 60) {
            timeDiff.put(inSeconds, "Seconds");
        } else if(inMinutes <= 60) {
            timeDiff.put(inMinutes, "Minutes");
        } else if(inHours <= 24) {
            timeDiff.put(inHours, "Hours");
        } else {
            timeDiff.put(inDays, "Days");
        }
    }

    public void validate() throws Exception {

        if(content.isEmpty()) {
            throw new Exception("Empty post");
        }

        if(countryId == null) {
            throw new Exception("No country selected");
        }
    }

    public PostResource toResource() {
        var postResource = new PostResource(this);

        return postResource;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Post (");

        sb.append(id);
        sb.append(", ").append(content);
        sb.append(", ").append(authorId);
        sb.append(", ").append(countryId);
        sb.append(", ").append(createdAt);

        sb.append(")");
        return sb.toString();
    }
}
