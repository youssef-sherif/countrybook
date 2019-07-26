
import React, { Component } from 'react'


export default class CommentButton extends Component {

    getButton = () => {
        if (localStorage.getItem('logged_in') === 'false') {
            return (
                <button className={`btn ${this.props.styles.disabledButton}`}>
                    Login or Sign up to start commenting
                </button>
            )
        } else if (this.props.content.length === 0) {
            return (
                <button className={`btn ${this.props.styles.disabledButton}`}>
                    write something
                </button>
            )
        } else {
            return (
                <button className={`btn ${this.props.styles.button}`}
                    onClick={(e) => {
                        this.props.newComment(this.props.parentPostId, this.props.parentCommentId, this.props.content);
                        if (this.props.isDirectReplyToPost === false) this.props.closeCommentArea();
                        this.props.clearArea();
                    }}>
                    comment
            </button>
            )
        }
    }

    render() {

        const button = this.getButton()

        return (
            <div className={this.props.styles.buttonsDiv}>
                {button}
            </div>
        )
    }
}