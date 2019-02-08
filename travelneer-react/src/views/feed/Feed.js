import React, { Component } from 'react'
import { connect } from 'react-redux'
import { push } from 'react-router-redux'
import PostList from './postlist/PostList'
import AppNavbar from '../navigation/app/AppNavbar'

import new_post from '../../images/new_post.png'
import styles from './Feed.scss'
import NewPost from '../newpost/NewPost';
import { showNew } from '../../actions/newPostActions';


class Feed extends Component {

    render() {

        return (
            <div>
                <AppNavbar />
                <br />
                <div className={this.props.showNewState? styles.NewPostShow : styles.NewPostHide}>
                    <br/>
                    <br/>
                    <br/>
                    <NewPost />
                </div>

                <div className={styles.div}>
                    <br/>
                    <br/> 
                    <br/> 
                    {this.props.showNewState?
                        <div />
                            :
                        <NewPost />
                    }
                    <br />
                    <PostList feed={true}/>
                </div>

                <img className={`btn ${styles.newPostImg}`}
                    alt='new post'
                    src={new_post}
                    onClick={() => {
                        this.props.showNew(!this.props.showNewState)
                    }} />
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    showNewState: state.newPost.showNew
})


const mapDispatchToProps = (dispatch) => ({
    navigateTo: (path) => dispatch(push(path)),
    showNew: (showNewState) => dispatch(showNew(showNewState))
})

export default connect(mapStateToProps, mapDispatchToProps)(Feed)
