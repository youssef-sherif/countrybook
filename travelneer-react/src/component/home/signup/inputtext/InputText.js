import React, { Component } from 'react'
import ValidMessage from '../validation/ValidMessage/ValidMessage'
import InvalidMessage from '../validation/InvalidMessage/InvalidMessage'

import styles from './InputText.scss'
import loading from '../../../loading.gif'

export default class InputText extends Component {

  getMessage() {
    if (this.props.isLoading) {
      return (
        <img alt="loading" src={loading} />
      )
    }
    if (this.props.isValid) {
      return (
        <ValidMessage />
      )
    }
    else {
      return (
        <InvalidMessage />
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
        {this.props.data === null? <br/> : message}
      </div>
    )
  }
}
