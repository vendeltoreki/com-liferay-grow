import React from "react";
import ReactDOM from "react-dom";
import ReactResizeDetector from 'react-resize-detector';
import axios from 'axios';
import { CarouselProvider, Slider, Slide, ButtonBack, ButtonNext } from 'pure-react-carousel';
import ClayIcon from '@clayui/icon';

import GrowRecommendedByText from "./modules/GrowRecommendedByText.es";
import { GrowCard } from "grow-clay";

class App extends React.Component {
	
	constructor(props) {
        super(props);

		const GROUP_ID = Liferay.ThemeDisplay.getCompanyGroupId();
		const USER_ID = Liferay.ThemeDisplay.getUserId();
		const AUTH_TOKEN = Liferay.authToken;

		this.PORTAL_URL = Liferay.ThemeDisplay.getPortalURL();
		this.SPRITEMAP = Liferay.ThemeDisplay.getPathThemeImages() + "/icons.svg";

		this.ADD_TO_MYFAVOURITES_QUERY = this.PORTAL_URL + "/o/favourites/" + "/addFavourite?p_auth=" + AUTH_TOKEN + "&groupId=" + GROUP_ID + "&userId=" + USER_ID + "&assetEntryId=";
		this.REMOVE_FROM_MYFAVOURITES_QUERY = this.PORTAL_URL + "/o/favourites/" + "/removeFavourite?p_auth=" + AUTH_TOKEN + "&groupId=" + GROUP_ID + "&userId=" + USER_ID + "&assetEntryId=";
		this.GET_ISFAVOURITE_AND_LIKED_ARRAY = this.PORTAL_URL + "/o/favourites/isFavouriteAndLikedArray?p_auth=" + AUTH_TOKEN + "&groupId="+ GROUP_ID + "&userId=" + USER_ID + "&assetEntryId=";

		this.ADD_ASSET_LIKE = this.PORTAL_URL + "/o/favourites/addAssetLike?p_auth=" + AUTH_TOKEN + "&userId=" + USER_ID + "&rank=1&assetEntryId=";
		this.REMOVE_ASSET_LIKE = this.PORTAL_URL + "/o/favourites/removeAssetLike?p_auth=" + AUTH_TOKEN + "&userId=" + USER_ID + "&assetEntryId=";
		this.GET_ASSETS_LIKED_BY_USER = this.PORTAL_URL + "/o/favourites/getAssetsLikedByUserId?p_auth=" + AUTH_TOKEN + "&userId=" + USER_ID;

		this.GET_RECOMMENDATIONS_DEFAULT = this.PORTAL_URL + "/o/gsearch-rest/recommendations/en_US?p_auth=" + AUTH_TOKEN;
		this.GET_RECOMMENDATIONS_BY_LIKED = this.PORTAL_URL + "/o/gsearch-rest/recommendations/en_US?p_auth=" + AUTH_TOKEN + "&count=15&includeAssetTags=true&includeAssetCategories=true&includeUserPortrait=true&assetEntryId=";
		
		this.RECOMMENDATION_TOGGLE_LIKE_EVENT = 'recommendationToggleLikeEvent'
		this.RECOMMENDATION_TOGGLE_STAR_EVENT = 'recommendationToggleStarEvent';
		this.FAVOURITES_TOGGLE_STAR_EVENT = 'favouritesToggleStarEvent';

		this.state = {
			data: [],
			error: null,
			isLoading: false,
			recommendedBy: [],
			totalSlides: 1,
			visibleSlides: 1
        };

		this.setVisibleSlides = this.setVisibleSlides.bind(this);
        this.onResize = this.onResize.bind(this);
		this.handleStarClick = this.handleStarClick.bind(this);
		this.handleLikeClick = this.handleLikeClick.bind(this);
		this.fireToggleStarEvent = this.fireToggleStarEvent.bind(this);
		this.toggleStar = this.toggleStar.bind(this);

		let instance = this; 

		Liferay.on(
			this.RECOMMENDATION_TOGGLE_LIKE_EVENT,
			function(event) {
				if(event && event.data) {
					instance.toggleLike(event.data);
				}
			}
		);
		Liferay.on(
			this.FAVOURITES_TOGGLE_STAR_EVENT,
			function(event) {
				if(event && event.data) {
					instance.toggleStar(event.data);
				}
			}
		);
		Liferay.on(
			this.RECOMMENDATION_TOGGLE_STAR_EVENT,
			function(event) {
				if(event && event.data) {
					instance.toggleStar(event.data);
				}
			}
		);
	}

	toggleLike(data) {
		if (data) {
			this.setState({ isLoading: true });
		
			const newData = this.state.data.map(card =>
				card.id.toString() === data.id.toString()
				? Object.assign(card, {like: data.like})
				: card
			);
			
			this.setState({
				data: newData,
				isLoading: false
			});
		}
	}

	toggleStar(data) {
		
		if (data) {
			this.setState({ isLoading: true });
		
			const newData = this.state.data.map(card =>
				card.id.toString() === data.id.toString()
				? Object.assign(card, {star: data.star})
				: card
			);
			
			this.setState({
				data: newData,
				isLoading: false
			});
		}
	}

