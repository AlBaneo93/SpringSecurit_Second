import React, { Component } from 'react'
import { Route } from 'react-router-dom'
import Info from './info'
import Signin from './Signin'
import Signup from './Signup'
import Denied from './denied'
import Error from './error'

class Home extends Component {
    render() {
        return (
            <div>
                <h1> Home</h1 >
                {/* path="/이동할 주소", component ={출력할컴포넌트} */}
                <Route path="/info" component={Info} />
                <Route path="/signin" component={Signin} />
                <Route path="/signup" component={Signup} />
                <Route path="/denied" component={Denied} />
                <Route path="/error" component={Error} />
            </div>
        )
    }
}
export default Home