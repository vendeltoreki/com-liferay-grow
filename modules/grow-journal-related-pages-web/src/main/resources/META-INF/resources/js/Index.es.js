import {GrowVerticalNav} from "grow-clay";
import React, { useState } from 'react';

const Component = (props) => {
  const spritemap = Liferay.ThemeDisplay.getPathThemeImages() + '/lexicon/icons.svg';

  props.data.items.map(item => {
    item.label = item.titleCurrentValue
    item.url = item.url
  });

  return (
    <GrowVerticalNav
      spritemap={spritemap}
      label="Related Pages"
      labelIcon="pages-tree"
      items={props.data.items}
    />
  );
};

  export default function(props) {
	return (
		<Component data={props} />
	);
}