	fireToggleLikeEvent(data) {
		Liferay.fire(
			this.RECOMMENDATION_TOGGLE_LIKE_EVENT,
			{
				data: data,
				isLoading: false
			}
		);
	}

	fireToggleStarEvent(data) {
		Liferay.fire(
			this.RECOMMENDATION_TOGGLE_STAR_EVENT,
			{
				data: data,
				isLoading: false
			}
		);
	}

	async handleStarClick(data) {

		if (data && !this.state.isLoading) {
			this.setState({ isLoading: true });
				
			let query = null;
			
			if (data.star) {
				query = this.ADD_TO_MYFAVOURITES_QUERY + data.id;
				
				await axios.put(query)
					.then(
						response => {					
							this.setState({
								data: this.state.data.map(card =>
									card.id === data.id
									? Object.assign(card, {star: data.star})
									: card
								),
								isLoading: false
							});
		
							this.fireToggleStarEvent(data);
						}
					)
					.catch(error => {
						this.setState({ error: error, isLoading: false });
						Liferay.Util.openToast(
							{
								message: error,
								title: Liferay.Language.get('error'),
								type: 'danger'
							}
						);
					});
				}
				else {
					query = this.REMOVE_FROM_MYFAVOURITES_QUERY + data.id;

					await axios.delete(query)
					.then(
						response => {				
							this.setState({
								data: this.state.data.map(card =>
									card.id === data.id
									? Object.assign(card, {star: data.star})
									: card
								),
								isLoading: false
							});
		
							this.fireToggleStarEvent(data);
						}
					)
					.catch(error => {
						this.setState({ error: error, isLoading: false });
						Liferay.Util.openToast(
							{
								message: error,
								title: Liferay.Language.get('error'),
								type: 'danger'
							}
						);
					});
				}
		}
	}

	async handleLikeClick(data) {
		if (data && !this.state.isLoading) {
			this.setState({ isLoading: true });
				
			let query = null;
			
			if (data.like) {
				query = this.ADD_ASSET_LIKE + data.id;
				
				await axios.put(query)
					.then(
						response => {						
							this.setState({
								data: this.state.data.map(card =>
									card.id === data.id
									? Object.assign(card, {like: data.like})
									: card
								),
								isLoading: false
							});

							this.fireToggleLikeEvent(data);
						}
					)
					.catch(error => {
						this.setState({ error: error, isLoading: false });
						Liferay.Util.openToast(
							{
								message: error,
								title: Liferay.Language.get('error'),
								type: 'danger'
							}
						);
					});
				}
				else {
					query = this.REMOVE_ASSET_LIKE + data.id;

					await axios.delete(query)
					.then(
						response => {					
							this.setState({
								data: this.state.data.map(card =>
									card.id === data.id
									? Object.assign(card, {like: data.like})
									: card
								),
								isLoading: false
							});

							this.fireToggleLikeEvent(data);
						}
					)
					.catch(error => {
						this.setState({ error: error, isLoading: false });
						Liferay.Util.openToast(
							{
								message: error,
								title: Liferay.Language.get('error'),
								type: 'danger'
							}
						);
					});
				}
		}
		
	}

    setVisibleSlides(visibleSlides) {
        if (visibleSlides != this.state.visibleSlides) {
            this.setState({
                visibleSlides: visibleSlides,
                isLoading: false
            });
        }
    }
    
    onResize(width) {
        if (width <= 680) {
            return this.setVisibleSlides(1);
        }
        else if (width <= 960) {
            return this.setVisibleSlides(2);
        }
        else {
            return this.setVisibleSlides(3);
        }
    }
	
