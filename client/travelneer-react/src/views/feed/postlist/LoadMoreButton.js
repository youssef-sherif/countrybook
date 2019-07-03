import React, { Component } from 'react'
import loadMoreImg from '../../../images/load-more.png'
import loading from '../../../images/loading.gif'


export default class LoadMoreButton extends Component {

    render() {

    if (this.props.loading)
        return (<img alt='loading' src={loading} />)

        
    else
        return (<img 
                alt='load more' src={loadMoreImg}
                onClick={ (e) => {
                    e.preventDefault();
                    this.props.loadMore(this.props.nextPostsResource);
                }}/>)
    }
}

