import React, { Component } from 'react'
import { connect } from 'react-redux'
import { push } from 'connected-react-router'

import globe from '../../images/globe.png'
import notifications from '../../images/notifications.png'
import profile from '../../images/profile.png'
import posts from '../../images/posts.png'
import styles from './AppNavbar.scss'
import logo from '../../images/logo.png'
import { authorizeUser } from '../../actions/authActions'


class AppNavbar extends Component {

    componentDidMount() {
        this.props.authorizeUser()
    }

    render() {
        return (
            <div className={`navbar navbar-fixed-top ${styles.navbar}`}>
                <div className="container">               
                    <div className={`btn col-md-2 col-l-2 col-xl-2 ${styles.logo}`}>
                        <img alt='logo' src={logo} />
                    </div>
                    <div className={`btn col-sm-2 col-xs-3 col-md-2 col-l-2 col-xl-2`} 
                        onClick={() => { 
                            this.props.navigateTo('/profile');
                            window.scroll(0, 0) 
                            } } >
                        <img className={styles.img} alt='profile' src={profile} />
                    </div>
                    <div className={`btn col-sm-2 col-xs-3 col-md-2 col-l-2 col-xl-2`}>
                        <img className={styles.img} alt='bags' src={notifications} />
                    </div>
                    <div className={`btn col-sm-2 col-xs-3 col-md-2 col-l-2 col-xl-2`} 
                        onClick={() => { 
                            this.props.navigateTo('/countries');
                            window.scroll(0, 0) 
                            } } >
                        <img className={styles.img} alt='globe' src={globe} />
                    </div>
                    <div className={`btn col-sm-2 col-xs-3 col-md-2 col-l-2 col-xl-2`} 
                        onClick={() => { 
                            this.props.navigateTo('/feed');
                            window.scroll(0, 0) 
                            } } >
                        <img className={styles.img} alt='feed' src={posts} />
                    </div>
                </div>
            </div>
        )
    }
}
const mapDispatchToProps = (dispatch) => ({
    authorizeUser: () => dispatch(authorizeUser()),
    navigateTo: (location) => dispatch(push(location))
})
  

  const mapStateToProps = (state) => {
    return {
        authSuccessful: state.auth.successful,
        authLoading: state.auth.loading
    }
  }

export default connect(mapStateToProps, mapDispatchToProps)(AppNavbar)