import React, { Component } from 'react'

import styles from './Post.scss'
import { favouritePost } from '../../../actions/favouritesActions'
import { connect } from 'react-redux'
import traveler from '../../../images/traveler.png'
import FavouritesButton from './FavouritesButton';

class Post extends Component {

    render() {
        return (
            <div className={styles.story}>
                <div className={styles.user}>
                    <img className={styles.avatar} src={traveler} alt='user' />
                    <div className={styles.details}>
                        {this.props.name} <span>{this.props.email}</span>
                    </div>
                </div>
                <div className={styles.timeStamp}>
                        {`${this.props.timeDiff} ago`}
                </div>
                <blockquote className={styles.content}>
                    {this.props.content}
                </blockquote>
                <div className={`container ${styles.actions}`}>
                    <i className={`col-sm-6 col-xs-6 col-lg-6 col-md-6 glyphicon glyphicon-comment ${styles.icon}`}></i>
                    <FavouritesButton
                        styles={styles}
                        successful={this.props.favouritePostSuccessful}
                        isFavourite={this.props.isFavourite}
                        favourite={this.props.favouritePost.bind(this)}    
                        resource={this.props.favouritesResource}
                        loading={this.props.favouritePostLoading}
                        />
                </div>
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    favouritePostLoading: state.favourites.loading,
    favouritePostSuccessful: state.favourites.successful,
})

const mapDispatchToProps = (dispatch) => ({
    favouritePost: (resource, method) => dispatch(favouritePost(resource, method))
})
export default connect(mapStateToProps, mapDispatchToProps)(Post)

