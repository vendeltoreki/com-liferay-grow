import React from "react";
import ReactDOM from "react-dom";
import axios from 'axios';
import { CarouselProvider, Slider, Slide, ButtonBack, ButtonNext } from 'pure-react-carousel';

import GrowFavouritesSlide from './modules/GrowFavouritesSlide.es';
import GrowIcon from './modules/GrowIcon.es';

const spritemap = Liferay.ThemeDisplay.getPathThemeImages();
const CARDS_PER_COLUMN = 3;
const API = 'https://jsonplaceholder.typicode.com';
const DEFAULT_QUERY = '/todos/1';

const mockupData = {
	"data": [
		{
		articleAuthor: "Author 01",
		authorAvatar: "/o/GrowFavouritesPortlet/images/0.jpeg",
		createDate: "01.01.2019",
		articleTitle: "Title 01",
		articleContent: 'A respect badge can be given by anyone to anyone. You can give 1 badge per month. You can use the Respect badge page to add badges. To give a respect badge, @ mention the name in the table, write your name to the From column and write the reason, why do you give it. The reason has to be for a "superpower", something why you respect the other person and something which you would like to learn from them. For Support Hungary 2016Q3 goals, you have to give min. 1 respect badge until Sept 30. If there will be need, we will add gamification to Grow later on.',
		tags: ["badge", "gamification", "respect", "test1", "test2"],
		readCount: "626",
		articleCategory: "Share"
		},
		{
		articleAuthor: "Author 02",
		authorAvatar: "/o/GrowFavouritesPortlet/images/0.jpeg",
		createDate: "01.01.2019",
		articleTitle: "Title 02",
		articleContent: "",
		tags: ["badge", "gamification", "respect", "test1", "test2"],
		readCount: "626",
		articleCategory: "Share"
		},
		{
		articleAuthor: "Author 03",
		authorAvatar: "/o/GrowFavouritesPortlet/images/0.jpeg",
		createDate: "01.01.2019",
		articleTitle: "Title 03",
		articleContent: "",
		tags: ["badge", "gamification", "respect", "test1", "test2"],
		readCount: "626",
		articleCategory: "Share"
		},
		{
		articleAuthor: "Author 04",
		authorAvatar: "/o/GrowFavouritesPortlet/images/0.jpeg",
		createDate: "01.01.2019",
		articleTitle: "Title 04",
		articleContent: "",
		tags: ["badge", "gamification", "respect", "test1", "test2"],
		readCount: "626",
		articleCategory: "Share"
		},
		{
		articleAuthor: "Author 05",
		authorAvatar: "/o/GrowFavouritesPortlet/images/0.jpeg",
		createDate: "01.01.2019",
		articleTitle: "Title 05",
		articleContent: "",
		tags: ["badge", "gamification", "respect", "test1", "test2"],
		readCount: "626",
		articleCategory: "Share"
		},
		{
		articleAuthor: "Author 06",
		authorAvatar: "/o/GrowFavouritesPortlet/images/0.jpeg",
		createDate: "01.01.2019",
		articleTitle: "Title 06",
		articleContent: "",
		tags: ["badge", "gamification", "respect", "test1", "test2"],
		readCount: "626",
		articleCategory: "Share"
		},
		{
		articleAuthor: "Author 07",
		authorAvatar: "/o/GrowFavouritesPortlet/images/0.jpeg",
		createDate: "01.01.2019",
		articleTitle: "Title 06",
		articleContent: "",
		tags: ["badge", "gamification", "respect", "test1", "test2"],
		readCount: "626",
		articleCategory: "Share"
		},
		{
		articleAuthor: "Author 08",
		authorAvatar: "/o/GrowFavouritesPortlet/images/0.jpeg",
		createDate: "01.01.2019",
		articleTitle: "Title 06",
		articleContent: "",
		tags: ["badge", "gamification", "respect", "test1", "test2"],
		readCount: "626",
		articleCategory: "Share"
		}
	]
};

class App extends React.Component {
	
	constructor(props) {
		super(props);
		this.state = 
			{
				data: [],
				spritemap: spritemap,
				isLoading: false,
				error: null,
			};
	}
	
	componentDidMount() {
		this.setState({ isLoading: true });
		
		setTimeout(() => {
			
		axios.get(API + DEFAULT_QUERY)
			.then(
				response => this.setState({ data: mockupData.data, isLoading: false })
			)
			.catch(error => this.setState({ error, isLoading: false }));
			
		}, 2000);
	}
	
	render() {
		
		const { data, isLoading, error } = this.state;
		
		 if (error) {
			 
			return (
				<p>{error.message}</p>
			);
			
		} else if (isLoading) {
			
			return (
				<p>Loading ...</p>
			);
			
		} else {
			let i=0,index=0;
			const slider = []
			
			while(i< data.length){						
				
				let dataSlide = data.filter(function(value, idx, Arr) {
					return idx >= (0 + i) && idx < (CARDS_PER_COLUMN + i);
				});
				
				slider.push(
					<Slide index={index} key={index}>
						<GrowFavouritesSlide
							spritemap={this.state.spritemap}
							data={dataSlide}
							slideIndex={index}
						/>
					</Slide>
				);
				
				i += CARDS_PER_COLUMN;
				index++;
			}
			
			return (
				<div className="grow-favourites-porltet">
					<div className="container">
					  <div className="row">
						<div className="col-sm-4">
						
							<div className="">
								<h1 className="my-favourites">
									My<br />Favourites
								</h1>
							
								<div className="text-secondary strong">Browse your most favourite articles</div>
							</div>
					
						</div>
						
						<div className="col-sm-8">
							<CarouselProvider
								naturalSlideWidth={400}
								naturalSlideHeight={520}
								totalSlides={index}
								visibleSlides={2}
							>
								<ButtonBack
									className={"grow-favourites-carousel-button-back"}>
									<GrowIcon
										spritemap={spritemap}
										classes="lexicon-icon inline-item"
										iconName="angle-left"
									/>
								</ButtonBack>
								<Slider>
									{slider}
								</Slider>		
								<ButtonNext
									className={"grow-favourites-carousel-button-next"}>
									<GrowIcon
										spritemap={spritemap}
										classes="lexicon-icon inline-item"
										iconName="angle-right"
									/>
								</ButtonNext>
							</CarouselProvider>
						</div>

					  </div>
					</div>
				
				</div>
			);
		}
	}
}

export default function(elementId) {
	ReactDOM.render(<App />, document.getElementById(elementId));
}
