import {ClayVerticalNav} from '@clayui/nav';
import React from 'react';

const Component = (props) => {
    props.data.items.map(item => {
      item.label = item.title + " " + item.size
      item.href = item.url
    })

    return (
      <ClayVerticalNav
        items={[
          {
            initialExpanded: true,
            items: props.data.items,
            label: "Attachments"
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