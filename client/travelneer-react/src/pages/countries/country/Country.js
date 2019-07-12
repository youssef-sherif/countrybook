import React, { Component } from 'react'

import styles from './Country.scss'

export default class Country extends Component {

    render() {
        return (
            <div className={styles.div}>
                <img alt={this.props.country.name} className={`btn`}
                    onClick={() => {
                        this.props.navigateTo({
                            pathname: `/c/${this.props.country.code}`,
                            fetch: true 
                            })}}
                    src={this.props.flagURL} />
            </div >
        )
    }
}
