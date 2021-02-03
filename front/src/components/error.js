import React, { Component } from 'react'

class Error extends Component {
    render() {
        return (
            <div>
                <h1> Error Occured</h1>
                // TODO: react router 사용하기
                <a href="/">돌아가기</a>
            </div>
        )
    }
}

export default Error