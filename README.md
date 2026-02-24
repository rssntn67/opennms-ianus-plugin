# OpenNMS Ianus Plugin Plugin


Build and install the plugin into your local Maven repository using:

```
mvn clean install
```


From the OpenNMS Karaf shell:
```
feature:repo-add mvn:org.opennms.plugins/karaf-features/0.1.0-SNAPSHOT/xml
feature:install opennms-plugins-ianus-plugin
```


```
cp assembly/kar/target/opennms-ianus-plugin-plugin.kar /opt/opennms/deploy/
feature:install opennms-plugins-ianus-plugin
```

```
bundle:watch *
```


Once installed, the plugin makes the following Karaf shell commands available:

You can also access the REST endpoint mounted by the plugin at `http://localhost:8980/opennms/rest/ianus-plugin/ping`
