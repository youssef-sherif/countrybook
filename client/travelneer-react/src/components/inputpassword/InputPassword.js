import React, { Component } from 'react'

import styles from './InputPassword.scss'
import loading from '../../images/loading.gif'

import strongPassword from '../../images/strong-password.png'
import mediumPassword from '../../images/medium-password.png'
import invalid from '../../images/invalid.png'

const STRONG_PASSWORD = 1
const MEDIUM_PASSWORD = 2
const INVALID_PASSWORD = 3

export default class InputPassword extends Component {

  getMessage() {
    if (this.props.isLoading) {
      return (<img alt="loading" src={loading} />)
    }
    if (this.props.passwordStrength === STRONG_PASSWORD) {
      return (
        <div className={styles.div}>
          <img alt="valid" src={strongPassword} className={styles.img} />
          <span className={styles.greenMessage}>Password Strength: Strong</span>
        </div>
      )
    }
    else if (this.props.passwordStrength === MEDIUM_PASSWORD) {
      return (
        <div>
          <img alt="valid" src={mediumPassword} className={styles.img} />
          <span className={styles.yellowMessage}>Password Strength: Medium</span>
        </div>
      )
    }
    else if (this.props.passwordStrength === INVALID_PASSWORD) {
      return (
        <div className={styles.div}>
          <img alt="valid" src={invalid}
            className={styles.img} />
          <span className={styles.redMessage}> Invalid Password </span>
        </div>
      )
    }
  }

  render() {
    const message = this.getMessage();
    return (
      <div>
        <input type="password" className={styles.input} placeholder="password"
          onChange={e => this.props.validate(e.target.value)} />
        <br />
        {this.props.data === null ? <br /> : message}
      </div>
    )
  }
}
