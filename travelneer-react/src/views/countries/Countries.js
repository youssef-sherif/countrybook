import React, { Component } from 'react'
import { connect } from 'react-redux'
import { push } from 'react-router-redux'
import { fetchCountries, fetchFollowedCountries, searchCountries } from '../../actions/countriesActions'

import AppNavbar from '../navigation/app/AppNavbar'
import CountryList from './countrylist/CountryList'

import styles from './Countries.scss'

class Countries extends Component {

    componentWillMount() {
        this.props.fetchCountries()
    }

    getSearchedCountries = () => {
        return (
            <div>
                <input onChange={(e) =>
                    this.props.searchCountries(this.props.searchResource, e.target.value)}
                    rows='1' placeholder='Search for a country' />
                <br/><br/>
                <CountryList countries={this.props.countries}
                    navigateTo={this.props.navigateTo}
                    loading={this.props.loading}
                    successful={this.props.successful} />
            </div>)
    }

    getCountries = () => {
        return (
            <div>
                <p>Countries</p>
                <br />
                <CountryList countries={this.props.countries}
                    navigateTo={this.props.navigateTo}
                    loading={this.props.loading}
                    successful={this.props.successful} />
            </div>)
    }

    render() {
        const countries = this.getCountries()
        const searchedCountries = this.getSearchedCountries()
        return (
            <div>
                <AppNavbar />
                <br /><br /><br />
                <div className={styles.div}>
                    <button className={`btn ${styles.button}`}
                        onClick={() => this.props.navigateTo('/countries')}>
                        countries
                    </button>
                    <button className={`btn ${styles.button}`}
                        onClick={() => this.props.navigateTo('/search_countries')}>
                        search
                    </button>
                </div>
                <br />
                <br />
                <div className={`container`}>
                    {this.props.search ?
                       searchedCountries : countries}
                </div>
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    countries: state.countries.countries,
    loading: state.countries.loading,
    successful: state.countries.successful,    
    searchResource: state.countries.searchCountriesResource,
    followedResource: state.countries.followedCountriesResource
})

const mapDispatchToProps = (dispatch) => ({
    navigateTo: (e) => dispatch(push(e)),
    fetchCountries: () => dispatch(fetchCountries()),
    fetchFollowedCountries: (resource) => dispatch(fetchFollowedCountries(resource)),
    searchCountries: (resource, param) => dispatch(searchCountries(resource, param))
})

export default connect(mapStateToProps, mapDispatchToProps)(Countries)
