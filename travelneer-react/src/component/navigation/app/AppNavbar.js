import React, { Component } from 'react'
import { connect } from 'react-redux'
import { push } from 'react-router-redux'

import globe from './globe.png'
import notifications from './notifications.png'
import profile from './profile.png'
import posts from './posts.png'
import styles from './AppNavbar.scss'
import logo from '../logo.png'


class AppNavbar extends Component {

    componentWillMount() {
        setTimeout(() => {
            if (localStorage.getItem('logged_in') === 'false')
                this.props.navigateTo('/')
        }, 1000)
    }

    render() {
        return (
            <div className={`navbar navbar-fixed-top ${styles.coloredNavbar}`}>
                <div className="container">
                    <div className={`btn col-md-2 col-l-2 col-xl-2 ${styles.logo}`}>
                        <img alt='logo' src={logo} />
                    </div>
                    <div className={`btn col-sm-2 col-xs-3 col-md-2 col-l-2 col-xl-2 ${styles.option}`}>
                        <img className={styles.img} alt='profile' src={profile} />
                    </div>
                    <div className={`btn col-sm-2 col-xs-3 col-md-2 col-l-2 col-xl-2 ${styles.option}`}>
                        <img className={styles.img} alt='bags' src={notifications} />
                    </div>
                    <div className={`btn col-sm-2 col-xs-3 col-md-2 col-l-2 col-xl-2 ${styles.option}`}
                        onClick={() => { this.props.navigateTo('/my_countries') }}>
                        <img className={styles.img} alt='globe' src={globe} />
                    </div>
                    <div className={`btn col-sm-2 col-xs-3 col-md-2 col-l-2 col-xl-2 ${styles.option}`}
                        onClick={() => { this.props.navigateTo('/feed') }}>
                        <img className={styles.img} alt='tlogs' src={posts} />
                    </div>
                </div>
            </div>
        )
    }
}

const mapDispatchToProps = (dispatch) => ({    
    navigateTo: (path) => dispatch(push(path))
})
export default connect(null, mapDispatchToProps)(AppNavbar)