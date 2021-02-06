import React from 'react'

import Header from "./components/Header";
import Body from "./components/Body";

class App extends React.Component {
  render() {
    const headerStyle = {
      height: "25px",
      margin: 0
    }
    const bodyStyle = {
      height: "90vh",
      margin: 0,
    }
    return (
      <div className="App">
        <div style={headerStyle}>
          <Header/>
        </div>
        <div style={bodyStyle}>
          <Body/>
        </div>
      </div>
    )
  }
}

export default App;

