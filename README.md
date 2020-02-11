# invesdwin-checkstyle-plugin

This plugin contains additional checks for checkstyle. It is packaged as an eclipse plugin, so it can be used with the [Eclipse Checkstyle Plugin](http://eclipse-cs.sourceforge.net) by just putting the [invesdwin-checkstyle-plugin.jar](http://invesdwin.de/artifactory/invesdwin-oss/de/invesdwin/invesdwin-checkstyle-plugin/1.0.4/invesdwin-checkstyle-plugin-1.0.4.jar) inside `eclipse/dropins/`.

## Maven

Releases and snapshots are deployed to this maven repository:
```
https://invesdwin.de/artifactory/invesdwin-oss-remote
```

Dependency declaration:
```xml
<dependency>
	<groupId>de.invesdwin</groupId>
	<artifactId>invesdwin-checkstyle-plugin</artifactId>
	<version>1.0.4</version>
</dependency>
```

## Checks

Currently this plugin contains the following checks:

* `de.invesdwin.checkstyle.InternalImportCheck`: with this you can enforce the rule that `internal` packages are not used outside of your module. You can also change the name of the package by using the `internalPackageName` property.
```xml
<module name="de.invesdwin.checkstyle.InternalImportCheck">
	<property name="internalPackageName" value="internal" />
</module>
```
* `de.invesdwin.checkstyle.NotNullParameterAnnotationCheck`: Findbugs can parse @Nonnull, but not @NotNull in parameters correctly, this check verifies that the developer does not use the wrong annotation.
```xml
<module name="de.invesdwin.checkstyle.NotNullParameterAnnotationCheck" />
```

## Support

If you need further assistance or have some ideas for improvements and don't want to create an issue here on github, feel free to start a discussion in our [invesdwin-platform](https://groups.google.com/forum/#!forum/invesdwin-platform) mailing list.
