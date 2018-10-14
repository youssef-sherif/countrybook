import React, { Component } from 'react'
import Login from '../../home/login/Login'

import logo from '../logo.png'
import styles from './HomeNavbar.scss'


export default class HomeNavbar extends Component {

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