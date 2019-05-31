import React from 'react';
import GrowIcon from "./GrowIcon.es";
import GrowFavouritesCard from './GrowFavouritesCard.es';

class GrowFavouritesSlide extends React.Component {
  
  constructor(props) {
    super(props);
	
	this.state = 
	{
		data: this.props.data,
		slideIndex: this.props.slideIndex,
		spritemap: this.props.spritemap
	};
  }

  render() {
	  
    return (
		<div className="grow-favourites-slide">
			
			{this.state.data.map((cardData, key) => 
				<div className="row" key={"row-"+this.state.slideIndex+"-"+key}>
					<div className="col-sm" key={"col-"+this.state.slideIndex+"-"+key}>
						<GrowFavouritesCard
							key={"growfavouritescard-"+this.state.slideIndex+"-"+key}
							spritemap={this.state.spritemap}
							articleAuthor={cardData.articleAuthor}
							articleAuthorAvatar={cardData.authorAvatar}
							articleCreateDate={cardData.createDate}
							articleTitle={cardData.articleTitle}
							articleCategory={cardData.articleCategory}
							star={true}
						/>
					</div>
				</div>
			)}
			
		</div>
    )
  }

}

export default GrowFavouritesSlide;