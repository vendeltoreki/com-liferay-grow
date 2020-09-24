import {GrowVerticalNav} from "grow-clay";
import React from 'react';

const Component = (props) => {
  const spritemap = Liferay.ThemeDisplay.getPathThemeImages() + '/lexicon/icons.svg';

  props.data.items.map(item => {
    item.label = item.title + " " + item.size
  })

  return (
    <GrowVerticalNav
	  items={props.data.items}
      label="Attachments"
      labelIcon="document"
      spritemap={spritemap}
    />
  );
};

  export default function(props) {
	return (
		<Component data={props} />
	);
}