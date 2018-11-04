import React, { Component } from 'react'
import { connect } from 'react-redux'
import { push } from 'react-router-redux'
import { fetchCountries, fetchFollowedCountries, searchCountries } from '../../actions/countryActions'

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
                <CountryList countries={this.props.countries}
                    navigateTo={this.props.navigateTo}
                    loading={this.props.loading}
                    successful={this.props.successful} />
            </div>)
    }

    getFollowedCountries = () => {
        return (
            <div>
                <p>Countries you are following</p>
                <br />
                <CountryList countries={this.props.countries}
                    navigateTo={this.props.navigateTo}
                    loading={this.props.loading}
                    successful={this.props.successful} />
            </div>)
    }

    render() {
        const followedCountries = this.getFollowedCountries()
        const searchedCountries = this.getSearchedCountries()
        return (
            <div>
                <AppNavbar />
                <br /><br /><br />
                <div className={styles.div}>
                    <button className={styles.button}
                        onClick={() => this.props.navigateTo('/my_countries')}>
                        following
                    </button>
                    <button className={styles.button}
                        onClick={() => this.props.navigateTo('/search_countries')}>
                        search
                    </button>
                </div>
                <br />
                <br />
                <div className={`container`}>
                    {this.props.search ?
                       searchedCountries : followedCountries}
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
