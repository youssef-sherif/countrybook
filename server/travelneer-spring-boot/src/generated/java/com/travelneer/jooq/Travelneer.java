/*
 * This file is generated by jOOQ.
 */
package com.travelneer.jooq;


import com.travelneer.jooq.tables.Comment;
import com.travelneer.jooq.tables.Country;
import com.travelneer.jooq.tables.CountryFollows;
import com.travelneer.jooq.tables.Favourites;
import com.travelneer.jooq.tables.Post;
import com.travelneer.jooq.tables.Role;
import com.travelneer.jooq.tables.User;
import com.travelneer.jooq.tables.UserRoles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Travelneer extends SchemaImpl {

    private static final long serialVersionUID = -1879279606;

    /**
     * The reference instance of <code>travelneer</code>
     */
    public static final Travelneer TRAVELNEER = new Travelneer();

    /**
     * The table <code>travelneer.comment</code>.
     */
    public final Comment COMMENT = com.travelneer.jooq.tables.Comment.COMMENT;

    /**
     * The table <code>travelneer.country</code>.
     */
    public final Country COUNTRY = com.travelneer.jooq.tables.Country.COUNTRY;

    /**
     * The table <code>travelneer.country_follows</code>.
     */
    public final CountryFollows COUNTRY_FOLLOWS = com.travelneer.jooq.tables.CountryFollows.COUNTRY_FOLLOWS;

    /**
     * The table <code>travelneer.favourites</code>.
     */
    public final Favourites FAVOURITES = com.travelneer.jooq.tables.Favourites.FAVOURITES;

    /**
     * The table <code>travelneer.post</code>.
     */
    public final Post POST = com.travelneer.jooq.tables.Post.POST;

    /**
     * The table <code>travelneer.role</code>.
     */
    public final Role ROLE = com.travelneer.jooq.tables.Role.ROLE;

    /**
     * The table <code>travelneer.user</code>.
     */
    public final User USER = com.travelneer.jooq.tables.User.USER;

    /**
     * The table <code>travelneer.user_roles</code>.
     */
    public final UserRoles USER_ROLES = com.travelneer.jooq.tables.UserRoles.USER_ROLES;

    /**
     * No further instances allowed
     */
    private Travelneer() {
        super("travelneer", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Comment.COMMENT,
            Country.COUNTRY,
            CountryFollows.COUNTRY_FOLLOWS,
            Favourites.FAVOURITES,
            Post.POST,
            Role.ROLE,
            User.USER,
            UserRoles.USER_ROLES);
    }
}
