import React from 'react'
import About from "./components/About";
import Admin from "./components/Admin";
import Home from "./components/Home";
import Signin from "./components/Signin";
import Signup from "./components/Signup";

class App extends React.Component {
    render() {
        return (
            <div className="App">
                <h1>App.js</h1>
                <About/>
                <Admin/>
                <Home/>
                <Signin/>
                <Signup/>
            </div>
        )
    }
}

export default App;

