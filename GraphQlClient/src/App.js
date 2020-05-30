import ApolloClient from "apollo-boost";
import { ApolloProvider } from "react-apollo";
import React, { Component }  from 'react';

import Articles from './Articles';

const client = new ApolloClient({
  uri: "http://localhost:8080/graphql"
});
     
const App = () => (
  <ApolloProvider client={client}>
    <div className="container">
      <nav className="navbar navbar-dark bg-primary">
        <a className="navbar-brand" href="#">React and GraphQL - Articles Application</a>
      </nav>
      <div>
        <Articles />
      </div>
    </div>
  </ApolloProvider>
);       
export default App;
