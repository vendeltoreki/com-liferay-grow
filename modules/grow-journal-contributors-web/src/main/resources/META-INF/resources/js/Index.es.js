import {ClayVerticalNav} from '@clayui/nav';
import React from 'react';

const Component = (props) => {
    const items = [{label: props.data.creator + " " + props.data.creatorDate}, {label: props.data.modifier + " " + props.data.modifierDate}];

    return (
      <ClayVerticalNav
        items={[
          {
            initialExpanded: true,
            items: items,
            label: "Contributors"
          }
        ]}
        large={false}
        spritemap={Liferay.ThemeDisplay.getPathThemeImages() + '/lexicon/icons.svg'}
      />
    );
  };

  export default function(props) {
	return (
		<Component data={props} />
	);
}