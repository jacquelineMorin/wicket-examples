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
package de.alpharogroup.wicket.components.examples.sign.in;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.odlabs.wiquery.core.javascript.JsUtils;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.behaviors.BuildableChainableStatement;
import de.alpharogroup.wicket.behaviors.JqueryStatementsBehavior;
import de.alpharogroup.wicket.behaviors.wrappers.Wrappers;
import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledEmailTextFieldPanel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledPasswordTextFieldPanel;
import de.alpharogroup.wicket.components.sign.in.SignInWithRedirectionBean;
import de.alpharogroup.wicket.components.sign.in.SigninPanel;
import de.alpharogroup.wicket.components.sign.in.form.SigninFormPanel;

public class SigninExamplesPanel extends BasePanel<SignInWithRedirectionBean>
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private final int labelSize = 2;
	private final int inputSize = 4;

	public SigninExamplesPanel(final String id, final IModel<SignInWithRedirectionBean> model)
	{
		super(id, model);
		add(newSigninFormPanel("horizontalFormPanel", model));
	}

	protected Component getFeedback()
	{
		final PubliclyBasePage<?> basePage = (PubliclyBasePage<?>)getPage();
		return basePage.getFeedback();
	}

	protected Component newSigninFormPanel(final String id,
		final IModel<SignInWithRedirectionBean> model)
	{
		final SigninFormPanel<SignInWithRedirectionBean> signFormPanel = new SigninFormPanel<SignInWithRedirectionBean>(
			id, new CompoundPropertyModel<SignInWithRedirectionBean>(model))
		{

			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected Button newButton(final String id)
			{
				final Button button = super.newButton(id);
				button.add(Wrappers.FORM_GROUP_ELEMENT)
					.add(
						new JqueryStatementsBehavior()
							.add(
								new BuildableChainableStatement.Builder()
									.label("wrap").args(JsUtils.quotes("<div class=\"col-sm-offset-"
										+ labelSize + " col-sm-" + inputSize + "\"></div>"))
						.build()));
				button.add(new AttributeAppender("class", " btn btn-default"));
				return button;
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected Form<?> newForm(final String id, final IModel<?> model)
			{
				final Form<?> form = super.newForm(id, model);
				form.add(new AttributeAppender("class", " form-horizontal"));
				return form;
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected MarkupContainer newPasswordForgottenLink(final String id,
				final IModel<SignInWithRedirectionBean> model)
			{
				final MarkupContainer passwordForgottenLink = super.newPasswordForgottenLink(id,
					model);
				passwordForgottenLink.add(new AttributeAppender("class", " btn btn-link"));
				return passwordForgottenLink;
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected Component newSigninPanel(final String id,
				final IModel<SignInWithRedirectionBean> model)
			{
				final SigninPanel<SignInWithRedirectionBean> signinPanel = new SigninPanel<SignInWithRedirectionBean>(
					id, model)
				{
					/**
					 * The serialVersionUID
					 */
					private static final long serialVersionUID = 1L;

					/**
					 * {@inheritDoc}
					 */
					@Override
					@SuppressWarnings("unchecked")
					protected Component newEmailTextField(final String id,
						final IModel<SignInWithRedirectionBean> model)
					{
						final LabeledEmailTextFieldPanel<String, SignInWithRedirectionBean> emailTextField = (LabeledEmailTextFieldPanel<String, SignInWithRedirectionBean>)super.newEmailTextField(
							id, model);
						emailTextField.add(new AttributeAppender("class", " form-group"));
						emailTextField.getEmailTextField()
							.add(new JqueryStatementsBehavior()
								.add(new BuildableChainableStatement.Builder().label("wrap")
									.args(JsUtils
										.quotes("<div class=\"col-sm-" + inputSize + "\"></div>"))
								.build()))
							.add(new AttributeAppender("class", " form-control"));
						emailTextField.getLabelComponent().add(
							new AttributeAppender("class", " control-label col-sm-" + labelSize));
						return emailTextField;
					}

					/**
					 * {@inheritDoc}
					 */
					@Override
					protected LabeledPasswordTextFieldPanel<String, SignInWithRedirectionBean> newPasswordTextField(final String id,
						final IModel<SignInWithRedirectionBean> model)
					{
						final LabeledPasswordTextFieldPanel<String, SignInWithRedirectionBean> pwTextField = super.newPasswordTextField(
							id, model);
						pwTextField.add(new AttributeAppender("class", " form-group"));
						pwTextField.getPasswordTextField()
							.add(new JqueryStatementsBehavior()
								.add(new BuildableChainableStatement.Builder().label("wrap")
									.args(JsUtils
										.quotes("<div class=\"col-sm-" + inputSize + "\"></div>"))
								.build()))
							.add(new AttributeAppender("class", " form-control"));
						pwTextField.getLabelComponent().add(
							new AttributeAppender("class", " control-label col-sm-" + labelSize));
						return pwTextField;
					}
				};
				return signinPanel;
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onPasswordForgotten(final AjaxRequestTarget target, final Form<?> form)
			{
				SigninExamplesPanel.this.onPasswordForgotten(target, form);
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onSignin(final AjaxRequestTarget target, final Form<?> form)
			{
				SigninExamplesPanel.this.onSignin(target, form);
			}

		};
		return signFormPanel;
	}

	/**
	 * Application specific callback method that have to be overwritten to provide the action for password
	 * forgotten.
	 *
	 * @param target the target
	 * @param form the form
	 */
	protected void onPasswordForgotten(final AjaxRequestTarget target, final Form<?> form)
	{
		target.add(getFeedback());
		info("Email: " + getModelObject().getEmail() + "\nPassword:"
			+ getModelObject().getPassword());
	}

	/**
	 * Application specific callback method that have to be overwritten to provide the action for signin.
	 *
	 * @param target the target
	 * @param form the form
	 */
	protected void onSignin(final AjaxRequestTarget target, final Form<?> form)
	{
		target.add(getFeedback());
		info("Email: " + getModelObject().getEmail() + "\nPassword:"
			+ getModelObject().getPassword());
	}
}
