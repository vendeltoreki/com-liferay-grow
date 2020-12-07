import {GrowVerticalNav} from "grow-clay";
import React from 'react';

const Component = (props) => {
  const spritemap = Liferay.ThemeDisplay.getPathThemeImages() + '/lexicon/icons.svg';

  props.data.items.map(item => {
    item.label = item.titleCurrentValue
    item.url = item.url
  });

  return (
    <GrowVerticalNav
	  items={props.data.items}
      label="Related Pages"
      labelIcon="pages-tree"
      spritemap={spritemap}
    />
  );
};

export default function(props) {
  return (
    <Component data={props} />
  );
}