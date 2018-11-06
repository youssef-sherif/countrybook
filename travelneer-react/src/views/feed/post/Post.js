import React, { Component } from 'react'

import styles from './Post.scss'
import traveler from '../../../images/traveler.png'

export default class Post extends Component {

    render() {
        return (
            <div className={styles.story}>
                <div className={styles.user}>
                    <img className={styles.avatar} src={traveler} alt='user' />
                    <div className={styles.details}>
                        {this.props.authorName} <span>{this.props.authorEmail}</span>
                    </div>
                </div>
                <div className={styles.timeStamp}>
                        {this.props.timeStamp}
                    </div>
                <blockquote className={styles.content}>
                    {this.props.content}
                </blockquote>
                <div className={`container ${styles.actions}`}>
                    <i className={`col-sm-6 col-xs-6 col-lg-6 col-md-6 glyphicon glyphicon-comment ${styles.icon}`}></i>
                    <i className={`col-sm-6 col-xs-6 col-lg-6 col-md-6 glyphicon glyphicon-star ${styles.icon}`}></i>
                </div>
            </div>
        )
    }
}

