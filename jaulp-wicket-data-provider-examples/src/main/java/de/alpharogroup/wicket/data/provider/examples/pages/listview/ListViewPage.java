/**
 * Copyright (C) 2010 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.wicket.data.provider.examples.pages.listview;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.wicket.data.provider.examples.listview.ListViewPanel;


public class ListViewPage extends WebPage
{

	private static final long serialVersionUID = 1L;

	public ListViewPage()
	{
		super();

		initLayout();
	}

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 *
	 * @param parameters
	 *            Page parameters
	 */
	public ListViewPage(final PageParameters parameters)
	{
		super(parameters);

		initLayout();


	}

	private void initLayout()
	{
		// Add the ListView...
		add(new ListViewPanel("listViewPanel"));
	}
}