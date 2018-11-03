import React, { Component } from 'react'

import invalid from '../../../../../images/invalid.png'

import styles from './InvalidMessage.scss'

export default class InvalidMessage extends Component {

    render() {
        return (
            <div className={styles.div}>
                <img alt="valid" src={invalid}
                    className={styles.img} />
                <span className={styles.redMessage}>
                    Invalid
                </span>
            </div>
        )
    }
}
