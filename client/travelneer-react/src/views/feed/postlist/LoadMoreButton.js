import React, { Component } from 'react'
import loadMore from '../../../images/load_more.png'
import loading from '../../../images/loading.gif'
import { connect } from 'react-redux'


class LoadMoreButton extends Component {

    render() {

    if (this.props.loading)
        return (<img alt='loading' src={loading} />)

        
    else
        return (<img alt='load more' src={loadMore}/>)
    }
}

const mapStateToProps = (state) => ({
})


const mapDispatchToProps = (dispatch) => ({
})


export default connect(mapStateToProps, mapDispatchToProps)(LoadMoreButton)

