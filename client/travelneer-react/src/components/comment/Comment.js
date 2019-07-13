import React, { Component } from 'react'

import styles from './Comment.scss'
import { connect } from 'react-redux'
import PostHeader from '../postheader/PostHeader';

class Comment extends Component {

    render() {
        return (
            <div className={styles.story}>
                <PostHeader
                    user={this.props.user}
                    email={this.props.email}
                    timeDiff={this.props.timeDiff}
                />

                <blockquote className={styles.content}>
                    {this.props.content}
                </blockquote>

                <div className={`container ${styles.actions}`}>
                    <i className={`col-sm-6 col-xs-6 col-lg-6 col-md-6 glyphicon glyphicon-comment ${styles.commentIcon}`} />
                </div>
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
})

const mapDispatchToProps = (dispatch) => ({

})

export default connect(mapStateToProps, mapDispatchToProps)(Comment)

