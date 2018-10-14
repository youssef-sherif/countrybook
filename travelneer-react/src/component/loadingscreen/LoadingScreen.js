import React, { Component } from 'react'    
import blue_paper_plane from '../blue_paper_plane.png'
import styles from './LoadingScreen.scss'

export default class LoadingScreen extends Component {


    render() {

        return (
            <div>                
                <img alt='loading' className={styles.img} src={blue_paper_plane}/>               
            </div>
        )
    }
}


