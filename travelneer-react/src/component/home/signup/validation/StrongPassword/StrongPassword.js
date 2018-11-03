import React from 'react'

import styles from './StrongPassword.scss'
import strongPassword from '../../../../../images/strong-password.png'

export default class StrongPassword extends React.Component {

    render() {
        return (
            <div className={styles.div}>
                <img alt="valid" src={strongPassword} className={styles.img} />
                <span className={styles.greenMessage}>Password Strength: Strong</span>
            </div>)
    }

}
