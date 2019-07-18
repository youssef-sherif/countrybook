import React, { Component } from 'react'
import { connect } from 'react-redux'

import styles from './CommentTree.scss'
import LoadMoreButton from '../postlist/LoadMoreButton'
import LoadingScreen from '../loadingscreen/LoadingScreen'

import { fetchComments } from '../../actions/postInfoActions'

class CommentTree extends Component {

    render() {

        if (this.props.successful)
            return (
                <div className={`container ${styles.div}`}>

                    {this.props.children}

                    <LoadMoreButton
                        loadMore={this.props.loadMoreComments.bind(this)}
                        nextPostsResource={this.props.nextCommentsResource} />
                </div>
            )
        else
            return (
                <LoadingScreen />
            )
    }
}

const mapStateToProps = (state) => ({
    successful: state.postInfo.successful,
    nextCommentsResource: state.postInfo.nextResource
})

const mapDispatchToProps = (dispatch) => ({
    loadMoreComments: (resource) => dispatch(fetchComments(resource, true)),
})

export default connect(mapStateToProps, mapDispatchToProps)(CommentTree)