import React, { Component } from 'react'
import AppNavbar from '../navigation/app/AppNavbar'
import PostList from '../postlist/PostList'
import { connect } from 'react-redux'
import { fetchUserInfo } from '../../actions/authActions'
import UserProfile from './userprofile/UserProfile';

class UserViewer extends Component {

    componentDidMount() {        
        this.props.fetchUserInfo()
    }

    getPostsDiv = () => {
        return (
            <div>
                <PostList fromProfile={true}/>
            </div>)
    }

    render() {
        const postsDiv = this.getPostsDiv()

        return (
            <div>
                <AppNavbar />
                <br /><br /><br />

                <UserProfile />

                <br /><br />
                
                {postsDiv}

            </div>
        )
    }

}


const mapStateToProps = (state) => ({

})

const mapDispatchToProps = (dispatch) => ({
    fetchUserInfo : () => dispatch(fetchUserInfo())
})

export default connect(mapStateToProps, mapDispatchToProps)(UserViewer)
