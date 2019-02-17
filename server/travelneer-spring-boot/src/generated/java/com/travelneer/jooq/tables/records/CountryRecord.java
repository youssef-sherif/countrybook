/*
 * This file is generated by jOOQ.
 */
package com.travelneer.jooq.tables.records;


import com.travelneer.jooq.tables.Country;

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
public class CountryRecord extends UpdatableRecordImpl<CountryRecord> implements Record5<Short, String, String, String, String> {

    private static final long serialVersionUID = 127684189;

    /**
     * Setter for <code>travelneer.country.id</code>.
     */
    public void setId(Short value) {
        set(0, value);
    }

    /**
     * Getter for <code>travelneer.country.id</code>.
     */
    public Short getId() {
        return (Short) get(0);
    }

    /**
     * Setter for <code>travelneer.country.code</code>.
     */
    public void setCode(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>travelneer.country.code</code>.
     */
    public String getCode() {
        return (String) get(1);
    }

    /**
     * Setter for <code>travelneer.country.name</code>.
     */
    public void setName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>travelneer.country.name</code>.
     */
    public String getName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>travelneer.country.profile_image_url</code>.
     */
    public void setProfileImageUrl(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>travelneer.country.profile_image_url</code>.
     */
    public String getProfileImageUrl() {
        return (String) get(3);
    }

    /**
     * Setter for <code>travelneer.country.flag_url</code>.
     */
    public void setFlagUrl(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>travelneer.country.flag_url</code>.
     */
    public String getFlagUrl() {
        return (String) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Short> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Short, String, String, String, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Short, String, String, String, String> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Short> field1() {
        return Country.COUNTRY.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Country.COUNTRY.CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Country.COUNTRY.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Country.COUNTRY.PROFILE_IMAGE_URL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Country.COUNTRY.FLAG_URL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Short component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getProfileImageUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getFlagUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Short value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getProfileImageUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getFlagUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryRecord value1(Short value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryRecord value2(String value) {
        setCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryRecord value3(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryRecord value4(String value) {
        setProfileImageUrl(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryRecord value5(String value) {
        setFlagUrl(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryRecord values(Short value1, String value2, String value3, String value4, String value5) {
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
     * Create a detached CountryRecord
     */
    public CountryRecord() {
        super(Country.COUNTRY);
    }

    /**
     * Create a detached, initialised CountryRecord
     */
    public CountryRecord(Short id, String code, String name, String profileImageUrl, String flagUrl) {
        super(Country.COUNTRY);

        set(0, id);
        set(1, code);
        set(2, name);
        set(3, profileImageUrl);
        set(4, flagUrl);
    }
}
