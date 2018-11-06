import React, { Component } from 'react'

import styles from './Country.scss'

export default class Country extends Component {

    render() {
        return (
            <div className={styles.div}>
                <img alt='country' className={`btn`}
                    onClick={() => {
                        this.props.navigateTo({
                            pathname: `/countries/${this.props.country.id}`,
                            fetch: true 
                            })}}
                    src={this.props.flagURL} />
            </div >
        )
    }
}
