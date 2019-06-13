import React, { Component } from 'react'

import Home from './views/home/Home'
import Feed from './views/feed/Feed'
import Countries from './views/countries/Countries'
import CountryViewer from './views/countryviewer/CountryViewer'
import { Route, Switch } from 'react-router-dom'

export default class Routes extends Component {

    render() {
        return (
                <Switch>
                    <Route path="/" exact component={Home} />
                    <Route path="/feed" exact component={Feed} />
                    <Route path="/countries" exact render={(props) => <Countries {...props} search={false} />} />
                    <Route path="/search_countries" render={(props) => <Countries {...props} search={true} />} />                                
                    <Route path="/countries/:countryId" exact render={(props) => <CountryViewer {...props} compose={false} />} />
                    <Route path="/countries/:countryId/new" exact render={(props) => <CountryViewer {...props} compose={true} />} />                
                </Switch>            
                )
    }
}