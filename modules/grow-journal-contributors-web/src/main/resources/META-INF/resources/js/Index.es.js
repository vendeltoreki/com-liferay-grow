import {ClayVerticalNav} from '@clayui/nav';
import React from 'react';

const Component = (props) => {
    console.log(props)
    return (
      <ClayVerticalNav
        items={[
          {
            initialExpanded: true,
            items: null,
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