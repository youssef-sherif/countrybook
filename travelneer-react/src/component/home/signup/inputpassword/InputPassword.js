import React, { Component } from 'react'

import MediumPassword from '../validation/MediumPassword/MediumPassword'
import StrongPassword from '../validation/StrongPassword/StrongPassword'
import InvalidMessage from '../validation/InvalidMessage/InvalidMessage'

import styles from './InputPassword.scss'
import loading from '../../../loading.gif'

const STRONG_PASSWORD = 1
const MEDIUM_PASSWORD = 2
const INVALID_PASSWORD = 3

export default class InputPassword extends Component {

  getMessage() {
	  if(this.props.isLoading) {
			return (<img alt="loading" src={loading} />)
		}
    if(this.props.passwordStrength === STRONG_PASSWORD) {
			return ( <StrongPassword />)
    }
    else if(this.props.passwordStrength === MEDIUM_PASSWORD) {
			return ( <MediumPassword />)
    }
		else if(this.props.passwordStrength === INVALID_PASSWORD) {
			return ( <InvalidMessage />)
		}
  }

  render() {    
    const message = this.getMessage();
    return (
      <div>
        <input type="password" className={styles.input} placeholder="password" onChange={ e => {this.props.validate(e.target.value)} }/>
        <br />
        {this.props.data === null? <br/> : message}
      </div>
      )
  }
}
