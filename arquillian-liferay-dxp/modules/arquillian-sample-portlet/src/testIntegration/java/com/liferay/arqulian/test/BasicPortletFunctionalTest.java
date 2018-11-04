package com.liferay.arqulian.test;

import com.google.common.io.Files;

import com.liferay.arquillian.portal.annotation.PortalURL;
import com.liferay.portal.kernel.exception.PortalException;

import java.io.File;
import java.io.IOException;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Cristina González
 */
@RunAsClient
@RunWith(Arquillian.class)
public class BasicPortletFunctionalTest {

	@Deployment
	public static JavaArchive create() throws Exception {
		final File tempDir = Files.createTempDir();

		String gradlew = "./gradlew";

		String osName = System.getProperty("os.name", "");
		if (osName.toLowerCase().contains("windows")) {
			gradlew = "C:/git_projects/my-courses/arquillian-liferay-dxp/gradlew.bat";
		}

		final ProcessBuilder processBuilder = new ProcessBuilder(
				gradlew, "jar", "-Pdir=" + tempDir.getAbsolutePath());

		final Process process = processBuilder.start();

		process.waitFor();

		final File jarFile = new File(
				tempDir.getAbsolutePath() +
						"/com.liferay.arquillian.sample-1.0.0.jar");

		return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
	}

	@Test
	public void testAdd()
		throws InterruptedException, IOException, PortalException {

		_browser.get(_portlerURL.toExternalForm());

		_firstParameter.clear();

		_firstParameter.sendKeys("3");

		_secondParameter.clear();

		_secondParameter.sendKeys("3");

		_add.click();

		Thread.sleep(5000);

		Assert.assertEquals("6", _result.getText());
	}

	@Test
	public void testInstallPortlet() throws IOException, PortalException {
		_browser.get(_portlerURL.toExternalForm());

		final String bodyText = _browser.getPageSource();

		Assert.assertTrue(
			"The portlet is not well deployed",
			bodyText.contains("Sample Portlet is working!"));
	}

	@FindBy(css = "button[type=submit]")
	private WebElement _add;

	@Drone
	private WebDriver _browser;

	@FindBy(css = "input[id$='firstParameter']")
	private WebElement _firstParameter;

	@PortalURL("arquillian_sample_portlet")
	private URL _portlerURL;

	@FindBy(css = "span[class='result']")
	private WebElement _result;

	@FindBy(css = "input[id$='secondParameter']")
	private WebElement _secondParameter;

}
