import React, { Component } from 'react'

import Country from '../country/Country'
import LoadingScreen from '../../loadingscreen/LoadingScreen'


export default class CountryList extends Component {

    render() {
        if (this.props.loading) {
            return <LoadingScreen />
        }
        return (
            <div>
                {this.props.countries.map((country) => {
                    return <Country flagURL={country.country.flagURL}
                        country={country.country}
                        key={country.country.countryId}
                        navigateTo={this.props.navigateTo} />
                })}
            </div>
        )
    }
}