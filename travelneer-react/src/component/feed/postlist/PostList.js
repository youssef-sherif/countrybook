import React, { Component } from 'react'
import Post from '../post/Post'
import LoadingScreen from '../../loadingscreen/LoadingScreen'

import styles from './PostList.scss'

export default class PostList extends Component {

    render() {     
        if (this.props.loading) {
            return <LoadingScreen />
        }   
        return (
            <div className={`${styles.div}`}>
                {this.props.posts.map((post) => {                    
                    return <Post key={post.post.postId}
                        content={post.post.content}
                        authorName={post.post.authorName}
                        authorEmail={post.post.authorEmail}
                        timeStamp={post.post.date} />
                })}
            </div>
        )
    }
}

