import React, { Component } from 'react'
import SignUpForm from './signup/SignUpForm'
import HomeNavbar from '../navigation/home/HomeNavbar'
import styles from './Home.scss'
import img from './home.jpg'

export default class Home extends Component {

    render() {

        return (
            <div>
                <HomeNavbar />                
                <div className={styles.header}>
                    <img className={styles.img} src={img} alt="Home Img" />
                    <div>
                        <div className={styles.introduction}>
                            <p className={styles.largeText}>
                                Travelneer
                            </p>
                            <ul className={styles.mediumText}>
                                <li> Get the latest news from the 4 corners of the world </li>
                            </ul>
                        </div>
                        <SignUpForm />
                    </div>
                </div>
            </div>
        )
    }
}