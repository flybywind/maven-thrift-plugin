package org.apache.thrift.maven;

import com.google.common.collect.ImmutableList;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * This mojo executes the {@code thrift} compiler for generating java sources
 * from thrift definitions. It also searches dependency artifacts for
 * thrift files and includes them in the thriftPath so that they can be
 * referenced. Finally, it adds the thrift files to the project as resources so
 * that they are included in the final artifact.
 *
 * @phase generate-sources
 * @goal compile
 * @requiresDependencyResolution compile
 */

@Mojo(name = "compile-thrift")
public final class ThriftCompileMojo extends AbstractThriftMojo {

  /**
   * The source directories containing the sources to be compiled.
   *
   * @parameter default-value="${basedir}/src/main/thrift"
   * @required
   */
   @Parameter(defaultValue="${basedir}/src/main/thrift")
  private File thriftSourceRoot;

  /**
   * This is the directory into which the {@code .java} will be created.
   *
   * @parameter default-value="${project.build.directory}/generated-sources/thrift"
   * @required
   */
   @Parameter(defaultValue="${project.build.directory}/generated-sources/thrift")
  private File outputDirectory;

    @Override
  protected Collection<Artifact> getDependencyArtifacts() {
    // TODO(gak): maven-project needs generics
    @SuppressWarnings("unchecked")
    Collection<Artifact> compileArtifacts = project.getArtifacts();
    return compileArtifacts;
  }

  @Override
  protected File getOutputDirectory() {
    return outputDirectory;
  }

  @Override
  protected File getThriftSourceRoot() {
    return thriftSourceRoot;
  }

  @Override
  protected void attachFiles() {
    project.addCompileSourceRoot(outputDirectory.getAbsolutePath());
    projectHelper.addResource(project, thriftSourceRoot.getAbsolutePath(),
        ImmutableList.of("**/*.thrift"), ImmutableList.of());
  }
}
