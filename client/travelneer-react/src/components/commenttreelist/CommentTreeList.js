import React, { Component } from 'react'
import { connect } from 'react-redux'

import styles from './CommentTreeList.scss'
import LoadMoreButton from '../postlist/LoadMoreButton'
import LoadingScreen from '../loadingscreen/LoadingScreen'

import { fetchComments } from '../../actions/postInfoActions'

class CommentTreeList extends Component {

    render() {

        if (this.props.successful)
            return (
                <div className={`container ${styles.div}`}>

                    {this.props.children}

                    {this.props.children.length > 10 ?
                        <LoadMoreButton
                            loadMore={this.props.loadMoreComments.bind(this)}
                            nextPostsResource={this.props.nextCommentsResource} />
                        : <div />
                    }
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

export default connect(mapStateToProps, mapDispatchToProps)(CommentTreeList)