import React from 'react'

import mediumPassword from '../../../../../images/medium-password.png'
import styles from './MediumPassword.scss'

export default class MediumPassword extends React.Component {

    render() {
        return (
            <div className={styles.div}>
                <img alt="valid" src={mediumPassword} className={styles.img} />
                <span className={styles.yellowMessage}>Password Strength: Medium</span>
            </div>)
    }

}