import React, { Component } from 'react'

import globe from '../../images/globe.png'
import notifications from '../../images/notifications.png'
import profile from '../../images/profile.png'
import posts from '../../images/posts.png'
import styles from './AppNavbar.scss'
import logo from '../../images/logo.png'
import { Link } from 'react-router-dom'


export default class AppNavbar extends Component {

    render() {
        return (
            <div className={`navbar navbar-fixed-top ${styles.navbar}`}>
                <div className="container">
                    <div className={`btn col-md-2 col-l-2 col-xl-2 ${styles.logo}`}>
                        <img alt='logo' src={logo} />
                    </div>
                    <Link to="/profile">
                        <div onClick={() => window.scroll(0, 0)} className={`btn col-sm-2 col-xs-3 col-md-2 col-l-2 col-xl-2`} >
                            <img className={styles.img} alt='profile' src={profile} />
                        </div>
                    </Link>
                    <Link to="/notifications">
                        <div onClick={() => window.scroll(0, 0)} className={`btn col-sm-2 col-xs-3 col-md-2 col-l-2 col-xl-2`}>
                            <img className={styles.img} alt='bags' src={notifications} />
                        </div>
                    </Link>
                    <Link to="/countries">
                        <div onClick={() => window.scroll(0, 0)} className={`btn col-sm-2 col-xs-3 col-md-2 col-l-2 col-xl-2`} >
                            <img className={styles.img} alt='globe' src={globe} />
                        </div>
                    </Link>
                    <Link to="/feed">
                        <div onClick={() => window.scroll(0, 0)} className={`btn col-sm-2 col-xs-3 col-md-2 col-l-2 col-xl-2`} >
                            <img className={styles.img} alt='feed' src={posts} />
                        </div>
                    </Link>
                </div>
            </div>
        )
    }
}