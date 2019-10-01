import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './App.css';
import Home from './components/Home';
import GroupList from './components/GroupList';
import GroupEdit from './components/GroupEdit';
import TestLoad from './components/TestLoad';

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

        <Route path= "/groups"  exact component={GroupList}/>
                <Route path= "/groups/:id"  exact component={GroupEdit}/>
 */

class App extends Component {
  render() {
    return (
        <Router basename={"/demo"}>
            <Switch>
                <Route path="/" exact component={Home}/>
                <Route path= "/groups"  exact component={GroupList}/>
                <Route path= "/groups/:id"  exact component={GroupEdit}/>

                <Route path= "/testload"  exact component={TestLoad}/>
            </Switch>
        </Router>
    )
  }
}

export default App;