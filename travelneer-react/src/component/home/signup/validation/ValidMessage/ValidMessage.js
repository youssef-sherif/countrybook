import React, { Component } from 'react'

import styles from './ValidMessage.scss'
import valid from './valid.png'

export default class ValidMessage extends Component {


    render() {
        return (
            <div className={styles.div}>
                <img alt="valid" src={valid}
                    className={styles.img} />
                <span className={styles.greenMessage}>
                    Valid
                </span>
            </div>
        )
    }
}
