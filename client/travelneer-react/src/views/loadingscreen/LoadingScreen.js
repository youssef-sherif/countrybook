import React, { Component } from 'react'    
import loading from '../../images/loading-screen.png'
import styles from './LoadingScreen.scss'

export default class LoadingScreen extends Component {


    render() {

        return (
            <div>                
                <img alt='loading' className={styles.img} src={loading}/>               
            </div>
        )
    }
}


