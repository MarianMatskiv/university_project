import gql from "graphql-tag";
import { Mutation } from 'react-apollo'
import React, { Component } from 'react'


const UPDATE_ARTICLE_QUERY = gql`
  mutation UpdateArticle($input: UpdateArticleInput!) {
  updateArticle(input: $input) {
   id
   title
   author
   description
   date
   liked
  }
}`;
 
class Article extends React.Component {

state = {
	props: {}
}

constructor(props) {
	super(props)
	this.state = {
		props: props,
		currentLiked: props.article.liked === null ? false : props.article.liked
	}
}

render() {
	let nextLiked = !this.state.currentLiked;
	const input = { 
		input: {
			id: this.state.props.article.id,
			liked: nextLiked	
		}
	}
	let currentLiked = this.state.currentLiked;
	return (
    <div className="card" style={{'width': '100%', 'marginTop': '10px'}}>
        <div className="card-body">
        <h5 className="card-title">{this.state.props.article.title}</h5>
        <h6 className="card-subtitle mb-2 text-muted">by {this.state.props.article.author}</h6>
        <p className="card-text">{this.state.props.article.description}</p>
        <a href={this.state.props.article.id} className="card-link">Go to article ...</a>
        <div class="text-right">

        <Mutation mutation={UPDATE_ARTICLE_QUERY} variables={ input }>
  		{updateMutation =>
			<input className={ this.state.currentLiked ? "btn btn-success" : "btn btn-primary" } 
			type="button" value={this.state.currentLiked ? "Liked" : "Like"} 
			onClick={() => {
				updateMutation(); 
				this.setState(state => ({props: this.state.props, currentLiked: !this.state.currentLiked }));
				}
			} />
		}
		</Mutation>

        </div>
        </div>
    </div>
);
}

}

export default Article;