import React, { Component } from 'react'

import styles from './InputText.scss'
import loading from '../../images/loading.gif'
import valid from '../../images/valid.png'
import invalid from '../../images/invalid.png'

export default class InputText extends Component {

  getMessage() {
    if (this.props.isLoading) {
      return (
        <img alt="loading" src={loading} />
      )
    }
    if (this.props.isValid) {
      return (
        <div>
          <img alt="valid" src={valid}
            className={styles.img} />
          <span className={styles.greenMessage}>Valid</span>
        </div>
      )
    }
    else {
      return (
        <div>
          <img alt="valid" src={invalid}
            className={styles.img} />
          <span className={styles.redMessage}>
            Invalid
        </span>
        </div>
      )
    }
  }

  render() {
    const message = this.getMessage()
    return (
      <div>
        <input className={styles.input} placeholder={this.props.type}
          onChange={e => this.props.validate(e.target.value)} />
        <br />
        {this.props.data === null ? <br /> : message}
      </div>
    )
  }
}
