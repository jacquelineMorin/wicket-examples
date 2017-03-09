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
package de.alpharogroup.wicket.base.components.viewmode.examples;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.test.objects.Gender;
import de.alpharogroup.test.objects.Person;
import de.alpharogroup.wicket.base.BasePage;
import de.alpharogroup.wicket.components.editable.checkbox.EditableCheckbox;
import de.alpharogroup.wicket.components.editable.textarea.EditableTextArea;
import de.alpharogroup.wicket.components.editable.textfield.EditableTextField;

public class ViewOrEditPage extends BasePage
{
	private static final long serialVersionUID = 1L;

	private boolean enableFields = true;
	private final EditableTextArea about;
	private final EditableTextField nameTextField;
	private final EditableCheckbox<Person> married;
	private final Form<Person> form;

	public ViewOrEditPage(final PageParameters parameters)
	{
		super(parameters);

		final Person person = new Person();
		person.setGender(Gender.UNDEFINED);
		person.setName("");
		person.setAbout("bla");
		person.setMarried(false);
		setDefaultModel(Model.of(person));


		final CompoundPropertyModel<Person> cpm = new CompoundPropertyModel<>(person);

		form = new Form<>("form", cpm);
		form.setOutputMarkupId(true);
		add(form);
		nameTextField = new EditableTextField("name",
			new PropertyModel<>(person, "name"), Model.of("Name"));
		form.add(nameTextField);

		about = new EditableTextArea("about",
			new PropertyModel<>(person, "about"), Model.of("About"));
		form.add(about);

		married = new EditableCheckbox<>("married",
			cpm, Model.of("Married"));

		form.add(married);

		// Create submit button for the form
		final AjaxButton submitButton = new AjaxButton("submitButton", form)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				ViewOrEditPage.this.onSubmit(target, form);
			}
		};

		final AjaxLink<Void> link = new AjaxLink<Void>("submitLink")
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				info("Person:" + getDefaultModelObjectAsString());
				ViewOrEditPage.this.enableFields = !ViewOrEditPage.this.enableFields;
				if (ViewOrEditPage.this.enableFields)
				{
					about.getSwapPanel().onSwapToEdit(target, form);
					nameTextField.getSwapPanel().onSwapToEdit(target, form);
					married.getSwapPanel().onSwapToEdit(target, form);
				}
				else
				{
					about.getSwapPanel().onSwapToView(target, form);
					nameTextField.getSwapPanel().onSwapToView(target, form);
					married.getSwapPanel().onSwapToView(target, form);
				}

			}
		};
		form.add(link);

		form.add(submitButton);

		add(new FeedbackPanel("feedbackpanel"));


	}

	public void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
		info("Person:" + getDefaultModelObjectAsString());
		ViewOrEditPage.this.enableFields = !ViewOrEditPage.this.enableFields;
		if (ViewOrEditPage.this.enableFields)
		{
			about.getSwapPanel().onSwapToEdit(target, form);
			nameTextField.getSwapPanel().onSwapToEdit(target, form);
			married.getSwapPanel().onSwapToEdit(target, form);
		}
		else
		{
			about.getSwapPanel().onSwapToView(target, form);
			nameTextField.getSwapPanel().onSwapToView(target, form);
			married.getSwapPanel().onSwapToView(target, form);
		}
	}
}
