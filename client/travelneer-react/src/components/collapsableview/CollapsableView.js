import React, { Component } from 'react'

import styles from './CollapsableView.scss'
import close from '../../images/close-button.png'

export default class CollapsableView extends Component {

    render() {

        return (
            <div className={`container ${this.props.visible ?
                styles.childrenVisible : styles.childrenInvisible}`}>
                <br /><br /><br />
                <img src={close} 
                    alt='close button' 
                    className={styles.img} 
                    onClick={() => {
                        this.props.showCollapsableArea(false);
                    }}
                />
                <br />
                {this.props.children}
            </div>
        );
    }
}