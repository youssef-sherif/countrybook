import React, { Component } from 'react'
import { connect } from 'react-redux'
import { push } from 'connected-react-router'
import { writePost, clearArea } from '../../actions/newPostActions'
import styles from './PostArea.scss'


class PostArea extends Component {

    componentDidMount() {
        this.props.clearArea();
    }

    render() {        
        return (
            <div className={`container ${styles.div}`}>
                <textarea className={`${styles.textarea}`}
                    placeholder={`What's happening in ${this.props.countryName}`}
                    id="" cols="20"
                    rows="10"
                    maxLength='500'
                    onChange={(e) => this.props.writePost(e.target.value)} />                

                <div className={styles.buttonsDiv}>
                    {
                        this.props.content.length === 0 || this.props.countryName === "(choose a country)" ?
                            <button className={`btn ${styles.disabledButton}`}>
                                write something and select a country
                            </button>
                            :
                            <button className={`btn ${styles.button}`}
                                onClick={(e) => {
                                    this.props.newPost(this.props.countryCode, this.props.content, this.props.refresh);
                                    this.props.clearArea();
                                }}>
                                post
                        </button>
                    }
                </div>
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