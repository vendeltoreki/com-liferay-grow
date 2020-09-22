import {GrowVerticalNav} from "grow-clay";
import React from 'react';

const Component = (props) => {
  const spritemap = Liferay.ThemeDisplay.getPathThemeImages() + '/lexicon/icons.svg';

  props.data.items.map(item => {
    item.label = item.title + " " + item.size
  })

  return (
    <GrowVerticalNav
      spritemap={spritemap}
      label="Attachments"
      labelIcon="document"
      items={props.data.items}
    />
  );
};

  export default function(props) {
	return (
		<Component data={props} />
	);
}