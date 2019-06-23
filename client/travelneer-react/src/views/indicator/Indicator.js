import React, { Component } from 'react'
import loading from '../../images/loading.gif'

import styles from './Indicator.scss'

class Indicator extends Component {

    render() {
        const errorMessage = this.props.errorMessage

        if(this.props.loading)
            return <img alt='new post loading' src={loading} />
        else
            if(this.props.successful) 
                return (                  
                    <div className={styles.successDiv}> 
                        <button className={`btn`}>success</button>
                    </div>)
            else if(this.props.error)
                return (
                    <div className={styles.errorDiv}>
                        <button className={`btn`}>
                            {errorMessage.toString()}
                        </button>                  
                    </div>
                )
            else
                return (<div/>)
                
    }
}

export default Indicator