	async componentDidMount() {
		this.setState({
			isLoading: true
		});

		let amount = this.props.totalNumberOfInstances * this.props.likedForRecommendation;

		await axios.get(this.GET_ASSETS_LIKED_BY_USER + "&amount=" + amount + "&random=" + this.props.random)
		.then(response => {
			let api = this.GET_RECOMMENDATIONS_DEFAULT;
			let amount = this.props.totalNumberOfInstances * this.props.likedForRecommendation;
			if (response.data.data.length > 0) {
				let assetEntryIdArr = [];
				let recommendedBy = [];
				if (response.data.data.length == amount){
					let end = amount * this.props.instanceNumber;
					let start = end - amount;

					for (let i = start; i < end; i++) {
						assetEntryIdArr.push(response.data.data[i].id);
						recommendedBy.push(response.data.data[i]);
					}
				}
				else {
					let ratio = Math.floor(response.data.data.length / this.props.totalNumberOfInstances);
					if (ratio >= 1) {
						let end = ratio * this.props.instanceNumber;
						let start = end - ratio;

						for (let i = start; i < end; i++) {
							assetEntryIdArr.push(response.data.data[i].id);
							recommendedBy.push(response.data.data[i]);
						}
					}
					else {
						for (let i = 0; i < response.data.data.length; i++) {
							if (i == this.props.instanceNumber) {
								assetEntryIdArr.push(response.data.data[i].id);
								recommendedBy.push(response.data.data[i]);
							}
							else if (response.data.length < this.props.instanceNumber) {
								assetEntryIdArr.push(response.data.data[i].id);
								recommendedBy.push(response.data.data[i]);
							}
						}
					}
				}

				const assetEntryIdStr = assetEntryIdArr.join('&assetEntryId=');

				this.setState({
					recommendedBy: recommendedBy
				});

				api = this.GET_RECOMMENDATIONS_BY_LIKED + assetEntryIdStr;
			}
			axios.get(api)
			.then(response => {
				this.setState({ 
					data: response.data.items,
					totalSlides: response.data.items.length
				})

				let assetEntryIdArr = [];
				response.data.items.map(asset => {
					assetEntryIdArr.push(asset.id);
				})

				const assetEntryIdStr = assetEntryIdArr.join('&assetEntryId=');

				axios.get(this.GET_ISFAVOURITE_AND_LIKED_ARRAY + assetEntryIdStr)
				.then(response => {
					let newData = [];
					let keys = Object.keys(response.data);
					for(var i = 0; i < keys.length; i++) {
						newData.push(Object.assign(
							{
								star: response.data[this.state.data[i].id][0].favourite,
								like: response.data[this.state.data[i].id][0].liked
							}, this.state.data[i]));
					}

					this.setState({
						data: newData
					})
				})
				.catch(error => {
					this.setState({ error: error, isLoading: false });
					Liferay.Util.openToast(
						{
							message: error,
							title: Liferay.Language.get('error'),
							type: 'danger'
						}
					);
				})
			})
			.catch(error => {
				this.setState({ error: error, isLoading: false });
				Liferay.Util.openToast(
					{
						message: error,
						title: Liferay.Language.get('error'),
						type: 'danger'
					}
				);
			})
		})
		.catch(error => {
			this.setState({ error: error, isLoading: false });
			Liferay.Util.openToast(
				{
					message: error,
					title: Liferay.Language.get('error'),
					type: 'danger'
				}
			);
		});
	}

	render() {
		
		const {isLoading, error } = this.state;
		return (
			<div className="grow-recommendations-portlet">
				<div className="container">
					{isLoading && (
						<div className="loading-indicator">
							<span aria-hidden="true" className="loading-animation"></span>
						</div>
					)}

					<GrowRecommendedByText 
						recommendedBy={this.state.recommendedBy}
						portalUrl={this.PORTAL_URL}
					/>

					<ReactResizeDetector handleWidth onResize={this.onResize} />

					<CarouselProvider
						className={"grow-recommendations-carousel"}
						naturalSlideWidth={350}
						naturalSlideHeight={300}
						totalSlides={this.state.totalSlides}
						visibleSlides={this.state.visibleSlides}
					>
						<ButtonBack
							className={"carousel-button-back"}>
							<ClayIcon
								spritemap={this.SPRITEMAP}
								symbol="angle-left"
							/>
						</ButtonBack>
						<Slider className={"grow-carousel-slider"}>
							{this.state.data.map((cardData, key) => 
								<Slide index={key} key={key}>
									<GrowCard
										spritemap={this.SPRITEMAP}
										portalUrl={this.PORTAL_URL}
										cardData={cardData}
										handleStarClick={this.handleStarClick}
										handleLikeClick={this.handleLikeClick}
										articleAuthor={cardData.articleAuthor || cardData.userName}
										articleAuthorAvatar={cardData.authorAvatar}
										articleCreateDate={cardData.createDate || cardData.date}
										articleTitle={cardData.articleTitle}
										articleContent={cardData.articleContent || cardData.description}
										articleTags={cardData.tags}
										articleReadCount={cardData.readCount}
										articleCategory={cardData.articleCategory}
										userInitials={cardData.userInitials}
										like={cardData.like ? cardData.like : false}
										star={cardData.star ? cardData.star : false}
										id={cardData.id}
										interactive={true}
										href={cardData.articleTitle ?
											this.PORTAL_URL +
											"/" +
											(cardData.articleCategory || "Share") +
											"/" +
											cardData.articleTitle
												.split(" ")
												.join("+")
												.replace(/'/g, '_APOSTROPHE_')
												.toLowerCase()
											: undefined
										}
									/>
								</Slide>
							)}
						</Slider>		
						<ButtonNext
							className={"carousel-button-next"}>
							<ClayIcon
								spritemap={this.SPRITEMAP}
								symbol="angle-right"
							/>
						</ButtonNext>
					</CarouselProvider>
				</div>
			</div>
		);
	}
}
export default function(elementId,likedForRecommendation, instanceNumber, totalNumberOfInstances, random) {
	ReactDOM.render(
	<App 
	likedForRecommendation={likedForRecommendation}
	instanceNumber={instanceNumber}
	totalNumberOfInstances={totalNumberOfInstances}
	random={random}
	/>, document.getElementById(elementId));
}