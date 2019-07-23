import React, { Component } from 'react'

import styles from './PostHeader.scss'

import traveler from '../../images/traveler.png'

export default class PostHeader extends Component {

    render() {
        return (

            <div className={styles.user}>
                <img className={styles.avatar} src={traveler} alt='user' />
                <div className={styles.details}>
                    {this.props.name}                
                    <br />
                    <div className={styles.timeStamp}>
                        {`${this.props.timeDiff} ago  `}
                    </div>

                </div>
            </div>
        )
    }
}
