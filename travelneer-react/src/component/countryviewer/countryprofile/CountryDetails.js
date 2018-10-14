import React, { Component } from 'react'
import styles from './CountryDetails.scss'


export default class CountryDetails extends Component {

    render() {
        return (
            <div className={styles.div} >
                <p>
                    {this.props.countryName}
                </p>
                <p>
                    {this.props.followersCount} followers
                </p>
                <p>
                    {this.props.postsCount} posts
                </p>
            </div>
        )
    }
}