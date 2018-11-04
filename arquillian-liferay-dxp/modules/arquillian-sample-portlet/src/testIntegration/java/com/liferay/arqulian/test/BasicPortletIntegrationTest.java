package com.liferay.arqulian.test;

import com.google.common.io.Files;

import com.liferay.arquillian.containter.remote.enricher.Inject;
import com.liferay.arquillian.sample.service.SampleService;
import com.liferay.portal.kernel.exception.PortalException;

import java.io.File;
import java.io.IOException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class BasicPortletIntegrationTest {

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
	public void testAdd() throws IOException, PortalException {
		final long result = _sampleService.add(1, 4);

		Assert.assertEquals(5, result);
	}

	@Inject
	private SampleService _sampleService;

}