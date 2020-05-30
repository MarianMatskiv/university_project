import React from 'react';
import { Query } from "react-apollo";
import gql from "graphql-tag";

import Article from './Article';

const Articles = () => (
  <Query
    query={gql`
      {
        getAllArticles {
          id
          title
          author
          description
          date
          liked
        }
      }
    `}
  >
    {({ loading, error, data }) => {
      if (loading) return <p>Loading...</p>;
      if (error) return <p>Error :(</p>;
      return data.getAllArticles.map((currentArticle) => (
    <Article article={currentArticle} />
));
    }}
  </Query>
);
export default Articles;