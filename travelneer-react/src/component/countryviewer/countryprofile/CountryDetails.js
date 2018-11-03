import React, { Component } from 'react'
import styles from './CountryDetails.scss'
import loading from '../../../images/loading.gif'


class CountryDetails extends Component {

    getPostsCount() {
        if (this.props.isLoading)
            return (<img alt='loading' src={loading} />)

            
        if (this.props.successful)
            return (
                <p>
                    {this.props.postsCount} posts
                </p>
            )
    }


    render() {
        return (
            <div className={styles.div} >
                <p>
                    {this.props.countryName}
                </p>
                <p>
                    {/* {this.props.followersCount} followers */}
                </p>
                    {this.getPostsCount()}

            </div>
        )
    }
}

export default CountryDetails