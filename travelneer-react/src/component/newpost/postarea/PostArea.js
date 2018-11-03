import React, { Component } from 'react'
import styles from './PostArea.scss'

export default class PostArea extends Component {

    render() {
        return (
            <div>
                <textarea className={styles.textarea}
                    placeholder="What's happening in - ?"
                    id="" cols="20"
                    rows="10"
                    maxLength='500'
                    onChange={(e) => this.props.writePost(e.target.value)}
                    >
                </textarea>
                <div className={styles.buttonsDiv}>
                    <button className={`btn ${styles.button}`}>
                        post
                    </button>
                    <button className={`btn ${styles.button}`}>
                        upload
                    </button>
                </div>
            </div>
        )
    }
}
