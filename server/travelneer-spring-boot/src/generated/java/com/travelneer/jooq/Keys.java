/*
 * This file is generated by jOOQ.
*/
package com.travelneer.jooq;


import com.travelneer.jooq.tables.Country;
import com.travelneer.jooq.tables.CountryFollows;
import com.travelneer.jooq.tables.Favourites;
import com.travelneer.jooq.tables.Post;
import com.travelneer.jooq.tables.Role;
import com.travelneer.jooq.tables.User;
import com.travelneer.jooq.tables.UserRoles;
import com.travelneer.jooq.tables.records.CountryFollowsRecord;
import com.travelneer.jooq.tables.records.CountryRecord;
import com.travelneer.jooq.tables.records.FavouritesRecord;
import com.travelneer.jooq.tables.records.PostRecord;
import com.travelneer.jooq.tables.records.RoleRecord;
import com.travelneer.jooq.tables.records.UserRecord;
import com.travelneer.jooq.tables.records.UserRolesRecord;

import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>travelneer</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<CountryRecord, Short> IDENTITY_COUNTRY = Identities0.IDENTITY_COUNTRY;
    public static final Identity<PostRecord, Integer> IDENTITY_POST = Identities0.IDENTITY_POST;
    public static final Identity<RoleRecord, Byte> IDENTITY_ROLE = Identities0.IDENTITY_ROLE;
    public static final Identity<UserRecord, Integer> IDENTITY_USER = Identities0.IDENTITY_USER;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<CountryRecord> KEY_COUNTRY_PRIMARY = UniqueKeys0.KEY_COUNTRY_PRIMARY;
    public static final UniqueKey<CountryFollowsRecord> KEY_COUNTRY_FOLLOWS_PRIMARY = UniqueKeys0.KEY_COUNTRY_FOLLOWS_PRIMARY;
    public static final UniqueKey<FavouritesRecord> KEY_FAVOURITES_PRIMARY = UniqueKeys0.KEY_FAVOURITES_PRIMARY;
    public static final UniqueKey<PostRecord> KEY_POST_PRIMARY = UniqueKeys0.KEY_POST_PRIMARY;
    public static final UniqueKey<RoleRecord> KEY_ROLE_PRIMARY = UniqueKeys0.KEY_ROLE_PRIMARY;
    public static final UniqueKey<RoleRecord> KEY_ROLE_ROLE_NAME = UniqueKeys0.KEY_ROLE_ROLE_NAME;
    public static final UniqueKey<UserRecord> KEY_USER_PRIMARY = UniqueKeys0.KEY_USER_PRIMARY;
    public static final UniqueKey<UserRecord> KEY_USER_USER_NAME = UniqueKeys0.KEY_USER_USER_NAME;
    public static final UniqueKey<UserRecord> KEY_USER_USER_EMAIL = UniqueKeys0.KEY_USER_USER_EMAIL;
    public static final UniqueKey<UserRolesRecord> KEY_USER_ROLES_PRIMARY = UniqueKeys0.KEY_USER_ROLES_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<CountryFollowsRecord, UserRecord> COUNTRY_FOLLOWS_IBFK_1 = ForeignKeys0.COUNTRY_FOLLOWS_IBFK_1;
    public static final ForeignKey<CountryFollowsRecord, CountryRecord> COUNTRY_FOLLOWS_IBFK_2 = ForeignKeys0.COUNTRY_FOLLOWS_IBFK_2;
    public static final ForeignKey<FavouritesRecord, PostRecord> FAVOURITES_IBFK_2 = ForeignKeys0.FAVOURITES_IBFK_2;
    public static final ForeignKey<FavouritesRecord, UserRecord> FAVOURITES_IBFK_1 = ForeignKeys0.FAVOURITES_IBFK_1;
    public static final ForeignKey<PostRecord, UserRecord> POST_IBFK_1 = ForeignKeys0.POST_IBFK_1;
    public static final ForeignKey<PostRecord, CountryRecord> POST_IBFK_2 = ForeignKeys0.POST_IBFK_2;
    public static final ForeignKey<UserRolesRecord, UserRecord> USER_ROLES_IBFK_1 = ForeignKeys0.USER_ROLES_IBFK_1;
    public static final ForeignKey<UserRolesRecord, RoleRecord> USER_ROLES_IBFK_2 = ForeignKeys0.USER_ROLES_IBFK_2;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<CountryRecord, Short> IDENTITY_COUNTRY = Internal.createIdentity(Country.COUNTRY, Country.COUNTRY.ID);
        public static Identity<PostRecord, Integer> IDENTITY_POST = Internal.createIdentity(Post.POST, Post.POST.ID);
        public static Identity<RoleRecord, Byte> IDENTITY_ROLE = Internal.createIdentity(Role.ROLE, Role.ROLE.ID);
        public static Identity<UserRecord, Integer> IDENTITY_USER = Internal.createIdentity(User.USER, User.USER.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<CountryRecord> KEY_COUNTRY_PRIMARY = Internal.createUniqueKey(Country.COUNTRY, "KEY_country_PRIMARY", Country.COUNTRY.ID);
        public static final UniqueKey<CountryFollowsRecord> KEY_COUNTRY_FOLLOWS_PRIMARY = Internal.createUniqueKey(CountryFollows.COUNTRY_FOLLOWS, "KEY_country_follows_PRIMARY", CountryFollows.COUNTRY_FOLLOWS.USER_ID, CountryFollows.COUNTRY_FOLLOWS.COUNTRY_ID);
        public static final UniqueKey<FavouritesRecord> KEY_FAVOURITES_PRIMARY = Internal.createUniqueKey(Favourites.FAVOURITES, "KEY_favourites_PRIMARY", Favourites.FAVOURITES.USER_ID, Favourites.FAVOURITES.POST_ID);
        public static final UniqueKey<PostRecord> KEY_POST_PRIMARY = Internal.createUniqueKey(Post.POST, "KEY_post_PRIMARY", Post.POST.ID);
        public static final UniqueKey<RoleRecord> KEY_ROLE_PRIMARY = Internal.createUniqueKey(Role.ROLE, "KEY_role_PRIMARY", Role.ROLE.ID);
        public static final UniqueKey<RoleRecord> KEY_ROLE_ROLE_NAME = Internal.createUniqueKey(Role.ROLE, "KEY_role_role_name", Role.ROLE.NAME);
        public static final UniqueKey<UserRecord> KEY_USER_PRIMARY = Internal.createUniqueKey(User.USER, "KEY_user_PRIMARY", User.USER.ID);
        public static final UniqueKey<UserRecord> KEY_USER_USER_NAME = Internal.createUniqueKey(User.USER, "KEY_user_user_name", User.USER.NAME);
        public static final UniqueKey<UserRecord> KEY_USER_USER_EMAIL = Internal.createUniqueKey(User.USER, "KEY_user_user_email", User.USER.EMAIL);
        public static final UniqueKey<UserRolesRecord> KEY_USER_ROLES_PRIMARY = Internal.createUniqueKey(UserRoles.USER_ROLES, "KEY_user_roles_PRIMARY", UserRoles.USER_ROLES.USER_ID, UserRoles.USER_ROLES.ROLE_ID);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<CountryFollowsRecord, UserRecord> COUNTRY_FOLLOWS_IBFK_1 = Internal.createForeignKey(com.travelneer.jooq.Keys.KEY_USER_PRIMARY, CountryFollows.COUNTRY_FOLLOWS, "country_follows_ibfk_1", CountryFollows.COUNTRY_FOLLOWS.USER_ID);
        public static final ForeignKey<CountryFollowsRecord, CountryRecord> COUNTRY_FOLLOWS_IBFK_2 = Internal.createForeignKey(com.travelneer.jooq.Keys.KEY_COUNTRY_PRIMARY, CountryFollows.COUNTRY_FOLLOWS, "country_follows_ibfk_2", CountryFollows.COUNTRY_FOLLOWS.COUNTRY_ID);
        public static final ForeignKey<FavouritesRecord, PostRecord> FAVOURITES_IBFK_2 = Internal.createForeignKey(com.travelneer.jooq.Keys.KEY_POST_PRIMARY, Favourites.FAVOURITES, "favourites_ibfk_2", Favourites.FAVOURITES.POST_ID);
        public static final ForeignKey<FavouritesRecord, UserRecord> FAVOURITES_IBFK_1 = Internal.createForeignKey(com.travelneer.jooq.Keys.KEY_USER_PRIMARY, Favourites.FAVOURITES, "favourites_ibfk_1", Favourites.FAVOURITES.USER_ID);
        public static final ForeignKey<PostRecord, UserRecord> POST_IBFK_1 = Internal.createForeignKey(com.travelneer.jooq.Keys.KEY_USER_PRIMARY, Post.POST, "post_ibfk_1", Post.POST.AUTHOR_ID);
        public static final ForeignKey<PostRecord, CountryRecord> POST_IBFK_2 = Internal.createForeignKey(com.travelneer.jooq.Keys.KEY_COUNTRY_PRIMARY, Post.POST, "post_ibfk_2", Post.POST.COUNTRY_ID);
        public static final ForeignKey<UserRolesRecord, UserRecord> USER_ROLES_IBFK_1 = Internal.createForeignKey(com.travelneer.jooq.Keys.KEY_USER_PRIMARY, UserRoles.USER_ROLES, "user_roles_ibfk_1", UserRoles.USER_ROLES.USER_ID);
        public static final ForeignKey<UserRolesRecord, RoleRecord> USER_ROLES_IBFK_2 = Internal.createForeignKey(com.travelneer.jooq.Keys.KEY_ROLE_PRIMARY, UserRoles.USER_ROLES, "user_roles_ibfk_2", UserRoles.USER_ROLES.ROLE_ID);
    }
}
