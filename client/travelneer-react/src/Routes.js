import React, { Component } from 'react'

import Home from './pages/home/Home'
import Feed from './pages/feed/Feed'
import Countries from './pages/countries/Countries'
import CountryViewer from './pages/countryviewer/CountryViewer'
import UserViewer from './pages/userviewer/UserViewer'
import PostViewer from './pages/postviewer/PostViewer';
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
                    <Route path="/me" exact render={(props) => <UserViewer {...props} />} />
                    <Route path="/post/:postId" exact render={(props) => <PostViewer {...props} />} />
                </Switch>            
                )
    }
}