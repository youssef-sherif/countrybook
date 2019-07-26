import React, { Component } from 'react'
import { connect } from 'react-redux'
import { push } from 'connected-react-router'
import { writePost, clearArea } from '../../actions/newPostActions'
import styles from './PostArea.scss'
import PostButton from './PostButton'


class PostArea extends Component {

    componentDidMount() {
        this.props.clearArea();
    }

    render() {
        return (
            <div className={`container ${styles.div}`}>

                <textarea
                    className={`${styles.textarea}`}
                    placeholder={`What's happening in ${this.props.countryName}`}
                    id="" cols="20"
                    rows="10"
                    maxLength='500'
                    onChange={(e) => this.props.writePost(e.target.value)}
                />

                <PostButton
                    styles={styles}
                    newPost={this.props.newPost.bind(this)}
                    clearArea={this.props.clearArea.bind(this)}
                    countryCode={this.props.countryCode}
                    countryName={this.props.countryName}
                    refresh={this.props.refresh}
                    content={this.props.content}
                />

            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    content: state.newPost.content,
    newPostSuccessful: state.newPost.successful,
    newPostLoading: state.newPost.loading,
    newPostError: state.newPost.error
})

const mapDispatchToProps = (dispatch) => ({
    writePost: (content) => dispatch(writePost(content)),
    clearArea: () => dispatch(clearArea()),
    navigateTo: (path) => dispatch(push(path)),
})

export default connect(mapStateToProps, mapDispatchToProps)(PostArea)