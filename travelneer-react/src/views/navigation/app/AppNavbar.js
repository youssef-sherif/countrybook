import React, { Component } from 'react'
import { connect } from 'react-redux'
import { push } from 'react-router-redux'

import globe from '../../../images/globe.png'
import notifications from '../../../images/notifications.png'
import profile from '../../../images/profile.png'
import posts from '../../../images/posts.png'
import styles from './AppNavbar.scss'
import logo from '../../../images/logo.png'


class AppNavbar extends Component {

    render() {
        return (
            <div className={`navbar navbar-fixed-top ${styles.navbar}`}>
                <div className="container">
                    <div className={`btn col-md-2 col-l-2 col-xl-2 ${styles.logo}`}>
                        <img alt='logo' src={logo} />
                    </div>
                    <div className={`btn col-sm-2 col-xs-3 col-md-2 col-l-2 col-xl-2`}>
                        <img className={styles.img} alt='profile' src={profile} />
                    </div>
                    <div className={`btn col-sm-2 col-xs-3 col-md-2 col-l-2 col-xl-2`}>
                        <img className={styles.img} alt='bags' src={notifications} />
                    </div>
                    <div className={`btn col-sm-2 col-xs-3 col-md-2 col-l-2 col-xl-2`}
                        onClick={() => { this.props.navigateTo('/countries') }}>
                        <img className={styles.img} alt='globe' src={globe} />
                    </div>
                    <div className={`btn col-sm-2 col-xs-3 col-md-2 col-l-2 col-xl-2`}
                        onClick={() => { this.props.navigateTo('/feed') }}>
                        <img className={styles.img} alt='tlogs' src={posts} />
                    </div>
                </div>
            </div>
        )
    }
}
const mapDispatchToProps = (dispatch) => ({
    navigateTo: (e) => dispatch(push(e))
  })
  
  export default connect(null, mapDispatchToProps)(AppNavbar)