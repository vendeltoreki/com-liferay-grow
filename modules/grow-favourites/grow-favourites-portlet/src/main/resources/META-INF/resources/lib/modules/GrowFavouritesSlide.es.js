import React from 'react';
import GrowIcon from "./GrowIcon.es";
import GrowFavouritesCard from './GrowFavouritesCard.es';

class GrowFavouritesSlide extends React.Component {
  
  constructor(props) {
    super(props);
	
	this.state = 
	{
		spritemap: this.props.spritemap
	};
  }

  render() {
	  
    return (
		<div className="grow-favourites-slide">
			
			{this.props.data.map((cardData, key) => 
				<div className="row" key={"row-"+key}>
					<div className="col-sm" key={"col-"+key}>
						<GrowFavouritesSlide
							key={key}
							spritemap={this.state.spritemap}
							articleAuthor={cardData.articleAuthor}
							articleAuthorAvatar={cardData.authorAvatar}
							articleCreateDate={cardData.createDate}
							articleTitle={cardData.articleTitle}
							articleCategory={cardData.articleCategory}
						/>
					</div>
				</div>
			)}
			
		</div>
    )
  }

}

export default GrowFavouritesSlide;