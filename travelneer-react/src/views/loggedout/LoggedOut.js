import React, { Component } from 'react'
import { connect } from 'react-redux'
import { push } from 'react-router-redux'
import styles from './LoggedOut.scss'

class LoggedOut extends Component {

    render() {

        return (
            <div className={styles.div}>
                <p className={styles.message}>You've been logged out successfully</p>           
            </div>
        )
    }

}

const mapDispatchToProps = (dispatch) => ({
    navigateTo: (e) => dispatch(push(e))
  })
  

export default connect(null, mapDispatchToProps)(LoggedOut)