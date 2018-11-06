import React, { Component } from 'react'
import Login from '../../home/login/Login'

import logo from '../../../images/logo.png'
import styles from './HomeNavbar.scss'


class HomeNavbar extends Component {

    render() {

        return (
            <nav>
                <div className={`container-fluid`}>
                    <div>
                        <a>
                            <img alt="logo" src={logo} />
                        </a>
                    </div>
                    <ul className={`nav navbar-nav navbar-right`}>
                        <li><a id={`${styles.loginBtn}`} href="/">Login</a></li>
                    </ul>
                    <div className={styles.input} >
                        <Login />
                    </div>
                </div>
            </nav>
        )
    }
}

export default HomeNavbar