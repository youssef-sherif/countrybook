import React, { Component } from 'react'
import PostList from '../../components/postlist/PostList'
import { connect } from 'react-redux'
import { fetchUserInfo } from '../../actions/authActions'
import UserProfile from './userprofile/UserProfile'
import PrivateRoute from '../../routes/PrivateRoute'

class UserViewer extends Component {

    componentDidMount() {        
        if(this.props.backButtonPressed === true) {
            window.scrollTo(0, this.props.scrollPositionY)
        } else {
            this.props.fetchUserInfo()
        }                
    }

    getPostsDiv = () => {
        return (
            <div>
                <PostList 
                    fromProfile={true}
                    originalPath={"/profile"}
                />
            </div>)
    }

    render() {
        const postsDiv = this.getPostsDiv()

        return (
            <PrivateRoute>
                <br />
                
                <UserProfile />

                <br />
                
                {postsDiv}

            </PrivateRoute>
        )
    }

}


const mapStateToProps = (state) => ({
    backButtonPressed: state.posts.backButtonPressed,
    scrollPositionY: state.posts.scrollPositionY
})

const mapDispatchToProps = (dispatch) => ({
    fetchUserInfo : () => dispatch(fetchUserInfo())
})

export default connect(mapStateToProps, mapDispatchToProps)(UserViewer)
