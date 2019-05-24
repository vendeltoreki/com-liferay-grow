import React from "react";
import ReactDOM from "react-dom";
import { CarouselProvider, Slider, Slide, ButtonBack, ButtonNext } from 'pure-react-carousel';

import GrowFavouritesCard from './modules/GrowFavouritesCard.es';

const spritemap = Liferay.ThemeDisplay.getPathThemeImages();

const GrowFavouriteCardData = {
	articleAuthor: "Gábor Ambrózy",
	authorAvatar: "/o/GrowFavouritesPortlet/images/0.jpeg",
	createDate: "01.01.2019",
	articleTitle: "Respect badge",
	articleContent:
		'A respect badge can be given by anyone to anyone. You can give 1 badge per month. You can use the Respect badge page to add badges. To give a respect badge, @ mention the name in the table, write your name to the From column and write the reason, why do you give it. The reason has to be for a "superpower", something why you respect the other person and something which you would like to learn from them. For Support Hungary 2016Q3 goals, you have to give min. 1 respect badge until Sept 30. If there will be need, we will add gamification to Grow later on.',
	tags: ["badge", "gamification", "respect", "test1", "test2"],
	readCount: "626",
	articleCategory: "Share"
};

class App extends React.Component {
	render() {
		return (
					<CarouselProvider
						naturalSlideWidth={100}
						naturalSlideHeight={125}
						totalSlides={3}
					>
						<Slider>
							<Slide index={0}>
								<div className="col-lg-6">
									<GrowFavouritesCard
										spritemap={spritemap}
										articleAuthor={GrowFavouriteCardData.articleAuthor}
										articleAuthorAvatar={GrowFavouriteCardData.authorAvatar}
										articleCreateDate={GrowFavouriteCardData.createDate}
										articleTitle={GrowFavouriteCardData.articleTitle}
										articleCategory={GrowFavouriteCardData.articleCategory}
									/>
								</div>
							</Slide>
							<Slide index={1}>
								<div className="col-lg-6">
									<GrowFavouritesCard
										spritemap={spritemap}
										articleAuthor={GrowFavouriteCardData.articleAuthor}
										articleAuthorAvatar={GrowFavouriteCardData.authorAvatar}
										articleCreateDate={GrowFavouriteCardData.createDate}
										articleTitle={GrowFavouriteCardData.articleTitle}
										articleCategory={"People"}
									/>
								</div>
							</Slide>
							<Slide index={2}>
								<div className="col-lg-6">
									<GrowFavouritesCard
										spritemap={spritemap}
										articleAuthor={GrowFavouriteCardData.articleAuthor}
										articleAuthorAvatar={GrowFavouriteCardData.authorAvatar}
										articleCreateDate={GrowFavouriteCardData.createDate}
										articleTitle={GrowFavouriteCardData.articleTitle}
										articleCategory={"Excellence"}
									/>
								</div>
							</Slide>
						</Slider>
						<ButtonBack>Back</ButtonBack>
						<ButtonNext>Next</ButtonNext>
					</CarouselProvider>
		);
	}
}

export default function(elementId) {
	ReactDOM.render(<App />, document.getElementById(elementId));
}
