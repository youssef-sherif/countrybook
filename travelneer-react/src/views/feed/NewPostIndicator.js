import React, { Component } from 'react'
import loading from '../../images/loading.gif'


class NewPostIndicator extends Component {

    render() {
        const errorMessage = this.props.errorMessage

        if(this.props.loading)
            return <img alt='new post loading' src={loading} />
        else
            if(this.props.successful) 
                return (                  
                    <div className={this.props.styles.newPostSuccessDiv}> 
                        <button className={`btn`}>posted</button>
                    </div>)
            else if(this.props.error)
                return (
                    <div className={this.props.styles.newPostErrorDiv}>
                        <button className={`btn`}>
                            {errorMessage.toString()}
                        </button>                  
                    </div>
                )
            else
                return (<div/>)
                
    }
}

export default NewPostIndicator
