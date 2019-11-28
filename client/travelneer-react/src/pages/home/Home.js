import React, { Component } from 'react'
import SignUpForm from './signup/SignUpForm'
import styles from './Home.scss'
import img from '../../images/home.jpg'
import { Link } from 'react-router-dom'
import PublicRoute from '../../routes/PublicRoute';

export default class Home extends Component {

    render() {

        return (
            <PublicRoute>
                <div className={styles.header}>
                    <img className={styles.img} src={img} alt="Home Img" />
                    <div>
                        <div className={styles.introduction}>
                            <p className={styles.largeText}>
                                Travelneer
                            </p>
                            <ul className={styles.mediumText}>
                                <li> Get the latest news from the 4 corners of the world </li>
                                <li><Link to="/countries">preview</Link></li>
                            </ul>
                        </div>
                        <SignUpForm />
                    </div>
                </div>
            </PublicRoute>
        )
    }
}