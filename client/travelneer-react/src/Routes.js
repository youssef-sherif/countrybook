import React, { Component } from 'react'

import Home from './pages/home/Home'
import Feed from './pages/feed/Feed'
import Countries from './pages/countries/Countries'
import CountryViewer from './pages/countryviewer/CountryViewer'
import UserViewer from './pages/userviewer/UserViewer'
import PostViewer from './pages/postviewer/PostViewer'
import ForgotPassword from './pages/forgotpassword/ForgotPassword'
import { Route, Switch } from 'react-router-dom'

export default class Routes extends Component {

    render() {
        return (
            <Switch>
                <Route path="/" exact component={Home} />
                <Route path="/feed" exact component={Feed} />
                <Route path="/profile" exact render={(props) => <UserViewer {...props} />} />
                <Route path="/countries" exact render={(props) => <Countries {...props} />} />                
                <Route path="/c/:countryCode" exact render={(props) => <CountryViewer {...props} compose={false} />} />
                <Route path="/c/:countryCode/new" exact render={(props) => <CountryViewer {...props} compose={true} />} />
                <Route path="/c/:countryCode/posts/:postId" exact render={(props) => <PostViewer {...props} commentView={false} />} />
                <Route path="/c/:countryCode/posts/:postId/threads/:commentId" exact render={(props) => <PostViewer {...props} commentView={true} />} />
                <Route path="/reset-password" exact render={(props) => <ForgotPassword />} />
            </Switch>
        )
    }
}