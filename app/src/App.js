import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import GroupList from './GroupList';
import GroupEdit from './GroupEdit';
import TestLoad from './TestLoad';

/*   "homepage" : "http://localhost:8080/demo/",
 */
/*basename={'/demo'}
* <Router basename={`${process.env.PUBLIC_URL}`}>
* */



/*
<Router basename={`${process.env.PUBLIC_URL}`}>
          <Switch>
            <Route path={`${process.env.PUBLIC_URL}/`}  exact={true} component={Home}/>
            <Route path={`${process.env.PUBLIC_URL}/groups`}  exact={true} component={GroupList}/>
            <Route path={`${process.env.PUBLIC_URL}/groups/:id`} component={GroupEdit}/>
            <Route path={`${process.env.PUBLIC_URL}/testload`} exact={true} component={TestLoad}/>
          </Switch>
        </Router>

         <Router basename={`${process.env.PUBLIC_URL}`}>
          <Switch>
            <Route path="/"  exact={true} component={Home}/>
            <Route path= "/groups" exact={true} component={GroupList}/>
            <Route path= "/groups/:id" component={GroupEdit}/>
            <Route path= "/testload" exact={true} component={TestLoad}/>
          </Switch>
        </Router>
 */

class App extends Component {
  render() {
    return (
        <Router basename={`${process.env.PUBLIC_URL}`} >
            <Switch>
                <Route path="/" exact={true} component={Home}/>
                <Route path= "/groups"  exact={true} component={GroupList}/>
                <Route path= "/groups/:id" exact={true} component={GroupEdit}/>
                <Route path= "/testload"  exact={true} component={TestLoad}/>
            </Switch>
        </Router>
    )
  }
}

export default App;