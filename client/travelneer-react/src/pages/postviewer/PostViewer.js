import React, { Component } from 'react'
import AppNavbar from '../../components/appnavbar/AppNavbar'
import { connect } from 'react-redux'
import traveler from '../../images/traveler.png'
import FavouritesButton from '../../components/post/FavouritesButton'

import styles from './PostViewer.scss'

import { fetchPostInfo } from '../../actions/postInfoActions'
import { favouritePost } from '../../actions/postsActions'


class PostViewer extends Component {

    componentDidMount() {        
        this.props.fetchPostInfo(this.props.match.params.postId)
    }

    render() {

        return (
           <div>
               <AppNavbar />
               <br /><br /><br />                  
                <div className={`container ${styles.div} ${styles.story}`}>
                <div className={styles.user}>
                    <img className={styles.avatar} src={traveler} alt='user' />
                    <div className={styles.details}>
                        {this.props.name} 
                        <span> 
                            {` ${this.props.email}`}
                        </span>
                        <span>
                            <div className={styles.timeStamp}>
                                {`${this.props.timeDiff} ago`}
                            </div>
                        </span>
                    </div>
                </div>
                <blockquote className={styles.content}>
                    {this.props.content}
                </blockquote>
                <div className={`container ${styles.actions}`}>
                    <i className={`col-sm-3 col-xs-3 col-lg-3 col-md-3 glyphicon glyphicon-comment ${styles.commentIcon}`}></i>                
                    <div className={`col-sm-3 col-xs-3 col-lg-3 col-md-3`}>{this.props.commentsCount}</div>
                    <div className={`col-sm-3 col-xs-3 col-lg-3 col-md-3`}>
                        <FavouritesButton
                            styles={styles}
                            isFavourite={this.props.favourite}
                            favourite={this.props.favouritePost.bind(this)}    
                            resource={this.props.favouritesResource}
                            loading={this.props.favouritePostLoading}
                            />
                    </div>
                    <div className={`col-sm-3 col-xs-3 col-lg-3 col-md-3`}>{this.props.favouritesCount}</div>
                </div>               
                </div> 
            </div>

        )
    }

}


const mapStateToProps = (state) => ({
    comments: state.postInfo.comments,
    content: state.postInfo.content,
    name: state.postInfo.name,
    email: state.postInfo.email,
    timeDiff: state.postInfo.timeDiff,
    favourite: state.postInfo.favourite,
    favouritesCount: state.postInfo.favouritesCount,
    commentsCount: state.postInfo.commentsCount,
    favouritesResource: state.postInfo.favouritesResource,
    favouritePostLoading: state.posts.favouriteLoading    
})

const mapDispatchToProps = (dispatch) => ({
    favouritePost: (resource, method) => dispatch(favouritePost(resource, method)),
    fetchPostInfo: (postId) => dispatch(fetchPostInfo(postId))
})

export default connect(mapStateToProps, mapDispatchToProps)(PostViewer)
