package com.liferay.arquillian.sample.portlet;

import com.liferay.arquillian.sample.service.SampleService;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author bopa
 */
@Component(
		property = {
				"com.liferay.portlet.display-category=category.sample",
				"com.liferay.portlet.instanceable=false",
				"javax.portlet.display-name=Arquillian Sample Portlet",
				"javax.portlet.init-param.template-path=/",
				"javax.portlet.init-param.view-template=/view.jsp",
				"javax.portlet.name=arquillian_sample_portlet",
				"javax.portlet.resource-bundle=content.Language",
				"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class SamplePortlet extends MVCPortlet {

	public void add(
			final ActionRequest actionRequest,
			final ActionResponse actionResponse) {

		final ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		final int firstParameter = ParamUtil.getInteger(
				actionRequest, "firstParameter");
		final int secondParameter = ParamUtil.getInteger(
				actionRequest, "secondParameter");

		final long result = _sampleService.add(firstParameter, secondParameter);

		final long plid = themeDisplay.getPlid();

		final PortletURL portletURL = PortletURLFactoryUtil.create(
				actionRequest, "arquillian_sample_portlet", plid,
				PortletRequest.RENDER_PHASE);

		portletURL.setParameter(
				"firstParameter", String.valueOf(firstParameter));
		portletURL.setParameter(
				"secondParameter", String.valueOf(secondParameter));
		portletURL.setParameter("result", String.valueOf(result));

		actionRequest.setAttribute(WebKeys.REDIRECT, portletURL.toString());
	}

	@Reference
	private SampleService _sampleService;

}