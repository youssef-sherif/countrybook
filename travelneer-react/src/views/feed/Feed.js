import React, { Component } from 'react'
import { connect } from 'react-redux'
import { push } from 'react-router-redux'
import PostList from './postlist/PostList'
import AppNavbar from '../navigation/app/AppNavbar'

import new_post from '../../images/new_post.png'
import styles from './Feed.scss'
import NewPost from '../newpost/NewPost';


class Feed extends Component {

    render() {
        return (
            <div>
                <AppNavbar />
                <br /><br /><br />
                <div className={styles.div}>
                    <NewPost />
                    <br />
                    <PostList feed={true}/>
                </div>
                <img className={`btn ${styles.img}`}
                    alt='new post'
                    src={new_post}
                    onClick={() => this.props.navigateTo('/new')} />
            </div>
        )
    }
}

const mapStateToProps = (state) => ({

})


const mapDispatchToProps = (dispatch) => ({
    navigateTo: (path) => dispatch(push(path))
})

export default connect(mapStateToProps, mapDispatchToProps)(Feed)
